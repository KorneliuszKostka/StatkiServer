package Widoki_GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JScrollPane;
import javax.swing.JSeparator;
import javax.swing.UIManager;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JComboBox;
import javax.swing.JLabel;
import javax.swing.SwingConstants;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

import javax.swing.JTextField;
import javax.swing.JTextArea;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.border.TitledBorder;
import javax.swing.DefaultComboBoxModel;

import Widoki_Zdarzenia.Widok_Gry_Zdarzenia;
import java.awt.Insets;

public class Widok_Gry extends JFrame {
	
	public Widok_Gry_Zdarzenia widokGryZdarzenia;
	public Widok_Glowny widokGlowny;
	public Widok_Wynikow widokWynikow;

	private JPanel contentPane;
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_InstrukcjaObslugi;
	private JPanel p_obszarGracza;
	private JPanel p_obszarPrzeciwnika;
	private JPanel p_poleGryGracza;
	private JPanel p_opcjeGry;
	public JButton btn_Skapituluj;
	public JButton btn_Przerwa;
	public JComboBox cb_RodzajStrzalu;
	private JLabel lb_tekst_RodzajStrzalu;
	public JLabel lb_AwatarGracza;
	private JPanel p_poleGryPrzeciwnika;
	public JLabel lb_AwatarPrzeciwnika;
	public JLabel lb_NazwaPrzeciwnika;
	public JLabel lb_NazwaGracza;
	private JPanel p_rozmowaGraczy;
	public JTextField tf_TrescWiadomosci;
	public JButton btn_Wyslij;
	public JTextArea ta_CzatGraczy;
	public JComboBox cb_szablonyWiadomosci;
	private JPanel p_Litery_GRACZ;
	private JPanel p_Cyfry_GRACZ;
	private JPanel p_Cyfry_PRZECIWNIK;
	private JPanel p_Litery_PRZECIWNIK;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Gry frame = new Widok_Gry(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public Widok_Gry(Widok_Glowny _widokGlowny) {
		
		widokGlowny = _widokGlowny;
		
		setTitle("Statki v.1.0 Beta | SkyGames - okno gry");
		setResizable(false);
		try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(630, 500);
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
		m_Gra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		m_Gra.add(mnI_ZaproponujRewanz);
		
		JSeparator separator = new JSeparator();
		m_Gra.add(separator);
		
		mnI_UstawieniaLokalne = new JMenuItem("Ustawienia lokalne");
		m_Gra.add(mnI_UstawieniaLokalne);
		
		JSeparator separator_1 = new JSeparator();
		m_Gra.add(separator_1);
		
		mnI_Wyjscie = new JMenuItem("Wyjście");
		m_Gra.add(mnI_Wyjscie);
		
		JMenu m_Pomoc = new JMenu("Pomoc");
		menuBar.add(m_Pomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		m_Pomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		m_Pomoc.add(mnI_oGrze);
		
		JSeparator separator_2 = new JSeparator();
		m_Pomoc.add(separator_2);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obsługi");
		m_Pomoc.add(mnI_InstrukcjaObslugi);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		p_obszarGracza = new JPanel();
		p_obszarGracza.setBorder(null);
		p_obszarGracza.setBounds(10, 11, 215, 334);
		contentPane.add(p_obszarGracza);
		p_obszarGracza.setLayout(null);
		
		p_poleGryGracza = new JPanel();
		p_poleGryGracza.setBounds(25, 154, 180, 180);
		p_obszarGracza.add(p_poleGryGracza);
		
		lb_AwatarGracza = new JLabel("AwatarGracza");
		lb_AwatarGracza.setBounds(90, 48, 70, 75);
		p_obszarGracza.add(lb_AwatarGracza);
		
		lb_NazwaGracza = new JLabel("NazwaGracza");
		lb_NazwaGracza.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_NazwaGracza.setHorizontalAlignment(SwingConstants.CENTER);
		lb_NazwaGracza.setBounds(25, 23, 200, 14);
		p_obszarGracza.add(lb_NazwaGracza);
		
		p_obszarPrzeciwnika = new JPanel();
		p_obszarPrzeciwnika.setBounds(349, 11, 265, 334);
		contentPane.add(p_obszarPrzeciwnika);
		p_obszarPrzeciwnika.setLayout(null);
		
		p_poleGryPrzeciwnika = new JPanel();
		p_poleGryPrzeciwnika.setBorder(null);
		p_poleGryPrzeciwnika.setBounds(25, 95, 240, 240);
		p_poleGryPrzeciwnika.setLayout(null);
		p_obszarPrzeciwnika.add(p_poleGryPrzeciwnika);
		
		
		lb_AwatarPrzeciwnika = new JLabel("AwatarPrzeciwnika");
		lb_AwatarPrzeciwnika.setBounds(35, 0, 70, 75);
		p_obszarPrzeciwnika.add(lb_AwatarPrzeciwnika);
		
		lb_NazwaPrzeciwnika = new JLabel("NazwaPrzeciwnika");
		lb_NazwaPrzeciwnika.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_NazwaPrzeciwnika.setBounds(115, 30, 150, 14);
		p_obszarPrzeciwnika.add(lb_NazwaPrzeciwnika);
		
		p_Cyfry_PRZECIWNIK = new JPanel();
		p_Cyfry_PRZECIWNIK.setBounds(25, 75, 240, 20);
		p_obszarPrzeciwnika.add(p_Cyfry_PRZECIWNIK);
		p_Cyfry_PRZECIWNIK.setLayout(null);
		
		p_Litery_PRZECIWNIK = new JPanel();
		p_Litery_PRZECIWNIK.setBounds(0, 95, 25, 240);
		p_obszarPrzeciwnika.add(p_Litery_PRZECIWNIK);
		p_Litery_PRZECIWNIK.setLayout(null);
		
		p_opcjeGry = new JPanel();
		p_opcjeGry.setBounds(225, 11, 126, 334);
		contentPane.add(p_opcjeGry);
		p_opcjeGry.setLayout(null);
		
		btn_Skapituluj = new JButton("Skapituluj");
		btn_Skapituluj.setBounds(0, 262, 125, 25);
		p_opcjeGry.add(btn_Skapituluj);
		btn_Skapituluj.setFont(new Font("Verdana", Font.BOLD, 10));
		
		btn_Przerwa = new JButton("Zaproponuj przerwe");
		btn_Przerwa.setMargin(new Insets(2, 0, 2, 0));
		btn_Przerwa.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_Przerwa.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_Przerwa.setBounds(0, 298, 125, 25);
		p_opcjeGry.add(btn_Przerwa);
		
		cb_RodzajStrzalu = new JComboBox();
		cb_RodzajStrzalu.setFont(new Font("Verdana", Font.PLAIN, 11));
		cb_RodzajStrzalu.setBounds(0, 173, 122, 25);
		p_opcjeGry.add(cb_RodzajStrzalu);
		
		lb_tekst_RodzajStrzalu = new JLabel("Rodzaj strzału");
		lb_tekst_RodzajStrzalu.setFont(new Font("Verdana", Font.PLAIN, 12));
		lb_tekst_RodzajStrzalu.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tekst_RodzajStrzalu.setBounds(0, 153, 105, 20);
		p_opcjeGry.add(lb_tekst_RodzajStrzalu);
		
		p_rozmowaGraczy = new JPanel();
		p_rozmowaGraczy.setBounds(10, 344, 604, 107);
		contentPane.add(p_rozmowaGraczy);
		p_rozmowaGraczy.setLayout(null);
		
		tf_TrescWiadomosci = new JTextField();
		tf_TrescWiadomosci.setFont(new Font("Verdana", Font.PLAIN, 15));
		tf_TrescWiadomosci.setBorder(new LineBorder(Color.BLACK, 1, true));
		tf_TrescWiadomosci.setBounds(0, 74, 352, 25);
		p_rozmowaGraczy.add(tf_TrescWiadomosci);
		tf_TrescWiadomosci.setColumns(10);
		
		ta_CzatGraczy = new JTextArea();
		ta_CzatGraczy.setFont(new Font("Verdana", Font.PLAIN, 12));
		
		JScrollPane sp_rozmowaGraczy = new JScrollPane(ta_CzatGraczy);
		sp_rozmowaGraczy.setBounds(0, 10, 604, 58);
		sp_rozmowaGraczy.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		
		p_rozmowaGraczy.add(sp_rozmowaGraczy);
		
		btn_Wyslij = new JButton("Wyslij");
		btn_Wyslij.setFont(new Font("Verdana", Font.BOLD, 11));
		btn_Wyslij.setBounds(499, 71, 105, 25);
		p_rozmowaGraczy.add(btn_Wyslij);
		
		cb_szablonyWiadomosci = new JComboBox();
		cb_szablonyWiadomosci.setModel(new DefaultComboBoxModel(new String[] {"", "Niezły strzał!", "Jesteś niezły :D", "Ale fart!", "Co za pech!", "Chyba przegram :(", "Gratulacje :)", "Brak szczęscia :/", "Zniszczę Cię !!!", "Rewanż ???"}));
		cb_szablonyWiadomosci.setFont(new Font("Verdana", Font.PLAIN, 10));
		cb_szablonyWiadomosci.setBounds(362, 72, 127, 25);
		p_rozmowaGraczy.add(cb_szablonyWiadomosci);
		
		p_Litery_GRACZ = new JPanel();
		p_Litery_GRACZ.setBounds(0, 154, 25, 180);
		p_obszarGracza.add(p_Litery_GRACZ);
		
		p_Cyfry_GRACZ = new JPanel();
		p_Cyfry_GRACZ.setBounds(25, 134, 180, 20);
		p_obszarGracza.add(p_Cyfry_GRACZ);
		
		widokGryZdarzenia = new Widok_Gry_Zdarzenia(this);
		
		p_poleGryGracza.setLayout(null);
		p_Cyfry_GRACZ.setLayout(null);
		p_Litery_GRACZ.setLayout(null);
		
		widokGryZdarzenia.stworzPolaGry(widokGryZdarzenia.lb_polaGry_GRACZ, 18, 18, 18, p_poleGryGracza, p_Litery_GRACZ, p_Cyfry_GRACZ, 17, 15);
		widokGryZdarzenia.stworzPolaGry(widokGryZdarzenia.lb_polaGry_PRZECIWNIK, 23, 23, 24, p_poleGryPrzeciwnika, p_Litery_PRZECIWNIK, p_Cyfry_PRZECIWNIK, 18, 18);
		
		widokWynikow = new Widok_Wynikow(this);
	}
}
