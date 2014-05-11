package Statki;

import java.net.InetAddress;

import javax.swing.ImageIcon;

public class Uzytkownik {
	
	public Gracz gracz;
	public Host host;
	
	private int port;
	//private String host;
	private InetAddress ip;
	private String nazwaGracza;
	public int nrAwatara;
	public Plansza plansza;
	
	public Uzytkownik(Host _host) {
		port = 50007;
		host = _host;
		plansza = new Plansza();
	}

	public Uzytkownik(Gracz _gracz) {
		port = 50007;
		gracz = _gracz;
		plansza = new Plansza();
	}
	
	public void wybierzNazwe(String _nazwaGracza) {
		nazwaGracza = _nazwaGracza;
	}
	
	public String getNazwaGracza()
	{
		return nazwaGracza;
	}
	
	public ImageIcon[] awatary;
	public void przekazAwatary(ImageIcon[] _awatary)
	{
		awatary = _awatary;
	}
	
	private ImageIcon awatarGracza;
	public void wybierzAwatar(int _nrAwatara) {
		nrAwatara = _nrAwatara;
		awatarGracza = awatary[nrAwatara];
	}
	
	public ImageIcon getAwatar()
	{
		return awatarGracza;
	}
	
	public Gracz getGracz()
	{
		return gracz;
	}
	
	public Host getHost()
	{
		return host;
	}
}
