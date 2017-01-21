package Application.Persistenz.DAO;

import Application.Persistenz.Spiel;

/**
 * Created by Angelo on 10.01.2017.
 */
public class SpielDao {
	private static SpielDao instance = new SpielDao();
	private Spiel spiel;

	SpielDao() {
	}

	public static SpielDao getInstance() {
		return instance;
	}

	public Spiel getSpiel() {
		return spiel;
	}

	public void setSpiel(Spiel spiel) {
		this.spiel = spiel;

	}
}
