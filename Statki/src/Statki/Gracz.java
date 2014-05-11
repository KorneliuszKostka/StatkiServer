package Statki;

import java.net.*;
import java.util.Scanner;
import java.io.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gracz extends Thread{
	
	private Socket socket;
	private String host;
	private BufferedReader in;
	private PrintWriter out;
	volatile public boolean polaczenieOK;

	
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
			    		int pozycjaKreski = str.indexOf("|");
			    		
			    		ustawNickHosta(str.substring(4, pozycjaKreski));
			    		poleNick.setText(nickHosta);
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
		    	czat.setText(czat.getText()+"Komunikat: GRACZ "+nickHosta.trim()+" ROZŁĄCZYŁ SIĘ!\n");
		    	polaczenieOK = false;
		    	rozlacz();
		    }
		    
		}
	}
	
	
	public void wyslijWiadomoscRozlaczenie() throws IOException
	{
		//PrintWriter out = null;
		 if(out != null)
		 {
		out = new PrintWriter(socket.getOutputStream());                                                                                
	    String str="Połączenie zerwane";   
	    out.println(str);                                                               
	    out.flush(); 
		 }
	    //out.close();
	}
	
	boolean czyRozlaczono;
	public void rozlacz() 
	{     
			
			try {
				if(out != null)
					out.close();
				//out = null;
				if(in != null)
					in.close();
				//in = null;
				if(socket != null)
					socket.close();
				//socket = null;
				czyRozlaczono = true;
			} catch (IOException e) {System.out.println("Błąd RozlPolacz: "+e.getMessage());
			}
			
	}
}
