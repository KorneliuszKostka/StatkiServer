package Statki;

import java.net.*;
import java.util.Scanner;
import java.awt.Color;
import java.io.*;

import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

import Widoki_Zdarzenia.Widok_Gry_Zdarzenia;

public class Gracz extends Thread{
	
	private Socket socket;
	private String host;
	private BufferedReader in;
	private PrintWriter out;
	volatile public boolean polaczenieOK;

	private ImageIcon img_zaznaczonePole = new ImageIcon(getClass().getResource("/plansza/plansza200x200/zaznaczonePole.png"));
	private ImageIcon img_trafionePole = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek_trafiony.png"));
	private ImageIcon img_nieTrafionePole = new ImageIcon(getClass().getResource("/plansza/plansza200x200/pole_nietrafione.png"));
	public ImageIcon img_statek2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek.png"));
	
	private ImageIcon img_zaznaczonePole2 = new ImageIcon(getClass().getResource("/plansza/plansza220x220/zaznaczonePole.png"));
	private ImageIcon img_trafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza220x220/statek_trafiony.png"));
	private ImageIcon img_nieTrafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza220x220/pole_nietrafione.png"));
	
	
	private ImageIcon img_strzalkaLEWO_g = new ImageIcon(getClass().getResource("/strzalki/strzalkaLEWO_g.png"));
	private ImageIcon img_strzalkaPRAWO_g = new ImageIcon(getClass().getResource("/strzalki/strzalkaPRAWO_g.png"));
	private ImageIcon img_strzalkaLEWO_r = new ImageIcon(getClass().getResource("/strzalki/strzalkaLEWO_r.png"));
	private ImageIcon img_strzalkaPRAWO_r = new ImageIcon(getClass().getResource("/strzalki/strzalkaPRAWO_r.png"));
	
	public Gracz() {
		host = "";
		socket = null;
		out   = null;
		in = null; 
		polaczenieOK = false;
		czyRozlaczono = false;
		nickHosta = "";
		nickGracza = "";
		liczbaWyslanychWiadoosci = 0;
		
		_j = -1; 
		_i = 0;
		liczbaPolPrzychodzacych = 0;
		planszaPrzeciwnika = "";
		czyOtwarteOknoPrzeciwnika = false;
		czyUstalacWynik = true;
	}
	
	public void zerujDaneGracza()
	{
		_j = -1; 
		_i = 0;
		liczbaPolPrzychodzacych = 0;
		planszaPrzeciwnika = "";
		czyOtwarteOknoPrzeciwnika = false;
		czyUstalacWynik = true;
	}
	
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
		czat.setText("Czat aktywny z komputerem o nr IP: "+ czat.getText()+pobierzIpSerwera()+"\n");
		poleNick.setText(nickHosta);
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
	
	public JLabel wspStrzaluPrzeciwnika;
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
	
	public void podajIP(String _serwer)
	{
		host = _serwer;
	}
	
	public boolean czyOtwartoStrWej;
	public boolean czyOtwartoStrWyj;
	public void polacz(JTextArea _komunikat)
	{
		//nawiazanie polaczenia z serwerem 
	      try {
	    	  socket = new Socket(host,50007);
			
	    	  _komunikat.setText("Połączono z serwerem: "+host+"\n");
	    	  if(socket != null)
	    		  out = new PrintWriter(socket.getOutputStream());                                    
		      polaczenieOK = true;
		      czyOtwartoStrWyj = true;
		} 
	    catch (UnknownHostException e) {System.out.println("Błąd1: "+e.getMessage());polaczenieOK = false;} 
	    catch (IOException e) {System.out.println("Błąd2: "+e.getMessage());polaczenieOK = false;} 
	}
	
	public void polacz(JLabel _komunikat)
	{
		//nawiazanie polaczenia z serwerem        
	      try {
	    	  socket=new Socket(host,50007);
			
	    	  _komunikat.setText("Połączono z serwerem: "+host+"\n");   
	    	  if(socket != null)
	    		  out = new PrintWriter(socket.getOutputStream());                                    
		      polaczenieOK = true;
		      czyOtwartoStrWyj = true;
		} 
	    catch (UnknownHostException e) {System.out.println("Błąd1: "+e.getMessage());polaczenieOK = false;} 
	    catch (IOException e) {System.out.println("Błąd2: "+e.getMessage());polaczenieOK = false;}  
	}
	
