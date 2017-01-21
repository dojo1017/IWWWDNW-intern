package Application.Persistenz;

import java.util.Random;

public class Würfel {
	private int zahl;
	private int wurfanzahl;

	public void ermittleNeuenWert() {
		zahl = new Random().nextInt(6) + 1;
		wurfanzahl++;
	}

	public int getZahl() {
		return zahl;
	}

	public void setWurfanzahl(int wurfanzahl) {
		this.wurfanzahl = wurfanzahl;
	}

	public int getWurfanzahl() {
		return wurfanzahl;
	}
}
