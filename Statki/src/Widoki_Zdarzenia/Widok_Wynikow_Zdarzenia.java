package Widoki_Zdarzenia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;

import javax.swing.BorderFactory;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Statki.Gracz;
import Statki.Host;
import Statki.Pole;
import Statki.Uzytkownik;
import Widoki_GUI.Widok_Wynikow;

public class Widok_Wynikow_Zdarzenia implements ActionListener, WindowListener, MouseListener, ItemListener, KeyListener{
	
	private Widok_Wynikow widokWynikow;
	
	public Host host;
	public Gracz gracz;
	
	public ImageIcon img_zwyklePole2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/pole.png"));
	public ImageIcon img_trafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek_trafiony.png"));
	public ImageIcon img_nieTrafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/pole_nietrafione.png"));
	public ImageIcon img_statek2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek.png"));
	
	public Widok_Wynikow_Zdarzenia(Widok_Wynikow _widokWynikow, Host _host, Gracz _gracz) {
		widokWynikow = _widokWynikow;
		gracz = _gracz;
		host = _host;
		
		widokWynikow.setFocusable(true);
		widokWynikow.addKeyListener(this);
		widokWynikow.tf_Wiadomosc.addKeyListener(this);
		widokWynikow.cb_szablonyRozmow.addKeyListener(this);
		widokWynikow.ta_Czat.addKeyListener(this);
		
		widokWynikow.addWindowListener(this);
		
		widokWynikow.btn_ZaproponujRewanz.addActionListener(this);
		widokWynikow.btn_OpuscGre.addActionListener(this);
		widokWynikow.mnI_ZaproponujRewanz.addActionListener(this);
		widokWynikow.mnI_UstawieniaLokalne.addActionListener(this);
		widokWynikow.mnI_Wyjscie.addActionListener(this);
		widokWynikow.mnI_InstrukcjaObslugi.addActionListener(this);
		widokWynikow.mnI_oGrze.addActionListener(this);
		widokWynikow.mnI_oTworcach.addActionListener(this);
		widokWynikow.btn_Wyslij.addActionListener(this);
		
		widokWynikow.cb_szablonyRozmow.addItemListener(this);
	}

