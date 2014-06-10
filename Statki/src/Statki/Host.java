package Statki;

import java.awt.Color;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.PooledConnection;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Widoki_Zdarzenia.Widok_Gry_Zdarzenia;

public class Host extends Thread{
	
	private Socket sock; 
	private ServerSocket serv;
	
	private BufferedReader in; 
	private PrintWriter out; 
	
	volatile public boolean polaczenieOK;
	
	private ImageIcon img_zaznaczonePole = new ImageIcon(getClass().getResource("/plansza/plansza200x200/zaznaczonePole.png"));
	private ImageIcon img_trafionePole = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek_trafiony.png"));
	private ImageIcon img_nieTrafionePole = new ImageIcon(getClass().getResource("/plansza/plansza200x200/pole_nietrafione.png"));
	
	private ImageIcon img_zaznaczonePole2 = new ImageIcon(getClass().getResource("/plansza/plansza220x220/zaznaczonePole.png"));
	private ImageIcon img_trafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza220x220/statek_trafiony.png"));
	private ImageIcon img_nieTrafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza220x220/pole_nietrafione.png"));
	public ImageIcon img_statek2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek.png"));
	
	private ImageIcon img_strzalkaLEWO_g = new ImageIcon(getClass().getResource("/strzalki/strzalkaLEWO_g.png"));
	private ImageIcon img_strzalkaPRAWO_g = new ImageIcon(getClass().getResource("/strzalki/strzalkaPRAWO_g.png"));
	private ImageIcon img_strzalkaLEWO_r = new ImageIcon(getClass().getResource("/strzalki/strzalkaLEWO_r.png"));
	private ImageIcon img_strzalkaPRAWO_r = new ImageIcon(getClass().getResource("/strzalki/strzalkaPRAWO_r.png"));
	
	public Host() {
		sock = null;
		serv = null;
		czyRozlaczono = false;
		polaczenieOK = false;
		nickHosta = "";
		nickGracza = "";
		
		_j = -1; 
		_i = 0;
		liczbaPolPrzychodzacych = 0;
		planszaPrzeciwnika = "";
		czyOtwarteOknoPrzeciwnika = false;
		czyUstalacWynik = true;
	}
	
	public void zerujDaneHosta()
	{
		_j = -1; 
		_i = 0;
		liczbaPolPrzychodzacych = 0;
		planszaPrzeciwnika = "";
		czyOtwarteOknoPrzeciwnika = false;
		czyUstalacWynik = true;
	}
	//boolean czatAktwyny;
	public void run()
	{
		System.out.println("Aktywny watek!");
		while(polaczenieOK)
		{
			pobierzWiadomosc();
		}
	}
	
