package Statki;

import java.net.*;
import java.util.Scanner;
import java.io.*;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Gracz {
	
	private Socket socket;
	private String host;
	private BufferedReader in;
	private String czas;
	PrintWriter out;
	public boolean polaczenieOK;

	
	public Gracz() {
		host = "";
		czas = null;
		socket = null;
		out   = null;
		in = null; 
		polaczenieOK = false;
	}
	
	Socket sock;
	public void polacz(String _serwer, JTextArea _czat)
	{
		//nawiazanie polaczenia z serwerem                                               
	                                                                           
	      try {
			sock=new Socket(_serwer,50007);
			
			 _czat.setText("Połączono z serwerem: "+_serwer+"\n");
		     // System.out.println("Nawiazalem polaczenie: "+_serwer);                              
		                                                                                       
		      //tworzenie strumieni danych pobieranych z klawiatury i dostarczanych do socketu 
		      //BufferedReader klaw;                                                             
		      //klaw=new BufferedReader(new InputStreamReader(System.in));                       
		      PrintWriter outp;                                                                
		      outp=new PrintWriter(sock.getOutputStream());                                    
		                                                                                       
		      //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia   
		      //System.out.print("Klient: ");                                                
		     // String str="Klient: "+_wiadomosc.getText();                                                      
		     // outp.println(str);                                                               
		    //  outp.flush();                                                                    
		                                                                                       
		      //zamykanie polaczenia                                                           
		     // klaw.close();                                                                    
		      //outp.close();                                                                    
		      //sock.close();
			
		      polaczenieOK = true;
		} 
	    catch (UnknownHostException e) {polaczenieOK = false;} 
	    catch (IOException e) {polaczenieOK = false;}                                                       
	}
	
	public void polacz(String _serwer, JLabel _czat)
	{
		//nawiazanie polaczenia z serwerem                                               
        
	      try {
			sock=new Socket(_serwer,50007);
			
			 _czat.setText("Połączono z serwerem: "+_serwer+"\n");
		     // System.out.println("Nawiazalem polaczenie: "+_serwer);                              
		                                                                                       
		      //tworzenie strumieni danych pobieranych z klawiatury i dostarczanych do socketu 
		      //BufferedReader klaw;                                                             
		      //klaw=new BufferedReader(new InputStreamReader(System.in));                       
		      PrintWriter outp;                                                                
		      outp=new PrintWriter(sock.getOutputStream());                                    
		                                                                                       
		      //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia   
		      //System.out.print("Klient: ");                                                
		     // String str="Klient: "+_wiadomosc.getText();                                                      
		     // outp.println(str);                                                               
		    //  outp.flush();                                                                    
		                                                                                       
		      //zamykanie polaczenia                                                           
		     // klaw.close();                                                                    
		      //outp.close();                                                                    
		      //sock.close();
			
		      polaczenieOK = true;
		} 
	    catch (UnknownHostException e) {polaczenieOK = false;} 
	    catch (IOException e) {polaczenieOK = false;}  
	}
	
	public String pobierzIpSerwera()
	{
		return sock.getInetAddress().getHostAddress();
	}
	
	public void wyslijWiadomosc(JTextField _wiadomosc) throws IOException
	{
		 PrintWriter outp;                                                                
	      outp=new PrintWriter(sock.getOutputStream());                                    
	                                                                                       
	      //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia   
	      //System.out.print("Klient: ");                                                
	      String str="Klient: "+_wiadomosc.getText();                                                      
	      outp.println(str);                                                               
	      outp.flush(); 
	}
	
	public void odbierzWiadomosc(JTextArea _czat) throws IOException
	{
		 BufferedReader inp;                                                    
	      inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));  
	                                                                             
	      //komunikacja - czytanie danych ze strumienia                          
	      String str;                                                            
	      str=inp.readLine();                                                    
	     // System.out.println("<Nadeszlo:> " + str);     
	      _czat.setText(_czat.getText()+"Klient: " + str+"\n");
	}
}
