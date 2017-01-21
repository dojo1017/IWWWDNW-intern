package Application.Persistenz;

import java.util.ArrayList;
import java.util.List;

public class Fragekategorie {
	private final Farbe farbe;
	private final List<Frage> fragen;
	private String titel;

	public Farbe getFarbe() {
		return farbe;
	}

	public List<Frage> getFragen() {
		return fragen;
	}

	public String getTitel() {
		return titel;
	}

	public Fragekategorie(Farbe farbe, String titel) {
		this.farbe = farbe;
		this.titel = titel;
		fragen = new ArrayList<>();
	}

	public Fragekategorie(String titel, Farbe farbe, List<Frage> fragen) {
		this.titel = titel;
		this.farbe = farbe;
		this.fragen = fragen;
	}

	public void setTitel(String titel) {
		this.titel = titel;
	}


}
