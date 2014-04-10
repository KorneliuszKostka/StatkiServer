package Widoki_Zdarzenia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import Widoki_GUI.Widok_Ustawien;

public class Widok_Ustawien_Zdarzenia implements ActionListener, WindowListener{
	
	private Widok_Ustawien widokUstawien;
	
	public Widok_Ustawien_Zdarzenia(Widok_Ustawien _widokUstawien) {
		widokUstawien = _widokUstawien;
		
		widokUstawien.addWindowListener(this);
		
		widokUstawien.btn_WrocDoWyboruKategorii.addActionListener(this);
		widokUstawien.mnI_InstrukcjaObslugi.addActionListener(this);
		widokUstawien.mnI_oGrze.addActionListener(this);
		widokUstawien.mnI_oTworcach.addActionListener(this);
		widokUstawien.mnI_Wyjscie.addActionListener(this);
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
		if(e.getSource() == widokUstawien.btn_WrocDoWyboruKategorii)
			powrotDoOknaGlownego();
		if(e.getSource() == widokUstawien.mnI_oGrze)
		{
			widokUstawien.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoUstawieniaLokalne");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokUstawien.mnI_oTworcach)
		{
			widokUstawien.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoUstawieniaLokalne");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokUstawien.mnI_Wyjscie)
			wyjscie();
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokUstawien, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	String oknoMacierzyste;
	public void setOknoMacierzyste(String _oknoMacierzyste)
	{
		oknoMacierzyste = _oknoMacierzyste;
	}
	
	private void powrotDoOknaGlownego()
	{
		widokUstawien.setVisible(false);
		if(oknoMacierzyste.equals("OknoGlowne"))
			widokUstawien.widokGlowny.setVisible(true);
		if(oknoMacierzyste.equals("OknoDolacz"))
			widokUstawien.widokGlowny.widokDolacz.setVisible(true);
		if(oknoMacierzyste.equals("OknoUtworz"))
			widokUstawien.widokGlowny.widokUtworz.setVisible(true);
		if(oknoMacierzyste.equals("OknoOpisAplikacji"))
			widokUstawien.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokUstawien.setVisible(false);
		widokUstawien.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokUstawien.setVisible(false);
		widokUstawien.widokGlowny.widokOpisTworcow.setVisible(true);
	}
}
