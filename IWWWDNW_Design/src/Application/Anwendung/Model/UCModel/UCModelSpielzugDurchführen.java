package Application.Anwendung.Model.UCModel;

import Application.Anwendung.Model.Model;
import Application.Anwendung.Model.UCModel.Funktion.SpielerWechseln;
import Application.Anwendung.Model.UCModel.Funktion.Wuerfeln;
import Application.Anwendung.Model.UCModel.Funktion.Ziehen;
import Application.Benutzerschnittstelle.View.View;
import Application.Persistenz.DAO.SpielDao;
import Application.Persistenz.Spiel;
import Application.Persistenz.Wissensstreiter;

import java.util.NoSuchElementException;

/**
 * Created by Angelo on 18.01.2017.
 */
public class UCModelSpielzugDurchführen implements Wuerfeln, Ziehen, SpielerWechseln {

	private final Model model;

	public UCModelSpielzugDurchführen(Model model) {
		this.model = model;
	}

	@Override
	public void ziehen(int auswahl) throws Wissensstreiter.CantMoveException {
		if(SpielDao.getInstance().getSpiel().getStatus() != Spiel.Status.ZIEHEN) {
			throw new IllegalStateException();
		}

		SpielDao.getInstance().getSpiel().getCurrentSpieler().getWissensstreiter(auswahl).move(
				SpielDao.getInstance().getSpiel().getWürfel().getZahl()
		);

	}

	@Override
	public void ziehen() {



		if(SpielDao.getInstance().getSpiel().getCurrentSpieler().canSet()) {
			SpielDao.getInstance().getSpiel().getCurrentSpieler().setOnStartfeld();
			model.noticeAufStartfeldGesetztListener();

		} else if(SpielDao.getInstance().getSpiel().getCurrentSpieler().canMoveAny()) {
			int auswahl = 0;
			while (true) {
				while(true) {
					model.noticeSpielerAuswaehlenListener();
					//view.spielerAuswählen();
					try {
						auswahl = View.keyboardAbfragenNachInteger(0, 3);
						break;
					} catch (NoSuchElementException ignored) {
					}
				}


				try {
					ziehen(auswahl);
					break;
				} catch (Wissensstreiter.CantMoveException ignored) {
				}
			}
			model.noticeBewegungAnzeigenListener(auswahl);
			//view.bewegungAnzeigen(auswahl);
		} else {
			model.noticeKeineAktionMoeglichListener();
			//view.keineAktionMöglich();
		}

		SpielDao.getInstance().getSpiel().setStatus(Spiel.Status.WÜRFELN);
	}

	@Override
	public void wuerfeln() {
		if(SpielDao.getInstance().getSpiel().getStatus() != Spiel.Status.WÜRFELN) {
			throw new IllegalStateException();
		}
		Spiel spiel = SpielDao.getInstance().getSpiel();

		spiel.getWürfel().ermittleNeuenWert();
		if(!spiel.getCurrentSpieler().canSet()
				&& !spiel.getCurrentSpieler().canMoveAny()
				&& spiel.getWürfel().getWurfanzahl() < 3) {
			SpielDao.getInstance().getSpiel().setStatus(Spiel.Status.WÜRFELN);
		} else {
			SpielDao.getInstance().getSpiel().setStatus(Spiel.Status.ZIEHEN);
		}
	}

	@Override
	public void spielerWechseln() {
		SpielDao.getInstance().getSpiel().changeNextSpieler();
	}
}
