package Widoki_GUI;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import javax.swing.border.*;
import Statki.Gracz;
import Statki.Host;
import Widoki_Zdarzenia.Widok_Wynikow_Zdarzenia;

public class Widok_Wynikow extends JFrame {

	public Widok_Wynikow_Zdarzenia widokWynikowZdarzenia;
	public Widok_Gry widokGry;
	public Host host;
	public Gracz gracz;
	
	private JPanel contentPane;
	public JTextField tf_Wiadomosc;
	private JPanel p_poleGry_GRACZ;
	public JButton btn_ZaproponujRewanz;
	public JButton btn_OpuscGre;
	private JPanel p_poleGry_PRZECIWNIK;
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
	public JTextArea ta_Czat;
	public JButton btn_Wyslij;
	public JComboBox cb_szablonyRozmow;
	private JLabel lb_tloPlanszy_PRZECIWNIKA;
	private JLabel lb_tloPlanszy_GRACZA;

	private ImageIcon img_tloPlanszy = new ImageIcon(getClass().getResource("/plansza/plansza200x200/plansza.png"));
	public JLabel lb_NazwaGracza;
	public JLabel lb_NazwaPrzeciwnika;
	private JLabel label;
	private JLabel label_1;
	public JLabel lb_XXX;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Wynikow frame = new Widok_Wynikow(null, null, null);
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
	public Widok_Wynikow(Widok_Gry _widokGry, Host _host, Gracz _gracz) {
		
		widokGry = _widokGry;
		host = _host;
		gracz = _gracz;
		
		setTitle("Statki v.1.0 Beta | SkyGames - wyniki gry");
		setResizable(false);
		/*try {
		      UIManager.setLookAndFeel("com.sun.java.swing.plaf.windows.WindowsLookAndFeel");
		    } catch (Exception e) {
		      e.printStackTrace();
		    }*/
		
		try {UIManager.setLookAndFeel("javax.swing.plaf.nimbus.NimbusLookAndFeel");}
		catch(Exception e) {}
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(630, 550);
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
		p_GRACZ.setBounds(10, 11, 203, 120);
		contentPane.add(p_GRACZ);
		p_GRACZ.setLayout(null);
		
		lb_NazwaGracza = new JLabel("NazwaGracza");
		lb_NazwaGracza.setHorizontalAlignment(SwingConstants.CENTER);
		lb_NazwaGracza.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_NazwaGracza.setBounds(0, 6, 203, 14);
		p_GRACZ.add(lb_NazwaGracza);
		
		lb_AwatarGracza = new JLabel("AwatarGracza");
		lb_AwatarGracza.setBounds(70, 25, 70, 75);
		p_GRACZ.add(lb_AwatarGracza);
		
		label = new JLabel("MOJA PLANSZA");
		label.setHorizontalAlignment(SwingConstants.CENTER);
		label.setForeground(Color.BLUE);
		label.setFont(new Font("Verdana", Font.BOLD, 10));
		label.setBounds(10, 98, 193, 22);
		p_GRACZ.add(label);
		
		JPanel p_PRZECIWNIK = new JPanel();
		p_PRZECIWNIK.setBounds(404, 11, 210, 120);
		contentPane.add(p_PRZECIWNIK);
		p_PRZECIWNIK.setLayout(null);
		
		lb_NazwaPrzeciwnika = new JLabel("NazwaGracza");
		lb_NazwaPrzeciwnika.setHorizontalAlignment(SwingConstants.CENTER);
		lb_NazwaPrzeciwnika.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_NazwaPrzeciwnika.setBounds(0, 6, 210, 14);
		p_PRZECIWNIK.add(lb_NazwaPrzeciwnika);
		
		lb_AwatarPrzeciwnika = new JLabel("AwatarGracza");
		lb_AwatarPrzeciwnika.setBounds(70, 25, 70, 75);
		p_PRZECIWNIK.add(lb_AwatarPrzeciwnika);
		
		label_1 = new JLabel("PLANSZA PRZECIWNIKA");
		label_1.setHorizontalAlignment(SwingConstants.CENTER);
		label_1.setForeground(Color.BLUE);
		label_1.setFont(new Font("Verdana", Font.BOLD, 10));
		label_1.setBounds(10, 98, 200, 22);
		p_PRZECIWNIK.add(label_1);
		
		JPanel p_KtoWygral = new JPanel();
		p_KtoWygral.setBounds(214, 11, 188, 120);
		contentPane.add(p_KtoWygral);
		p_KtoWygral.setLayout(null);
		
		JLabel lb_tekst_WojneStatkowWygral = new JLabel("Wojnę statków wygrał");
		lb_tekst_WojneStatkowWygral.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tekst_WojneStatkowWygral.setFont(new Font("Verdana", Font.PLAIN, 11));
		lb_tekst_WojneStatkowWygral.setBounds(0, 30, 190, 20);
		p_KtoWygral.add(lb_tekst_WojneStatkowWygral);
		
		lb_ZwyciescaGry = new JLabel("");
		lb_ZwyciescaGry.setForeground(new Color(50, 205, 50));
		lb_ZwyciescaGry.setHorizontalAlignment(SwingConstants.CENTER);
		lb_ZwyciescaGry.setFont(new Font("Verdana", Font.BOLD, 14));
		lb_ZwyciescaGry.setBounds(0, 55, 190, 14);
		p_KtoWygral.add(lb_ZwyciescaGry);
		
		JPanel p_obszarGracza = new JPanel();
		p_obszarGracza.setLayout(null);
		p_obszarGracza.setBounds(10, 132, 220, 207);
		contentPane.add(p_obszarGracza);
		
		p_poleGry_GRACZ = new JPanel();
		p_poleGry_GRACZ.setLayout(null);
		p_poleGry_GRACZ.setBounds(10, 0, 205, 205);
		p_obszarGracza.add(p_poleGry_GRACZ);
		
		JPanel p_obszarPrzeciwnika = new JPanel();
		p_obszarPrzeciwnika.setLayout(null);
		p_obszarPrzeciwnika.setBounds(394, 132, 220, 207);
		contentPane.add(p_obszarPrzeciwnika);
		
		p_poleGry_PRZECIWNIK = new JPanel();
		p_poleGry_PRZECIWNIK.setLayout(null);
		p_poleGry_PRZECIWNIK.setBounds(10, 0, 205, 205);
		p_obszarPrzeciwnika.add(p_poleGry_PRZECIWNIK);
		
		JPanel p_CZAT = new JPanel();
		p_CZAT.setLayout(null);
		p_CZAT.setBounds(10, 340, 604, 150);
		contentPane.add(p_CZAT);
		
		tf_Wiadomosc = new JTextField();
		tf_Wiadomosc.setFont(new Font("Verdana", Font.PLAIN, 15));
		tf_Wiadomosc.setColumns(10);
		tf_Wiadomosc.setBorder(new LineBorder(Color.BLACK, 1, true));
		tf_Wiadomosc.setBounds(0, 125, 352, 25);
		p_CZAT.add(tf_Wiadomosc);
		
		JScrollPane sp_Czat = new JScrollPane((Component) null);
		sp_Czat.setBorder(new LineBorder(new Color(0, 0, 0), 1, true));
		sp_Czat.setBounds(0, 10, 604, 104);
		p_CZAT.add(sp_Czat);
		
		ta_Czat = new JTextArea();
		ta_Czat.setFont(new Font("Verdana", Font.PLAIN, 12));
		sp_Czat.setViewportView(ta_Czat);
		
		btn_Wyslij = new JButton("Wyslij");
		btn_Wyslij.setFont(new Font("Verdana", Font.BOLD, 11));
		btn_Wyslij.setBounds(499, 122, 105, 25);
		p_CZAT.add(btn_Wyslij);
		
		cb_szablonyRozmow = new JComboBox();
		cb_szablonyRozmow.setModel(new DefaultComboBoxModel(new String[] {"", "Niezły strzał!", "Jesteś niezły :D", "Ale fart!", "Co za pech!", "Chyba przegram :(", "Gratulacje :)", "Brak szczęscia :/", "Zniszczę Cię !!!", "Rewanż ???"}));
		cb_szablonyRozmow.setFont(new Font("Verdana", Font.PLAIN, 10));
		cb_szablonyRozmow.setBounds(362, 123, 127, 25);
		p_CZAT.add(cb_szablonyRozmow);
		
		JPanel p_OpcjeOknaWynikow = new JPanel();
		p_OpcjeOknaWynikow.setLayout(null);
		p_OpcjeOknaWynikow.setBounds(230, 132, 165, 207);
		contentPane.add(p_OpcjeOknaWynikow);
		
		JLabel lb_tekst_CoCheszRobicDalej = new JLabel("Co chcesz zrobić dalej ?");
		lb_tekst_CoCheszRobicDalej.setFont(new Font("Verdana", Font.BOLD, 10));
		lb_tekst_CoCheszRobicDalej.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tekst_CoCheszRobicDalej.setBounds(10, 30, 145, 25);
		p_OpcjeOknaWynikow.add(lb_tekst_CoCheszRobicDalej);
		
		btn_ZaproponujRewanz = new JButton("Zaproponuj rewanż");
		btn_ZaproponujRewanz.setEnabled(true);
		btn_ZaproponujRewanz.setMargin(new Insets(2, 5, 2, 5));
		btn_ZaproponujRewanz.setFont(new Font("Verdana", Font.BOLD, 11));
		btn_ZaproponujRewanz.setBounds(0, 66, 165, 25);
		p_OpcjeOknaWynikow.add(btn_ZaproponujRewanz);
		
		btn_OpuscGre = new JButton("Opuść grę");
		btn_OpuscGre.setMargin(new Insets(2, 5, 2, 5));
		btn_OpuscGre.setFont(new Font("Verdana", Font.BOLD, 11));
		btn_OpuscGre.setBounds(0, 102, 165, 25);
		p_OpcjeOknaWynikow.add(btn_OpuscGre);
		
		widokWynikowZdarzenia = new Widok_Wynikow_Zdarzenia(this, host, gracz);

		widokWynikowZdarzenia.stworzPolaGry("pole przeciwnika", false, false, widokWynikowZdarzenia.lb_polaGry_PRZECIWNIK, 18, 18, 18, p_poleGry_PRZECIWNIK, null, null, 16, 16);
		widokWynikowZdarzenia.stworzPolaGry("pole gracza", false, true, widokWynikowZdarzenia.lb_polaGry_GRACZ, 18, 18, 18, p_poleGry_GRACZ, null, null, 16, 16);

		lb_tloPlanszy_PRZECIWNIKA = new JLabel(img_tloPlanszy);
		lb_tloPlanszy_PRZECIWNIKA.setBounds(0, 0, 205, 205);
		p_poleGry_PRZECIWNIK.add(lb_tloPlanszy_PRZECIWNIKA);
		
		lb_tloPlanszy_GRACZA = new JLabel(img_tloPlanszy);
		lb_tloPlanszy_GRACZA.setBounds(0, 0, 205, 205);
		p_poleGry_GRACZ.add(lb_tloPlanszy_GRACZA);
		
		lb_XXX = new JLabel("XX");
		lb_XXX.setBounds(10, 501, 604, 14);
		contentPane.add(lb_XXX);
	}
}
