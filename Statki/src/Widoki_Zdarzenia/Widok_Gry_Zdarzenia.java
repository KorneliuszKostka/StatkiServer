package Widoki_Zdarzenia;

import java.awt.Color;
import java.awt.Font;
import java.awt.RenderingHints.Key;
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
import java.io.File;
import java.io.IOException;
import java.net.UnknownHostException;
import java.util.Map;

import javax.swing.BorderFactory;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Statki.Gracz;
import Statki.Host;
import Statki.Uzytkownik;
import Widoki_GUI.Widok_Gry;
import Widoki_GUI.Widok_Wynikow;

public class Widok_Gry_Zdarzenia extends Thread implements ActionListener, WindowListener, MouseListener, ItemListener, KeyListener{
	public Widok_Gry widokGry;
	public Widok_Wynikow widokWynikow;
	
	public Host host;
	public Gracz gracz;
	
	public ImageIcon img_zaznaczonePole = new ImageIcon(getClass().getResource("/plansza/plansza220x220/zaznaczonePole.png"));
	public ImageIcon img_zwyklePole = new ImageIcon(getClass().getResource("/plansza/plansza220x220/pole.png"));
	public ImageIcon img_trafionePole = new ImageIcon(getClass().getResource("/plansza/plansza220x220/statek_trafiony.png"));
	public ImageIcon img_nieTrafionePole = new ImageIcon(getClass().getResource("/plansza/plansza220x220/pole_nietrafione.png"));
	
	public ImageIcon img_zwyklePole2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/pole.png"));
	public ImageIcon img_trafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek_trafiony.png"));
	public ImageIcon img_nieTrafionePole2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/pole_nietrafione.png"));
	public ImageIcon img_statek2 = new ImageIcon(getClass().getResource("/plansza/plansza200x200/statek.png"));
	
	public ImageIcon img_strzalkaLEWO_g = new ImageIcon(getClass().getResource("/strzalki/strzalkaLEWO_g.png"));
	public ImageIcon img_strzalkaPRAWO_g = new ImageIcon(getClass().getResource("/strzalki/strzalkaPRAWO_g.png"));
	public ImageIcon img_strzalkaLEWO_r = new ImageIcon(getClass().getResource("/strzalki/strzalkaLEWO_r.png"));
	public ImageIcon img_strzalkaPRAWO_r = new ImageIcon(getClass().getResource("/strzalki/strzalkaPRAWO_r.png"));
	
	public Widok_Gry_Zdarzenia(Widok_Gry _widokGry, Host _host, Gracz _gracz) {
		widokGry = _widokGry;
		gracz = _gracz;
		host = _host;
		
		widokWynikow = new Widok_Wynikow(widokGry, host, gracz);
		
		widokGry.setFocusable(true);
		widokGry.addKeyListener(this);
		widokGry.tf_TrescWiadomosci.addKeyListener(this);
		widokGry.cb_szablonyWiadomosci.addKeyListener(this);
		widokGry.ta_CzatGraczy.addKeyListener(this);
		
		widokGry.addWindowListener(this);
		
		widokGry.btn_Przerwa.addActionListener(this);
		widokGry.btn_Skapituluj.addActionListener(this);
		widokGry.btn_Wyslij.addActionListener(this);
		widokGry.mnI_Skapituluj.addActionListener(this);
		widokGry.mnI_ZaproponujRewanz.addActionListener(this);
		widokGry.mnI_UstawieniaLokalne.addActionListener(this);
		widokGry.mnI_Wyjscie.addActionListener(this);
		widokGry.mnI_InstrukcjaObslugi.addActionListener(this);
		widokGry.mnI_oGrze.addActionListener(this);
		widokGry.mnI_oTworcach.addActionListener(this);
		
		widokGry.cb_szablonyWiadomosci.addItemListener(this);
		
		czyGraczSkapitulowal = false;
		czyUstalacKtoWygral = true;
	}
	
