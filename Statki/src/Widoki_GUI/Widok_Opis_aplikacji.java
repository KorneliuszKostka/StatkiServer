package Widoki_GUI;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JButton;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JLabel;

import Widoki_Zdarzenia.Widok_OpisAplikacji_Zdarzenia;

public class Widok_Opis_aplikacji extends JFrame {

	public Widok_Glowny widokGlowny;
	public Widok_OpisAplikacji_Zdarzenia widokOpisAplikacjiZdarzenia;
	
	private JPanel contentPane;
	public JMenuItem mnI_InstrukcjaObslugi;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JButton btn_WrocDoWyboruKategorii;
	public JTextArea ta_OpisGry;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Opis_aplikacji frame = new Widok_Opis_aplikacji(null);
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
	public Widok_Opis_aplikacji(Widok_Glowny _widokGlowny) {
		
		widokGlowny = _widokGlowny;
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setTitle("Statki v.1.0 Beta | SkyGames - opis gry");
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
		
		JMenuBar mb_pasekMenu = new JMenuBar();
		setJMenuBar(mb_pasekMenu);
		
		JMenu m_Gra = new JMenu("Gra");
		mb_pasekMenu.add(m_Gra);
		
		mnI_Skapituluj = new JMenuItem("Skapituluj");
		mnI_Skapituluj.setEnabled(false);
		m_Gra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		mnI_ZaproponujRewanz.setEnabled(false);
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
		mb_pasekMenu.add(m_Pomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		m_Pomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		mnI_oGrze.setVisible(false);
		m_Pomoc.add(mnI_oGrze);
		
		JSeparator separator_2 = new JSeparator();
		m_Pomoc.add(separator_2);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obsługi");
		m_Pomoc.add(mnI_InstrukcjaObslugi);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel p_ObrazekGryTworcy = new JPanel();
		p_ObrazekGryTworcy.setBounds(10, 11, 494, 136);
		contentPane.add(p_ObrazekGryTworcy);
		p_ObrazekGryTworcy.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTutajBdzieObrazek = new JLabel("Tutaj będzie obrazek oraz twórcy gry");
		lblTutajBdzieObrazek.setFont(new Font("Verdana", Font.BOLD, 15));
		p_ObrazekGryTworcy.add(lblTutajBdzieObrazek, BorderLayout.CENTER);
		
		JPanel p_OpisGry = new JPanel();
		p_OpisGry.setBounds(10, 158, 494, 160);
		contentPane.add(p_OpisGry);
		p_OpisGry.setLayout(null);
		
		ta_OpisGry = new JTextArea();
		ta_OpisGry.setFont(new Font("Verdana", Font.BOLD, 15));
		ta_OpisGry.setOpaque(false);
		ta_OpisGry.setText("Tutaj będzie opis gry");
		ta_OpisGry.setBounds(10, 11, 474, 138);
		p_OpisGry.add(ta_OpisGry);
		
		JPanel p_Przyciski = new JPanel();
		p_Przyciski.setLayout(null);
		p_Przyciski.setOpaque(false);
		p_Przyciski.setBounds(10, 326, 494, 55);
		contentPane.add(p_Przyciski);
		
		btn_WrocDoWyboruKategorii = new JButton("Wróć do wyboru kategorii");
		btn_WrocDoWyboruKategorii.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WrocDoWyboruKategorii.setBounds(138, 11, 235, 33);
		p_Przyciski.add(btn_WrocDoWyboruKategorii);
		
		widokOpisAplikacjiZdarzenia = new Widok_OpisAplikacji_Zdarzenia(this);
	}
}
