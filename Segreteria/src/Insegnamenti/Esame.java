package Insegnamenti;

import Persone.Professore;
import myInterfaces.Event;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import myInputReader.myScanner;

public class Esame extends Materia implements Event {

	private byte cfu;
	private byte voto;
	private boolean superato;
	private LocalDate data;
	private String orario;
	private short durata; // in minuti
	
	public Esame(String nomeEsame, byte crediti, Professore prof, LocalDate data, String orario, short tempo) {
		super(nomeEsame, crediti, prof);
		durata = tempo;
		this.data = data;
		this.orario = orario;
	}

	public LocalDate getData() {
		return data;
	}

	public void setData(LocalDate data) {
		this.data = data;
	}

	public String getOrario() {
		return orario;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}

	public static LocalDate scegliData() {
		myScanner input = new myScanner(System.in);
		LocalDate data = null;
		int giorno, mese, anno;
		boolean inCatch;
		do {
			inCatch = false;
			System.out.print("\nScegliere la data. ");
			anno = input.nextInt("\nAnno: ");
			mese = input.nextInt("\nMese: ");
			giorno = input.nextInt(("\nGiorno: "));

			try {
				data = LocalDate.of(anno, mese, giorno);

			} catch (DateTimeException e) {
				System.out.println(e.getMessage());
				inCatch = true;
			}
		} while (inCatch);

		return data;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy");

		return "Data: " + data.format(formatter) + " alle " + orario + "---|---" + "Nome: " + nome + " ---|--- " +
				"Voto: " + voto + " ---|--- " + "CFU: " + cfu;
	}
}
