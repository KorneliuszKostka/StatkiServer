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
import javax.swing.text.MaskFormatter;
import javax.swing.ImageIcon;
import javax.swing.JList;
import javax.swing.JMenuBar;
import javax.swing.JMenu;
import javax.swing.JMenuItem;
import javax.swing.JSeparator;
import javax.swing.JLabel;
import javax.swing.ListCellRenderer;

import java.awt.Font;

import javax.swing.SwingConstants;
import javax.swing.JButton;
import javax.swing.JFormattedTextField;
import javax.swing.JComboBox;

import Widoki_Zdarzenia.Widok_Dolacz_Zdarzenia;

public class Widok_Dolacz extends JFrame {

	private Widok_Dolacz_Zdarzenia widokDolaczZdarzenia;
	public Widok_Glowny widokGlowny;
	public Widok_Rozmiesc widokRozmiesc;
	
	
	private JPanel contentPane;
	public JMenuItem mnI_Skapituluj;
	public JMenuItem mnI_ZaproponujRewanz;
	public JMenuItem mnI_UstawieniaLokalne;
	public JMenuItem mnI_Wyjscie;
	public JMenuItem mnI_oTworcach;
	public JMenuItem mnI_oGrze;
	public JMenuItem mnI_InstrukcjaObslugi;

	public JLabel lb_komunikat_TRWALACZENIE;
	
	public JButton btn_RozpocznijRozmieszczanieStatkow;
	public JButton btn_WrocDoWyboruKategorii;
	
	public JComboBox cb_AwatarGracza;
	
