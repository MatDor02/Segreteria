package gestioneSegreteria;

public class Giorno {
	
	protected enum nomeGiorno {lunedi, martedi, mercoledi, giovedi, venerdi};
	protected nomeGiorno nome;
	
	public Giorno(nomeGiorno nome) {
		this.nome = nome;
	}
	
}
