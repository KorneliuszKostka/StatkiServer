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

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Widoki_GUI.Widok_Gry;

public class Widok_Gry_Zdarzenia implements ActionListener, WindowListener, MouseListener, ItemListener{
	private Widok_Gry widokGry;
	
	public Widok_Gry_Zdarzenia(Widok_Gry _widokGry) {
		widokGry = _widokGry;
		
		widokGry.addWindowListener(this);
		
		widokGry.btn_Przerwa.addActionListener(this);
		widokGry.btn_Skapituluj.addActionListener(this);
		widokGry.btn_Wyslij.addMouseListener(this);
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
	public void windowOpened(WindowEvent e) {
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
			
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
		if(e.getSource() == widokGry.cb_szablonyWiadomosci)
			wstawSzablonWiadomosci(widokGry.cb_szablonyWiadomosci.getSelectedItem().toString());
			
		
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
		widokGry.widokGlowny.widokDolacz.widokRozmiesc.widokGry.widokWynikow.setVisible(true);
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
