package Application.Persistenz;

public class Wissensstreiter {
	private static int count;
	private int nummer;
	private final Spieler spieler;
	private Feld feld = null;



	public Wissensstreiter(Spieler spieler) {
		this.spieler = spieler;
		this.nummer = count % 4;
		count++;
	}

	public Feld getFeld() {
		return feld;
	}

	public void setFeld(Feld feld) {
		this.feld = feld;
	}

	public int getNummer() {
		return nummer;
	}

	public Spieler getSpieler() {
		return spieler;
	}

	public boolean onSpielfeld() {
		return feld != null;
	}

	public boolean canMove(int felder) {
		if(!onSpielfeld()) {
			return false;
		}
		return feld.isFree(felder);
	}

	public void move(int felder) throws CantMoveException {
		if(!canMove(felder)) {
			throw new CantMoveException();
		}
		this.getFeld().setWissensstreiter(null);
		this.feld = this.getFeld().getNext(felder);
		this.getFeld().setWissensstreiter(this);
	}

	public class CantMoveException extends Throwable {
	}
}
