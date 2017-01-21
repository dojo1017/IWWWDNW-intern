package Application.Persistenz;

import java.util.ArrayList;
import java.util.List;

public class Spiel {
	private final List<Farbe> farben;
	private final List<Fragekategorie> fragekategorien;
	private final List<Spieler> spieler;
	private final Spielfeld spielfeld;
	private final Würfel würfel;
	private Spieler currentSpieler;
	private Status status;

	public Spiel(Spielfeld spielfeld, Würfel würfel) {
		farben = new ArrayList<>();
		fragekategorien = new ArrayList<>();
		spieler = new ArrayList<>();

		this.spielfeld = spielfeld;
		this.würfel = würfel;
		status = Status.INIT;
	}

	public Status getStatus() {
		return status;
	}

	public void setStatus(Status status) {
		this.status = status;
	}

	public List<Farbe> getFarben() {
		return farben;
	}

	public List<Fragekategorie> getFragekategorien() {
		return fragekategorien;
	}

	public List<Spieler> getSpieler() {
		return spieler;
	}

	public Spielfeld getSpielfeld() {
		return spielfeld;
	}

	public Würfel getWürfel() {
		return würfel;
	}

	public Spieler getCurrentSpieler() {
		return currentSpieler;
	}

	public void setCurrentSpieler(Spieler currentSpieler) {
		this.currentSpieler = currentSpieler;
	}

	public void changeNextSpieler() {
		int spielerNr = spieler.indexOf(currentSpieler);

		if (spielerNr == 3) {
			currentSpieler = spieler.get(0);
		} else {
			currentSpieler = spieler.get(spielerNr + 1);
		}

		würfel.setWurfanzahl(0);
	}

	public enum Status {
		INIT,
		WÜRFELN,
		ZIEHEN
	}
}

