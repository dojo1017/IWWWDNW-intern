package Application.Benutzerschnittstelle.Controller.UCController;

import Application.Anwendung.Model.Model;
import Application.Benutzerschnittstelle.View.View;
import Application.Persistenz.Spiel;

/**
 * Created by Angelo on 17.01.2017.
 */
public class UCControllerInit {
	private final Model model;
	private final View view;

	public UCControllerInit(Model model, View view) {
		this.model = model;
		this.view = view;
	}

	public void initialisieren() {
		model.init();
		model.addAufStartfeldGesetztListener(view::wissensstreiterWurdeGesetzt);
		model.addSpielerAuswaehlenListener(view::spielerAuswählen);
		model.addBewegungAnzeigenListener((auswahl) -> view.bewegungAnzeigen(auswahl));
		model.addKeineAktionMoeglichListener(view::keineAktionMöglich);
		//view.aktuellenStandAnzeigen();
	}
}
