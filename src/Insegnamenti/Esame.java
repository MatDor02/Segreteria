package Insegnamenti;

import Persone.*;
import myExceptions.InvalidDateException;
import myInterfaces.Event;

import java.time.*;
import java.time.format.DateTimeFormatter;
import java.util.InputMismatchException;
import java.util.Scanner;
import java.util.Vector;

import myInputReader.*;

public class Esame extends Materia implements Event {

	private byte voto, cfu;
	private boolean superato;
	private LocalDateTime dataEOra;
	private short durata; // in minuti
	private Vector<Studente> studenti = new Vector<Studente>();

	
	public Esame(String nomeEsame, byte crediti, Professore prof, LocalDateTime dataEOra, short durata) {
		super(nomeEsame, crediti, prof);
		this.durata = durata;
		this.dataEOra = dataEOra;
	}

	public byte getVoto() {
		return voto;
	}

	public void setVoto (byte valutazione) {

		if(valutazione >= 18) {
			voto = valutazione;
			superato = true;
		}
		else
			voto = -1;
	}

	public boolean isSuperato() {
		return superato;
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
				if (dataEOra.compareTo(LocalDateTime.now()) <= 0)
					throw new InvalidDateException("\nData non valida: non è una data futura.");

			} catch (DateTimeException e) {
				System.out.println(e.getMessage());
				inCatch = true;
			} catch (InvalidDateException e) {
				System.out.println(e.getMessage());
				inCatch = true;
			}
		} while (inCatch);

		return dataEOra;
	}

	public Vector<Studente> getStudenti() {
		return studenti;
	}
	public void aggiungiStudente(Studente stud) {
		studenti.add(stud);
	}

	public boolean equals(Object o) {
		if(!(o instanceof Esame))
			return false;
		if (this == o)
			return true;
		Esame esame = (Esame) o;
		return nome.equals(esame.getNome()) && dataEOra.equals(esame.getDataEOra());
	}

	public String  toString() {
		DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd-MM-yyyy HH:mm");

		String str = "Data e ora: " + dataEOra.format(formatter) + " ---|--- " + "Nome: " + nome + " ---|--- " +
					 "CFU: " + cfu;
		if(voto !=  0) {
			if(voto == -1)
				str.concat(" ---|--- Voto: insufficiente");
			else
				str.concat(" ---|--- Voto: " + voto);
		}
		return str;
	}
}
