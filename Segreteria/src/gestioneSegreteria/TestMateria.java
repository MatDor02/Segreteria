package gestioneSegreteria;

import java.util.*;

import gestioneSegreteria.Giorno.nomeGiorno;

public class TestMateria {

	private static nomeGiorno day;
	private static String oraInizio, oraFine;

	public static void main(String[] args) {
		
		Scanner input = new Scanner(System.in);
		
		Materia fisica = new Materia("fisica", (byte) 9);
		
		do {
			System.out.println("\nCosa vuoi fare?");
			System.out.println("s) Stampa materia");
			System.out.println("o) Stampa orario");
			System.out.println("m) Modifica orario");
			System.out.println("e) Esci");
			System.out.print("Inserisci una lettera: ");
			
			switch(input.nextLine().charAt(0))
			{
			case 's', 'S': {
				fisica.toString();
				break;
			}
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
			System.out.println("e) Torna al menù della materia");
			System.out.print("Inserisci una lettera: ");
			
			switch(input.nextLine().charAt(0))
			{
			case 'a', 'A': {
				scegliGiornoEOra();
				
				if(insegnamento.aggiungiGiorno(day, oraInizio, oraFine))
					System.out.println("\nGiorno di lezione aggiunto!");
				else
					System.out.println("\nInsegnamento già presente ogni giorno. Lezione non aggiunta!");
				break;
			}
			case 'r', 'R': {
				nomeGiorno day = scegliGiorno();
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

	public static nomeGiorno scegliGiorno() {
		
		Scanner input = new Scanner(System.in);
		byte nGiorno = 1;
		do {
			if(nGiorno < 1 || nGiorno > 5)
				System.out.println("\nInput non valido!");
			
			System.out.println("\nChe giorno? ");
			System.out.println("1) Lunedi");
			System.out.println("2) Martedi");
			System.out.println("3) Mercoledi");
			System.out.println("4) Giovedi");
			System.out.println("5) Venerdi");
			System.out.print("Inserisci il numero del giorno feriale: ");
			
			if(input.hasNextByte()) {
				nGiorno = input.nextByte();
				input.nextLine(); // raccolgo l'invio
			}
			else {
				input.nextLine();
				nGiorno = 0; //cosi reinizia il ciclo ed entro nel primo if
			}
		} while(nGiorno < 1 || nGiorno > 5);
		
		return nomeGiorno.getPosition((byte)(nGiorno - 1));
	}
	
	public static void scegliGiornoEOra() {
		Scanner input = new Scanner(System.in);
		
		day = scegliGiorno();
		do {
			System.out.print("\nOrario di inizio [hh:mm]: ");
			oraInizio = input.nextLine();
			if(!Check.orario(oraInizio))
				System.out.println("\nFormato orario non valido, riprova!");
		} while(!Check.orario(oraInizio));
		do {
			System.out.print("\nOrario di fine [hh:mm]: ");
			oraFine = input.nextLine();
			if(!Check.orario(oraFine))
				System.out.println("\nFormato orario non valido, riprova!");
		} while(!Check.orario(oraFine));
	}
	
}
