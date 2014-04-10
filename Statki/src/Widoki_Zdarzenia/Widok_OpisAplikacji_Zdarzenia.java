package Widoki_Zdarzenia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JFrame;
import javax.swing.JOptionPane;

import Widoki_GUI.Widok_Opis_aplikacji;

public class Widok_OpisAplikacji_Zdarzenia implements ActionListener, WindowListener{
	
	private Widok_Opis_aplikacji widokOpisAplikacji;
	
	public Widok_OpisAplikacji_Zdarzenia(Widok_Opis_aplikacji _widokOpisAplikacji) {
		widokOpisAplikacji = _widokOpisAplikacji;
		
		widokOpisAplikacji.btn_WrocDoWyboruKategorii.addActionListener(this);
		widokOpisAplikacji.mnI_InstrukcjaObslugi.addActionListener(this);
		widokOpisAplikacji.mnI_oTworcach.addActionListener(this);
		widokOpisAplikacji.mnI_UstawieniaLokalne.addActionListener(this);
		widokOpisAplikacji.mnI_Wyjscie.addActionListener(this);
		
		widokOpisAplikacji.addWindowListener(this);
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
		if(e.getSource() == widokOpisAplikacji.btn_WrocDoWyboruKategorii)
			powrotDoOknaGlownego();
		if(e.getSource() == widokOpisAplikacji.mnI_Wyjscie)
			wyjscie();
		if(e.getSource() == widokOpisAplikacji.mnI_oTworcach)
		{
			widokOpisAplikacji.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoOpisAplikacji");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokOpisAplikacji.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokOpisAplikacji.mnI_UstawieniaLokalne)
		{
			widokOpisAplikacji.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoOpisAplikacji");
			pokazOknoUstawienLokalnych();
		}
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokOpisAplikacji, "Czy na pewno chcesz wyjść z gry?",
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
		widokOpisAplikacji.setVisible(false);
		if(oknoMacierzyste.equals("OknoGlowne"))
			widokOpisAplikacji.widokGlowny.setVisible(true);
		if(oknoMacierzyste.equals("OknoDolacz"))
			widokOpisAplikacji.widokGlowny.widokDolacz.setVisible(true);
		if(oknoMacierzyste.equals("OknoUtworz"))
			widokOpisAplikacji.widokGlowny.widokUtworz.setVisible(true);
		if(oknoMacierzyste.equals("OknoUstawieniaLokalne"))
			widokOpisAplikacji.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokOpisAplikacji.setVisible(false);
		widokOpisAplikacji.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokOpisAplikacji.setVisible(false);
		widokOpisAplikacji.widokGlowny.widokOpisTworcow.setVisible(true);
	}
}
