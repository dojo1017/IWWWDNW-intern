package Application.Persistenz;

import Application.Persistenz.DAO.SpielDao;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class Spieler {
	private final List<Wissensstreiter> wissensstreiter;
	private final Map<Fragekategorie, Wissensstandsanzeiger> wissensstandsanzeiger;
	private final String name;
	private final String symbol;


	public Spieler(String name, String symbol) {
		this.name = name;
		this.symbol = symbol;
		this.wissensstreiter = new ArrayList<>();
		wissensstreiter.add(new Wissensstreiter(this));
		wissensstreiter.add(new Wissensstreiter(this));
		wissensstreiter.add(new Wissensstreiter(this));
		wissensstreiter.add(new Wissensstreiter(this));

		this.wissensstandsanzeiger = new HashMap<>();
	}

	public String getName() {
		return name;
	}

	public Wissensstandsanzeiger getWissensstand(Fragekategorie fragekategorie) {
		addFragekategorieIfNotExist(fragekategorie);

		return wissensstandsanzeiger.get(fragekategorie);
	}

	private void addFragekategorieIfNotExist(Fragekategorie fragekategorie) {
		if (!wissensstandsanzeiger.containsKey(fragekategorie)) {
			wissensstandsanzeiger.put(fragekategorie, new Wissensstandsanzeiger(this));
		}
	}

	public String getSymbol() {
		return symbol;
	}

	public List<Wissensstreiter> getWissensstreiter() {
		return wissensstreiter;
	}

	public Wissensstreiter getWissensstreiter(int auswahl) {
		return wissensstreiter.get(auswahl);
	}

	public boolean canMoveAny() {
		int würfelzahl = SpielDao.getInstance().getSpiel().getWürfel().getZahl();

		for (int i = 0; i < wissensstreiter.size(); i++) {
			if (wissensstreiter.get(i).canMove(würfelzahl)) {
				return true;
			}
		}

		return false;
	}

	public boolean canSet() {
		int würfelzahl = SpielDao.getInstance().getSpiel().getWürfel().getZahl();

		if (würfelzahl != 6) {
			return false;
		}

		if (getUnsetWissensstreiterCount() == 0) {
			return false;
		}


		Spiel spiel = SpielDao.getInstance().getSpiel();

		int startfeldNr = getStartfeldNr();
		if (spiel.getSpielfeld().getFelder().get(startfeldNr).isFree()) {
			return true;
		}

		return false;
	}

	public int getUnsetWissensstreiterCount() {
		int anzahl = 0;
		for (Wissensstreiter ws : wissensstreiter) {
			if (ws.getFeld() == null) {
				anzahl++;
			}
		}

		return anzahl;
	}

	public void setOnStartfeld() {
		if (!canSet()) {
			throw new IllegalStateException("Can't set.");
		}

		for (Wissensstreiter ws : wissensstreiter) {
			if (!ws.onSpielfeld()) {
				ws.setFeld(SpielDao.getInstance().getSpiel().getSpielfeld().getFelder().get(this.getStartfeldNr()));
				SpielDao.getInstance().getSpiel().getSpielfeld().getFelder().get(this.getStartfeldNr()).setWissensstreiter(ws);
				break;
			}
		}
	}

	private int getStartfeldNr() {

		return getSpielerId() *
				(SpielDao.getInstance().getSpiel().getSpielfeld().getSize()
						/ 4);
	}

	public int getSpielerId() {
		return SpielDao.getInstance().getSpiel().getSpieler().indexOf(this);
	}
}
