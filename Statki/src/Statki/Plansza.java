package Statki;


import java.awt.EventQueue;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.Random;

public class Plansza {

	private Statek[] l_statek;
	public Pole[][] l_polaPlanszy;
	private int iNumerStatku;
	
	public Plansza() {
		l_polaPlanszy = new Pole[10][10];
		
		for(int i = 0;i < 10;i++)
			for(int j = 0;j < 10;j++)
			{
				l_polaPlanszy[j][i] = new Pole(j, i); 
				l_polaPlanszy[j][i].setRodzajPola("0");
			}
		
		iNumerStatku = -1;
		l_statek = new Statek[10];
		for(int j = 0;j < 10;j++)
			l_statek[j] = new Statek(); 
		
		strzalX = -1;
		strzalY = -1;
	}
	
	private int strzalX, strzalY;
	public void ustawStrzalPrzeciwnika(int _strzalX, int _strzalY)
	{
		strzalX = _strzalX;
		strzalY = _strzalY;
	}
	
	public String getStrzalPrzeciwnika()
	{
		return ""+strzalX+""+strzalY;
	}
	
	public void rozmiescStatkiLosowo()
	{
		Random rnd = new Random();
		
		int iloscJenomasztowcow = 4, iloscDwumasztowcow = 3, iloscTrojmasztowcow = 2, iloscCzteromasztowcow = 1;
		String rozmiarStatku = "";
		int pozycja = 0;
		String pozycjaStatku = "";
		int poczStatkuX = 0, poczStatkuY = 0;
		
		do
		{
			rozmiarStatku = "czteromasztowiec";
			pozycja = rnd.nextInt(2);
			if(pozycja == 0) pozycjaStatku = "poziomo";
			if(pozycja == 1) pozycjaStatku = "pionowo";
			
			poczStatkuX = rnd.nextInt(7);
			poczStatkuY = rnd.nextInt(7);
			
			if(sprawdzWspolrzedneUstawianegoStatku(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY))
			{
				dodajZablokowanePola(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				ustawStatek(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				iloscCzteromasztowcow--;
			}
		}
		while (iloscCzteromasztowcow > 0);
		
		do
		{
			rozmiarStatku = "trójmasztowiec";
			pozycja = rnd.nextInt(2);
			if(pozycja == 0) pozycjaStatku = "poziomo";
			if(pozycja == 1) pozycjaStatku = "pionowo";
			
			poczStatkuX = rnd.nextInt(8);
			poczStatkuY = rnd.nextInt(8);
			
			if(sprawdzWspolrzedneUstawianegoStatku(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY))
			{
				dodajZablokowanePola(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				ustawStatek(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				iloscTrojmasztowcow--;
			}
		}
		while (iloscTrojmasztowcow > 0);
		
		do
		{
			rozmiarStatku = "dwumasztowiec";
			pozycja = rnd.nextInt(2);
			if(pozycja == 0) pozycjaStatku = "poziomo";
			if(pozycja == 1) pozycjaStatku = "pionowo";
			
			poczStatkuX = rnd.nextInt(9);
			poczStatkuY = rnd.nextInt(9);
			
			if(sprawdzWspolrzedneUstawianegoStatku(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY))
			{
				dodajZablokowanePola(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				ustawStatek(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				iloscDwumasztowcow--;
			}
		}
		while (iloscDwumasztowcow > 0);
		
		do
		{
			rozmiarStatku = "jednomasztowiec";
			pozycja = rnd.nextInt(2);
			if(pozycja == 0) pozycjaStatku = "poziomo";
			if(pozycja == 1) pozycjaStatku = "pionowo";
			
			poczStatkuX = rnd.nextInt(10);
			poczStatkuY = rnd.nextInt(10);
			
			if(sprawdzWspolrzedneUstawianegoStatku(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY))
			{
				dodajZablokowanePola(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				ustawStatek(rozmiarStatku, pozycjaStatku, poczStatkuX, poczStatkuY);
				iloscJenomasztowcow--;
			}
		}
		while (iloscJenomasztowcow > 0);
	}
	
	public void czyscPlansze()
	{
		l_polaPlanszy = new Pole[10][10];
		
		for(int i = 0;i < 10;i++)
			for(int j = 0;j < 10;j++)
			{
				l_polaPlanszy[j][i] = new Pole(j, i); 
				l_polaPlanszy[j][i].setRodzajPola("0");
			}
		
		iNumerStatku = -1;
		l_statek = new Statek[10];
		for(int j = 0;j < 10;j++)
			l_statek[j] = new Statek(); 
	}
	
	public boolean sprawdzWspolrzedneUstawianegoStatku(String _rozmiarStatku, String _pozycja, int _poczStatku_X, int _poczStatku_Y)
	{
		int rozmiarStatku = 0;
		switch(_rozmiarStatku)
		{
			case "jednomasztowiec": rozmiarStatku = 1;break;
			case "dwumasztowiec": rozmiarStatku = 2;break;
			case "trójmasztowiec": rozmiarStatku = 3;break;
			case "czteromasztowiec": rozmiarStatku = 4;break;
		}
		int poczStatku_X = _poczStatku_X, poczStatku_Y = _poczStatku_Y;
		
		boolean wspolrzedneOK = true;
		int buf_sprawdzanyX = 0, buf_sprawdzanyY = 0;
		String buf_rodzajPola = "";
		buf_sprawdzanyX = poczStatku_X;
		buf_sprawdzanyY = poczStatku_Y;
		
		for(int k=0;k<rozmiarStatku;k++)
		{
			if(_pozycja.equals("poziomo") && buf_sprawdzanyX+k<10)
			{
				buf_sprawdzanyX+=k;
				buf_rodzajPola = l_polaPlanszy[buf_sprawdzanyX][buf_sprawdzanyY].getRodzajPola();
			}
					
			if(_pozycja.equals("pionowo") && buf_sprawdzanyY+k<10)
			{
				buf_sprawdzanyY+=k;
				buf_rodzajPola = l_polaPlanszy[buf_sprawdzanyX][buf_sprawdzanyY].getRodzajPola();
			}
			
			if(buf_rodzajPola.equals("1") || buf_rodzajPola.equals("X"))
			{
				System.out.println("ZLY 1");
				wspolrzedneOK = false;
			}
			else
				wspolrzedneOK = true;
			if(!wspolrzedneOK)
				if(buf_rodzajPola.equals("1") || buf_rodzajPola.equals("X"))
				{
					System.out.println("ZLY 2");
					wspolrzedneOK = false;
					break;
				}
				else
					wspolrzedneOK = true;
		}
		
		return wspolrzedneOK;
	}
	
	public void dodajZablokowanePola(String _rozmiarStatku, String _pozycja, int _poczStatku_X, int _poczStatku_Y)
	{
		int rozmiarStatku = 0;
		switch(_rozmiarStatku)
		{
			case "jednomasztowiec": rozmiarStatku = 1;break;
			case "dwumasztowiec": rozmiarStatku = 2;break;
			case "trójmasztowiec": rozmiarStatku = 3;break;
			case "czteromasztowiec": rozmiarStatku = 4;break;
		}
		int poczStatku_X = _poczStatku_X, poczStatku_Y = _poczStatku_Y;
		int iPoleX = 0, iPoleY = 0;
		int iloscZablokowanychPol = 0;
		int zakresX = 0, zakresY = 0;
		int koniecStatku_X = 0;
		int koniecStatku_Y = 0;
		if(_pozycja.equals("poziomo"))
		{
			koniecStatku_X = (_poczStatku_X + rozmiarStatku) - 1;
			koniecStatku_Y = _poczStatku_Y;
			
			if(poczStatku_X > 0 && koniecStatku_X < 9)
			{
				if(poczStatku_Y > 0 && poczStatku_Y < 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+2) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y;
					zakresX = iPoleX+rozmiarStatku+1;
					zakresY = poczStatku_Y+1;
					
					/*System.out.println("Ilość zab pol: "+iloscZablokowanychPol);
					System.out.println("iPoleX: "+iPoleX);
					System.out.println("iPoleY: "+iPoleY);
					System.out.println("zakresX: "+zakresX);
					System.out.println("zakresY: "+zakresY);
					System.out.println("poczX: "+poczStatku_X);
					System.out.println("konX: "+koniecStatku_X);
					System.out.println("poczY: "+poczStatku_Y);
					System.out.println("konY: "+koniecStatku_Y);*/
				}
				if(poczStatku_Y == 0)
				{
					iloscZablokowanychPol = (rozmiarStatku+2) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y;
					zakresX = iPoleX+rozmiarStatku+1;
					zakresY = poczStatku_Y+1;
				}
				if(poczStatku_Y == 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+2) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = iPoleX+rozmiarStatku+1;
					zakresY = poczStatku_Y;
				}
				
				if(poczStatku_Y == 0)
					for(int i = 0;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				else
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
			}
			if(poczStatku_X == 0)
			{
				if(poczStatku_Y > 0 && poczStatku_Y < 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 3;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X;
					iPoleY = poczStatku_Y-1;
					zakresX = rozmiarStatku;
					zakresY = poczStatku_Y+1;
				}
				if(poczStatku_Y == 0)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = (poczStatku_X+(rozmiarStatku-1))+1;
					iPoleY = poczStatku_Y;
					zakresX = rozmiarStatku;
					zakresY = 1;
				}
				if(poczStatku_Y == 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X;
					iPoleY = poczStatku_Y-1;
					zakresX = rozmiarStatku;
					zakresY = 9;
				}
				
				if(poczStatku_Y != 0)
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = 0;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				else
					for(int i = poczStatku_Y;i <= zakresY;i++)
						for(int j = 0;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
			}
			if(koniecStatku_X == 9)
			{
				if(poczStatku_Y > 0 && poczStatku_Y < 9)
				{
					System.out.println("OK!");
					iloscZablokowanychPol = (rozmiarStatku+1) * 3;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X+rozmiarStatku-1;
					zakresY = poczStatku_Y+1;
				}
				if(poczStatku_Y == 0)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y;
					zakresX = poczStatku_X+rozmiarStatku-1;
					zakresY = poczStatku_Y+1;
				}
				if(poczStatku_Y == 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X+rozmiarStatku-1;
					zakresY = poczStatku_Y;
				}
				
				if(poczStatku_Y != 0)
				{
					System.out.println("OK2!");
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				}
				else
					for(int i = poczStatku_Y;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
			}
		}
		if(_pozycja.equals("pionowo"))
		{
			koniecStatku_X = _poczStatku_X;
			koniecStatku_Y = (_poczStatku_Y + rozmiarStatku) - 1;
			
			if(poczStatku_Y > 0 && koniecStatku_Y < 9)
			{
				if(poczStatku_X > 0 && poczStatku_X < 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+2) * 3;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X+1;
					zakresY = iPoleY+rozmiarStatku+1;
				}
				if(poczStatku_X == 0)
				{
					iloscZablokowanychPol = (rozmiarStatku+2) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X+1;
					zakresY = iPoleY+rozmiarStatku+1;
				}
				if(poczStatku_X == 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+2) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X;
					zakresY = iPoleY+rozmiarStatku+1;
				}
				
				if(poczStatku_X != 0)
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				else
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
			}
			if(poczStatku_Y == 0)
			{
				if(poczStatku_X > 0 && poczStatku_X < 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 3;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y;
					zakresX = poczStatku_X+1;
					zakresY = iPoleY+rozmiarStatku;
				}
				if(poczStatku_X == 0)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X+1;
					iPoleY = poczStatku_Y;
					zakresX = poczStatku_X+1;
					zakresY = iPoleY+rozmiarStatku;
				}
				if(poczStatku_X == 9)
				{
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y;
					zakresX = poczStatku_X;
					zakresY = 3;
				}
				
				if(poczStatku_Y != 0)
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				else if(poczStatku_X != 0)
					for(int i = poczStatku_Y;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				else
					for(int i = poczStatku_Y;i <= zakresY;i++)
						for(int j = poczStatku_X;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
			}
			if(koniecStatku_Y == 9)
			{
				if(poczStatku_X > 0 && poczStatku_X < 9)
				{
					System.out.println("OK!");
					iloscZablokowanychPol = (rozmiarStatku+1) * 3;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X+1;
					zakresY = poczStatku_Y+rozmiarStatku-1;
				}
				if(poczStatku_X == 0)
				{
					System.out.println("OK!");
					iloscZablokowanychPol = (rozmiarStatku+1) * 3;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X+1;
					zakresY = poczStatku_Y+rozmiarStatku-1;
				}
				if(poczStatku_X == 9)
				{
					System.out.println("OK!");
					iloscZablokowanychPol = (rozmiarStatku+1) * 2;
					iloscZablokowanychPol -= rozmiarStatku;
					iPoleX = poczStatku_X-1;
					iPoleY = poczStatku_Y-1;
					zakresX = poczStatku_X;
					zakresY = poczStatku_Y+rozmiarStatku-1;
				}
				
				if(poczStatku_Y != 0 && koniecStatku_Y != 9)
				{
					System.out.println("OK2!");
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				}
				else if(poczStatku_X != 0)
				{
					System.out.println("OK3!");
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X-1;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
				}
				else
					for(int i = poczStatku_Y-1;i <= zakresY;i++)
						for(int j = poczStatku_X;j <= zakresX;j++)
						{
							if((j >= poczStatku_X && j <= koniecStatku_X) && (i >= poczStatku_Y && i <= koniecStatku_Y))
								l_polaPlanszy[j][i].setRodzajPola("1");
							else
								l_polaPlanszy[j][i].setRodzajPola("X");
						}
			}
		}
	}
	
	int i;
	
	public void ustawStatek(String _rozmiarStatku, String _pozycja, int _poczStatku_X, int _poczStatku_Y)
	{
		System.out.println("X: "+_poczStatku_X+" | Y: "+_poczStatku_Y);
		boolean czyUstawionoStatek = true;
		i++;
		if(i > 1)
		{
			if(sprawdzWspolrzedneUstawianegoStatku(_rozmiarStatku, _pozycja, _poczStatku_X, _poczStatku_Y))
			{
				dodajZablokowanePola(_rozmiarStatku, _pozycja, _poczStatku_X, _poczStatku_Y);
				iNumerStatku++;
				l_statek[iNumerStatku] = new Statek(_rozmiarStatku, _pozycja, _poczStatku_X, _poczStatku_Y);
				czyUstawionoStatek = true;
			}
			else
				czyUstawionoStatek = false;
		}
		else
		{
			dodajZablokowanePola(_rozmiarStatku, _pozycja, _poczStatku_X, _poczStatku_Y);
			iNumerStatku++;
			l_statek[iNumerStatku] = new Statek(_rozmiarStatku, _pozycja, _poczStatku_X, _poczStatku_Y);
			czyUstawionoStatek = true;
		}
		System.out.println("Rozmiar: "+_rozmiarStatku);
		System.out.println("Statek: "+_pozycja);
		System.out.println("CzyOK: "+czyUstawionoStatek);
		//return czyUstawionoStatek;
	}
	
	public int[] getLiczbaStatkow()
	{
		int liczbyStatkow[] = new int[4];
		for(int i = 0;i < l_statek.length; i++)
		{
			if(l_statek[i].getRozmiar().equals("jednomasztowiec"))
				liczbyStatkow[0]++;
			if(l_statek[i].getRozmiar().equals("dwumasztowiec"))
				liczbyStatkow[1]++;
			if(l_statek[i].getRozmiar().equals("trójmasztowiec"))
				liczbyStatkow[2]++;
			if(l_statek[i].getRozmiar().equals("czteromasztowiec"))
				liczbyStatkow[3]++;
		}
		return liczbyStatkow;
	}
	
	public int getLiczbaWszystkichStatkow()
	{
		int liczbaWszystkichStatkow = 0;
		for(int i = 0;i < l_statek.length; i++)
		{
			if(!l_statek[i].getRozmiar().equals(""))
				liczbaWszystkichStatkow++;
		}
		return liczbaWszystkichStatkow;
	}
	
/*	public static void main(String[] args) throws IOException {
		Plansza plansza = new Plansza();
		BufferedReader bufferRead = new BufferedReader(new InputStreamReader(System.in));
		String tekst = "";
		String rozmiar = "";
		String pozycja = "";
		do
		{
			for(int i=0;i<100;i++)
				System.out.println();
			System.out.print("-----------------------------------------------------------\n");
			for(int i=0;i<10;i++)
			{
				for(int j=0;j<10;j++)
				{
					System.out.print(plansza.l_polaPlanszy[j][i].getRodzajPola()+" ");
					if(j == 9)
						System.out.println();
				}
			}
			System.out.print("Rozmiar (j/d/t/c): ");
			tekst = bufferRead.readLine();
			if(!tekst.equals("k"))
			{
				switch(tekst)
				{
					case "j": rozmiar = "jednomasztowiec";
					break;
					case "d": rozmiar = "dwumasztowiec";
					break;
					case "t": rozmiar = "trójmasztowiec";
					break;
					case "c": rozmiar = "czteromasztowiec";
					break;
				}
				
				System.out.print("Pozycja (pn/pz): ");
				tekst = bufferRead.readLine();
				
				switch(tekst)
				{
					case "pn": pozycja = "pionowo";
					break;
					case "pz": pozycja = "poziomo";
					break;
				}
				
				System.out.print("X: ");
				tekst = bufferRead.readLine();
				int x = Integer.valueOf(tekst);
				
				System.out.print("Y: ");
				tekst = bufferRead.readLine();
				int y = Integer.valueOf(tekst);
				
				plansza.ustawStatek(rozmiar, pozycja, x, y);
			}
		}while(!tekst.equals("k"));
	}*/
}
