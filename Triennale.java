package segreteria;

public class Triennale extends CorsoLaurea {
	private String tipo = "Corso Triennale";
	public Triennale (String nomeCorso, byte min_cfu, byte n_def, byte n_scl) {
		super (nomeCorso, min_cfu, n_def, n_scl);
	}

}
