
package Application.Benutzerschnittstelle.View;

import Application.Anwendung.Model.Model;
import Application.Persistenz.DAO.SpielDao;
import Application.Persistenz.*;
import Design.Observer;

import java.io.IOException;
import java.util.List;
import java.util.NoSuchElementException;
import java.util.Scanner;


public class View {
	private final Model model;

	public View(Model model) {
		this.model = model;
	}

	public static int keyboardAbfragenNachInteger(int rangeFrom, int rangeTo) throws NoSuchElementException {
		Scanner scanner = new Scanner(System.in);

		//try (Scanner scanner = new Scanner(System.in)) {
			while (!scanner.hasNextInt()) {
				scanner.next(); // Skip everything thats not a number
			}
			int input = scanner.nextInt() - 1; // read number
			scanner.nextLine(); //skip until after ENTER

			if (input < rangeFrom || input > rangeTo) {
				throw new NoSuchElementException(); // Throw if not in range
			} else {
				return input;
			}
		//}

	}

	public static void pressEnterToContinue() {
		try {
			System.in.skip(System.in.available());
			System.in.read();
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	private String zahl2FeldNr(int zahl) {
		switch (zahl) {
			case 0:
				return "¹";
			case 1:
				return "²";
			case 2:
				return "³";
			default:
				return "⁴";
		}
	}

	private String zahl2Str(int zahl) {
		switch (zahl) {
			case 1:
				return "Eins";
			case 2:
				return "Zwei";
			case 3:
				return "Drei";
			case 4:
				return "Vier";
			case 5:
				return "Fünf";
			default:
				return "Sechs";
		}
	}

	/**
	 * Würfelt..... Fünf!
	 */
	public void würfelErgebnisAnzeigen() {
		int zahl = SpielDao.getInstance().getSpiel().getWürfel().getZahl();
		String zahlStr = zahl2Str(zahl);

		try {
			System.out.print("\nWürfelt");
			for (int i = 0; i < 3; i++) {
				Thread.sleep(500);
				System.out.print(".");
			}
			Thread.sleep(500);
			System.out.println(zahlStr + "!");
		} catch (InterruptedException e) {
			e.printStackTrace();
		}

	}

	/**
	 * 😈² bewegte sich um fünf Felder.
	 *
	 * @param auswahl Die Auswahl des Spielers
	 */
	public void bewegungAnzeigen(int auswahl) {
		Wissensstreiter wissensstreiter = SpielDao.getInstance().getSpiel().getCurrentSpieler().getWissensstreiter(auswahl);
		int schritte = SpielDao.getInstance().getSpiel().getWürfel().getZahl();

		System.out.println("\n" +
				wissensstreiter.getSpieler().getSymbol() + zahl2FeldNr(wissensstreiter.getNummer()) + " bewegte sich um " + zahl2Str(schritte).toLowerCase() + " Felder.\n");
	}

	public void endeZug() {
		System.out.println("\n==================================================================================================================\n");
	}

	public boolean spielerAuswählen() {
		int würfelzahl = SpielDao.getInstance().getSpiel().getWürfel().getZahl();
		Spieler aktuellerSpieler = SpielDao.getInstance().getSpiel().getCurrentSpieler();

		if(!aktuellerSpieler.canMoveAny()) {
			System.out.println("Du kannst leider keinen Wissensstreiter bewegen.");
			return false;
		}

		boolean w1 = aktuellerSpieler.getWissensstreiter(0).canMove(würfelzahl);
		boolean w2 = aktuellerSpieler.getWissensstreiter(1).canMove(würfelzahl);
		boolean w3 = aktuellerSpieler.getWissensstreiter(2).canMove(würfelzahl);
		boolean w4 = aktuellerSpieler.getWissensstreiter(3).canMove(würfelzahl);




		System.out.println();
		System.out.print("Wähle deinen Wissensstreiter (");
		if (w1) {
			System.out.print("1");
			if (w2 || w3 || w4) {
				System.out.print(", ");
			}
		}
		if (w2) {
			System.out.print("2");
			if (w3 || w4) {
				System.out.print(", ");
			}
		}
		if (w3) {
			System.out.print("3");
			if (w4) {
				System.out.print(", ");
			}
		}
		if (w4) {
			System.out.print("4");
		}

		System.out.print("): ");
		return true;
	}

	/**
	 * [😀]-o-o-o-o-o-o-😑-o-o-o-o-[ ]-o-o-o-o-o-😈¹-o-o-o-o-o-[ ]-o-o-o-😋-😈²-o-o-o-o-o-o-[ ]-o-o-o-😋-o-o-o-o-o-o-o
	 */
	public void spielfeldAnzeigen() {
		Spielfeld spielfeld = SpielDao.getInstance().getSpiel().getSpielfeld();

		System.out.println();
		for (int i = 0; i < spielfeld.getSize(); ) {
			Feld feld = spielfeld.getFelder().get(i);

			if (feld.isStartfeld()) {
				System.out.print("[");
			}

			if (feld.getWissensstreiter() != null) {
				Wissensstreiter wissensstreiter = feld.getWissensstreiter();
				System.out.print(wissensstreiter.getSpieler().getSymbol());

				if (wissensstreiter.getSpieler().equals(SpielDao.getInstance().getSpiel().getCurrentSpieler())) {
					System.out.print(zahl2FeldNr(wissensstreiter.getNummer()));
				}

			} else {
				System.out.print(" ");
			}

			if (feld.isStartfeld()) {
				System.out.print("]");
			}

			if (++i < spielfeld.getSize()) {
				System.out.print("-");
			}
		}

		System.out.println();
	}

	/**
	 * Spieler 😈, du bist am Zug!
	 * <p>
	 * Dein aktueller Wissensstand ist:
	 * Kategorie "Mathe":      Unwissender
	 * Kategorie "Geschichte": Anfänger
	 * Kategorie "Informatik": Profi
	 * Kategorie "Erdkunde":   Anfänger
	 * <p>
	 * Zum Würfeln, drücke <<Enter>>
	 */
	public void aktuellenStandAnzeigen() {
		Spiel spiel = SpielDao.getInstance().getSpiel();
		Spieler spieler = spiel.getCurrentSpieler();
		List<Fragekategorie> fragekategorien = spiel.getFragekategorien();

		System.out.printf("Spieler %s%s, du bist am Zug!\n" +
				"\n" +
				"Dein aktueller Wissensstand ist:\n", spieler.getName(), spieler.getSymbol());

		for (Fragekategorie fragekategorie : fragekategorien) {
			System.out.printf("Kategorie \"%s\": %s\n", fragekategorie.getTitel(), spieler.getWissensstand(fragekategorie).getLevelAsString());
		}


	}

	public void keineAktionMöglich() {
		System.out.println("Keine Aktion Möglich.\n");
	}

	public void wissensstreiterWurdeGesetzt() {
		System.out.println("Ein Wissensstreiter wurde auf das Spielfeld gesetzt.\n");
	}

	public void zumWuerfelnAuffordern() {
		System.out.println("\nZum Würfeln, drücke <<Enter>>");
	}
}
