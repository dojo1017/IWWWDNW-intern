package Application.Persistenz;

public class Wissensstandsanzeiger {
	private final Spieler spieler;
	private int level = 0;

	public Wissensstandsanzeiger(Spieler spieler) {
		this.spieler = spieler;
	}

	public String getLevelAsString() {
		switch (level) {
			case 0: return "Anfänger";
			case 1: return "Fortgeschrittener";
			case 2: return "Profi";
			default: return "Ungültig";
		}
	}

	public void incrementLevel() {
		if(level < 2) {
			level++;
		}
	}

	public void setLevel(int level) {
		this.level = level;
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public int getLevel() {
		return level;
	}
}
