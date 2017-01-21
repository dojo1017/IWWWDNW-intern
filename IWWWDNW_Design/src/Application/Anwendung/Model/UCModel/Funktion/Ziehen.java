package Application.Anwendung.Model.UCModel.Funktion;

import Application.Anwendung.Model.UCModel.UCModelSpielzugDurchführen;
import Application.Persistenz.Wissensstreiter;

/**
 * Created by Angelo on 18.01.2017.
 */
public interface Ziehen {
	void ziehen(int auswahl) throws Wissensstreiter.CantMoveException;

	void ziehen();
}
