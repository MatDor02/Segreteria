package gestioneSegreteria;

public class Esame extends Materia {

	private byte cfu;
	private byte voto;
	private boolean superato;
	
	public Esame(String nomeEsame, byte crediti) {
		super(nomeEsame, crediti);
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
	
	public String  toString() {
		return "Nome: " + nome + " ---|--- " + "Numero cfu: " + cfu + " ---|--- " + "Votatio: " + voto;
	}
	
}
