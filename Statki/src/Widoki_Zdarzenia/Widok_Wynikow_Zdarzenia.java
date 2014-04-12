package Widoki_Zdarzenia;

import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.BorderFactory;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Widoki_GUI.Widok_Wynikow;

public class Widok_Wynikow_Zdarzenia implements ActionListener, WindowListener, MouseListener{
	
	private Widok_Wynikow widokWynikow;
	
	
	public Widok_Wynikow_Zdarzenia(Widok_Wynikow _widokWynikow) {
		widokWynikow = _widokWynikow;
		
		widokWynikow.addWindowListener(this);
		
		widokWynikow.btn_ZaproponujRewanz.addActionListener(this);
		widokWynikow.btn_OpuscGre.addActionListener(this);
		widokWynikow.mnI_ZaproponujRewanz.addActionListener(this);
		widokWynikow.mnI_UstawieniaLokalne.addActionListener(this);
		widokWynikow.mnI_Wyjscie.addActionListener(this);
		widokWynikow.mnI_InstrukcjaObslugi.addActionListener(this);
		widokWynikow.mnI_oGrze.addActionListener(this);
		widokWynikow.mnI_oTworcach.addActionListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
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
