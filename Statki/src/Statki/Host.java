package Statki;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;

import javax.sql.PooledConnection;
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
	
	public Host() {
		sock = null;
		serv = null;
		czyRozlaczono = false;
		polaczenieOK = false;
		nickHosta = "";
		nickGracza = "";
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
	
	private Uzytkownik uzytkownik;
	public void przekazUzytkownika(Uzytkownik _uzytkownik)
	{
		uzytkownik = _uzytkownik;
	}
	
	private JLabel wspStrzaluPrzeciwnika;
	public void przekazPoleWspPrzeciwnika(JLabel _wspStrzaluPrzeciwnika)
	{
		wspStrzaluPrzeciwnika = _wspStrzaluPrzeciwnika;
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
			    		int pozycjaKreski = str.indexOf("|");
			    		
			    		ustawNickGracza(str.substring(4, pozycjaKreski));
			    		poleNick.setText(nickGracza);
			    		poleAwatar.setIcon(uzytkownik.awatary[Integer.valueOf(str.substring(pozycjaKreski+1))]);
			    	}
			    	else if (str.substring(0, 4).equals("#WS#"))
			    	{
			    		int strzalX = Integer.valueOf(str.substring(4,5));
			    		int strzalY =Integer.valueOf(str.substring(5,6));
			    		uzytkownik.plansza.ustawStrzalPrzeciwnika(strzalX, strzalY);
			    		wspStrzaluPrzeciwnika.setText(""+strzalX+""+strzalY);
			    	}
		    	}
		    }
		    else
		    {
		    	czat.setText(czat.getText()+"Komunikat: GRACZ "+nickGracza.trim()+" ROZŁĄCZYŁ SIĘ!\n");
		    	polaczenieOK = false;
		    	rozlacz();
		    }
		}
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
