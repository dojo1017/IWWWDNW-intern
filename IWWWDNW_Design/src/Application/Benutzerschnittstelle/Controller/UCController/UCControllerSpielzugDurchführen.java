
package Application.Benutzerschnittstelle.Controller.UCController;

import Application.Anwendung.Model.Model;
import Application.Benutzerschnittstelle.View.View;
import Application.Persistenz.DAO.SpielDao;
import Application.Persistenz.Wissensstreiter;

import java.util.NoSuchElementException;

public class UCControllerSpielzugDurchführen {
	private final Model model;
	private final View view;

	public UCControllerSpielzugDurchführen(Model model, View view) {
		this.model = model;
		this.view = view;
	}


	public void würfeln() {
		view.aktuellenStandAnzeigen();
		view.spielfeldAnzeigen();
		view.zumWuerfelnAuffordern();
		View.pressEnterToContinue();//keyboardAbfragen();
		model.wuerfeln();
		view.würfelErgebnisAnzeigen();
	}

	public void ziehen() {

		model.ziehen();
		view.spielfeldAnzeigen();
		model.spielerWechseln();
		view.endeZug();
	}


}
