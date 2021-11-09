package segreteria;

public class Magistrale extends CorsoLaurea {
	private String tipo = "Corso Magistrale";
	
	public Magistrale (String nomeCorso, byte min_cfu, byte n_def, byte n_scl) {
		super (nomeCorso, min_cfu, n_def, n_scl);
	}
}
