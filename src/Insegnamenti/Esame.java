package Insegnamenti;

import Persone.*;
import myInterfaces.Event;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import myInputReader.*;

public class Esame extends Materia implements Event {

	private byte cfu;
	private byte voto;
	private boolean superato;
	private LocalDateTime dataEOra;
	private short durata; // in minuti
	
	public Esame(String nomeEsame, byte crediti, Professore prof, LocalDateTime dataEOra, short durata) {
		super(nomeEsame, crediti, prof);
		this.durata = durata;
		this.dataEOra = dataEOra;
	}

	public LocalDateTime getDataEOra() {
		return dataEOra;
	}

	public void setDataEOra(LocalDateTime dataEOra) {
		this.dataEOra = dataEOra;
	}

	public static LocalDateTime scegliDataEOra() {
		myScanner input = new myScanner(System.in);
		LocalDateTime dataEOra = null;
		int giorno, mese, anno, ora, minuto;
		boolean inCatch;
		do {
			inCatch = false;
			System.out.print("\nScelga la data. ");
			do {
				anno = input.nextInt("\nAnno: ");
				if(anno != 2021 && anno != 2022)
					System.out.println("\nAnno non valido.");
			} while (anno != 2021 && anno != 2022);

			mese = input.nextInt("\nMese: ");
			giorno = input.nextInt(("\nGiorno: "));

			System.out.println("\nInserisca l'orario di inizio (prima l'ora, poi il minuto)");
			ora = input.nextInt("\nOra: ");
			minuto = input.nextInt("\nMinuto: ");

			input.nextLine(""); // prendo l'invio dopo l'ultimo nextInt andato a buon fine
			try {
				dataEOra = LocalDateTime.of(anno, mese, giorno, ora, minuto);

			} catch (DateTimeException e) {
				System.out.println(e.getMessage());
				inCatch = true;
			}
		} while (inCatch);

		return dataEOra;
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
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		return "Data e ora: " + dataEOra.format(formatter) + "---|---" + "Nome: " + nome + " ---|--- " +
				"Voto: " + voto + " ---|--- " + "CFU: " + cfu;
	}
}