	@Override
	public void mouseClicked(MouseEvent e) {
		JLabel ikonaTrafionePole = new JLabel(img_trafionePole);
		
		if(widokGry.uzytkownik.getMozliwoscWykonaniaRuchu())
		{
			for(int i = 0;i < 10; i++)
				for(int j = 0;j < 10; j++)
					if(e.getSource() == lb_polaGry_PRZECIWNIK[j][i])
					{
						if(lb_polaGry_PRZECIWNIK[j][i].getIcon() != null)
						{
							if(!lb_polaGry_PRZECIWNIK[j][i].getIcon().toString().equals(ikonaTrafionePole.getIcon().toString()))
							{
								if(gracz != null)
								{
									gracz.przekazPoleStrzalu(lb_polaGry_PRZECIWNIK[j][i]);
									gracz.wyslijWiadomosc(""+j+""+i, "#WS#");
									System.out.println("KLICK GRACZ!");
									//widokGry.lb_wspStrzaluPrzeciwnika.setText(""+gracz.uzytkownik.plansza.iloscTrafionychStrzalow);
									if(gracz.uzytkownik.plansza.iloscTrafionychStrzalow == 20)
									{
										gracz.wyslijWiadomosc("true", "#KG#");
										komunikatKoniecGry("Rozgrywka dobiegła końca!");
										//gracz.wyslijWiadomosc(gracz.uzytkownik.getNazwaGracza(), "#KS#");
										gracz.uzytkownik.ktoWygral = gracz.uzytkownik.getNazwaGracza();
									}
								}
								if(host != null)
								{
									host.przekazPoleStrzalu(lb_polaGry_PRZECIWNIK[j][i]);
									host.wyslijWiadomosc(""+j+""+i, "#WS#");
									System.out.println("KLICK HOST!");
									if(host.uzytkownik.plansza.iloscTrafionychStrzalow == 20)
									{
										host.wyslijWiadomosc("true", "#KG#");
										komunikatKoniecGry("Rozgrywka dobiegła końca!");
										//host.wyslijWiadomosc(host.uzytkownik.getNazwaGracza(), "#KS#");
										host.uzytkownik.ktoWygral = host.uzytkownik.getNazwaGracza();
									}
								}
							}
						}
						else
						{
							if(gracz != null)
							{
								gracz.przekazPoleStrzalu(lb_polaGry_PRZECIWNIK[j][i]);
								gracz.wyslijWiadomosc(""+j+""+i, "#WS#");
								System.out.println("KLICK GRACZ!");
								//widokGry.lb_wspStrzaluPrzeciwnika.setText(""+gracz.uzytkownik.plansza.iloscTrafionychStrzalow);
								if(gracz.uzytkownik.plansza.iloscTrafionychStrzalow == 20)
								{
									gracz.wyslijWiadomosc("true", "#KG#");
									komunikatKoniecGry("Rozgrywka dobiegła końca!");
									//gracz.wyslijWiadomosc(gracz.uzytkownik.getNazwaGracza(), "#KS#");
									gracz.uzytkownik.ktoWygral = gracz.uzytkownik.getNazwaGracza();
								}
							}
							if(host != null)
							{
								host.przekazPoleStrzalu(lb_polaGry_PRZECIWNIK[j][i]);
								host.wyslijWiadomosc(""+j+""+i, "#WS#");
								System.out.println("KLICK HOST!");
								if(host.uzytkownik.plansza.iloscTrafionychStrzalow == 20)
								{
									host.wyslijWiadomosc("true", "#KG#");
									komunikatKoniecGry("Rozgrywka dobiegła końca!");
									//host.wyslijWiadomosc(host.uzytkownik.getNazwaGracza(), "#KS#");
									host.uzytkownik.ktoWygral = host.uzytkownik.getNazwaGracza();
								}
							}
						}
					}
		}
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
	public void windowOpened(WindowEvent e) {
		if(gracz != null)
		{
			System.out.println("Włączam pobieranie wiadomosci KLIENT!");
			gracz.przekazPoleCzatu(widokGry.ta_CzatGraczy);
			gracz.przekazPoleWiadomosci(widokGry.tf_TrescWiadomosci);
			gracz.przekazPoleNick(widokGry.lb_NazwaPrzeciwnika);
			gracz.przekazPoleAwatar(widokGry.lb_AwatarPrzeciwnika);
			gracz.przekazUzytkownika(widokGry.uzytkownik);
			gracz.przekazPlanszeGracza(lb_polaGry_GRACZ);
			gracz.przekazEtykieteStrzalki(widokGry.lb_strzalkaCzyjaKolej);
			gracz.przekazEtykieteCzyjRuch(widokGry.lb_czyjRuch);
			gracz.przekazObiektWidokOknaGryZdarzenia(this);
			
			widokGry.lb_strzalkaCzyjaKolej.setIcon(img_strzalkaLEWO_r);
			
			gracz.wlaczPobieranieWiadomosci();
			gracz.ustawNickGracza(widokGry.lb_NazwaGracza.getText());
			
			wyslijDaneGracza();
			widokGry.lb_czyjRuch.setForeground(Color.RED);
			widokGry.lb_czyjRuch.setText("RUCH PRZECIWNIKA");
		}
		if(host != null)
		{
			System.out.println("Włączam pobieranie wiadomosci HOST!");
			host.przekazPoleCzatu(widokGry.ta_CzatGraczy);
			host.przekazPoleWiadomosci(widokGry.tf_TrescWiadomosci);
			host.przekazPoleNick(widokGry.lb_NazwaPrzeciwnika);
			host.przekazPoleAwatar(widokGry.lb_AwatarPrzeciwnika);
			host.przekazUzytkownika(widokGry.uzytkownik);
			host.przekazPlanszeGracza(lb_polaGry_GRACZ);
			host.przekazEtykieteStrzalki(widokGry.lb_strzalkaCzyjaKolej);
			host.przekazEtykieteCzyjRuch(widokGry.lb_czyjRuch);
			host.przekazObiektWidokOknaGryZdarzenia(this);
			
			widokGry.lb_strzalkaCzyjaKolej.setIcon(img_strzalkaPRAWO_g);
			
			host.wlaczPobieranieWiadomosci();
			host.ustawNickHosta(widokGry.lb_NazwaGracza.getText());
			
			wyslijDaneGracza();
			widokGry.lb_czyjRuch.setForeground(new Color(0,204,0));
			widokGry.lb_czyjRuch.setText("TWÓJ RUCH");
		}
	}
	
	public void wyslijDaneGracza()
	{
		if(gracz != null)
		{
			String[][] poleGraczaDoWyslania1 = new String[10][10];
			String daneGraczaDoWyslania1 = gracz.uzytkownik.getNazwaGracza()+"|"+gracz.uzytkownik.nrAwatara+"|";
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)
				{
					poleGraczaDoWyslania1[i][j] = widokGry.uzytkownik.plansza.getPlansza()[i][j].getRodzajPola();
					daneGraczaDoWyslania1 = daneGraczaDoWyslania1 + poleGraczaDoWyslania1[i][j];
				}
			gracz.wyslijWiadomosc(daneGraczaDoWyslania1, "#DG#");
		}
		
		if(host != null)
		{
			String[][] poleGraczaDoWyslania2 = new String[10][10];
			String daneGraczaDoWyslania2 = host.uzytkownik.getNazwaGracza()+"|"+host.uzytkownik.nrAwatara+"|";
			for(int i=0;i<10;i++)
				for(int j=0;j<10;j++)
				{
					poleGraczaDoWyslania2[i][j] = widokGry.uzytkownik.plansza.getPlansza()[i][j].getRodzajPola();
					daneGraczaDoWyslania2 = daneGraczaDoWyslania2 + poleGraczaDoWyslania2[i][j];
				}
			
			host.wyslijWiadomosc(daneGraczaDoWyslania2, "#DG#");
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
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == widokGry.mnI_UstawieniaLokalne)
		{
			widokGry.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoGry");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokGry.mnI_Skapituluj || e.getSource() == widokGry.btn_Skapituluj)
		{
			skapituluj();
		}
		if(e.getSource() == widokGry.mnI_ZaproponujRewanz)
		{
			
		}
		if(e.getSource() == widokGry.mnI_Wyjscie)
		{
			wyjscie();
		}
		if(e.getSource() == widokGry.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokGry.mnI_oGrze)
		{
			widokGry.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoGry");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokGry.mnI_oTworcach)
		{
			widokGry.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoGry");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokGry.btn_Przerwa)
		{
			
		}
		if(e.getSource() == widokGry.btn_Wyslij)
			wyslijWiadomosc();
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == widokGry.cb_szablonyWiadomosci)
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
	}
	
	@Override
	public void keyTyped(KeyEvent e) {
	}

	boolean czyALT = false, czy1 = false;
	@Override
	public void keyPressed(KeyEvent e) {
		int klawisz = e.getKeyCode();
		
		if(klawisz == KeyEvent.VK_ENTER)
			wyslijWiadomosc();
		if(klawisz == KeyEvent.VK_F1)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(1).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F2)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(2).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F3)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(3).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F4)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(4).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F5)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(5).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F6)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(6).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F7)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(7).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F8)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(8).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
		if(klawisz == KeyEvent.VK_F9)
		{
			widokGry.cb_szablonyWiadomosci.setSelectedItem(widokGry.cb_szablonyWiadomosci.getItemAt(9).toString());
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			wyslijWiadomosc();
		}
	}

	@Override
	public void keyReleased(KeyEvent e) {
	}
	
	public void wyslijWiadomosc()
	{
		if(!widokGry.tf_TrescWiadomosci.getText().equals(""))
		{
			if(gracz != null)
				gracz.wyslijWiadomosc(widokGry.tf_TrescWiadomosci.getText(), "#WP#");
			if(host != null)
				host.wyslijWiadomosc(widokGry.tf_TrescWiadomosci.getText(), "#WP#");
			czyscPoleWiadomosci();
		}
	}

	public void czyscPoleWiadomosci()
	{
		widokGry.tf_TrescWiadomosci.setText("");
	}
	
	private void wstawSzablonWiadomosci(String _wiadomosc)
	{
		widokGry.tf_TrescWiadomosci.setText(_wiadomosc);
	}
	
	public void komunikatKoniecGry(String _komunikat)
	{
		pobierzWynikiGry();
		JOptionPane.showMessageDialog(widokGry, _komunikat);
        pokazOknoOpisWynikow();
	}

	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokGry, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			if(host != null)
			{
				host.czyWyslacZakonczenie = true;
				host.wyslijWiadomosc(null, null);
				host.rozlacz();
			}
			if(gracz != null)
			{
				gracz.czyWyslacZakonczenie = true;
				gracz.wyslijWiadomosc(null, null);
				gracz.rozlacz();
			}
			System.exit(0);
		} 
	}
	
	public boolean czyGraczSkapitulowal;
	public boolean czyUstalacKtoWygral;
	private void skapituluj()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokGry, "Czy na pewno chcesz skapitulować?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			
			if(host != null)
			{
				czyGraczSkapitulowal = true;
				czyUstalacKtoWygral = false;
				//widokWynikow.lb_ZwyciescaGry.setText(host.getNickGracza());
				host.wyslijWiadomosc("true", "#GS#");
				komunikatKoniecGry("Rozgrywka dobiegła końca! Jeden z graczy skapitulował :(");
			}
			
			if(gracz != null)
			{
				czyGraczSkapitulowal = true;
				czyUstalacKtoWygral = false;
				//widokWynikow.lb_ZwyciescaGry.setText(gracz.getNickHosta());
				gracz.wyslijWiadomosc("true", "#GS#");
				komunikatKoniecGry("Rozgrywka dobiegła końca! Jeden z graczy skapitulował :(");
			}
		} 
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokGry.setVisible(false);
		widokGry.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokGry.setVisible(false);
		widokGry.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokGry.setVisible(false);
		widokGry.widokGlowny.widokOpisTworcow.setVisible(true);
	}
	
	private void pokazOknoOpisWynikow()
	{
		widokGry.setVisible(false);
		widokWynikow.setVisible(true);
	}
	
	public void pobierzWynikiGry()
	{
		JLabel ikonaTrafionePole2 = new JLabel(img_trafionePole2);
		JLabel ikonaNieTrafionePole2 = new JLabel(img_nieTrafionePole2);
		JLabel ikonaStatek2 = new JLabel(img_statek2);
		
		if(widokGry.gracz != null)
		{
			for(int a = 0;a < 10; a++)
				for(int b = 0;b < 10; b++)
				{
					if(lb_polaGry_GRACZ[b][a].getIcon().toString().equals(ikonaTrafionePole2.getIcon().toString()))
						widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[b][a] = "T";
					else if(lb_polaGry_GRACZ[b][a].getIcon().toString().equals(ikonaNieTrafionePole2.getIcon().toString()))
						widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[b][a] = "P";
					else if(lb_polaGry_GRACZ[b][a].getIcon().toString().equals(ikonaStatek2.getIcon().toString()))
						widokGry.gracz.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[b][a] = "S";
				}	
		}
		
		if(widokGry.host != null)
		{
			for(int i = 0;i < 10; i++)
				for(int j = 0;j < 10; j++)
				{
					if(lb_polaGry_GRACZ[j][i].getIcon().toString().equals(ikonaTrafionePole2.getIcon().toString()))
						widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i] = "T";
					else if(lb_polaGry_GRACZ[j][i].getIcon().toString().equals(ikonaNieTrafionePole2.getIcon().toString()))
						widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i] = "P";
					else if(lb_polaGry_GRACZ[j][i].getIcon().toString().equals(ikonaStatek2.getIcon().toString()))
						widokGry.host.uzytkownik.plansza.l_polaPlanszy_GRACZ_STRZALY[j][i] = "S";
				}	
		}
	}
	
	public void przerysujPlanszeGRACZA(JLabel[][] _polaGryGRACZ)
	{
		for(int k=0;k<10;k++)
			for(int l=0;l<10;l++)
			{
				/*if(widokGry.uzytkownik.plansza.l_polaPlanszy[l][k].getRodzajPola().equals("1"))	
					_polaGryGRACZ[l][k].setIcon(img_statek2);
					//_polaGryGRACZ[l][k].setBackground(Color.BLUE);
				//else if(widokGry.uzytkownik.plansza.l_polaPlanszy[l][k].getRodzajPola().equals("X"))
					//_polaGryGRACZ[l][k].setBackground(Color.RED);
				else
					_polaGryGRACZ[l][k].setIcon(img_zwyklePole2);
					//_polaGryGRACZ[l][k].setBackground(new Color(240,240,240));*/
			}
	}
	
	public JLabel lb_polaGry_GRACZ[][];
	public JLabel lb_polaGry_PRZECIWNIK[][];
	public void stworzPolaGry(String _nazwaPola, boolean _czyRysowacSymbole, boolean _czyPrzerysowac, JLabel[][] _pola, int _szerPojPola, int _wysPojPola, int _skok, JPanel _obszarPol, JPanel _obszarLiter, JPanel _obszarCyfr, int _szer, int _wys)
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
				if(!_nazwaPola.equals("pole gracza"))
					_pola[j][i].setBounds(x-1, y, _szerPojPola, _wysPojPola);
				else
					_pola[j][i].setBounds(x+1, y+1, _szerPojPola, _wysPojPola);
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
				
				if(_nazwaPola.equals("pole gracza"))
				{
					if(widokGry.uzytkownik.plansza.l_polaPlanszy_GRACZ[j][i].getRodzajPola().equals("1"))	
						_pola[j][i].setIcon(img_statek2);
					else
						_pola[j][i].setIcon(img_zwyklePole2);
				}	
				
				
				
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
		if(_czyPrzerysowac)
		{
			przerysujPlanszeGRACZA(_pola);
			lb_polaGry_GRACZ = _pola;
		}
		else
		{
			lb_polaGry_PRZECIWNIK = _pola;
		}
	}
}
