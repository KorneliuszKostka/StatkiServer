package Widoki_Zdarzenia;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.JOptionPane;

import Widoki_GUI.Widok_Opis_tworcow;

public class Widok_OpisTworcow_Zdarzenia implements ActionListener, WindowListener{
	
	private Widok_Opis_tworcow widokOpisTworcow;
	
	public Widok_OpisTworcow_Zdarzenia(Widok_Opis_tworcow _widokOpisTworcow) {
		widokOpisTworcow = _widokOpisTworcow;
		
		widokOpisTworcow.btn_WrocDoWyboruKategorii.addActionListener(this);
		widokOpisTworcow.mnI_InstrukcjaObslugi.addActionListener(this);
		widokOpisTworcow.mnI_oTworcach.addActionListener(this);
		widokOpisTworcow.mnI_UstawieniaLokalne.addActionListener(this);
		widokOpisTworcow.mnI_Wyjscie.addActionListener(this);
		
		widokOpisTworcow.addWindowListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
	}

	@Override
	public void windowClosing(WindowEvent e) {
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
		if(e.getSource() == widokOpisTworcow.btn_WrocDoWyboruKategorii)
			powrotDoOknaGlownego();
		if(e.getSource() == widokOpisTworcow.mnI_Wyjscie)
			wyjscie();
		if(e.getSource() == widokOpisTworcow.mnI_InstrukcjaObslugi)
		{
			
		}
		if(e.getSource() == widokOpisTworcow.mnI_UstawieniaLokalne)
		{
			widokOpisTworcow.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoOpisAplikacji");
			pokazOknoUstawienLokalnych();
		}
		
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokOpisTworcow, "Czy na pewno chcesz wyjść z gry?",
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
		widokOpisTworcow.setVisible(false);
		if(oknoMacierzyste.equals("OknoGlowne"))
			widokOpisTworcow.widokGlowny.setVisible(true);
		if(oknoMacierzyste.equals("OknoDolacz"))
			widokOpisTworcow.widokGlowny.widokDolacz.setVisible(true);
		if(oknoMacierzyste.equals("OknoUtworz"))
			widokOpisTworcow.widokGlowny.widokUtworz.setVisible(true);
		if(oknoMacierzyste.equals("OknoOpisAplikacji"))
			widokOpisTworcow.widokGlowny.widokOpisAplikacji.setVisible(true);
		if(oknoMacierzyste.equals("OknoUstawieniaLokalne"))
			widokOpisTworcow.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokOpisTworcow.setVisible(false);
		widokOpisTworcow.widokGlowny.widokUstawien.setVisible(true);
	}

}