	public void uspijWatek()
	{
		try {
			sleep((long) 1000);
			//join();
			interrupt();
			polaczenieOK = false;
		} catch (InterruptedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
	
	public void zatrzymajSkanowanie()
	{
		polaczenieOK = false;
	}
	
	private JTextArea czat;
	public void przekazPoleCzatu(JTextArea _czat)
	{
		czat = _czat;
	}
	
	private JTextField wiadomosc;
	public void przekazPoleWiadomosci(JTextField _wiadomosc)
	{
		wiadomosc = _wiadomosc;
	}
	
	public void zaladujCzat ()
	{
		czat.setText("Czat aktywny z komputerem o nr IP: "+ czat.getText()+pobierzIpKlienta()+"\n");
		//poleNick.setText(nickGracza);
	}
	
	private JLabel poleNick;
	public void przekazPoleNick(JLabel _poleNick)
	{
		poleNick = _poleNick;
	}
	
	private JLabel poleAwatar;
	public void przekazPoleAwatar(JLabel _poleAwatar)
	{
		poleAwatar = _poleAwatar;
	}
	
	public Uzytkownik uzytkownik;
	public void przekazUzytkownika(Uzytkownik _uzytkownik)
	{
		uzytkownik = _uzytkownik;
	}
	
	private JLabel wspStrzaluPrzeciwnika;
	public void przekazPoleWspPrzeciwnika(JLabel _wspStrzaluPrzeciwnika)
	{
		wspStrzaluPrzeciwnika = _wspStrzaluPrzeciwnika;
	}
	
	public JLabel poleStrzalu;
	public void przekazPoleStrzalu(JLabel _poleStrzalu)
	{
		poleStrzalu = _poleStrzalu;
	}
	
	public JLabel[][] planszaGracza;
	public void przekazPlanszeGracza(JLabel[][] _planszaGracza)
	{
		planszaGracza = _planszaGracza;
	}
	
	public JLabel etykietaStrzalki;
	public void przekazEtykieteStrzalki(JLabel _etykietaStrzalki)
	{
		etykietaStrzalki = _etykietaStrzalki;
	}
	
	public JLabel etykietaCzyjRuch;
	public void przekazEtykieteCzyjRuch(JLabel _etykietaCzyjRuch)
	{
		etykietaCzyjRuch = _etykietaCzyjRuch;
	}
	
	Widok_Gry_Zdarzenia widokGryZdarzenia;
	public void przekazObiektWidokOknaGryZdarzenia(Widok_Gry_Zdarzenia _widokGryZdarzenia)
	{
		widokGryZdarzenia = _widokGryZdarzenia;
	}
	
	public void pobierzWiadomosc()
	{
		if(polaczenieOK)
		{
			odbierzWiadomosc();
			System.out.println("Pobralem wiadomosc!");
		}
	}
	  
	public boolean czyOtwartoStrWej;
	public void stworzHost(JTextArea _komunikat) throws IOException
	{
		try {
			//tworzenie gniazda serwerowego  
			serv=new ServerSocket(50007);                                                                                                        
			//oczekiwanie na polaczenie i tworzenie gniazda sieciowego                                                                          
			sock=serv.accept();
			_komunikat.setText("Połączono z klientem: "+sock.getInetAddress().getHostAddress()+"\n");                                                                 
			//tworzenie strumienia danych pobieranych z gniazda sieciowego                                                          
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			polaczenieOK = true;
			czyOtwartoStrWej = true;
	      } catch (IOException e) {System.out.println("Błąd: "+e.getMessage());polaczenieOK = false;}  
	}
	
	public void stworzHost(JLabel _komunikat) 
	{                                                                                   
		try {
			//tworzenie gniazda serwerowego  
			serv=new ServerSocket(50007);                                                                                                         
			//oczekiwanie na polaczenie i tworzenie gniazda sieciowego                                                                          
			sock=serv.accept();
			_komunikat.setText("Połączono z klientem: "+sock.getInetAddress().getHostAddress()+"\n");
	                                                                             
			//tworzenie strumienia danych pobieranych z gniazda sieciowego                                                          
			in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
			polaczenieOK = true;
	      } catch (IOException e) {System.out.println("Błąd: "+e.getMessage());polaczenieOK = false;}  
		
	}
	
	public int i=0;
	public void wlaczPobieranieWiadomosci()
	{
		i++;
		if(polaczenieOK)
		{
			System.out.println("I: "+i);
			if(i == 1)
			{
				System.out.println("Włączono skanowanie HOST!");
				this.start();
			}
	    	zaladujCzat();
		}
	}
	
	public String pobierzIpKlienta()
	{
		return sock.getInetAddress().getHostAddress();
	}
	
	public String nickHosta;
	public void ustawNickHosta(String _nickHosta)
	{
		nickHosta = _nickHosta;
	}
	
	public String nickGracza;
	public void ustawNickGracza(String _nickGracza)
	{
		nickGracza = _nickGracza;
	}
	
	public String getNickGracza()
	{
		return nickGracza;
	}
	
	public int nrAwataruGracza;
	public void ustawNrAwataruGracza(int _nrAwataruGracza)
	{
		nrAwataruGracza = _nrAwataruGracza;
	}
	
	public int getNrAwataruGracza()
	{
		return nrAwataruGracza;
	}
	
	public boolean czyWyslacZakonczenie;
	public void wyslijWiadomosc(String _wiadomosc, String _typWiadomosci)
	{             
	    try {
			out = new PrintWriter(sock.getOutputStream());
		} catch (IOException e) {System.out.println("Błąd WysWiad: "+e.getMessage());
		}                                                                                                                                                                
	    String str="";  
	    if(czyWyslacZakonczenie)
	    	str = "KONIEC";
	    else
	    {
	    	if(this != null)
	    	  {
		    	if(_typWiadomosci.equals("#WP#"))
		    	  {
			    	  str=_typWiadomosci+nickHosta+": "+wiadomosc.getText()+"\n";  
					  czat.setText(czat.getText()+str.substring(4));
		    	  }
		    	  else 
		    		  str = _typWiadomosci+_wiadomosc; 
	    	  }
	    }
	    if(out!=null)
	    {
	    	out.println(str);                                                               
	    	out.flush();
	    }
	}
	
	int liczbaPolPrzychodzacych;
	public int _j; 
	public int _i;
	public String planszaPrzeciwnika;
	public int iloscTrafionychGRACZ;
	public boolean czyOtwarteOknoPrzeciwnika;
	public boolean czyUstalacWynik;
	public String ktoSkapitulowal;
	
	public void odbierzWiadomosc()
	{      
		if(polaczenieOK)
		{
			String str = "";
		    try {
				in = new BufferedReader(new InputStreamReader(sock.getInputStream()));
				                                                          
			    str = in.readLine();   
			} catch (IOException e) {System.out.println("Błąd OdbWiad HOST: "+e.getMessage());
			}
		    //komunikacja - czytanie danych ze strumienia                          
		    if(!str.equals("KONIEC")) 
		    {
		    	if(str.length() > 4)
		    	{
			    	if(str.substring(0, 4).equals("#WP#"))
			    		czat.setText(czat.getText()+str.substring(4)+"\n");
			    	else if (str.substring(0, 4).equals("#DG#"))
			    	{
			    		int licznikKresek = 0;
			    		int pozycjaKreski1 = 0, pozycjaKreski2 = 0;
			    		System.out.println(str);
			    		
			    		for(int i=0;i<str.length();i++)
			    		{
			    			if(str.substring(i, i+1).equals("|"))
			    				licznikKresek++;
			    			if(licznikKresek == 1)
			    				pozycjaKreski1 = i-1;
			    			else if(licznikKresek == 2)
			    			{
			    				pozycjaKreski2 = i;
			    				licznikKresek = 0;
			    			}
			    		}
			    		System.out.println("PK1: "+pozycjaKreski1);
			    		System.out.println("PK2: "+pozycjaKreski2);
			    		System.out.println("NR awatara: "+str.substring(pozycjaKreski1+1, pozycjaKreski2));
			    		ustawNickGracza(str.substring(4, pozycjaKreski1));
			    		ustawNrAwataruGracza(Integer.valueOf(str.substring(pozycjaKreski1+1, pozycjaKreski2)));
			    		poleNick.setText(nickGracza);
			    		//poleStrzalu.setText("NR awatara: "+str.substring(pozycjaKreski1+1, pozycjaKreski2));
			    		poleAwatar.setIcon(uzytkownik.awatary[Integer.valueOf(str.substring(pozycjaKreski1+1, pozycjaKreski2))]);
			    	}
			    	else if (str.substring(0, 4).equals("#WS#"))
			    	{
			    		//uzytkownik.setMozliwoscWykonaniaRuchu(true);
			    		//etykietaStrzalki.setIcon(img_strzalkaPRAWO_g);
			    		int strzalX = Integer.valueOf(str.substring(4,5));
			    		int strzalY =Integer.valueOf(str.substring(5,6));
			    		boolean czyTrafiono = uzytkownik.plansza.sprawdzStrzalPrzeciwnika(strzalX, strzalY);
			    		
			    		wyslijWiadomosc(String.valueOf(czyTrafiono), "#SS#");
			    		
			    		for(int i=0;i<10;i++)
			    			for(int j=0;j<10;j++)
			    				if(i == strzalX && j == strzalY)
			    				{
			    					if(uzytkownik.plansza.l_polaPlanszy_GRACZ[i][j].getRodzajPola().equals("1"))
			    						planszaGracza[i][j].setIcon(img_trafionePole);
			    					else if(uzytkownik.plansza.l_polaPlanszy_GRACZ[i][j].getRodzajPola().equals("X") || 
			    							uzytkownik.plansza.l_polaPlanszy_GRACZ[i][j].getRodzajPola().equals("0"))
			    						planszaGracza[i][j].setIcon(img_nieTrafionePole);	
			    				}
			    		
			    		//wspStrzaluPrzeciwnika.setText(""+strzalX+""+strzalY);
			    	}
			    	else if (str.substring(0, 4).equals("#SS#"))
			    	{
			    		String czyTrafiono = str.substring(4);
			    		uzytkownik.plansza.setWynikStrzalu(czyTrafiono);
			    		if(czyTrafiono.equals("true"))
			    		{
			    			poleStrzalu.setIcon(img_trafionePole2);
			    			uzytkownik.setMozliwoscWykonaniaRuchu(true);
				    		//etykietaStrzalki.setIcon(img_strzalkaPRAWO_g);
				    		uzytkownik.plansza.iloscTrafionychStrzalow++;
				    		//etykietaCzyjRuch.setForeground(new Color(0, 204, 0));
				    		//etykietaCzyjRuch.setText("TWÓJ RUCH");
			    		}
			    		else if (czyTrafiono.equals("false"))
			    		{
			    			poleStrzalu.setIcon(img_nieTrafionePole2);
			    			uzytkownik.setMozliwoscWykonaniaRuchu(false);
				    		etykietaStrzalki.setIcon(img_strzalkaLEWO_r);
				    		wyslijWiadomosc("true", "#KR#");
				    		etykietaCzyjRuch.setForeground(Color.RED);
				    		etykietaCzyjRuch.setText("RUCH PRZECIWNIKA");
			    		}
			    		//wspStrzaluPrzeciwnika.setText(czyTrafiono);
			    	}
			    	//odbior wiadomosci ze koniec ruchu
			    	else if (str.substring(0, 4).equals("#KR#"))
			    	{
			    		if(str.substring(4).equals("true"))
			    		{
			    			System.out.println("Czy ruch hosta: "+str.substring(4));
			    			uzytkownik.setMozliwoscWykonaniaRuchu(true);
				    		etykietaStrzalki.setIcon(img_strzalkaPRAWO_g);
				    		etykietaCzyjRuch.setForeground(new Color(0, 204, 0));
				    		etykietaCzyjRuch.setText("TWÓJ RUCH");
			    		}
			    		else if(str.substring(4).equals("false"))
			    		{
			    			System.out.println("Czy ruch hosta: "+str.substring(4));
			    			uzytkownik.setMozliwoscWykonaniaRuchu(false);
				    		//etykietaStrzalki.setIcon(img_strzalkaLEWO_r);
				    		//etykietaCzyjRuch.setForeground(Color.RED);
				    		//etykietaCzyjRuch.setText("RUCH PRZECIWNIKA");
			    		}
			    	}
			    	//odbior wiadomosci czy koniec gry
			    	else if (str.substring(0, 4).equals("#KG#"))
			    	{
			    		if(str.substring(4).equals("true"))
			    		{
			    			widokGryZdarzenia.komunikatKoniecGry("Rozgrywka dobiegła końca!");
			    			
			    		}
			    	}
			    	else if (str.substring(0, 4).equals("#GS#"))
			    	{
			    		if(str.substring(4).equals("true"))
			    		{
			    			widokGryZdarzenia.widokWynikow.lb_ZwyciescaGry.setText(getNickGracza());
			    			widokGryZdarzenia.czyGraczSkapitulowal = false;
			    			widokGryZdarzenia.czyUstalacKtoWygral = false;
			    			widokGryZdarzenia.komunikatKoniecGry("Rozgrywka dobiegła końca! Jeden z graczy skapitulował :(");	
			    		}
			    	}
			    	//odbior planszy wynikowej przeciwnika
			    	else if (str.substring(0, 4).equals("#PP#"))
			    	{
			    		planszaPrzeciwnika = str.substring(4);
			    		for(int i=0;i<100;i++)
			    		{
					    	if(i>0 && i%10==0)
					    	{
					    		_j = 0;
					    		_i++;
					    	}
					    	else
					    		_j++;
					    	
					    	uzytkownik.plansza.l_polaPlanszy_PRZECIWNIK_STRZALY[_j][_i] = planszaPrzeciwnika.substring(i, i+1);
			    		}
			    		
			    		for(int i=0;i<10;i++)
							for(int j=0;j<10;j++)
								if(uzytkownik.plansza.l_polaPlanszy_PRZECIWNIK_STRZALY[j][i].equals("T"))
								{
									widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.lb_polaGry_PRZECIWNIK[j][i].setIcon(img_trafionePole);
									iloscTrafionychGRACZ++;
								}
								else if(uzytkownik.plansza.l_polaPlanszy_PRZECIWNIK_STRZALY[j][i].equals("P"))
									widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.lb_polaGry_PRZECIWNIK[j][i].setIcon(img_nieTrafionePole);
								else if(uzytkownik.plansza.l_polaPlanszy_PRZECIWNIK_STRZALY[j][i].equals("S"))
									widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.lb_polaGry_PRZECIWNIK[j][i].setIcon(img_statek2);
			    		
			    		
			    		widokGryZdarzenia.widokWynikow.lb_XXX.setText(str.substring(4));
			    	}
			    	else if (str.substring(0, 4).equals("#PR#"))
			    	{
			    		widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.uzyskanieOdpNaPytanie_oRewanz();
			    	}
			    	else if (str.substring(0, 4).equals("#OR#"))
			    	{
			    		if(str.substring(4).equals("true"))
			    		{
			    			widokGryZdarzenia.widokWynikow.setVisible(false);
			    			widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.zacznijGreOdNowa();
			    			widokGryZdarzenia.widokGry.widokGlowny.setVisible(true);
			    		}
			    		else if (str.substring(4).equals("false"))
			    			widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.komunikat_odpPrzeciwnikaNaTematRewanzu();
			    	}
		    	}
		    }
		    else
		    {
		    	czat.setText(czat.getText()+"Komunikat: GRACZ "+nickGracza.trim()+" ROZŁĄCZYŁ SIĘ!\n");
		    	polaczenieOK = false;
		    	rozlacz();
		    	widokGryZdarzenia.komunikatKoniecGry("Rozgrywka dobiegła końca! Przeciwnik uciekł :(");
		    }
		}
		if(widokGryZdarzenia.czyUstalacKtoWygral)
			   widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.ustalKtoWygral();	
	}
	
	boolean czyRozlaczono;
	public void rozlacz() 
	{
		try {
			if(out !=null)
				out.close();
			if(in!=null)
				in.close();
			if(sock!=null)
				sock.close();                                                          
		    //serv.close();
			czyRozlaczono = true;
		} catch (IOException e) {System.out.println("Błąd RozlPolacz: "+e.getMessage());
		}
		
	}
}
