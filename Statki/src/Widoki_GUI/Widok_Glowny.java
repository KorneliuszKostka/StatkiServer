package Widoki_GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.ImageIcon;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;

import java.awt.GridLayout;

import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JButton;

import Widoki_Zdarzenia.Widok_Glowny_Zdarzenia;

import java.awt.Color;
import java.awt.Font;
import java.awt.Cursor;

public class Widok_Glowny extends JFrame {

	private static final long serialVersionUID = 1L;
	
	private Widok_Glowny_Zdarzenia widokGlowny_zdarzenia;
	public Widok_Dolacz widokDolacz;
	public Widok_Utworz widokUtworz;
	public Widok_Opis_aplikacji widokOpisAplikacji;
	public Widok_Ustawien widokUstawien;
	public Widok_Opis_tworcow widokOpisTworcow;
	
	private JPanel obszarKontenera;
	
	ImageIcon img_ObrazekGry = new ImageIcon(getClass().getResource("/tloGry/obrazekGry.png"));
	public JButton btn_UtworzSerwer;
	public JButton btn_DolaczDoGry;
	public JLabel lb_oGrze;
	public JLabel lb_UstawieniaLokalne;
	public JLabel lb_oTworcach;
	public JLabel lb_Wyjscie;
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
					Widok_Glowny frame = new Widok_Glowny();
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
	public Widok_Glowny() {
		widokDolacz = new Widok_Dolacz(this);
		widokUtworz = new Widok_Utworz(this);
		widokOpisAplikacji = new Widok_Opis_aplikacji(this);
		widokUstawien = new Widok_Ustawien(this);
		widokOpisTworcow = new Widok_Opis_tworcow(this);
		
		setTitle("Statki v.1.0 Beta | SkyGames - okno powitalne");
		setResizable(false);
		/*try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }*/
		
		//ustawienie UIManagera
		//try {UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
		//catch(Exception e) {}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(520, 430);
		Toolkit zestaw = Toolkit.getDefaultToolkit(); //narzedzie dzieki ktoremu mozna pobrac rozdzielczosc
		Dimension wymiary = zestaw.getScreenSize();
		int wysokosc = wymiary.height;
		int szerokosc = wymiary.width;
		setLocation((szerokosc/2)-(this.getSize().width/2),(wysokosc/2)-(this.getSize().height/2));
		
		JMenuBar mb_pasekMenu = new JMenuBar();
		setJMenuBar(mb_pasekMenu);
		
		JMenu mn_Gra = new JMenu("Gra");
		mb_pasekMenu.add(mn_Gra);
		
		mnI_Skapituluj = new JMenuItem("Skapituluj");
		mnI_Skapituluj.setEnabled(false);
		mn_Gra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		mnI_ZaproponujRewanz.setEnabled(false);
		mn_Gra.add(mnI_ZaproponujRewanz);
		
		JSeparator separator1 = new JSeparator();
		mn_Gra.add(separator1);
		
		mnI_UstawieniaLokalne = new JMenuItem("Ustawienia lokalne");
		mn_Gra.add(mnI_UstawieniaLokalne);
		
		JSeparator separator2 = new JSeparator();
		mn_Gra.add(separator2);
		
		mnI_Wyjscie = new JMenuItem("Wyjście");
		mn_Gra.add(mnI_Wyjscie);
		
		JMenu mn_Pomoc = new JMenu("Pomoc");
		mb_pasekMenu.add(mn_Pomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		mn_Pomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		mn_Pomoc.add(mnI_oGrze);
		
		JSeparator separator3 = new JSeparator();
		mn_Pomoc.add(separator3);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obsługi");
		mn_Pomoc.add(mnI_InstrukcjaObslugi);
		
		obszarKontenera = new JPanel();
		obszarKontenera.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(obszarKontenera);
		obszarKontenera.setLayout(null);
		
		JPanel p_oknoGlowne = new JPanel();
		p_oknoGlowne.setOpaque(false);
		p_oknoGlowne.setBounds(5, 5, 504, 371);
		obszarKontenera.add(p_oknoGlowne);
		p_oknoGlowne.setLayout(null);
		
		JPanel p_ObrazekGry = new JPanel();
		p_ObrazekGry.setOpaque(false);
		p_ObrazekGry.setBounds(10, 15, 484, 200);
		p_oknoGlowne.add(p_ObrazekGry);
		p_ObrazekGry.setLayout(null);
		
		JLabel lb_ObrazekGry = new JLabel(img_ObrazekGry);
		lb_ObrazekGry.setBounds(0, 0, 484, 200);
		p_ObrazekGry.add(lb_ObrazekGry);
		
		JPanel p_Przyciski = new JPanel();
		p_Przyciski.setOpaque(false);
		p_Przyciski.setBounds(10, 225, 484, 100);
		p_oknoGlowne.add(p_Przyciski);
		p_Przyciski.setLayout(null);
		
		btn_DolaczDoGry = new JButton("Dołącz do istniejącej gry");
		btn_DolaczDoGry.setFont(new Font("Verdana", Font.BOLD, 14));
		btn_DolaczDoGry.setBounds(10, 11, 225, 80);
		p_Przyciski.add(btn_DolaczDoGry);
		
		btn_UtworzSerwer = new JButton("Utwórz własny serwer");
		btn_UtworzSerwer.setFont(new Font("Verdana", Font.BOLD, 14));
		btn_UtworzSerwer.setBounds(249, 11, 225, 80);
		p_Przyciski.add(btn_UtworzSerwer);
		
		JPanel p_Linki = new JPanel();
		p_Linki.setOpaque(false);
		p_Linki.setBounds(10, 330, 484, 30);
		p_oknoGlowne.add(p_Linki);
		p_Linki.setLayout(null);
		
		lb_oGrze = new JLabel("<HTML><U>O grze<U><HTML>");
		lb_oGrze.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_oGrze.setFont(new Font("Verdana", Font.BOLD, 13));
		lb_oGrze.setForeground(new Color(0, 0, 255));
		lb_oGrze.setBounds(10, 0, 47, 30);
		p_Linki.add(lb_oGrze);
		
		lb_UstawieniaLokalne = new JLabel("<HTML><U>Ustawienia lokalne<U><HTML>");
		lb_UstawieniaLokalne.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_UstawieniaLokalne.setForeground(Color.BLUE);
		lb_UstawieniaLokalne.setFont(new Font("Verdana", Font.BOLD, 13));
		lb_UstawieniaLokalne.setBounds(67, 0, 136, 30);
		p_Linki.add(lb_UstawieniaLokalne);
		
		lb_oTworcach = new JLabel("<HTML><U>O twórcach<U><HTML>");
		lb_oTworcach.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_oTworcach.setForeground(Color.BLUE);
		lb_oTworcach.setFont(new Font("Verdana", Font.BOLD, 13));
		lb_oTworcach.setBounds(213, 0, 82, 30);
		p_Linki.add(lb_oTworcach);
		
		lb_Wyjscie = new JLabel("<HTML><U>Wyjście<U><HTML>");
		lb_Wyjscie.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));
		lb_Wyjscie.setForeground(Color.BLUE);
		lb_Wyjscie.setFont(new Font("Verdana", Font.BOLD, 13));
		lb_Wyjscie.setBounds(305, 0, 57, 30);
		p_Linki.add(lb_Wyjscie);
		
		widokGlowny_zdarzenia = new Widok_Glowny_Zdarzenia(this);
	}
}
