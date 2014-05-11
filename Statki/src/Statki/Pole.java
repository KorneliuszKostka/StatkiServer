package Statki;

public class Pole {

	private int x;
	private int y;
	private String rodzajPola;	//1 - ustawiony masz, -1 - trafiony maszt, 0 - puste pole, X - zablokowane pole wokol statku
	
	public Pole(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public void setPole(int _x, int _y) {
		x = _x;
		y = _y;
	}
	
	public void setRodzajPola(String _rodzajPola) {
		rodzajPola = _rodzajPola;
	}
	
	public int getX() {
		return x;
	}
	
	public int getY() {
		return y;
	}
	
	public String getRodzajPola() {
		return rodzajPola;
	}
}
