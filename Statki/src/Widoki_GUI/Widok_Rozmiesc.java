package Widoki_GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;
import java.awt.geom.Dimension2D;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JTextField;
import javax.swing.JComboBox;
import javax.swing.DefaultComboBoxModel;
import javax.swing.JButton;

import Widoki_Zdarzenia.Widok_Rozmiesc_Zdarzenia;

import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class Widok_Rozmiesc extends JFrame {

	public Widok_Rozmiesc_Zdarzenia widokRozmiescZdarzenia;
	public Widok_Glowny widokGlowny;
	public Widok_Gry widokGry;
	
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
	public JPanel p_Litery;
	public JPanel p_Cyfry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Rozmiesc frame = new Widok_Rozmiesc(null);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	public Widok_Rozmiesc(Widok_Glowny _widokGlowny) {
		
		widokGlowny = _widokGlowny;
	
		setTitle("Statki v.1.0 Beta | SkyGames - rozmieszczenie statków");
		setResizable(false);
		try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }
		

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
		p_rodzajeStatkow.setBounds(257, 67, 248, 237);
		contentPane.add(p_rodzajeStatkow);
		p_rodzajeStatkow.setLayout(null);
		
		JLabel lblStatek = new JLabel("Statek:");
		lblStatek.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblStatek.setBounds(10, 11, 103, 17);
		p_rodzajeStatkow.add(lblStatek);
		
		cb_RodzajStatku = new JComboBox();
		cb_RodzajStatku.setModel(new DefaultComboBoxModel(new String[] {"", "Jednomasztowiec", "Dwumasztowiec", "Trójmasztowiec", "Czteromasztowiec"}));
		cb_RodzajStatku.setBounds(123, 10, 115, 20);
		p_rodzajeStatkow.add(cb_RodzajStatku);
		
		JLabel lblRozmieszczenie = new JLabel("Rozmieszczenie:");
		lblRozmieszczenie.setFont(new Font("Verdana", Font.PLAIN, 11));
		lblRozmieszczenie.setBounds(10, 57, 103, 17);
		p_rodzajeStatkow.add(lblRozmieszczenie);
		
		cb_Rozmieszczenie = new JComboBox();
		cb_Rozmieszczenie.setModel(new DefaultComboBoxModel(new String[] {"", "Pionowe", "Poziome"}));
		cb_Rozmieszczenie.setBounds(123, 54, 115, 20);
		p_rodzajeStatkow.add(cb_Rozmieszczenie);
		
		btn_RozmiescLosowo = new JButton("Rozmieść losowo");
		btn_RozmiescLosowo.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btn_RozmiescLosowo.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_RozmiescLosowo.setBounds(40, 130, 175, 30);
		p_rodzajeStatkow.add(btn_RozmiescLosowo);
		
		btn_WyczyscPlansze = new JButton("Wyczyść plansze");
		btn_WyczyscPlansze.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WyczyscPlansze.setBounds(40, 170, 175, 30);
		p_rodzajeStatkow.add(btn_WyczyscPlansze);
		
		JPanel p_przyciskRozpocznijRozgrywke = new JPanel();
		p_przyciskRozpocznijRozgrywke.setBounds(257, 315, 248, 55);
		contentPane.add(p_przyciskRozpocznijRozgrywke);
		p_przyciskRozpocznijRozgrywke.setLayout(new BorderLayout(0, 0));
		
		btn_RozpocznijRozgrywke = new JButton("Rozpocznij rozgrywkę");
		btn_RozpocznijRozgrywke.setFont(new Font("Verdana", Font.BOLD, 15));
		p_przyciskRozpocznijRozgrywke.add(btn_RozpocznijRozgrywke, BorderLayout.CENTER);
		
		JPanel p_rozmieszczenieStatkow = new JPanel();
		p_rozmieszczenieStatkow.setBounds(10, 67, 235, 235);
		contentPane.add(p_rozmieszczenieStatkow);
		p_rozmieszczenieStatkow.setLayout(null);
		
		p_polaGry = new JPanel();
		p_polaGry.setBounds(25, 25, 200, 200);
		p_rozmieszczenieStatkow.add(p_polaGry);
		
		p_Litery = new JPanel();
		p_Litery.setBounds(0, 25, 25, 200);
		p_rozmieszczenieStatkow.add(p_Litery);
		p_Litery.setLayout(null);
		
		p_Cyfry = new JPanel();
		p_Cyfry.setBounds(25, 1, 200, 24);
		p_rozmieszczenieStatkow.add(p_Cyfry);
		
		widokRozmiescZdarzenia = new Widok_Rozmiesc_Zdarzenia(this);
		widokRozmiescZdarzenia.stworzPolaGry(p_polaGry, p_Litery, p_Cyfry);
		p_Cyfry.setLayout(null);
		p_polaGry.setLayout(null);
		
		widokGry = new Widok_Gry(widokGlowny);
	}
}