	public void polacz()
	{
		//nawiazanie polaczenia z serwerem                                               
	      try {
	    	  socket=new Socket(host,50007);
			        
	    	  if(socket != null)
	    		  out = new PrintWriter(socket.getOutputStream());                                    
		      polaczenieOK = true;
		      czyOtwartoStrWyj = true;
		} 
	    catch (UnknownHostException e) {polaczenieOK = false;} 
	    catch (IOException e) {polaczenieOK = false;}  
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
				System.out.println("Włączono skanowanie KLIENT!");
				this.start();
			}
	    	zaladujCzat();
		}
	}
	
	public String pobierzIpSerwera()
	{
		return socket.getInetAddress().getHostAddress();
	}
	
	public String nickGracza;
	public void ustawNickGracza(String _nickGracza)
	{
		nickGracza = _nickGracza;
	}
	
	public String nickHosta;
	public void ustawNickHosta(String _nickHosta)
	{
		nickHosta = _nickHosta;
	}
	
	public String getNickHosta()
	{
		return nickHosta;
	}
	
	public int nrAwataruHosta;
	public void ustawNrAwataruHosta(int _nrAwataruHosta)
	{
		nrAwataruHosta = _nrAwataruHosta;
	}
	
	public int getNrAwataruHosta()
	{
		return nrAwataruHosta;
	}
	
	int liczbaWyslanychWiadoosci = 0;
	public boolean czyWyslacZakonczenie;
	public void wyslijWiadomosc(String _wiadomosc, String _typWiadomosci) 
	{        
	      try {
	    	  if(socket != null)
	    		  out = new PrintWriter(socket.getOutputStream());
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
			    	  str=_typWiadomosci+nickGracza+": "+wiadomosc.getText()+"\n";  
			    	  czat.setText(czat.getText()+str.substring(4));
		    	  }
		    	  else 
		    		  str = _typWiadomosci+_wiadomosc;
	    	  }
	      }
	      if(out != null)
	      {
		      out.println(str);                                                               
		      out.flush();
	      }
	}
	
	int liczbaPolPrzychodzacych;
	public int _j; 
	public int _i;
	public String planszaPrzeciwnika;
	public int iloscTrafionychHOST;
	public boolean czyOtwarteOknoPrzeciwnika;
	public boolean czyUstalacWynik;
	public String ktoSkapitulowal;
	
	public void odbierzWiadomosc()
	{
		if(polaczenieOK)
		{
			String str = "";
		    try {
		    	 if(socket != null)
		    	 {
		    		 in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
				                                                           
		    		 str = in.readLine();
		    	 }
			} catch (IOException e) {System.out.println("Błąd OdbWiad KLIENT: "+e.getMessage());
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
			    		ustawNickHosta(str.substring(4, pozycjaKreski1));
			    		ustawNrAwataruHosta(Integer.valueOf(str.substring(pozycjaKreski1+1, pozycjaKreski2)));
			    		
			    		poleNick.setText(nickHosta);
			    		poleAwatar.setIcon(uzytkownik.awatary[Integer.valueOf(str.substring(pozycjaKreski1+1, pozycjaKreski2))]);
			    	}
			    	else if (str.substring(0, 4).equals("#WS#"))
			    	{
			    		int strzalX = Integer.valueOf(str.substring(4,5));
			    		int strzalY =Integer.valueOf(str.substring(5,6));
			    		boolean czyTrafiono = uzytkownik.plansza.sprawdzStrzalPrzeciwnika(strzalX, strzalY);
			    		
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
			    		
			    		wyslijWiadomosc(String.valueOf(czyTrafiono), "#SS#");
			    	}
			    	else if (str.substring(0, 4).equals("#SS#"))
			    	{
			    		String czyTrafiono = str.substring(4);
			    		uzytkownik.plansza.setWynikStrzalu(czyTrafiono);
			    		if(czyTrafiono.equals("true"))
			    		{
			    			poleStrzalu.setIcon(img_trafionePole2);
			    			uzytkownik.setMozliwoscWykonaniaRuchu(true);
				    		uzytkownik.plansza.iloscTrafionychStrzalow++;
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
			    	}
			    	else if (str.substring(0, 4).equals("#KR#"))
			    	{
			    		if(str.substring(4).equals("true"))
			    		{
			    			System.out.println("Czy ruch gracza: "+str.substring(4));
			    			uzytkownik.setMozliwoscWykonaniaRuchu(true);
				    		etykietaStrzalki.setIcon(img_strzalkaPRAWO_g);
				    		etykietaCzyjRuch.setForeground(new Color(0, 204, 0));
				    		etykietaCzyjRuch.setText("TWÓJ RUCH");
			    		}
			    		else if(str.substring(4).equals("false"))
			    		{
			    			System.out.println("Czy ruch gracza: "+str.substring(4));
			    			uzytkownik.setMozliwoscWykonaniaRuchu(false);
			    		}
			    	}
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
			    			widokGryZdarzenia.widokWynikow.lb_ZwyciescaGry.setText(getNickHosta());
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
									iloscTrafionychHOST++;
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
		    	czat.setText(czat.getText()+"Komunikat: GRACZ "+nickHosta.trim()+" ROZŁĄCZYŁ SIĘ!\n");
		    	polaczenieOK = false;
		    	rozlacz();
		    	widokGryZdarzenia.komunikatKoniecGry("Rozgrywka dobiegła końca! Przeciwnik uciekł :(");
		    }
		   if(widokGryZdarzenia.czyUstalacKtoWygral)
			   widokGryZdarzenia.widokWynikow.widokWynikowZdarzenia.ustalKtoWygral();
		}
		
	}
	
	
	public void wyslijWiadomoscRozlaczenie() throws IOException
	{
		 if(out != null)
		 {
		out = new PrintWriter(socket.getOutputStream());                                                                                
	    String str="Połączenie zerwane";   
	    out.println(str);                                                               
	    out.flush(); 
		 }
	}
	
	public boolean czyRozlaczono;
	public void rozlacz() 
	{     
			
			try {
				if(out != null)
					out.close();
				if(in != null)
					in.close();
				if(socket != null)
					socket.close();
				czyRozlaczono = true;
			} catch (IOException e) {System.out.println("Błąd RozlPolacz: "+e.getMessage());
			}
			
	}
}
