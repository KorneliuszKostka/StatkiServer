package Widoki_GUI;

import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;

import Statki.Gracz;
import Statki.Host;
import Statki.Uzytkownik;
import Widoki_Zdarzenia.Widok_Rozmiesc_Zdarzenia;

public class Widok_Rozmiesc extends JFrame {

	public Widok_Rozmiesc_Zdarzenia widokRozmiescZdarzenia;
	public Widok_Glowny widokGlowny;
	public Widok_Gry widokGry;
	public Host host;
	public Gracz gracz;
	public Uzytkownik uzytkownik;
	
	private JPanel contentPane;
	
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_InstrukcjaObslugi;
	
	public JComboBox cb_RodzajStatku;
	public JComboBox cb_Rozmieszczenie;
	
	public JButton btn_RozmiescLosowo;
	public JButton btn_WyczyscPlansze;
	public JButton btn_RozpocznijRozgrywke;
	
	public JPanel p_polaGry;
	
	private ImageIcon img_tloPlanszy = new ImageIcon(getClass().getResource("/plansza/plansza220x220/plansza.png"));

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Rozmiesc frame = new Widok_Rozmiesc(null, null, null, null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public String typUsera;
	public JLabel lblRozmieszczenie;
	public JLabel lblStatek;
	public Widok_Rozmiesc(Widok_Glowny _widokGlowny, Host _host, Gracz _gracz, Uzytkownik _uzytkownik) {
		
		widokGlowny = _widokGlowny;
		if(_host != null)
			host = _host;
		if(_gracz != null)
			gracz = _gracz;
		uzytkownik = _uzytkownik;
	
		setTitle("Statki v.1.0 Beta | SkyGames - rozmieszczenie statków");
		setResizable(false);
		/*try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }*/
		
		try {UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
		catch(Exception e) {}

		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(520, 430);
		Toolkit zestaw = Toolkit.getDefaultToolkit(); //narzedzie dzieki ktoremu mozna pobrac rozdzielczosc
		Dimension wymiary = zestaw.getScreenSize();
		int wysokosc = wymiary.height;
		int szerokosc = wymiary.width;
		setLocation((szerokosc/2)-(this.getSize().width/2),(wysokosc/2)-(this.getSize().height/2));
		
		JMenuBar menuBar = new JMenuBar();
		setJMenuBar(menuBar);
		
		JMenu m_Gra = new JMenu("Gra");
		menuBar.add(m_Gra);
		
		mnI_Skapituluj = new JMenuItem("Skapituluj");
		mnI_Skapituluj.setEnabled(false);
		m_Gra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		mnI_ZaproponujRewanz.setEnabled(false);
		m_Gra.add(mnI_ZaproponujRewanz);
		
		JSeparator separator_1 = new JSeparator();
		m_Gra.add(separator_1);
		
		mnI_UstawieniaLokalne = new JMenuItem("Ustawienia lokalne");
		m_Gra.add(mnI_UstawieniaLokalne);
		
		JSeparator separator_2 = new JSeparator();
		m_Gra.add(separator_2);
		
		mnI_Wyjscie = new JMenuItem("Wyjście");
		m_Gra.add(mnI_Wyjscie);
		
		JMenu m_Pomoc = new JMenu("Pomoc");
		menuBar.add(m_Pomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		m_Pomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		m_Pomoc.add(mnI_oGrze);
		
		JSeparator separator = new JSeparator();
		m_Pomoc.add(separator);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obsługi");
		m_Pomoc.add(mnI_InstrukcjaObslugi);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel p_naglowek = new JPanel();
		p_naglowek.setOpaque(false);
		p_naglowek.setBounds(10, 11, 495, 45);
		contentPane.add(p_naglowek);
		p_naglowek.setLayout(new BorderLayout(0, 0));
		
		JLabel lb_naglowek = new JLabel("ROZMIEŚĆ WŁASNE STATKI");
		lb_naglowek.setHorizontalAlignment(SwingConstants.CENTER);
		lb_naglowek.setFont(new Font("Verdana", Font.BOLD, 14));
		p_naglowek.add(lb_naglowek);
		
		JPanel p_rodzajeStatkow = new JPanel();
		p_rodzajeStatkow.setBounds(248, 67, 257, 237);
		contentPane.add(p_rodzajeStatkow);
		p_rodzajeStatkow.setLayout(null);
		
		lblStatek = new JLabel("Statek");
		lblStatek.setHorizontalAlignment(SwingConstants.CENTER);
		lblStatek.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblStatek.setBounds(63, 10, 139, 17);
		p_rodzajeStatkow.add(lblStatek);
		
		cb_RodzajStatku = new JComboBox();
		cb_RodzajStatku.setModel(new DefaultComboBoxModel(new String[] {"jednomasztowiec", "dwumasztowiec", "trójmasztowiec", "czteromasztowiec"}));
		cb_RodzajStatku.setBounds(45, 30, 175, 25);
		p_rodzajeStatkow.add(cb_RodzajStatku);
		
		lblRozmieszczenie = new JLabel("Rozmieszczenie");
		lblRozmieszczenie.setHorizontalAlignment(SwingConstants.CENTER);
		lblRozmieszczenie.setVisible(false);
		lblRozmieszczenie.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblRozmieszczenie.setBounds(63, 65, 139, 17);
		p_rodzajeStatkow.add(lblRozmieszczenie);
		
		cb_Rozmieszczenie = new JComboBox();
		cb_Rozmieszczenie.setVisible(false);
		cb_Rozmieszczenie.setModel(new DefaultComboBoxModel(new String[] {"pionowo", "poziomo"}));
		cb_Rozmieszczenie.setBounds(45, 85, 175, 25);
		p_rodzajeStatkow.add(cb_Rozmieszczenie);
		
		btn_RozmiescLosowo = new JButton("Rozmieść losowo");
		btn_RozmiescLosowo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_RozmiescLosowo.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_RozmiescLosowo.setBounds(45, 130, 175, 30);
		p_rodzajeStatkow.add(btn_RozmiescLosowo);
		
		btn_WyczyscPlansze = new JButton("Wyczyść plansze");
		btn_WyczyscPlansze.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WyczyscPlansze.setBounds(45, 170, 175, 30);
		p_rodzajeStatkow.add(btn_WyczyscPlansze);
		
		JPanel p_przyciskRozpocznijRozgrywke = new JPanel();
		p_przyciskRozpocznijRozgrywke.setBounds(248, 315, 257, 55);
		contentPane.add(p_przyciskRozpocznijRozgrywke);
		p_przyciskRozpocznijRozgrywke.setLayout(new BorderLayout(0, 0));
		
		btn_RozpocznijRozgrywke = new JButton("Rozpocznij rozgrywkę");
		btn_RozpocznijRozgrywke.setEnabled(false);
		btn_RozpocznijRozgrywke.setFont(new Font("Verdana", Font.BOLD, 15));
		p_przyciskRozpocznijRozgrywke.add(btn_RozpocznijRozgrywke, BorderLayout.CENTER);
		
		JPanel p_rozmieszczenieStatkow = new JPanel();
		p_rozmieszczenieStatkow.setBounds(10, 67, 235, 235);
		contentPane.add(p_rozmieszczenieStatkow);
		p_rozmieszczenieStatkow.setLayout(null);
		
		p_polaGry = new JPanel();
		p_polaGry.setBorder(null);
		p_polaGry.setBounds(10, 11, 219, 220);
		p_rozmieszczenieStatkow.add(p_polaGry);
		
		widokRozmiescZdarzenia = new Widok_Rozmiesc_Zdarzenia(this);
		p_polaGry.setLayout(null);
		
		widokRozmiescZdarzenia.stworzPolaGry(p_polaGry, null, null);
		
		JLabel lb_tloPlanszy = new JLabel(img_tloPlanszy);
		lb_tloPlanszy.setBounds(0, 0, 218, 220);
		p_polaGry.add(lb_tloPlanszy);
		
		widokRozmiescZdarzenia.przerysujPlansze();
		
		//widokGry = new Widok_Gry(widokGlowny, host, gracz, widokRozmiescZdarzenia.getUzytkownik());
	}
}
