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
import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.SwingConstants;

import Widoki_GUI.Widok_Rozmiesc;

public class Widok_Rozmiesc_Zdarzenia implements ActionListener, WindowListener, ItemListener, MouseListener{
	private Widok_Rozmiesc widokRozmiesc;
	
	public Widok_Rozmiesc_Zdarzenia(Widok_Rozmiesc _widokRozmiesc) {
		widokRozmiesc = _widokRozmiesc;
		
		widokRozmiesc.addWindowListener(this);
		
		widokRozmiesc.btn_RozmiescLosowo.addActionListener(this);
		widokRozmiesc.btn_RozpocznijRozgrywke.addActionListener(this);
		widokRozmiesc.btn_WyczyscPlansze.addActionListener(this);
		widokRozmiesc.mnI_InstrukcjaObslugi.addActionListener(this);
		widokRozmiesc.mnI_oGrze.addActionListener(this);
		widokRozmiesc.mnI_oTworcach.addActionListener(this);
		widokRozmiesc.mnI_UstawieniaLokalne.addActionListener(this);
		widokRozmiesc.mnI_Wyjscie.addActionListener(this);
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
		if(e.getSource() == widokRozmiesc.mnI_UstawieniaLokalne)
		{
			widokRozmiesc.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoRozmiesc");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokRozmiesc.mnI_Wyjscie)
		{
			wyjscie();
		}
		if(e.getSource() == widokRozmiesc.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokRozmiesc.mnI_oGrze)
		{
			widokRozmiesc.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoRozmiesc");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokRozmiesc.mnI_oTworcach)
		{
			widokRozmiesc.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoRozmiesc");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokRozmiesc.btn_RozpocznijRozgrywke)
		{
			pokazOknoOpisGry();
		}
	}

	@Override
	public void itemStateChanged(ItemEvent e) {
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
		for(int i = 0;i < 10; i++)
			for(int j = 0;j < 10; j++)
				if(e.getSource() == lb_polaGry[j][i])
					zaznaczMaszty(true, j, i, widokRozmiesc.cb_RodzajStatku, widokRozmiesc.cb_Rozmieszczenie);
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		for(int i = 0;i < 10; i++)
			for(int j = 0;j < 10; j++)
				if(e.getSource() == lb_polaGry[j][i])
					zaznaczMaszty(false ,j, i, widokRozmiesc.cb_RodzajStatku, widokRozmiesc.cb_Rozmieszczenie);
					
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokRozmiesc, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGlowny.widokOpisTworcow.setVisible(true);
	}
	
	private void pokazOknoOpisGry()
	{
		widokRozmiesc.setVisible(false);
		widokRozmiesc.widokGry.setVisible(true);
	}
	
	JLabel lb_polaGry[][];
	public void stworzPolaGry(JPanel _obszarPol, JPanel _obszarLiter, JPanel _obszarCyfr)
	{
		JLabel lb_litery[] = new JLabel[10];
		JLabel lb_cyfry[] = new JLabel[10];
		lb_polaGry = new JLabel[10][10];
		String litery[] = {"A","B","C","D","E","F","G","H","I","J"}; 
		
		int x = -20, y = -20;
		for(int i = 0;i < 10; i++)
		{
			x = -20;
			y+=20;
			for(int j = 0;j < 10; j++)
			{
				x+=20;
				lb_polaGry[j][i] = new JLabel();
				lb_polaGry[j][i].setBounds(x, y, 20, 20);
				lb_polaGry[j][i].setText(String.valueOf(j)+String.valueOf(i));
				lb_polaGry[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				lb_polaGry[j][i].addMouseListener(this);
				_obszarPol.add(lb_polaGry[j][i]);
				
				lb_cyfry[i] = new JLabel();
				lb_cyfry[i].setBounds(x, 0, 20, 20);
				lb_cyfry[i].setFont(new Font("Verdana", Font.BOLD, 12));
				lb_cyfry[i].setText(String.valueOf(j+1));
				lb_cyfry[i].setHorizontalTextPosition(SwingConstants.CENTER);
				_obszarCyfr.add(lb_cyfry[i]);
			}
			lb_litery[i] = new JLabel();
			lb_litery[i].setBounds(0, y, 20, 20);
			lb_litery[i].setFont(new Font("Verdana", Font.BOLD, 12));
			lb_litery[i].setText(litery[i]);
			lb_litery[i].setHorizontalTextPosition(SwingConstants.CENTER);
			_obszarLiter.add(lb_litery[i]);
		}
	}

	
	public void zaznaczMaszty(boolean _zdarzenie, int _pozXpoczatekStatku, int _pozYpoczatekStatku, JComboBox _rodzajStatku, JComboBox _rozmieszczenie)
	{
		String rodzajStatku = _rodzajStatku.getSelectedItem().toString();
		String rozmieszczenie = _rozmieszczenie.getSelectedItem().toString();
		int x = _pozXpoczatekStatku;
		int y = _pozYpoczatekStatku;
		
		if(rozmieszczenie.equals("Pionowe"))
		{
			if(rodzajStatku.equals("Jednomasztowiec"))
			{
				if(_zdarzenie)
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				else 
				{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				}
			}
			if(rodzajStatku.equals("Dwumasztowiec"))
			{
				if(_pozYpoczatekStatku <= 8)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("Trójmasztowiec"))
			{
				if(_pozYpoczatekStatku <= 7)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("Czteromasztowiec"))
			{
				if(_pozYpoczatekStatku <= 6)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x][y+3].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+1].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+2].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x][y+3].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
		}
		if(rozmieszczenie.equals("Poziome"))
		{
			if(rodzajStatku.equals("Jednomasztowiec"))
			{
				if(_zdarzenie)
				lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
				else 
				{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
				}
			}
			if(rodzajStatku.equals("Dwumasztowiec"))
			{
				if(_pozXpoczatekStatku <= 8)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("Trójmasztowiec"))
			{
				if(_pozXpoczatekStatku <= 7)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
			if(rodzajStatku.equals("Czteromasztowiec"))
			{
				if(_pozXpoczatekStatku <= 6)
				{
					if(_zdarzenie)
					{
					lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					lb_polaGry[x+3][y].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
					}
					else 
					{
						lb_polaGry[x][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+1][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+2][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
						lb_polaGry[x+3][y].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
					}
				}
			}
		}
		
		//lb_polaGry[j][i].setBorder(BorderFactory.createLineBorder(Color.GREEN, 3));
		
		//lb_polaGry[j][i].setBorder(BorderFactory.createLineBorder(new Color(240, 240, 240), 3));
	}


}
