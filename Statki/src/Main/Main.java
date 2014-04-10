package Main;

import java.awt.EventQueue;

import Widoki_GUI.Widok_Glowny;

public class Main {

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
}
