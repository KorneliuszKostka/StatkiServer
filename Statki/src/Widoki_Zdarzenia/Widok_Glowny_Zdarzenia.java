package Widoki_Zdarzenia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JButton;
import javax.swing.JComponent;
import javax.swing.JOptionPane;

import Widoki_GUI.Widok_Glowny;

public class Widok_Glowny_Zdarzenia implements ActionListener, WindowListener, MouseListener{
	
	private Widok_Glowny widokGlowny;
	
	
	public Widok_Glowny_Zdarzenia(Widok_Glowny _widokGlowny) {
		widokGlowny = _widokGlowny;
		
		widokGlowny.addWindowListener(this);
		
		widokGlowny.btn_DolaczDoGry.addActionListener(this);
		widokGlowny.btn_UtworzSerwer.addActionListener(this);
		widokGlowny.mnI_Skapituluj.addActionListener(this);
		widokGlowny.mnI_ZaproponujRewanz.addActionListener(this);
		widokGlowny.mnI_UstawieniaLokalne.addActionListener(this);
		widokGlowny.mnI_Wyjscie.addActionListener(this);
		widokGlowny.mnI_InstrukcjaObslugi.addActionListener(this);
		widokGlowny.mnI_oGrze.addActionListener(this);
		widokGlowny.mnI_oTworcach.addActionListener(this);
		widokGlowny.lb_oGrze.addMouseListener(this);
		widokGlowny.lb_oTworcach.addMouseListener(this);
		widokGlowny.lb_UstawieniaLokalne.addMouseListener(this);
		widokGlowny.lb_Wyjscie.addMouseListener(this);
	}
	
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == widokGlowny.btn_DolaczDoGry)
			pokazOknoDolacz();
		if(e.getSource() == widokGlowny.btn_UtworzSerwer)
			 pokazOknoUtworz();
		if(e.getSource() == widokGlowny.mnI_UstawieniaLokalne)
		{
			widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoGlowne");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokGlowny.mnI_Skapituluj)
		{
			
		}
		if(e.getSource() == widokGlowny.mnI_ZaproponujRewanz)
		{
			
		}
		if(e.getSource() == widokGlowny.mnI_Wyjscie)
		{
			wyjscie();
		}
		if(e.getSource() == widokGlowny.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokGlowny.mnI_oGrze)
		{
			widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoGlowne");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokGlowny.mnI_oTworcach)
		{
			widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoGlowne");
			pokazOknoOpisTworcow();
		}
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
	public void mouseClicked(MouseEvent e) {
		if(e.getSource() == widokGlowny.lb_oGrze)
		{
			widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoGlowne");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokGlowny.lb_oTworcach)
		{
			widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoGlowne");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokGlowny.lb_UstawieniaLokalne)
		{
			widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoGlowne");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokGlowny.lb_Wyjscie)
		{
			wyjscie();
		}
		
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokGlowny, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	private void pokazOknoDolacz()
	{
		widokGlowny.setVisible(false);
		widokGlowny.widokDolacz.setVisible(true);
	}
	
	private void pokazOknoUtworz()
	{
		widokGlowny.setVisible(false);
		widokGlowny.widokUtworz.setVisible(true);
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokGlowny.setVisible(false);
		widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokGlowny.setVisible(false);
		widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokGlowny.setVisible(false);
		widokGlowny.widokOpisTworcow.setVisible(true);
	}
}
