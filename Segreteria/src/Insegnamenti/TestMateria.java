package Insegnamenti;

import java.util.*;

import Insegnamenti.Giorno.nomeGiorno;
import Persone.Professore;
import myExceptions.HourFormatException;

public class TestMateria {

	private static nomeGiorno day;
	private static String oraInizio, oraFine;

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Materia fisica = new Materia("fisica", (byte) 9, new Professore("Rouf"));
		
		do {
			System.out.println("\nCosa vuoi fare?");
			System.out.println("s) Stampa materia");
			System.out.println("o) Stampa orario");
			System.out.println("m) Modifica orario");
			System.out.println("e) Esci");
			System.out.print("Inserisci una lettera: ");
			
			switch(input.nextLine().charAt(0))
			{
			case 's', 'S':
				System.out.println(fisica);
				break;
			case 'o', 'O': {
				fisica.stampaOrario();
				break;
			}
			case 'm', 'M': {
				menuOrario(fisica);
				break;
			}
			case 'e', 'E': return;
			default:
				System.out.println("\nInput non valido, riprova!");
			}
		} while(true);
	}
	
	public static void menuOrario(Materia insegnamento) {
		
		Scanner input = new Scanner(System.in);
		do {
			System.out.println("\na) Aggiungi giorno");
			System.out.println("r) Rimuovi giorno");
			System.out.println("m) Modifica giorno");
			System.out.println("e) Torna al men� della materia");
			System.out.print("Inserisci una lettera: ");
			
			switch(input.nextLine().charAt(0))
			{
			case 'a', 'A': {
				scegliGiornoEOra();
				
				if(insegnamento.aggiungiGiorno(day, oraInizio, oraFine))
					System.out.println("\nGiorno di lezione aggiunto!");
				else
					System.out.println("\nInsegnamento gi� presente ogni giorno. Lezione non aggiunta!");
				break;
			}
			case 'r', 'R': {
				nomeGiorno day = Giorno.scegliGiorno();
				if(insegnamento.rimuoviGiorno(day))
					System.out.println("\nGiorno di lezione rimosso!");
				else
					System.out.println("\nNessuna lezione il " + day + "!");
				break;
			}
			case 'm', 'M': {
				scegliGiornoEOra();
				
				if(insegnamento.modificaGiorno(day, oraInizio, oraFine))
					System.out.println("\nOrario modifiato!");
				else {
					System.out.println("\nNessuna lezione il " + day + "!");
					System.out.print("Aggiungerne una con giorno e orari appena inseriti? [s/n]: ");
					if(input.nextLine().charAt(0) == 's' && insegnamento.aggiungiGiorno(day, oraInizio, oraFine))
						System.out.println("Giorno di lezione aggiunto!");
				}
				break;
			}
			case 'e', 'E': return;
			default:
				System.out.println("\nInput non valido!");
			}
		} while(true);
	}
	
	public static void scegliGiornoEOra() {
		Scanner input = new Scanner(System.in);
		
		day = Giorno.scegliGiorno();
		boolean inCatch = false;
		do {
			System.out.print("\nOrario di inizio [hh:mm]: ");
			oraInizio = input.nextLine();
			try {
				Check.orario(oraInizio);
			} catch (HourFormatException e) {
				System.out.println(e.getMessage());
				inCatch = true;
			}
		} while(inCatch);
		do {
			System.out.print("\nOrario di fine [hh:mm]: ");
			oraFine = input.nextLine();
			try {
				Check.orario(oraFine);
			} catch (HourFormatException e) {
				System.out.println(e.getMessage());
				inCatch = true;
			}
		} while(inCatch);
	}
	
}
