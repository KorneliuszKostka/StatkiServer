package Statki;

public class Statek {

	private String pozycja;
	private String rozmiar;
	private int poczatekStatku_X;
	private int poczatekStatku_Y;
	
	public Statek() {
		rozmiar = "";
		pozycja = "";
		poczatekStatku_X = -1;
		poczatekStatku_Y = -1;
	}
	
	public Statek(String _rozmiar, String _pozycja, int _poczStatku_X,  int _poczStatku_Y) {
		rozmiar = _rozmiar;
		pozycja = _pozycja;
		poczatekStatku_X = _poczStatku_X;
		poczatekStatku_Y = _poczStatku_Y;
	}
	
	public void setRozmiar(String _rozmiar)
	{
		rozmiar = _rozmiar;
	}
	
	public void setPoczatekStatku(int _poczStatku_X,  int _poczStatku_Y)
	{
		poczatekStatku_X = _poczStatku_X;
		poczatekStatku_Y = _poczStatku_Y;
	}
	
	public void setPozycja(String _pozycja)
	{
		pozycja = _pozycja;
	}
	
	public String getRozmiar()
	{
		return rozmiar;
	}
	
	public int getPoczatekStatku_X()
	{
		return poczatekStatku_X;
	}
	
	public int getPoczatekStatku_Y()
	{
		return poczatekStatku_Y;
	}
	
	public String getPozycja()
	{
		return pozycja;
	}
}
