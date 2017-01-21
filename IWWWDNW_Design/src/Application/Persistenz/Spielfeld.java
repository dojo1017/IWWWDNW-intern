package Application.Persistenz;

import java.util.ArrayList;
import java.util.Collection;
import java.util.LinkedList;
import java.util.List;

public class Spielfeld {
	private final List<Feld> felder;

	public Spielfeld(int size) {
		felder = new ArrayList<>();

		for(int i = 0; i < size; i++) {
			felder.add(i, new Feld((i % (size/4)) == 0));
		}

		for(int i = 0; i < size; i++) {
			if(i < size - 1) {
				felder.get(i).setNext(felder.get(i+1));
			} else {
				felder.get(i).setNext(felder.get(0));
			}
		}
	}

	public Spielfeld() {
		this(44);
	}

	public List<Feld> getFelder() {
		return felder;
	}

	public int getSize() {
		return felder.size();
	}

	public void addFeld(Feld feld) {
		this.felder.add(feld);
	}


}
