package Application.Persistenz;

import java.awt.*;

public class Farbe {
	private final Color farbe;
	private String name;

	public Farbe(String name, Color farbe) {
		this.name = name;
		this.farbe = farbe;
	}

	public String getName() {
		return name;
	}

	public Color getFarbe() {
		return farbe;
	}
}
