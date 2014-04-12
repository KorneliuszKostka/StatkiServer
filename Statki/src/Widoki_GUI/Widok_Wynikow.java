package Widoki_GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.border.LineBorder;

import java.awt.Color;

import javax.swing.JScrollPane;

import java.awt.Component;

import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JTextArea;

import java.awt.Insets;

import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import Widoki_Zdarzenia.Widok_Wynikow_Zdarzenia;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Widok_Wynikow extends JFrame {

	public Widok_Wynikow_Zdarzenia widokWynikowZdarzenia;
	public Widok_Gry widokGry;
	
	private JPanel contentPane;
	public JTextField tf_Wiadomosc;
	private JPanel p_poleGry_GRACZ;
	private JPanel p_litery_GRACZ;
	private JPanel p_cyfry_GRACZ;
	public JButton btn_ZaproponujRewanz;
	public JButton btn_OpuscGre;
	private JPanel p_poleGry_PRZECIWNIK;
	private JPanel p_litery_PRZECIWNIK;
	private JPanel p_cyfry_PRZECIWNIK;
	public JLabel lb_AwatarGracza;
	public JLabel lb_AwatarPrzeciwnika;
	public JLabel lb_ZwyciescaGry;
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_InstrukcjaObslugi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Wynikow frame = new Widok_Wynikow(null);
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
	public Widok_Wynikow(Widok_Gry _widokGry) {
		
		widokGry = _widokGry;
		
		setTitle("Statki v.1.0 Beta | SkyGames - wyniki gry");
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
		mnI_Skapituluj.setEnabled(false);
		m_Gra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
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
		
		JPanel p_GRACZ = new JPanel();
		p_GRACZ.setBounds(10, 11, 220, 120);
		contentPane.add(p_GRACZ);
		p_GRACZ.setLayout(null);
		
		JLabel lb_NazwaGracza = new JLabel("NazwaGracza");
		lb_NazwaGracza.setHorizontalAlignment(SwingConstants.CENTER);
		lb_NazwaGracza.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_NazwaGracza.setBounds(0, 11, 200, 14);
		p_GRACZ.add(lb_NazwaGracza);
		
		lb_AwatarGracza = new JLabel("AwatarGracza");
		lb_AwatarGracza.setBounds(65, 36, 70, 75);
		p_GRACZ.add(lb_AwatarGracza);
		
		JPanel p_PRZECIWNIK = new JPanel();
		p_PRZECIWNIK.setBounds(394, 11, 220, 120);
		contentPane.add(p_PRZECIWNIK);
		p_PRZECIWNIK.setLayout(null);
		
		JLabel lb_NazwaPrzeciwnika = new JLabel("NazwaGracza");
		lb_NazwaPrzeciwnika.setHorizontalAlignment(SwingConstants.CENTER);
		lb_NazwaPrzeciwnika.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_NazwaPrzeciwnika.setBounds(10, 11, 200, 14);
		p_PRZECIWNIK.add(lb_NazwaPrzeciwnika);
		
		lb_AwatarPrzeciwnika = new JLabel("AwatarGracza");
		lb_AwatarPrzeciwnika.setBounds(75, 36, 70, 75);
		p_PRZECIWNIK.add(lb_AwatarPrzeciwnika);
		
		JPanel p_KtoWygral = new JPanel();
		p_KtoWygral.setBounds(230, 11, 165, 120);
		contentPane.add(p_KtoWygral);
		p_KtoWygral.setLayout(null);
		
		JLabel lb_tekst_WojneStatkowWygral = new JLabel("Wojnę statków wygrał");
		lb_tekst_WojneStatkowWygral.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tekst_WojneStatkowWygral.setFont(new Font("Verdana", Font.PLAIN, 11));
		lb_tekst_WojneStatkowWygral.setBounds(0, 30, 165, 20);
		p_KtoWygral.add(lb_tekst_WojneStatkowWygral);
		
		lb_ZwyciescaGry = new JLabel("XXXXXXXXXXXXXXXX");
		lb_ZwyciescaGry.setForeground(new Color(50, 205, 50));
		lb_ZwyciescaGry.setHorizontalAlignment(SwingConstants.CENTER);
		lb_ZwyciescaGry.setFont(new Font("Verdana", Font.BOLD, 12));
		lb_ZwyciescaGry.setBounds(0, 55, 165, 14);
		p_KtoWygral.add(lb_ZwyciescaGry);
		
		JPanel p_obszarGracza = new JPanel();
		p_obszarGracza.setLayout(null);
		p_obszarGracza.setBounds(10, 139, 220, 200);
		contentPane.add(p_obszarGracza);
		
		p_litery_GRACZ = new JPanel();
		p_litery_GRACZ.setLayout(null);
		p_litery_GRACZ.setBounds(0, 20, 25, 180);
		p_obszarGracza.add(p_litery_GRACZ);
		
		p_poleGry_GRACZ = new JPanel();
		p_poleGry_GRACZ.setLayout(null);
		p_poleGry_GRACZ.setBounds(25, 20, 180, 180);
		p_obszarGracza.add(p_poleGry_GRACZ);
		
		p_cyfry_GRACZ = new JPanel();
		p_cyfry_GRACZ.setLayout(null);
		p_cyfry_GRACZ.setBounds(25, 0, 180, 20);
		p_obszarGracza.add(p_cyfry_GRACZ);
		
		JPanel p_obszarPrzeciwnika = new JPanel();
		p_obszarPrzeciwnika.setLayout(null);
		p_obszarPrzeciwnika.setBounds(394, 139, 220, 200);
		contentPane.add(p_obszarPrzeciwnika);
		
		p_litery_PRZECIWNIK = new JPanel();
		p_litery_PRZECIWNIK.setLayout(null);
		p_litery_PRZECIWNIK.setBounds(15, 20, 25, 180);
		p_obszarPrzeciwnika.add(p_litery_PRZECIWNIK);
		
		p_poleGry_PRZECIWNIK = new JPanel();
		p_poleGry_PRZECIWNIK.setLayout(null);
		p_poleGry_PRZECIWNIK.setBounds(40, 20, 180, 180);
		p_obszarPrzeciwnika.add(p_poleGry_PRZECIWNIK);
		
		p_cyfry_PRZECIWNIK = new JPanel();
		p_cyfry_PRZECIWNIK.setLayout(null);
		p_cyfry_PRZECIWNIK.setBounds(40, 0, 180, 20);
		p_obszarPrzeciwnika.add(p_cyfry_PRZECIWNIK);
		
		JPanel p_CZAT = new JPanel();
		p_CZAT.setLayout(null);
		p_CZAT.setBounds(10, 340, 604, 107);
		contentPane.add(p_CZAT);
		
		tf_Wiadomosc = new JTextField();
		tf_Wiadomosc.setFont(new Font("Verdana", Font.PLAIN, 15));
		tf_Wiadomosc.setColumns(10);
		tf_Wiadomosc.setBorder(new LineBorder(Color.BLACK, 1, true));
		tf_Wiadomosc.setBounds(0, 74, 352, 25);
		p_CZAT.add(tf_Wiadomosc);
		
		JScrollPane sp_Czat = new JScrollPane((Component) null);
		sp_Czat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		sp_Czat.setBounds(0, 10, 604, 58);
		p_CZAT.add(sp_Czat);
		
		JTextArea ta_Rozmowa = new JTextArea();
		ta_Rozmowa.setFont(new Font("Verdana", Font.PLAIN, 12));
		sp_Czat.setViewportView(ta_Rozmowa);
		
		JButton button = new JButton("Wyslij");
		button.setFont(new Font("Verdana", Font.BOLD, 11));
		button.setBounds(499, 71, 105, 25);
		p_CZAT.add(button);
		
		JComboBox comboBox = new JComboBox();
		comboBox.setFont(new Font("Verdana", Font.PLAIN, 10));
		comboBox.setBounds(362, 72, 127, 25);
		p_CZAT.add(comboBox);
		
		JPanel p_OpcjeOknaWynikow = new JPanel();
		p_OpcjeOknaWynikow.setLayout(null);
		p_OpcjeOknaWynikow.setBounds(230, 139, 165, 200);
		contentPane.add(p_OpcjeOknaWynikow);
		
		JLabel lb_tekst_CoCheszRobicDalej = new JLabel("Co chcesz zrobić dalej ?");
		lb_tekst_CoCheszRobicDalej.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_tekst_CoCheszRobicDalej.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tekst_CoCheszRobicDalej.setBounds(10, 30, 145, 25);
		p_OpcjeOknaWynikow.add(lb_tekst_CoCheszRobicDalej);
		
		btn_ZaproponujRewanz = new JButton("Zaproponuj rewanż");
		btn_ZaproponujRewanz.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_ZaproponujRewanz.setMargin(new Insets(2, 5, 2, 5));
		btn_ZaproponujRewanz.setFont(new Font("Verdana", Font.BOLD, 11));
		btn_ZaproponujRewanz.setBounds(10, 66, 145, 25);
		p_OpcjeOknaWynikow.add(btn_ZaproponujRewanz);
		
		btn_OpuscGre = new JButton("Opuść grę");
		btn_OpuscGre.setMargin(new Insets(2, 5, 2, 5));
		btn_OpuscGre.setFont(new Font("Verdana", Font.BOLD, 11));
		btn_OpuscGre.setBounds(10, 102, 145, 25);
		p_OpcjeOknaWynikow.add(btn_OpuscGre);
		
		widokWynikowZdarzenia = new Widok_Wynikow_Zdarzenia(this);
		
		widokWynikowZdarzenia.stworzPolaGry(widokWynikowZdarzenia.lb_polaGry_GRACZ, 18, 18, 18, p_poleGry_GRACZ, p_litery_GRACZ, p_cyfry_GRACZ, 17, 15);
		widokWynikowZdarzenia.stworzPolaGry(widokWynikowZdarzenia.lb_polaGry_PRZECIWNIK, 18, 18, 18, p_poleGry_PRZECIWNIK, p_litery_PRZECIWNIK, p_cyfry_PRZECIWNIK, 17, 15);

	}
}
