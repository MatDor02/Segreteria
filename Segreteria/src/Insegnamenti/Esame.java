package Insegnamenti;

public class Esame extends Materia {

	private byte cfu;
	private byte voto;
	private boolean superato;
	private GiornoEsame giorno;
	private short durata; // in minuti
	
	public Esame(String nomeEsame, byte crediti, short tempo, GiornoEsame giorno) {
		super(nomeEsame, crediti);
		durata = tempo;
		this.giorno = giorno;
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
		} //altrimenti restano al valore di default, cio� voto � 0, isSuperato � false
	}
	
	public String  toString() {
		return "Data: " + giorno.getData() + "---|---" + "Nome: " + nome + " ---|--- " + "Numero crediti: " + cfu + " ---|--- " + "Voto: " + voto;
	}
}
