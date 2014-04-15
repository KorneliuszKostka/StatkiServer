package Widoki_GUI;

import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Toolkit;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.UIManager;
import javax.swing.border.EmptyBorder;
import javax.swing.text.DefaultFormatterFactory;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.JTextField;
import javax.swing.ListCellRenderer;
import javax.swing.SwingConstants;

import Widoki_Zdarzenia.Widok_Utworz_Zdarzenia;

import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

public class Widok_Utworz extends JFrame {
	
	private Widok_Utworz_Zdarzenia widokUtworzZdarzenia;
	public Widok_Glowny widokGlowny;
	public Widok_Rozmiesc widokRozmiesc;
	
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_InstrukcjaObslugi;
	public JTextField tf_naglowek2_TwojeIP;
	public JButton btn_WrocDoWyboruKategorii;
	public JButton btn_RozpocznijRozmieszczanieStatkw;
	public JComboBox cb_awatarGracza;
	public JFormattedTextField ftf_NazwaGracza;
	public JLabel lb_tekst_OczekiwanieNaPrzeciwnika;
	public JButton btn_utworzSerwer;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Utworz frame = new Widok_Utworz(null);
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
	public Widok_Utworz(Widok_Glowny _widokGlowny) {
		widokGlowny = _widokGlowny;
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(520, 430);
		setTitle("Statki v.1.0 Beta | SkyGames - tworzenie serwera");
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
		
		JMenu mnGra = new JMenu("Gra");
		mb_pasekMenu.add(mnGra);
		
		JMenuItem mnI_Skapituluj = new JMenuItem("Skapituluj");
		mnI_Skapituluj.setEnabled(false);
		mnGra.add(mnI_Skapituluj);
		
		JMenuItem mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		mnI_ZaproponujRewanz.setEnabled(false);
		mnGra.add(mnI_ZaproponujRewanz);
		
		JSeparator separator_1 = new JSeparator();
		mnGra.add(separator_1);
		
		mnI_UstawieniaLokalne = new JMenuItem("Ustawienia lokalne");
		mnGra.add(mnI_UstawieniaLokalne);
		
		JSeparator separator_2 = new JSeparator();
		mnGra.add(separator_2);
		
		mnI_Wyjscie = new JMenuItem("Wyjście");
		mnGra.add(mnI_Wyjscie);
		
		JMenu mnPomoc = new JMenu("Pomoc");
		mb_pasekMenu.add(mnPomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		mnPomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		mnPomoc.add(mnI_oGrze);
		
		JSeparator separator = new JSeparator();
		mnPomoc.add(separator);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obslugi");
		mnPomoc.add(mnI_InstrukcjaObslugi);
		getContentPane().setLayout(null);
		
		JPanel p_naglowekOkna = new JPanel();
		p_naglowekOkna.setOpaque(false);
		p_naglowekOkna.setBounds(10, 11, 495, 145);
		getContentPane().add(p_naglowekOkna);
		p_naglowekOkna.setLayout(null);
		
		JLabel lb_naglowek1 = new JLabel("UTWÓRZ WŁASNY SERWER");
		lb_naglowek1.setFont(new Font("Verdana", Font.BOLD, 14));
		lb_naglowek1.setHorizontalAlignment(SwingConstants.CENTER);
		lb_naglowek1.setBounds(0, 0, 495, 47);
		p_naglowekOkna.add(lb_naglowek1);
		
		tf_naglowek2_TwojeIP = new JTextField("Twoje IP:");
		tf_naglowek2_TwojeIP.setOpaque(false);
		tf_naglowek2_TwojeIP.setBorder(null);
		tf_naglowek2_TwojeIP.setFont(new Font("Verdana", Font.PLAIN, 13));
		tf_naglowek2_TwojeIP.setHorizontalAlignment(SwingConstants.CENTER);
		tf_naglowek2_TwojeIP.setBounds(0, 48, 495, 33);
		tf_naglowek2_TwojeIP.setEditable(false);
		p_naglowekOkna.add(tf_naglowek2_TwojeIP);
		
		JLabel lb_naglowek3 = new JLabel("(skopiuj je graczowi, z którym chcesz zagrać)");
		lb_naglowek3.setFont(new Font("Verdana", Font.PLAIN, 12));
		lb_naglowek3.setHorizontalAlignment(SwingConstants.CENTER);
		lb_naglowek3.setVerticalAlignment(SwingConstants.TOP);
		lb_naglowek3.setBounds(0, 86, 495, 53);
		p_naglowekOkna.add(lb_naglowek3);
		
		JPanel p_daneGracza = new JPanel();
		p_daneGracza.setOpaque(false);
		p_daneGracza.setBounds(10, 167, 265, 155);
		getContentPane().add(p_daneGracza);
		p_daneGracza.setLayout(null);
		
		JLabel lb_tekst_NazwaGracza = new JLabel("Nazwa Gracza:");
		lb_tekst_NazwaGracza.setFont(new Font("Verdana", Font.PLAIN, 11));
		lb_tekst_NazwaGracza.setBounds(10, 11, 103, 17);
		p_daneGracza.add(lb_tekst_NazwaGracza);
		
		

		ImageIcon[] images;
	    String[] nazwaAwatarow = {"awatar1", "awatar2"};
        images = new ImageIcon[nazwaAwatarow.length];
        Integer[] intArray = new Integer[nazwaAwatarow.length];
        for (int i = 0; i < nazwaAwatarow.length; i++) {
            intArray[i] = new Integer(i);
            images[i] = createImageIcon("/awatary/" + nazwaAwatarow[i] + ".png");
            
        }

        cb_awatarGracza = new JComboBox(intArray);
        cb_awatarGracza.setBounds(122, 40, 126, 80);
        ComboBoxRenderer renderer= new ComboBoxRenderer(images);
        renderer.setPreferredSize(new Dimension(150, 80));
        cb_awatarGracza.setRenderer(renderer);
        cb_awatarGracza.setMaximumRowCount(3);
		
		p_daneGracza.add(cb_awatarGracza);
		
		JLabel lb_tekst_Awatar = new JLabel("Awatar:");
		lb_tekst_Awatar.setFont(new Font("Verdana", Font.PLAIN, 11));
		lb_tekst_Awatar.setBounds(10, 39, 103, 80);
		p_daneGracza.add(lb_tekst_Awatar);
		
		JPanel p_OczekiwanieNaPrzeciwnika = new JPanel();
		p_OczekiwanieNaPrzeciwnika.setOpaque(false);
		p_OczekiwanieNaPrzeciwnika.setBounds(285, 167, 219, 155);
		getContentPane().add(p_OczekiwanieNaPrzeciwnika);
		p_OczekiwanieNaPrzeciwnika.setLayout(new BorderLayout(0, 0));
		
		lb_tekst_OczekiwanieNaPrzeciwnika = new JLabel("Oczekiwanie na przeciwnika...");
		lb_tekst_OczekiwanieNaPrzeciwnika.setVisible(false);
		lb_tekst_OczekiwanieNaPrzeciwnika.setHorizontalAlignment(SwingConstants.CENTER);
		lb_tekst_OczekiwanieNaPrzeciwnika.setFont(new Font("Verdana", Font.BOLD, 10));
		p_OczekiwanieNaPrzeciwnika.add(lb_tekst_OczekiwanieNaPrzeciwnika, BorderLayout.CENTER);
		
		JPanel panel_2 = new JPanel();
		panel_2.setOpaque(false);
		panel_2.setBounds(11, 323, 494, 47);
		getContentPane().add(panel_2);
		panel_2.setLayout(null);
		
		btn_WrocDoWyboruKategorii = new JButton("Wróć do wyboru kategorii");
		btn_WrocDoWyboruKategorii.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WrocDoWyboruKategorii.setBounds(10, 11, 235, 33);
		panel_2.add(btn_WrocDoWyboruKategorii);
		
		btn_RozpocznijRozmieszczanieStatkw = new JButton("Rozpocznij rozmieszczanie statków");
		btn_RozpocznijRozmieszczanieStatkw.setEnabled(false);
		btn_RozpocznijRozmieszczanieStatkw.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_RozpocznijRozmieszczanieStatkw.setBounds(259, 11, 235, 33);
		panel_2.add(btn_RozpocznijRozmieszczanieStatkw);
		
		btn_utworzSerwer = new JButton("Utwórz");
		btn_utworzSerwer.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_utworzSerwer.setBounds(166, 132, 82, 23);
		p_daneGracza.add(btn_utworzSerwer);
		
		widokUtworzZdarzenia = new Widok_Utworz_Zdarzenia(this);
		
		String maska = "";
		for(int i=0;i<16;i++)
			maska = maska + "A";
		
		ftf_NazwaGracza = new JFormattedTextField(new DefaultFormatterFactory(widokUtworzZdarzenia.stworzFormat(maska)));
		ftf_NazwaGracza.setFont(new Font("Verdana", Font.PLAIN, 11));
		ftf_NazwaGracza.setFocusLostBehavior(JFormattedTextField.COMMIT);
		ftf_NazwaGracza.setBounds(123, 9, 125, 20);
		p_daneGracza.add(ftf_NazwaGracza);
		
		
		widokRozmiesc = new Widok_Rozmiesc(widokGlowny, widokUtworzZdarzenia.host, null);
		
	}
	
	protected static ImageIcon createImageIcon(String path) {
	      java.net.URL imgURL = Widok_Dolacz.class.getResource(path);
	      if (imgURL != null) {
	           return new ImageIcon(imgURL);
	       } else {
	           System.err.println("Couldn't find file: " + path);
	                return null;
	       }
	  }
		
	class ComboBoxRenderer extends JLabel implements ListCellRenderer 
	{
		private Font uhOhFont;
		private ImageIcon images[];
		
		public ComboBoxRenderer(ImageIcon[] _images) {
			setOpaque(true);
			setHorizontalAlignment(SwingConstants.CENTER);
			images = _images;
		}

		public Component getListCellRendererComponent(JList list,Object value, int index, boolean isSelected, boolean cellHasFocus) 
		{
			int selectedIndex = ((Integer)value).intValue();
						
			if (isSelected) {
				setBackground(list.getSelectionBackground());
				setForeground(list.getSelectionForeground());
			} else {
				setBackground(list.getBackground());
				setForeground(list.getForeground());
			}	
			ImageIcon icon = images[selectedIndex];
			setIcon(icon);
			if (icon != null) {
				setFont(list.getFont());
			}
			return this;
		}
					
		protected void setUhOhText(String uhOhText, Font normalFont) {
				if (uhOhFont == null) { //lazily create this font
				uhOhFont = normalFont.deriveFont(Font.ITALIC);
				}
				setFont(uhOhFont);
				setText(uhOhText);
			}
		}
}
