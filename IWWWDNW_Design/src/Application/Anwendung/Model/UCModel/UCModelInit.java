package Application.Anwendung.Model.UCModel;

import Application.Anwendung.Model.Model;
import Application.Anwendung.Model.UCModel.Funktion.Initial;
import Application.Persistenz.DAO.SpielDao;
import Application.Persistenz.*;

import java.awt.*;


/**
 * Created by Angelo on 18.01.2017.
 */
public class UCModelInit implements Initial {

	private final Model model;

	public UCModelInit(Model model) {

		this.model = model;

	}

	@Override
	public void init() {
		SpielDao spielDao =  SpielDao.getInstance();

		Spiel spiel = new Spiel(new Spielfeld(44), new Würfel());
		spiel.getSpieler().add(new Spieler("Johanna", "\uD83D\uDE48"));
		spiel.getSpieler().add(new Spieler("Angelo", "\uD83D\uDE08"));
		spiel.getSpieler().add(new Spieler("Melina", "\uD83D\uDE00"));
		spiel.getSpieler().add(new Spieler("Heinz Gustav Müller", "\uD83D\uDE11"));

		Farbe rot = new Farbe("rot", Color.RED);
		Farbe grün = new Farbe("grün", Color.GREEN);
		Farbe gelb = new Farbe("gelb", Color.YELLOW);
		Farbe blau = new Farbe("blau", Color.BLUE);

		spiel.getFarben().add(rot);
		spiel.getFarben().add(grün);
		spiel.getFarben().add(gelb);
		spiel.getFarben().add(blau);

		Fragekategorie geschichte = new Fragekategorie(rot, "Geschichte");
		Fragekategorie natur = new Fragekategorie(rot, "Natur");
		Fragekategorie sport = new Fragekategorie(rot, "Sport");
		Fragekategorie sprache = new Fragekategorie(rot, "Sprache");

		spiel.getFragekategorien().add(geschichte);
		spiel.getFragekategorien().add(natur);
		spiel.getFragekategorien().add(sport);
		spiel.getFragekategorien().add(sprache);

		spiel.setCurrentSpieler(spiel.getSpieler().get(0));
		spielDao.setSpiel(spiel);

		spiel.setStatus(Spiel.Status.WÜRFELN);
	}
}
