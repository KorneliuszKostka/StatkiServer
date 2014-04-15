package Statki;

import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.net.DatagramPacket;
import java.net.DatagramSocket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.net.UnknownHostException;
import java.util.Date;
import java.util.Scanner;

import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class Host{
	
	private DatagramSocket gniazdo;
	
	public String ZgloszenieKlienta;
	public boolean polaczenieOK;
	
	public Host() {
		ZgloszenieKlienta = "";
		polaczenieOK = false;
	}
	Socket sock; 
	ServerSocket serv;  
	public void stworzHost(JTextArea _czat) throws IOException
	{
		try {
			//tworzenie gniazda serwerowego  
			serv=new ServerSocket(50007);
		                                                                                                               
			//oczekiwanie na polaczenie i tworzenie gniazda sieciowego                                                                          
			sock=serv.accept();
		  
			_czat.setText("Połączono z klientem: "+sock.getInetAddress().getHostAddress()+"\n");
	                                                                             
			//tworzenie strumienia danych pobieranych z gniazda sieciowego         
			BufferedReader inp;                                                    
			inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));
			polaczenieOK = true;
	      } catch (IOException e) {polaczenieOK = false;}  
	                                                                             
	      //komunikacja - czytanie danych ze strumienia                          
	    //  String str;                                                            
	    //  str=inp.readLine();                                                    
	    //  System.out.println("<Nadeszlo:> " + str);     
	     // _czat.setText(_czat.getText()+"\n"+"<Nadeszlo:> " + str);
	                                                                             
	      //zamykanie polaczenia                                                 
	      //inp.close();                                                           
	      //sock.close();                                                          
	      //serv.close();     
	     
	}
	
	public void stworzHost(JLabel _czat) 
	{                                                                                   
		try {
			//tworzenie gniazda serwerowego  
			serv=new ServerSocket(50007);
		                                                                                                               
			//oczekiwanie na polaczenie i tworzenie gniazda sieciowego                                                                          
			sock=serv.accept();
		  
			_czat.setText("Połączono z klientem: "+sock.getInetAddress().getHostAddress()+"\n");
	                                                                             
			//tworzenie strumienia danych pobieranych z gniazda sieciowego         
			BufferedReader inp;                                                    
			inp=new BufferedReader(new InputStreamReader(sock.getInputStream()));
			polaczenieOK = true;
	      } catch (IOException e) {polaczenieOK = false;}  
	                                                                             
	      //komunikacja - czytanie danych ze strumienia                          
	    //  String str;                                                            
	    //  str=inp.readLine();                                                    
	    //  System.out.println("<Nadeszlo:> " + str);     
	     // _czat.setText(_czat.getText()+"\n"+"<Nadeszlo:> " + str);
	                                                                             
	      //zamykanie polaczenia                                                 
	      //inp.close();                                                           
	      //sock.close();                                                          
	      //serv.close();     
	     
	}
	
	public String pobierzIpKlienta()
	{
		return sock.getInetAddress().getHostAddress();
	}
	
	public void wyslijWiadomosc(JTextField _wiadomosc) throws IOException
	{
		 PrintWriter outp;                                                                
	      outp=new PrintWriter(sock.getOutputStream());                                    
	                                                                                       
	      //komunikacja - czytanie danych z klawiatury i przekazywanie ich do strumienia   
	      //System.out.print("Klient: ");                                                
	      String str="Serwer: "+_wiadomosc.getText();                                                      
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
	
	public void rozlacz() throws IOException
	{
		sock.close();                                                          
	    serv.close();
	}
}
