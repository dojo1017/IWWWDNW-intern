package Application.Benutzerschnittstelle.Controller;

import Application.Anwendung.Model.Model;
import Application.Benutzerschnittstelle.Controller.UCController.UCControllerInit;
import Application.Benutzerschnittstelle.Controller.UCController.UCControllerSpielzugDurchführen;
import Application.Benutzerschnittstelle.View.View;
import Application.Persistenz.DAO.SpielDao;

public class UserInterface {

	private Model model = new Model();
	private View view = new View(model);
	private UCControllerInit ucControllerInit = new UCControllerInit(model, view);
	private UCControllerSpielzugDurchführen ucControllerSpielzugDurchführen = new UCControllerSpielzugDurchführen(model, view);
	//private UCControllerWissensfrage ucControllerWissensfrage = new UCControllerWissensfrage(model, view);
	//private UCControllerGewinnerkührung ucControllerGewinnerkührung = new UCControllerGewinnerkührung(model, view);

	public static void main(String[] args) {
		UserInterface ui = new UserInterface();
		ui.spielschleife();
	}

	private void spielschleife() {
		ucControllerInit.initialisieren();
		while (true) {
			switch(SpielDao.getInstance().getSpiel().getStatus()) {

				case INIT:
					ucControllerInit.initialisieren();
					break;
				case WÜRFELN:
					ucControllerSpielzugDurchführen.würfeln();
					break;
				case ZIEHEN:
					ucControllerSpielzugDurchführen.ziehen();
					break;
			}
		}
	}
}