	public JFormattedTextField ftf_NazwaGracza;
	public JFormattedTextField ftf_IpSerwera;
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					Widok_Dolacz frame = new Widok_Dolacz(null);
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
	public Widok_Dolacz(Widok_Glowny _widokGlowny) {
		widokGlowny = _widokGlowny;
		
		widokRozmiesc = new Widok_Rozmiesc(widokGlowny);
		
		setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		setSize(520, 430);
		setTitle("Statki v.1.0 Beta | SkyGames - dołączanie do gry");
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
		
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JPanel p_DolaczDoGry = new JPanel();
		p_DolaczDoGry.setOpaque(false);
		p_DolaczDoGry.setBounds(10, 33, 494, 358);
		contentPane.add(p_DolaczDoGry);
		p_DolaczDoGry.setLayout(null);
		
		JPanel p_naglowekOkna = new JPanel();
		p_naglowekOkna.setOpaque(false);
		p_naglowekOkna.setBounds(10, 10, 475, 55);
		p_DolaczDoGry.add(p_naglowekOkna);
		p_naglowekOkna.setLayout(new BorderLayout(0, 0));
		
		JLabel lb_naglowekOkna = new JLabel("DOŁĄCZ DO ISTNIEJĄCEGO SERWERA");
		lb_naglowekOkna.setHorizontalAlignment(SwingConstants.CENTER);
		lb_naglowekOkna.setFont(new Font("Verdana", Font.BOLD, 18));
		p_naglowekOkna.add(lb_naglowekOkna, BorderLayout.CENTER);
		
		JPanel p_przyciski = new JPanel();
		p_przyciski.setOpaque(false);
		p_przyciski.setBounds(0, 303, 494, 55);
		p_DolaczDoGry.add(p_przyciski);
		p_przyciski.setLayout(null);
		
		btn_WrocDoWyboruKategorii = new JButton("Wróć do wyboru kategorii");
		btn_WrocDoWyboruKategorii.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_WrocDoWyboruKategorii.setBounds(10, 11, 235, 33);
		p_przyciski.add(btn_WrocDoWyboruKategorii);
		
		btn_RozpocznijRozmieszczanieStatkow = new JButton("Rozpocznij rozmieszczanie statków");
		btn_RozpocznijRozmieszczanieStatkow.setFont(new Font("Verdana", Font.BOLD, 10));
		btn_RozpocznijRozmieszczanieStatkow.setBounds(250, 11, 235, 33);
		p_przyciski.add(btn_RozpocznijRozmieszczanieStatkow);
		
		JPanel p_daneSerwerUzytkownik = new JPanel();
		p_daneSerwerUzytkownik.setOpaque(false);
		p_daneSerwerUzytkownik.setBounds(10, 75, 235, 222);
		p_DolaczDoGry.add(p_daneSerwerUzytkownik);
		p_daneSerwerUzytkownik.setLayout(null);
		
		
		
		JLabel lb_tekst_IPSERWERA = new JLabel("IP Serwera:");
		lb_tekst_IPSERWERA.setBounds(10, 26, 80, 20);
		p_daneSerwerUzytkownik.add(lb_tekst_IPSERWERA);
		
		JLabel lb_tekst_NAZWAGRACZA = new JLabel("Nazwa Gracza:");
		lb_tekst_NAZWAGRACZA.setBounds(10, 73, 80, 17);
		p_daneSerwerUzytkownik.add(lb_tekst_NAZWAGRACZA);
		
		JLabel lb_tekst_AWATAR = new JLabel("Awatar:");
		lb_tekst_AWATAR.setBounds(10, 115, 80, 80);
		p_daneSerwerUzytkownik.add(lb_tekst_AWATAR);
		
		
		
		ImageIcon[] images;
	    String[] nazwaAwatarow = {"awatar1", "awatar2"};
        images = new ImageIcon[nazwaAwatarow.length];
        Integer[] intArray = new Integer[nazwaAwatarow.length];
        for (int i = 0; i < nazwaAwatarow.length; i++) {
            intArray[i] = new Integer(i);
            images[i] = createImageIcon("/awatary/" + nazwaAwatarow[i] + ".png");
            
        }

        //Create the combo box.
        cb_AwatarGracza = new JComboBox(intArray);
		cb_AwatarGracza.setBounds(99, 115, 126, 80);
        ComboBoxRenderer renderer= new ComboBoxRenderer(images);
        renderer.setPreferredSize(new Dimension(150, 80));
        cb_AwatarGracza.setRenderer(renderer);
        cb_AwatarGracza.setMaximumRowCount(3);
		
		
		p_daneSerwerUzytkownik.add(cb_AwatarGracza);
		
		JPanel p_TrwaLaczenie = new JPanel();
		p_TrwaLaczenie.setOpaque(false);
		p_TrwaLaczenie.setBounds(250, 75, 235, 222);
		p_DolaczDoGry.add(p_TrwaLaczenie);
		p_TrwaLaczenie.setLayout(new BorderLayout(0, 0));
		
		lb_komunikat_TRWALACZENIE = new JLabel("TRWA ŁĄCZENIE Z SERWEREM..");
		lb_komunikat_TRWALACZENIE.setVisible(false);
		lb_komunikat_TRWALACZENIE.setFont(new Font("Verdana", Font.BOLD, 13));
		p_TrwaLaczenie.add(lb_komunikat_TRWALACZENIE, BorderLayout.CENTER);
		
		JMenuBar mb_pasekMenu = new JMenuBar();
		mb_pasekMenu.setBounds(0, 0, 514, 21);
		contentPane.add(mb_pasekMenu);
		
		JMenu mn_Gra = new JMenu("Gra");
		mb_pasekMenu.add(mn_Gra);
		
		mnI_Skapituluj = new JMenuItem("Skapituluj");
		mnI_Skapituluj.setEnabled(false);
		mn_Gra.add(mnI_Skapituluj);
		
		mnI_ZaproponujRewanz = new JMenuItem("Zaproponuj rewanż");
		mnI_ZaproponujRewanz.setEnabled(false);
		mn_Gra.add(mnI_ZaproponujRewanz);
		
		JSeparator separator = new JSeparator();
		mn_Gra.add(separator);
		
		mnI_UstawieniaLokalne = new JMenuItem("Ustawienia lokalne");
		mn_Gra.add(mnI_UstawieniaLokalne);
		
		JSeparator separator_1 = new JSeparator();
		mn_Gra.add(separator_1);
		
		mnI_Wyjscie = new JMenuItem("Wyjście");
		mn_Gra.add(mnI_Wyjscie);
		
		JMenu mn_Pomoc = new JMenu("Pomoc");
		mb_pasekMenu.add(mn_Pomoc);
		
		mnI_oTworcach = new JMenuItem("O twórcach");
		mn_Pomoc.add(mnI_oTworcach);
		
		mnI_oGrze = new JMenuItem("O grze");
		mn_Pomoc.add(mnI_oGrze);
		
		JSeparator separator_2 = new JSeparator();
		mn_Pomoc.add(separator_2);
		
		mnI_InstrukcjaObslugi = new JMenuItem("Instrukcja obsługi");
		mn_Pomoc.add(mnI_InstrukcjaObslugi);
		
		widokDolaczZdarzenia = new Widok_Dolacz_Zdarzenia(this);
		
		String maska = "";
		maska = "###.###.###.###";
		ftf_IpSerwera = new JFormattedTextField();
		ftf_IpSerwera.setFormatterFactory(new DefaultFormatterFactory(widokDolaczZdarzenia.stworzFormat(maska)));
		ftf_IpSerwera.setFocusLostBehavior(JFormattedTextField.COMMIT);
		ftf_IpSerwera.setBounds(100, 26, 125, 20);
		p_daneSerwerUzytkownik.add(ftf_IpSerwera);
		
		maska = "";
		for(int i=0;i<16;i++)
			maska = maska + "A";
		
		ftf_NazwaGracza = new JFormattedTextField();
		ftf_NazwaGracza.setFormatterFactory(new DefaultFormatterFactory(widokDolaczZdarzenia.stworzFormat(maska)));
		ftf_NazwaGracza.setFocusLostBehavior(JFormattedTextField.COMMIT);
		ftf_NazwaGracza.setBounds(100, 73, 125, 20);
		p_daneSerwerUzytkownik.add(ftf_NazwaGracza);
		
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
