package gestioneSegreteria;

public class Esame extends Materia {

	private byte cfu;
	private byte voto;
	private boolean superato;
	private GiornoEsame quando;
	private short durata; // in minuti
	
	public Esame(String nomeEsame, byte crediti, short tempo, GiornoEsame giorno) {
		super(nomeEsame, crediti);
		durata = tempo;
		quando = giorno;
	}
	
	public byte getvoto() {
		return voto;
	}
	
	public boolean isSuperato() {
		return superato;
	}
	
	public void setVoto (byte valutazione) {
		
		if(valutazione >= 18) {
			voto = valutazione;
			superato = true;
		} //altrimenti restano al valore di default, cioè voto è 0, isSuperato è false
	}
	
	public short getDurata() {
		return durata;
	}
	
	public String  toString() {
		return "Nome: " + nome + " ---|--- " + "Numero crediti: " + cfu + " ---|--- " + "Voto: " + voto;
	}
	
}
