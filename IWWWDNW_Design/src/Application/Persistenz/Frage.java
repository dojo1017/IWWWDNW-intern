package Application.Persistenz;
public class Frage {
	private final String text;
	private final Fragekategorie fragekategorie;

	public Frage(String text, Fragekategorie fragekategorie) {
		this.text = text;
		this.fragekategorie = fragekategorie;
	}
}
