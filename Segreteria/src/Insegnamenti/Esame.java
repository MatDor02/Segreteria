package Insegnamenti;

import Persone.Professore;
import myInterfaces.Event;

public class Esame extends Materia implements Event {

	private byte cfu;
	private byte voto;
	private boolean superato;
	private String data, orario;
	private short durata; // in minuti
	
	public Esame(String nomeEsame, byte crediti, Professore prof, String data, String orario, short tempo) {
		super(nomeEsame, crediti, prof);
		durata = tempo;
		this.data = data;
		this.orario = orario;
	}

	public String getData() {
		return data;
	}

	public void setData(String data) {
		this.data = data;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}
	
	public byte getVoto() {
		return voto;
	}
	
	public boolean isSuperato() {
		return superato;
	}
	
	public void setVoto (byte valutazione) {
		
		if(valutazione >= 18) {
			voto = valutazione;
			superato = true;
		} //altrimenti restano al valore di default, cioe' voto e' 0, isSuperato e' false
	}
	
	public String  toString() {
		return "Data: " + data + " alle " + orario + "---|---" + "Nome: " + nome + " ---|--- " +
				"Voto: " + voto + " ---|--- " + "CFU: " + cfu;
	}
}
