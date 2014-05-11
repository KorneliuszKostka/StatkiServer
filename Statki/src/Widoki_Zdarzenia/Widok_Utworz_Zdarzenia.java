package Widoki_Zdarzenia;

import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;
import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;

import javax.swing.JOptionPane;
import javax.swing.text.MaskFormatter;

import Statki.Host;
import Widoki_GUI.Widok_Utworz;

public class Widok_Utworz_Zdarzenia implements ActionListener, WindowListener{
	
	private Widok_Utworz widokUtworz;
	public Host host;
	
	public Widok_Utworz_Zdarzenia(Widok_Utworz _widokUtworz, Host _host) {
		widokUtworz = _widokUtworz;
		host = _host;
		
		widokUtworz.addWindowListener(this);
		
		widokUtworz.btn_RozpocznijRozmieszczanieStatkw.addActionListener(this);
		widokUtworz.btn_WrocDoWyboruKategorii.addActionListener(this);
		widokUtworz.mnI_InstrukcjaObslugi.addActionListener(this);
		widokUtworz.mnI_oGrze.addActionListener(this);
		widokUtworz.mnI_oTworcach.addActionListener(this);
		widokUtworz.mnI_UstawieniaLokalne.addActionListener(this);
		widokUtworz.mnI_Wyjscie.addActionListener(this);
		widokUtworz.btn_utworzSerwer.addActionListener(this);
	}

	@Override
	public void windowOpened(WindowEvent e) {
		//System.out.println("OTWARTO OKNO!!!");
		//utworzSerwer();
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
		widokUtworz.tf_naglowek2_TwojeIP.setText("Twoje IP: " + pobierzIpHosta());
		widokUtworz.tf_naglowek2_TwojeIP.setForeground(Color.RED);
	}

	@Override
	public void windowDeactivated(WindowEvent e) {
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		if(e.getSource() == widokUtworz.btn_WrocDoWyboruKategorii)
			powrotDoOknaGlownego();
		if(e.getSource() == widokUtworz.mnI_Wyjscie)
			wyjscie();
		if(e.getSource() ==  widokUtworz.mnI_oGrze)
		{
			widokUtworz.widokGlowny.widokOpisAplikacji.widokOpisAplikacjiZdarzenia.setOknoMacierzyste("OknoUtworz");
			pokazOknoOpisuAplikacji();
		}
		if(e.getSource() == widokUtworz.mnI_UstawieniaLokalne)
		{
			widokUtworz.widokGlowny.widokUstawien.widokUstawienZdarzenia.setOknoMacierzyste("OknoUtworz");
			pokazOknoUstawienLokalnych();
		}
		if(e.getSource() == widokUtworz.mnI_oTworcach)
		{
			widokUtworz.widokGlowny.widokOpisTworcow.widokOpisTworcowZdarzenia.setOknoMacierzyste("OknoUtworz");
			pokazOknoOpisTworcow();
		}
		if(e.getSource() == widokUtworz.btn_RozpocznijRozmieszczanieStatkw)
		{
			pokazOknoRozmiesc();
		}
		if(e.getSource() == widokUtworz.btn_utworzSerwer)
		{
			widokUtworz.btn_utworzSerwer.setEnabled(false);
			utworzSerwer();
		}
	}
	
	public void utworzSerwer()
	{
		host.stworzHost(widokUtworz.lb_tekst_OczekiwanieNaPrzeciwnika);
		if(host.polaczenieOK)
		{
			widokUtworz.btn_RozpocznijRozmieszczanieStatkw.setEnabled(true);
			widokUtworz.lb_tekst_OczekiwanieNaPrzeciwnika.setVisible(true);
		}
	}
	
	public void powrotDoOknaGlownego()
	{
		widokUtworz.setVisible(false);
		widokUtworz.widokGlowny.setVisible(true);
		widokUtworz.widokGlowny.setAlwaysOnTop(true);
	}

	public String pobierzIpHosta()
	{
		String adresIP = "";
		try {
			InetAddress adres = InetAddress.getLocalHost();
			adresIP = adres.getHostAddress();
		} catch (UnknownHostException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return adresIP;
	}
	
	public MaskFormatter stworzFormat(String s) {
	    MaskFormatter formatter = null;
	    try {
	        formatter = new MaskFormatter(s);
	        formatter.setPlaceholderCharacter(' ');
	       
	    } catch (java.text.ParseException exc) {
	        System.err.println("formatter is bad: " + exc.getMessage());
	        System.exit(-1);
	    }
	    return formatter;
	}
	
	private void wyjscie()
	{
		Object[] opcje = {"Tak","Nie"};
		int odpowiedz = JOptionPane.showOptionDialog(widokUtworz, "Czy na pewno chcesz wyjść z gry?",
		"Statki", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE,
		null, opcje, opcje[0]); 
		
		if (odpowiedz == JOptionPane.YES_OPTION) {
			System.exit(0);
		} 
	}
	
	private void pokazOknoOpisuAplikacji()
	{
		widokUtworz.setVisible(false);
		widokUtworz.widokGlowny.widokOpisAplikacji.setVisible(true);
	}
	
	private void pokazOknoUstawienLokalnych()
	{
		widokUtworz.setVisible(false);
		widokUtworz.widokGlowny.widokUstawien.setVisible(true);
	}
	
	private void pokazOknoOpisTworcow()
	{
		widokUtworz.setVisible(false);
		widokUtworz.widokGlowny.widokOpisTworcow.setVisible(true);
	}
	
	private void pokazOknoRozmiesc()
	{
		widokUtworz.uzytkownik.wybierzNazwe(widokUtworz.ftf_NazwaGracza.getText().trim());
		widokUtworz.uzytkownik.wybierzAwatar(widokUtworz.cb_awatarGracza.getSelectedIndex());
		widokUtworz.host.ustawNickHosta(widokUtworz.uzytkownik.getNazwaGracza());
		widokUtworz.setVisible(false);
		widokUtworz.widokRozmiesc.setVisible(true);
	}
}
