
package Application.Anwendung.Model;

import Application.Anwendung.Model.UCModel.Funktion.Initial;
import Application.Anwendung.Model.UCModel.Funktion.SpielerWechseln;
import Application.Anwendung.Model.UCModel.Funktion.Wuerfeln;
import Application.Anwendung.Model.UCModel.Funktion.Ziehen;
import Application.Anwendung.Model.UCModel.UCModelInit;
import Application.Anwendung.Model.UCModel.UCModelSpielzugDurchführen;
import Application.Persistenz.DAO.SpielDao;
import Application.Persistenz.Wissensstreiter;

import java.util.ArrayList;
import java.util.List;


/**
 * Fassade für das Model.
 */
public class Model implements Initial, Wuerfeln, Ziehen, SpielerWechseln {
	List<ListenerOhneParameter> aufStartfeldGesetztListeners = new ArrayList<>();
	List<ListenerOhneParameter> aufSpielerAuswaehlenListeners = new ArrayList<>();
	private UCModelInit ucModelInit = new UCModelInit(this);
	private UCModelSpielzugDurchführen ucModelSpielzugDurchführen = new UCModelSpielzugDurchführen(this);
	private List<ListenerMitIntParameter> aufBewegungAnzeigenListeners = new ArrayList<>();
	private List<ListenerOhneParameter> aufKeineAktionMoeglichListeners = new ArrayList<>();

	@Override
	public void init() {
		ucModelInit.init();
	}

	@Override
	public void wuerfeln() {
		ucModelSpielzugDurchführen.wuerfeln();
	}

	@Override
	public void ziehen() {
		ucModelSpielzugDurchführen.ziehen();
	}

	@Override
	public void ziehen(int auswahl) throws Wissensstreiter.CantMoveException {
		ucModelSpielzugDurchführen.ziehen(auswahl);
	}

	@Override
	public void spielerWechseln() {
		ucModelSpielzugDurchführen.spielerWechseln();
	}

	public void addAufStartfeldGesetztListener(ListenerOhneParameter aufStartfeldGesetztListener) {
		aufStartfeldGesetztListeners.add(aufStartfeldGesetztListener);
	}

	public void removeAufStartfeldGesetztListener(ListenerOhneParameter aufStartfeldGesetztListener) {
		aufStartfeldGesetztListeners.remove(aufStartfeldGesetztListener);
	}

	public void noticeAufStartfeldGesetztListener() {
		for (ListenerOhneParameter l : aufStartfeldGesetztListeners) {
			l.update();
		}
	}

	public void addSpielerAuswaehlenListener(ListenerOhneParameter aufSpielerAuswaehlenListener) {
		aufSpielerAuswaehlenListeners.add(aufSpielerAuswaehlenListener);
	}

	public void removeSpielerAuswaehlenListener(ListenerOhneParameter aufSpielerAuswaehlenListener) {
		aufSpielerAuswaehlenListeners.remove(aufSpielerAuswaehlenListener);
	}

	public void noticeSpielerAuswaehlenListener() {
		for (ListenerOhneParameter l : aufSpielerAuswaehlenListeners) {
			l.update();
		}
	}

	public void addBewegungAnzeigenListener(ListenerMitIntParameter listener) {
		aufBewegungAnzeigenListeners.add(listener);
	}

	public void removeBewegungAnzeigenListener(ListenerMitIntParameter listener) {
		aufBewegungAnzeigenListeners.remove(listener);
	}

	public void noticeBewegungAnzeigenListener(int auswahl) {
		for (ListenerMitIntParameter l : aufBewegungAnzeigenListeners) {
			l.update(auswahl);
		}
	}

	public void addKeineAktionMoeglichListener(ListenerOhneParameter listener) {
		aufKeineAktionMoeglichListeners.add(listener);
	}

	public void removeKeineAktionMoeglichListener(ListenerOhneParameter listener) {
		aufKeineAktionMoeglichListeners.remove(listener);
	}

	public void noticeKeineAktionMoeglichListener() {
		for (ListenerOhneParameter l : aufKeineAktionMoeglichListeners) {
			l.update();
		}
	}

	/*public void noticeOnceAufStartfeldGesetztListener() {
		Iterator<ListenerOhneParameter> iterator = aufStartfeldGesetztListeners.iterator();
		while(iterator.hasNext()) {
			ListenerOhneParameter l = iterator.next();
			l.update();
			removeAufStartfeldGesetztListener(l);
		}
	}*/


	public interface ListenerOhneParameter {
		void update();
	}

	public interface ListenerMitIntParameter {
		void update(int param1);
	}
}
