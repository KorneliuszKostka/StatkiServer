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

import javax.swing.JLabel;

import Widoki_Zdarzenia.Widok_Ustawien_Zdarzenia;

public class Widok_Ustawien extends JFrame {

	public Widok_Glowny widokGlowny;
	public Widok_Ustawien_Zdarzenia widokUstawienZdarzenia;
	
	private JPanel contentPane;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_InstrukcjaObslugi;
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JButton btn_WrocDoWyboruKategorii;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Ustawien frame = new Widok_Ustawien(null);
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
	public Widok_Ustawien(Widok_Glowny _widokGlowny) {
		
		widokGlowny = _widokGlowny;
		
		setSize(520, 430);
		setTitle("Statki v.1.0 Beta | SkyGames - ustawienia lokalne");
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
		
		JMenu mnGra = new JMenu("Gra");
		menuBar.add(mnGra);
		
		mnI_Skapituluj = new JMenuItem("Skapituluj");
		mnI_Skapituluj.setEnabled(false);
		mnGra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		mnI_ZaproponujRewanz.setEnabled(false);
		mnGra.add(mnI_ZaproponujRewanz);
		
		JSeparator separator_1 = new JSeparator();
		separator_1.setVisible(false);
		mnGra.add(separator_1);
		
		mnI_UstawieniaLokalne = new JMenuItem("Ustawienia lokalne");
		mnI_UstawieniaLokalne.setVisible(false);
		mnGra.add(mnI_UstawieniaLokalne);
		
		JSeparator separator_2 = new JSeparator();
		mnGra.add(separator_2);
		
		mnI_Wyjscie = new JMenuItem("Wyjście");
		mnGra.add(mnI_Wyjscie);
		
		JMenu mnPomoc = new JMenu("Pomoc");
		menuBar.add(mnPomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		mnPomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		mnPomoc.add(mnI_oGrze);
		
		JSeparator separator = new JSeparator();
		mnPomoc.add(separator);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obsługi");
		mnPomoc.add(mnI_InstrukcjaObslugi);
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel panel = new JPanel();
		panel.setBounds(10, 11, 494, 293);
		contentPane.add(panel);
		panel.setLayout(new BorderLayout(0, 0));
		
		JLabel lblTuBdUstawienia = new JLabel("Tu będą ustawienia lokalne");
		panel.add(lblTuBdUstawienia, BorderLayout.CENTER);
		
		JPanel panel_1 = new JPanel();
		panel_1.setLayout(null);
		panel_1.setOpaque(false);
		panel_1.setBounds(10, 315, 494, 55);
		contentPane.add(panel_1);
		
		btn_WrocDoWyboruKategorii = new JButton("Wróć do wyboru kategorii");
		btn_WrocDoWyboruKategorii.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WrocDoWyboruKategorii.setBounds(138, 11, 235, 33);
		panel_1.add(btn_WrocDoWyboruKategorii);
		
		widokUstawienZdarzenia = new Widok_Ustawien_Zdarzenia(this);
	}
}
