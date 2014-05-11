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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Statki.Gracz;
import Statki.Host;
import Widoki_GUI.Widok_Wynikow;

public class Widok_Wynikow_Zdarzenia implements ActionListener, WindowListener, MouseListener, ItemListener{
	
	private Widok_Wynikow widokWynikow;
	
	public Host host;
	public Gracz gracz;
	
	public Widok_Wynikow_Zdarzenia(Widok_Wynikow _widokWynikow, Host _host, Gracz _gracz) {
		widokWynikow = _widokWynikow;
		gracz = _gracz;
		host = _host;
		
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

	@Override
	public void windowOpened(WindowEvent e) {
		if(gracz != null)
		{
			System.out.println("Włączam pobieranie wiadomosci KLIENT!");
			System.out.println("PolaczenieOK: "+gracz.polaczenieOK);
			gracz.przekazPoleCzatu(widokWynikow.ta_Czat);
			gracz.przekazPoleWiadomosci(widokWynikow.tf_Wiadomosc);
			gracz.wlaczPobieranieWiadomosci();
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
		if(e.getSource() == widokWynikow.mnI_ZaproponujRewanz)
		{
			
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
		}
	}
	
	private void wstawSzablonWiadomosci(String _wiadomosc)
	{
		widokWynikow.tf_Wiadomosc.setText(_wiadomosc);
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
	
	public JLabel lb_polaGry_GRACZ[][];
	public JLabel lb_polaGry_PRZECIWNIK[][];
	public void stworzPolaGry(JLabel[][] _pola, int _szerPojPola, int _wysPojPola, int _skok, JPanel _obszarPol, JPanel _obszarLiter, JPanel _obszarCyfr, int _szer, int _wys)
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
				_pola[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				_pola[j][i].setHorizontalAlignment(SwingConstants.CENTER);
				_pola[j][i].setVerticalAlignment(SwingConstants.CENTER);
				//_pola[j][i].setBorder(BorderFactory.createLineBorder(Color.RED, 1));
				_pola[j][i].addMouseListener(this);
				_obszarPol.add(_pola[j][i]);
				
				
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
	}
}
