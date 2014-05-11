package Widoki_Zdarzenia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.UnknownHostException;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;
import javax.swing.Timer;

import Statki.Gracz;
import Statki.Host;
import Widoki_GUI.Widok_Gry;
import Widoki_GUI.Widok_Wynikow;

public class Widok_Gry_Zdarzenia extends Thread implements ActionListener, WindowListener, MouseListener, ItemListener{
	private Widok_Gry widokGry;
	public Widok_Wynikow widokWynikow;
	
	public Host host;
	public Gracz gracz;
	
	public Widok_Gry_Zdarzenia(Widok_Gry _widokGry, Host _host, Gracz _gracz) {
		widokGry = _widokGry;
		gracz = _gracz;
		host = _host;
		
		widokWynikow = new Widok_Wynikow(widokGry, host, gracz);
		
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
		widokGry.cb_RodzajStrzalu.addItemListener(this);
		
		
		
	}
	
	/*boolean czatAktwyny;
	public void run()
	{
		while(czatAktwyny)
		{
			pobierzWiadomosc();
		}
	}*/

	@Override
	public void mouseClicked(MouseEvent e) {
		//widokGry.lb_wspStrzaluPrzeciwnika.setText(lb_polaGry_PRZECIWNIK[0][0].getText());
		for(int i = 0;i < 10; i++)
			for(int j = 0;j < 10; j++)
				if(e.getSource() == lb_polaGry_PRZECIWNIK[j][i])
				{
					if(gracz != null)
						gracz.wyslijWiadomosc(""+j+""+i, "#WS#");
					if(host != null)
						host.wyslijWiadomosc(""+j+""+i, "#WS#");
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
			gracz.przekazPoleWspPrzeciwnika(widokGry.lb_wspStrzaluPrzeciwnika);
			gracz.przekazUzytkownika(widokGry.uzytkownik);
			gracz.ustawNickGracza(widokGry.lb_NazwaGracza.getText());
			gracz.wlaczPobieranieWiadomosci();
			gracz.wyslijWiadomosc(widokGry.lb_NazwaGracza.getText()+"|"+widokGry.uzytkownik.nrAwatara, "#DG#");
		}
		if(host != null)
		{
			System.out.println("Włączam pobieranie wiadomosci HOST!");
			host.przekazPoleCzatu(widokGry.ta_CzatGraczy);
			host.przekazPoleWiadomosci(widokGry.tf_TrescWiadomosci);
			host.przekazPoleNick(widokGry.lb_NazwaPrzeciwnika);
			host.przekazPoleAwatar(widokGry.lb_AwatarPrzeciwnika);
			host.przekazPoleWspPrzeciwnika(widokGry.lb_wspStrzaluPrzeciwnika);
			host.przekazUzytkownika(widokGry.uzytkownik);
			host.ustawNickHosta(widokGry.lb_NazwaGracza.getText());
			host.wlaczPobieranieWiadomosci();
			host.wyslijWiadomosc(widokGry.lb_NazwaGracza.getText()+"|"+widokGry.uzytkownik.nrAwatara, "#DG#");
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
		{
			if(gracz != null)
				gracz.wyslijWiadomosc(widokGry.tf_TrescWiadomosci.getText(), "#WP#");
			if(host != null)
				host.wyslijWiadomosc(widokGry.tf_TrescWiadomosci.getText(), "#WP#");
			czyscPoleWiadomosci();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == widokGry.cb_szablonyWiadomosci)
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
	}
	
	public void czyscPoleWiadomosci()
	{
		widokGry.tf_TrescWiadomosci.setText("");
	}
	
	private void wstawSzablonWiadomosci(String _wiadomosc)
	{
		widokGry.tf_TrescWiadomosci.setText(_wiadomosc);
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
	
	private void skapituluj()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokGry, "Czy na pewno chcesz skapitulować?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			pokazOknoOpisWynikow();
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
		//widokGry.widokGlowny.widokDolacz.widokRozmiesc.widokGry.widokWynikow.setVisible(true);
		widokWynikow.setVisible(true);
	}
	
	public void przerysujPlanszeGRACZA(JLabel[][] _polaGryGRACZ)
	{
		for(int k=0;k<10;k++)
			for(int l=0;l<10;l++)
			{
				if(widokGry.uzytkownik.plansza.l_polaPlanszy[l][k].getRodzajPola().equals("1"))	
					_polaGryGRACZ[l][k].setBackground(Color.BLUE);
				//else if(widokGry.uzytkownik.plansza.l_polaPlanszy[l][k].getRodzajPola().equals("X"))
					//_polaGryGRACZ[l][k].setBackground(Color.RED);
				else
					_polaGryGRACZ[l][k].setBackground(new Color(240,240,240));
			}
	}
	
	public JLabel lb_polaGry_GRACZ[][];
	public JLabel lb_polaGry_PRZECIWNIK[][];
	public void stworzPolaGry(boolean _czyPrzerysowac, JLabel[][] _pola, int _szerPojPola, int _wysPojPola, int _skok, JPanel _obszarPol, JPanel _obszarLiter, JPanel _obszarCyfr, int _szer, int _wys)
	{
		JLabel lb_litery[] = new JLabel[10];
		JLabel lb_cyfry[] = new JLabel[10];
		_pola = new JLabel[10][10];
		String litery[] = {"A","B","C","D","E","F","G","H","I","J"}; 
		
		int x = _skok*(-1), y = _skok*(-1);
		for(int i = 0;i < 10; i++)
		{
			x = _skok*(-1);
			y+=_skok;
			for(int j = 0;j < 10; j++)
			{
				x+=_skok;
				
				_pola[j][i] = new JLabel();
				_pola[j][i].setBounds(x, y, _szerPojPola, _wysPojPola);
				_pola[j][i].setText(String.valueOf(j)+String.valueOf(i));
				_pola[j][i].setOpaque(true);
				_pola[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				_pola[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				_pola[j][i].setVerticalAlignment(SwingConstants.CENTER);
				//_pola[j][i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				_pola[j][i].addMouseListener(this);
				_obszarPol.add(_pola[j][i]);
				
				/*if(widokGry.uzytkownik.plansza.l_polaPlanszy[j][i].getRodzajPola().equals("1"))	
					_pola[j][i].setBackground(Color.BLUE);
				else if(widokGry.uzytkownik.plansza.l_polaPlanszy[j][i].getRodzajPola().equals("X"))
					_pola[j][i].setBackground(Color.RED);
				else
					_pola[j][i].setBackground(new Color(240,240,240));*/
				
				
				lb_cyfry[i] = new JLabel();
				lb_cyfry[i].setBounds(x+3, 0, _szer, _wys);
				lb_cyfry[i].setFont(new Font("Verdana", Font.BOLD, 11));
				lb_cyfry[i].setText(String.valueOf(j+1));
				lb_cyfry[i].setHorizontalTextPosition(SwingConstants.CENTER);
				//lb_cyfry[i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				_obszarCyfr.add(lb_cyfry[i]);
			}
			lb_litery[i] = new JLabel();
			lb_litery[i].setBounds(0, y+3, _szer, _wys);
			lb_litery[i].setFont(new Font("Verdana", Font.BOLD, 11));
			lb_litery[i].setText(litery[i]);
			lb_litery[i].setHorizontalAlignment(SwingConstants.RIGHT);
			//lb_litery[i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
			_obszarLiter.add(lb_litery[i]);
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