	public int iloscTrafionychGRACZ;
	public int iloscTrafionychPRZECIWNIK = 0;
	@Override
	public void windowOpened(WindowEvent e) {
		if(gracz != null)
		{
			//System.out.println("Włączam pobieranie wiadomosci KLIENT!");
			//System.out.println("PolaczenieOK: "+gracz.polaczenieOK);
			gracz.przekazPoleCzatu(widokWynikow.ta_Czat);
			gracz.przekazPoleWiadomosci(widokWynikow.tf_Wiadomosc);
			gracz.wlaczPobieranieWiadomosci();
			
			gracz.przekazPoleNick(widokWynikow.lb_NazwaGracza);
			gracz.przekazPoleAwatar(widokWynikow.lb_AwatarGracza);
			
			widokWynikow.lb_NazwaGracza.setText(gracz.uzytkownik.getNazwaGracza());
			widokWynikow.lb_AwatarGracza.setIcon(gracz.uzytkownik.getAwatar());
			
			widokWynikow.lb_NazwaPrzeciwnika.setText(gracz.getNickHosta());
			widokWynikow.lb_AwatarPrzeciwnika.setIcon(gracz.uzytkownik.awatary[gracz.getNrAwataruHosta()]);
			
			
			if(!gracz.polaczenieOK)
			{
				widokWynikow.btn_ZaproponujRewanz.setEnabled(false);
				widokWynikow.btn_Wyslij.setEnabled(false);
			}
			
			String pp1 = "";
			if(widokWynikow.widokGry.gracz != null)
			{
				for(int x=0;x<10;x++)
					for(int y=0;y<10;y++)
						pp1 = pp1 + widokWynikow.widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[y][x];
				widokWynikow.widokGry.gracz.wyslijWiadomosc(pp1, "#PP#");
			}
				
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)
					if(widokWynikow.widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i].equals("T"))
					{
						lb_polaGry_GRACZ[j][i].setIcon(img_trafionePole2);
					}
					else if(widokWynikow.widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i].equals("P"))
						lb_polaGry_GRACZ[j][i].setIcon(img_nieTrafionePole2);
					else if(widokWynikow.widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i].equals("S"))
						lb_polaGry_GRACZ[j][i].setIcon(img_statek2);
			
			//ustalanie kto wygral
			//widokWynikow.btn_OpuscGre.setText(String.valueOf(widokWynikow.widokGry.widokGryZdarzenia.czyUstalacKtoWygral));
			if(widokWynikow.widokGry.widokGryZdarzenia.czyUstalacKtoWygral)
				ustalKtoWygral();
			else
			{
				//widokWynikow.btn_OpuscGre.setText(String.valueOf(widokWynikow.widokGry.widokGryZdarzenia.czyGraczSkapitulowal));
				if(widokWynikow.widokGry.widokGryZdarzenia.czyGraczSkapitulowal)
					widokWynikow.lb_ZwyciescaGry.setText(widokWynikow.lb_NazwaPrzeciwnika.getText());
				else 
					widokWynikow.lb_ZwyciescaGry.setText(widokWynikow.lb_NazwaGracza.getText());
			}
		}
		if(host != null)
		{
			System.out.println("Włączam pobieranie wiadomosci HOST!");
			System.out.println("PolaczenieOK: "+host.polaczenieOK);
			host.polaczenieOK = true;
			host.przekazPoleCzatu(widokWynikow.ta_Czat);
			host.przekazPoleWiadomosci(widokWynikow.tf_Wiadomosc);
			host.i++;
			host.wlaczPobieranieWiadomosci();
			
			host.przekazPoleNick(widokWynikow.lb_NazwaGracza);
			host.przekazPoleAwatar(widokWynikow.lb_AwatarGracza);
			
			widokWynikow.lb_NazwaGracza.setText(host.uzytkownik.getNazwaGracza());
			widokWynikow.lb_AwatarGracza.setIcon(host.uzytkownik.getAwatar());
			
			widokWynikow.lb_NazwaPrzeciwnika.setText(host.getNickGracza());
			widokWynikow.lb_AwatarPrzeciwnika.setIcon(host.uzytkownik.awatary[host.getNrAwataruGracza()]);
			
			
			if(!host.polaczenieOK)
			{
				widokWynikow.btn_ZaproponujRewanz.setEnabled(false);
				widokWynikow.btn_Wyslij.setEnabled(false);
			}
			
			String pp2 = "";
				for(int k=0;k<10;k++)
					for(int l=0;l<10;l++)
						pp2 = pp2 + widokWynikow.widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[l][k];
				widokWynikow.widokGry.host.wyslijWiadomosc(pp2, "#PP#");
				
					
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)
					if(widokWynikow.widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i].equals("T"))
					{
						lb_polaGry_GRACZ[j][i].setIcon(img_trafionePole2);
					}
					else if(widokWynikow.widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i].equals("P"))
						lb_polaGry_GRACZ[j][i].setIcon(img_nieTrafionePole2);
					else if(widokWynikow.widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i].equals("S"))
						lb_polaGry_GRACZ[j][i].setIcon(img_statek2);
				
			//ustalanie kto wygral
			//widokWynikow.btn_OpuscGre.setText(String.valueOf(widokWynikow.widokGry.widokGryZdarzenia.czyUstalacKtoWygral));
			if(widokWynikow.widokGry.widokGryZdarzenia.czyUstalacKtoWygral)
				ustalKtoWygral();
			else
			{
				if(widokWynikow.widokGry.widokGryZdarzenia.czyGraczSkapitulowal)
					widokWynikow.lb_ZwyciescaGry.setText(widokWynikow.lb_NazwaPrzeciwnika.getText());
				else 
					widokWynikow.lb_ZwyciescaGry.setText(widokWynikow.lb_NazwaGracza.getText());
			}
		}
	}

	@Override
	public void windowClosing(WindowEvent e) {
		wyjscie();
	}

	@Override
	public void windowClosed(WindowEvent e) {
	}

	@Override
	public void windowIconified(WindowEvent e) {
	}

	@Override
	public void windowDeiconified(WindowEvent e) {
	}

	@Override
	public void windowActivated(WindowEvent e) {
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}
	

	@Override
	public void mouseClicked(MouseEvent e) {	
	}

	@Override
	public void mousePressed(MouseEvent e) {
	}

	@Override
	public void mouseReleased(MouseEvent e) {
	}

	@Override
	public void mouseEntered(MouseEvent e) {
	}

	@Override
	public void mouseExited(MouseEvent e) {
	}
	
	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == widokWynikow.cb_szablonyRozmow)
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
		
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == widokWynikow.mnI_UstawieniaLokalne)
		{
			widokWynikow.widokGry.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoWynikow");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokWynikow.btn_ZaproponujRewanz || e.getSource() == widokWynikow.mnI_ZaproponujRewanz)
		{
			zaproponujRewanz();
		}
		if(e.getSource() == widokWynikow.mnI_Wyjscie || e.getSource() == widokWynikow.btn_OpuscGre)
		{
			
			wyjscie();
		}
		if(e.getSource() == widokWynikow.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokWynikow.mnI_oGrze)
		{
			widokWynikow.widokGry.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoWynikow");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokWynikow.mnI_oTworcach)
		{
			widokWynikow.widokGry.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoWynikow");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokWynikow.btn_Wyslij)
		{
			//wyslijWiadomosc();
			if(gracz != null)
				gracz.wyslijWiadomosc(widokWynikow.tf_Wiadomosc.getText(), "#WP#");
			if(host != null)
				host.wyslijWiadomosc(widokWynikow.tf_Wiadomosc.getText(), "#WP#");
			czyscPoleWiadomosci();
		}
	}
	

	@Override
	public void keyTyped(KeyEvent e) {
	}

	@Override
	public void keyPressed(KeyEvent e) {
		int klawisz = e.getKeyCode();
		
		if(klawisz == KeyEvent.VK_ENTER)
			wyslijWiadomosc();
		
		if(klawisz == KeyEvent.VK_F1)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(1).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F2)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(2).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F3)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(3).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F4)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(4).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F5)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(5).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F6)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(6).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F7)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(7).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F8)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(8).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F9)
		{
			widokWynikow.cb_szablonyRozmow.setSelectedItem(widokWynikow.cb_szablonyRozmow.getItemAt(9).toString());
			wstawSzablonWiadomosci(widokWynikow.cb_szablonyRozmow.getSelectedItem().toString());
			wyslijWiadomosc();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public void zaproponujRewanz()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokWynikow, "Czy na pewno chcesz zaproponować rewanż?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) 	
		{
			if(gracz != null)
			{
				gracz.wyslijWiadomosc("true", "#PR#");
			}
			if(host != null)
			{
				host.wyslijWiadomosc("true", "#PR#");
			}
		}
	}

	public void uzyskanieOdpNaPytanie_oRewanz()
	{
		boolean b_odpowiedz = false;
		
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokWynikow, "Przeciwnik proponuje rewanż. Zgadzasz się?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) 
		{
			if(gracz != null)
			{
				gracz.wyslijWiadomosc("true", "#OR#");
				widokWynikow.setVisible(false);
				//zacznijGreOdNowa();
		lb_polaGry_GRACZ=null;
		lb_polaGry_PRZECIWNIK=null;
				gracz.planszaGracza=null;
				gracz.planszaPrzeciwnika=null;
				gracz.iloscTrafionychHOST=0;
				gracz.uzytkownik.plansza.zerujDanePlanszy();
				gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ=null;
				gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY=null;
				gracz.uzytkownik.plansza.l_polaPlanszy_PRZECIWNIK_STRZALY=null;
				
    			widokWynikow.widokGry.widokGlowny.setVisible(true);
				//widokWynikow.widokGry.widokGlowny.widokDolacz.widokRozmiesc.setVisible(true);
			}
			if(host != null)
			{
				host.wyslijWiadomosc("true", "#OR#");
				widokWynikow.setVisible(false);
				//zacznijGreOdNowa();
				host.planszaGracza=null;
				host.planszaPrzeciwnika=null;
				host.iloscTrafionychGRACZ=0;
				host.uzytkownik.plansza.zerujDanePlanszy();
				host.uzytkownik.plansza.l_polaPlanszy_GRACZ=null;
				host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY=null;
				host.uzytkownik.plansza.l_polaPlanszy_PRZECIWNIK_STRZALY=null;
				lb_polaGry_GRACZ=null;
				lb_polaGry_PRZECIWNIK=null;
				
    			widokWynikow.widokGry.widokGlowny.setVisible(true);
				//widokWynikow.widokGry.widokGlowny.widokUtworz.widokRozmiesc.setVisible(true);
    			widokWynikow.widokGry.widokGlowny.setVisible(true);
			}
		}
		else
		{
			if(gracz != null)
			{
				gracz.wyslijWiadomosc("false", "#OR#");
			}
			if(host != null)
			{
				host.wyslijWiadomosc("false", "#OR#");
			}
		}
	}
	
	public void komunikat_odpPrzeciwnikaNaTematRewanzu()
	{
		JOptionPane.showMessageDialog(widokWynikow, "Przeciwnik nie zgodził się na rewanż :(");
	}
	
	public void zacznijGreOdNowa()
	{
		if(gracz != null)
		{
			gracz = null;
		}
		if(host != null)
		{
			host = null;
		}
	}
	
	public void wyslijWiadomosc()
	{
		if(!widokWynikow.tf_Wiadomosc.getText().equals(""))
		{
			if(gracz != null)
				gracz.wyslijWiadomosc(widokWynikow.tf_Wiadomosc.getText(), "#WP#");
			if(host != null)
				host.wyslijWiadomosc(widokWynikow.tf_Wiadomosc.getText(), "#WP#");
			czyscPoleWiadomosci();
		}
	}
	
	private void wstawSzablonWiadomosci(String _wiadomosc)
	{
		widokWynikow.tf_Wiadomosc.setText(_wiadomosc);
	}
	
	public void czyscPoleWiadomosci()
	{
		widokWynikow.tf_Wiadomosc.setText("");
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokWynikow, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokWynikow.setVisible(false);
		widokWynikow.widokGry.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokWynikow.setVisible(false);
		widokWynikow.widokGry.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokWynikow.setVisible(false);
		widokWynikow.widokGry.widokGlowny.widokOpisTworcow.setVisible(true);
	}
	
	public void ustalKtoWygral()
	{
		
		iloscTrafionychGRACZ = 0;
		iloscTrafionychPRZECIWNIK = 0;
		JLabel ikonaTrafionePole2 = new JLabel(img_trafionePole2);
		
			for(int a = 0;a < 10; a++)
				for(int b = 0;b < 10; b++)
				{
					if(lb_polaGry_GRACZ[b][a].getIcon().toString().equals(ikonaTrafionePole2.getIcon().toString()))
						iloscTrafionychGRACZ++;
				}	

			for(int i = 0;i < 10; i++)
				for(int j = 0;j < 10; j++)
				{
					if(lb_polaGry_PRZECIWNIK[j][i].getIcon().toString().equals(ikonaTrafionePole2.getIcon().toString()))
						iloscTrafionychPRZECIWNIK++;
				}	
			
			if(iloscTrafionychGRACZ > iloscTrafionychPRZECIWNIK)
				widokWynikow.lb_ZwyciescaGry.setText(widokWynikow.lb_NazwaPrzeciwnika.getText());
			else if(iloscTrafionychGRACZ < iloscTrafionychPRZECIWNIK)
				widokWynikow.lb_ZwyciescaGry.setText(widokWynikow.lb_NazwaGracza.getText());
		
	}
	
	public void czyscPolaPlansz()
	{
		for(int i=0;i<10;i++)
			for(int j=0;j<10;j++)
			{
				lb_polaGry_GRACZ[j][i].setIcon(null);
				lb_polaGry_PRZECIWNIK[j][i].setIcon(null);
			}
	}
	
	public JLabel lb_polaGry_GRACZ[][];
	public JLabel lb_polaGry_PRZECIWNIK[][];
	public void stworzPolaGry(String _nazwaPola, boolean _czyRysowacSymbole, boolean _czyjePole, JLabel[][] _pola, int _szerPojPola, int _wysPojPola, int _skok, JPanel _obszarPol, JPanel _obszarLiter, JPanel _obszarCyfr, int _szer, int _wys)
	{
		JLabel lb_litery[] = new JLabel[10];
		JLabel lb_cyfry[] = new JLabel[10];
		_pola = new JLabel[10][10];
		String litery[] = {"A","B","C","D","E","F","G","H","I","J"}; 
		int x = 0, y = 0;
		
		/*if(_czyRysowacSymbole)
		{
			x = _skok*(-1); y = _skok*(-1);
		}
		else
		{*/
			x = 0; y = 0;
		//}
		for(int i = 0;i < 10; i++)
		{
			//if(_czyRysowacSymbole)
				//x = _skok*(-1);
			//else
				x = 0;
			y+=_skok;
			for(int j = 0;j < 10; j++)
			{
				x+=_skok;
				
				_pola[j][i] = new JLabel();
				//if(!_nazwaPola.equals("pole gracza"))
					//_pola[j][i].setBounds(x-1, y, _szerPojPola, _wysPojPola);
				//else
					_pola[j][i].setBounds(x+3, y+3, _szerPojPola, _wysPojPola);
				if(_czyRysowacSymbole)
					_pola[j][i].setText(String.valueOf(j)+String.valueOf(i));
				//if(_czyRysowacSymbole)
				_pola[j][i].setOpaque(false);
				//_pola[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				_pola[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				_pola[j][i].setVerticalAlignment(SwingConstants.CENTER);
				//_pola[j][i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				_pola[j][i].addMouseListener(this);
				_obszarPol.add(_pola[j][i]);
				
				
				_pola[j][i].setIcon(img_zwyklePole2);
				
				
				
				/*if(_czyRysowacSymbole)
				{
					lb_cyfry[i] = new JLabel();
					lb_cyfry[i].setBounds(x+3, 0, _szer, _wys);
					lb_cyfry[i].setFont(new Font("Verdana", Font.BOLD, 11));
					lb_cyfry[i].setText(String.valueOf(j+1));
					lb_cyfry[i].setHorizontalTextPosition(SwingConstants.CENTER);
					//lb_cyfry[i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
					_obszarCyfr.add(lb_cyfry[i]);
				}*/
			}
			/*if(_czyRysowacSymbole)
			{
				lb_litery[i] = new JLabel();
				lb_litery[i].setBounds(0, y+3, _szer, _wys);
				lb_litery[i].setFont(new Font("Verdana", Font.BOLD, 11));
				lb_litery[i].setText(litery[i]);
				lb_litery[i].setHorizontalAlignment(SwingConstants.RIGHT);
				//lb_litery[i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				_obszarLiter.add(lb_litery[i]);
			}*/
		}
		if(_czyjePole)
			lb_polaGry_GRACZ = _pola;
		else
			lb_polaGry_PRZECIWNIK = _pola;
	}
}
