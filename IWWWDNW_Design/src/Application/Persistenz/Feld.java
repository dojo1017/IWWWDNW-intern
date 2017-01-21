package Application.Persistenz;

import Application.Persistenz.DAO.SpielDao;

/**
 * Created by Angelo on 18.01.2017.
 */
public class Feld {
	final boolean STARTFELD;
	private Wissensstreiter wissensstreiter;
	private Feld next;

	public Feld(boolean b) {
		STARTFELD = b;
	}

	public boolean isStartfeld() {
		return STARTFELD;
	}

	public Wissensstreiter getWissensstreiter() {
		return wissensstreiter;
	}

	public void setWissensstreiter(Wissensstreiter wissensstreiter) {
		this.wissensstreiter = wissensstreiter;
	}

	public boolean isFree(int felder) {
		return getNext(felder).isFree();
	}

	public boolean isFree() {
		return wissensstreiter == null;
	}

	public Feld getNext(int felder) {
		Feld next = this;
		for(; felder > 0; felder--) {
			next = next.next;
		}
		return next;
	}

	public Feld getNext() {
		return this.next;
	}

	public void setNext(Feld next) {
		this.next = next;
	}
}
