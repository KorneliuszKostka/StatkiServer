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
import javax.swing.JLabel;

import java.awt.Font;

import javax.swing.JTextArea;
import javax.swing.JButton;

import Widoki_Zdarzenia.Widok_OpisTworcow_Zdarzenia;

public class Widok_Opis_tworcow extends JFrame {

	public Widok_OpisTworcow_Zdarzenia widokOpisTworcowZdarzenia;
	public Widok_Glowny widokGlowny;
	
	private JPanel contentPane;
	public JMenuItem mnI_Wyjscie;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JButton btn_WrocDoWyboruKategorii;
	public JMenuItem mnI_InstrukcjaObslugi;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Opis_tworcow frame = new Widok_Opis_tworcow(null);
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
	public Widok_Opis_tworcow(Widok_Glowny _widokGlowny) {
		
		widokGlowny = _widokGlowny;
		
		setSize(520, 430);
		setTitle("Statki v.1.0 Beta | SkyGames - opis twórców");
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
		mnI_oTworcach.setVisible(false);
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
		
		JPanel p_OpisTworcow = new JPanel();
		p_OpisTworcow.setBounds(10, 158, 494, 160);
		contentPane.add(p_OpisTworcow);
		p_OpisTworcow.setLayout(new BorderLayout(0, 0));
		
		JTextArea ta_OpisTworcow = new JTextArea();
		ta_OpisTworcow.setText("Tutaj będzie opis twórców");
		ta_OpisTworcow.setOpaque(false);
		ta_OpisTworcow.setFont(new Font("Verdana", Font.BOLD, 15));
		p_OpisTworcow.add(ta_OpisTworcow);
		
		JPanel p_przyciski = new JPanel();
		p_przyciski.setLayout(null);
		p_przyciski.setOpaque(false);
		p_przyciski.setBounds(10, 326, 494, 55);
		contentPane.add(p_przyciski);
		
		btn_WrocDoWyboruKategorii = new JButton("Wróć do wyboru kategorii");
		btn_WrocDoWyboruKategorii.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WrocDoWyboruKategorii.setBounds(138, 11, 235, 33);
		p_przyciski.add(btn_WrocDoWyboruKategorii);
		
		JPanel p_obrazekGry = new JPanel();
		p_obrazekGry.setBounds(10, 11, 494, 136);
		contentPane.add(p_obrazekGry);
		p_obrazekGry.setLayout(new BorderLayout(0, 0));
		
		JLabel label = new JLabel("Tutaj będzie obrazek oraz twórcy gry");
		label.setFont(new Font("Verdana", Font.BOLD, 15));
		p_obrazekGry.add(label);
		
		widokOpisTworcowZdarzenia = new Widok_OpisTworcow_Zdarzenia(this);
	}
}
