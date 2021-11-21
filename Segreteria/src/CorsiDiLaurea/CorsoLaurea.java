package CorsiDiLaurea;
import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.ObjectInputStream;
import java.util.InputMismatchException;
import java.util.Scanner;
import Insegnamenti.*;
import Insegnamenti.Giorno.nomeGiorno;
import Persone.*;
import myExceptions.HourFormatException;

import javax.sound.midi.Soundbank;

public class CorsoLaurea {
	// ATTRIBUTI
	private String nomeCorso;
	private String tipo = "Corso a Ciclo unico";
	private byte min_cfu;
	private Studente[] studenti = new Studente[200];
	private Materia[] ins_def;
	private Materia[] ins_scl;
	private Studente[] laureati = new Studente[200];
	private Studente[] fuoricorso = new Studente[200];
	// TODO private Studente studLoggato;
	private Professore profLoggato;
	Scanner input = new Scanner(System.in);
	// ATTRIBUTI ORARIO
	private static nomeGiorno day;
	private static String oraInizio, oraFine;

	//COSTRUTTORE
	public CorsoLaurea(String nomeCorso, byte min_cfu, byte n_def, byte n_scl) {
		this.nomeCorso = nomeCorso;
		this.min_cfu = min_cfu;
		ins_def = new Materia[n_def];
		ins_scl = new Materia[n_scl];
	}

	//GETTER
	public String get_nomeCorso() {
		return nomeCorso;
	}

	public Byte get_min_cfu() {
		return min_cfu;
	}

	public Materia[] get_ins_def() {
		return ins_def;
	}

	public Materia[] get_ins_scl() {
		return ins_scl;
	}

	// METODI AUSILIARI
	public static boolean vett_pieno(Object[] vettore) {
		byte i = 0;
		while (i < vettore.length && vettore[i] != null) i++;
		if (i == vettore.length) return true;
		else return false;
	}

	@Override
	public String toString() {
		return this.nomeCorso + "[anno accademico 2021/2022]\n"
				+ "TIPOLOGIA: " + this.tipo
				+ "\nNumero di CFU da conseguire per laurearsi: "
				+ this.min_cfu;
	}

	// METODI STUDENTI
	public boolean Iscrizione(String nome) {
		if (vett_pieno(studenti)) {
			System.out.println("Impossibile iscriversi, posti pieni!");
			return false;
		} else {
			byte i = 0;
			while (i < studenti.length && studenti[i] != null) i++;
			studenti[i] = new Studente(nome);
			if (studenti[i] != null) {
				System.out.println("Iscrizione avvenuta con successo!");
				return true;
			} else {
				System.out.println("Iscrizione non effettuata!");
				return false;
			}
		}
	}

	public Studente CercaStudente(String matricola, Studente[] dove) {
		byte i = 0;
		while (i < dove.length) {
			if (dove[i].get_matricola().equals(matricola))
				return dove[i];
			else i++;
		}

		System.out.println("Non esistono studenti iscritti al corso selezionato "
				+ "corrispondenti alla matricola inserita!");
		return null;
	}

	public void Ritiro(String matricola) {
		boolean c = true;
		do {
			System.out.println("Sei sicuro di voler disiscrivere lo studente da questo corso? "
					+ "[S] o [N]");
			String risposta;
			switch (risposta = input.next()) {
				case "S": {
					Studente s = CercaStudente(matricola, studenti);
					s = null;
					c = false;
					break;
				}
				case "N":
					c = false;
				default:
					System.out.println("Utilizza solo i caratteri consentiti!");
			}
		} while (c);
	}

	public boolean LaureaStudente(Studente candidato, byte votofinale) {
		if (CercaStudente(candidato.get_matricola(), studenti) != null) {
			if (candidato.get_CFU() >= this.min_cfu) {
				byte i = 0;
				while (i < laureati.length && laureati[i] != null) i++;
				candidato.set_voto_finale(votofinale);
				laureati[i] = candidato;
				Studente s = CercaStudente(candidato.get_matricola(), studenti);
				s = null;
				return true;
			}
		}
		return false;
	}

	public boolean FuoriCorso (String matricola) {
		if (CercaStudente(matricola, studenti).get_anno() > 5)
			return true;
		return false;
	}

		public void VisualizzaStudenti () {
			System.out.println("STUDENTI ISCRITTI AL CORSO DI " + this.nomeCorso + ":");
			for (byte i = 0; i < studenti.length; i++) {
				if (studenti[i] != null) System.out.println(studenti[i]);
			}
		}

		public void VisualizzaLaureati () {
			System.out.println("STUDENTI LAUREATI AL CORSO DI " + this.nomeCorso + ":");
			for (byte i = 0; i < laureati.length; i++) {
				if (laureati[i] != null) System.out.println(laureati[i]);
			}
		}
// METODI MATERIE	
		public boolean In_ins_def () {
			if (vett_pieno(ins_def)) {
				System.out.println("Numero massimo di materie raggiunto!");
				return false;
			} else {
				String nome;
				Professore prof;
				Esercitatore ex;
				byte crediti;
				for (byte i = 0; i < ins_def.length; i++) {
					if (ins_def[i] == null) {
						System.out.print("Inserire il nome della materia:\n-> ");
						nome = input.next();
						System.out.print("Inserire il nome del professore:\n-> ");
						String nomeprof = input.next();
						prof = new Professore(nomeprof);
						System.out.print("Inserire i CFU :\n-> ");
						crediti = input.nextByte();
						System.out.print("Inserire il nome dell'esercitatore (se c'é altrimenti premere 0):\n-> ");
						String nomeex = input.next();
						if (!nomeex.equals("0")) {
							ex = new Esercitatore(nomeex);
							ins_def[i] = new Materia(nome, crediti, prof, ex);
						} else ins_def[i] = new Materia(nome, crediti, prof);
					}
				}
			}
			return true;
		}

		public boolean In_ins_scl() {
			if (vett_pieno(ins_scl)) {
				System.out.println("Numero massimo di materie raggiunto!");
				return false;
			} else {
				String nome;
				Professore prof;
				Esercitatore ex;
				byte crediti;
				for (byte i = 0; i < ins_scl.length; i++) {
					if (ins_scl[i] == null) {
						System.out.print("Inserire il nome della materia:\n-> ");
						nome = input.next();
						System.out.print("Inserire il nome del professore:\n-> ");
						String nomeprof = input.next();
						prof = new Professore(nomeprof);
						System.out.print("Inserire i CFU :\n-> ");
						crediti = input.nextByte();
						System.out.print("Inserire il nome dell'esercitatore (se c'é altrimenti premere 0):\n-> ");
						String nomeex = input.next();
						if (!nomeex.equals("0")) {
							ex = new Esercitatore(nomeex);
							ins_def[i] = new Materia(nome, crediti, prof, ex);
						} else ins_def[i] = new Materia(nome, crediti, prof);
					}
				}
			}
			return true;
		}

		public boolean Out_ins_scl () {
			if (!vett_pieno(ins_scl)) {
				System.out.println("Non ci sono materie registrate!");
				return false;
			} else {
				boolean c = true;
				do {
					System.out.print("Inserire il nome della materia:\n-> ");
					String nome = input.next();
					byte i = 0;
					while (i < ins_scl.length) {
						if (ins_scl[i] != null && ins_scl[i].getNome().equals(nome)) {
							ins_scl[i] = null;
							c = false;
						} else i++;
					}
					if (i == ins_scl.length) System.out.println("Non esiste nessuna materia con il nome inserito!");
				} while (c);

			}
			return true;
		}

		public boolean Out_ins_def () {
			if (!vett_pieno(ins_def)) {
				System.out.println("Non ci sono materie registrate!");
				return false;
			} else {
				boolean c = true;
				do {
					System.out.print("Inserire il nome della materia:\n-> ");
					String nome = input.next();
					byte i = 0;
					while (i < ins_def.length) {
						if (ins_def[i] != null && ins_def[i].getNome().equals(nome)) {
							ins_def[i] = null;
							c = false;
						} else i++;
					}
					if (i == ins_def.length) System.out.println("Non esiste nessuna materia con il nome inserito!");
				} while (c);
			}
			return true;
		}

		public void VisualizzaMaterie () {
			System.out.println("MATERIE DEL CORSO DI " + this.nomeCorso + ":");
			for (byte i = 0; i < ins_def.length; i++) {
				if (ins_def[i] != null) System.out.println(ins_def[i]);
			}
			for (byte i = 0; i < ins_scl.length; i++) {
				if (ins_scl[i] != null) System.out.println(ins_scl[i]);
			}
		}

// METODI ORARIO MATERIE	

		public static void menuOrario (Materia insegnamento){

			Scanner input = new Scanner(System.in);

			do {
				insegnamento.stampaOrario();
				System.out.println("\na) Aggiungi giorno");
				System.out.println("r) Rimuovi giorno");
				System.out.println("m) Modifica giorno");
				System.out.println("e) Torna al menù della materia");
				System.out.print("Inserisci una lettera: ");

				switch (input.nextLine().charAt(0)) {
					case 'a', 'A': {
						scegliGiornoEOra();

						if (insegnamento.aggiungiGiorno(day, oraInizio, oraFine))
							System.out.println("\nGiorno di lezione aggiunto!");
						else
							System.out.println("\nInsegnamento giù presente ogni giorno. Lezione non aggiunta!");
						break;
					}
					case 'r', 'R': {
						nomeGiorno day = scegliGiorno();
						if (insegnamento.rimuoviGiorno(day))
							System.out.println("\nGiorno di lezione rimosso!");
						else
							System.out.println("\nNessuna lezione il " + day + "!");
						break;
					}
					case 'm', 'M': {
						scegliGiornoEOra();

						if (insegnamento.modificaGiorno(day, oraInizio, oraFine))
							System.out.println("\nOrario modifiato!");
						else {
							System.out.println("\nNessuna lezione il " + day + "!");
							System.out.print("Aggiungerne una con giorno e orari appena inseriti? [s/n]: ");
							if (input.nextLine().charAt(0) == 's' && insegnamento.aggiungiGiorno(day, oraInizio, oraFine))
								System.out.println("Giorno di lezione aggiunto!");
						}
						break;
					}
					case 'e', 'E':
						return;
					default:
						System.out.println("\nInput non valido!");
				}
			} while (true);
		}

		public static nomeGiorno scegliGiorno () {

			Scanner input = new Scanner(System.in);
			byte nGiorno = 1;
			do {
				if (nGiorno < 1 || nGiorno > 5)
					System.out.println("\nInput non valido!");

				System.out.println("\nChe giorno? ");
				System.out.println("1) Lunedi");
				System.out.println("2) Martedi");
				System.out.println("3) Mercoledi");
				System.out.println("4) Giovedi");
				System.out.println("5) Venerdi");
				System.out.print("Inserisci il numero del giorno feriale: ");

				if (input.hasNextByte()) {
					nGiorno = input.nextByte();
					input.nextLine(); // raccolgo l'invio
				} else {
					input.nextLine();
					nGiorno = 0; //cosi reinizia il ciclo ed entro nel primo if
				}
			} while (nGiorno < 1 || nGiorno > 5);

			return nomeGiorno.getPosition((byte) (nGiorno - 1));
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

// MENU'
		// TODO: per prima cosa leggiamo da file (CON SERIALIZZAZIONE) tutti gli attributi di questa classe.
		// 		Al termine della funzione scriviamo gli attributi sui relativi file
		public void menu_corso () {
			System.out.println("CORSO DI " + this.nomeCorso + "[anno accademico 2021/2022]\n" + this.tipo);
			boolean f = true;
			do {
				// TODO 1) sei uno studente? --> menu studente (gestione esami passati e futuri, stampa media, ecc.)
				//		2) sei un professore? --> menu professore (seleziona una materia che insegna, aggiunge nuovi appelli)
				//		3) sei un segretario? --> menu_corso() (gestisci studenti, modifica insegnamenti, orari, ...)
				//		4) esercitatore? --> menu_sfigato()
				//		5) goldrake? --> affila il ferro
				//		6) e tanto altro ancora (consultare la documentazione)
				//		......................
				System.out.println("COMANDI GESTIONE STUDENTI: ");
				System.out.println("I) Iscrivi uno studente a questo corso");
				System.out.println("C) Cerca uno studente tra quelli iscritti");
				System.out.println("R) Ritira uno studente");
				System.out.println("V) Visualizza gli studenti iscritti al corso");
				System.out.println("L) Laurea uno studente");
				System.out.println("U) Visualizza gli studenti laureati al corso");
				System.out.println("O) Cerca uno studente tra quelli laureati");
				System.out.println("COMANDI GESTIONE MATERIE:");
				System.out.println("Y) Inserisci un insegnamento di base");
				System.out.println("G) Rimuovi un insegnamento di base");
				System.out.println("J) Inserisci un insegnamento a scelta");
				System.out.println("X) Rimuovi un insegnamento a scelta");
				System.out.println("W) Modifica orario settimanale di una materia");
				System.out.println("M) Visualizza le materie del corso");
				System.out.println("\nE) Torna al menù precedente");

				String ris = input.nextLine();
				switch (ris) {
					case "i", "I": {
						System.out.println("Inserisci il nome e l'anno a cui iscrivere lo studente:\n-> ");
						String nome = input.next();
						Iscrizione(nome);
					}
					break;
					case "c", "C": {
						System.out.println("Inserisci la matricola dello studente\n-> ");
						String mat = input.next();
						CercaStudente(mat, studenti);
					}
					break;
					case "r", "R": {
						System.out.println("Inserisci la matricola dello studente\n-> ");
						String matr = input.next();
						Ritiro(matr);
					}
					break;
					case "v", "V":
						VisualizzaStudenti();
						break;
					case "l", "L": {
						System.out.println("Inserisci la matricola dello studente\n-> ");
						String matri = input.next();
						System.out.println("Inserisci il voto finale da dare allo studente\n-> ");
						byte voto = input.nextByte();
						LaureaStudente(CercaStudente(matri, studenti), voto);
					}
					break;
					case "u", "U":
						VisualizzaLaureati();
						break;
					case "o", "O": {
						System.out.println("Inserisci la matricola dello studente laureato\n-> ");
						String matric = input.next();
						CercaStudente(matric, laureati);
					}
					break;
					case "y", "Y":
						In_ins_def();
						break;
					case "g", "G":
						Out_ins_def();
						break;
					case "j", "J":
						In_ins_scl();
						break;
					case "x", "X":
						Out_ins_scl();
						break;
					case "w", "W": {
						byte cont = 1;
						for (byte x = 0; x < ins_def.length; x++)
							System.out.println(cont++ + ") " + ins_def[x].getNome());
						for (byte x = 0; x < ins_scl.length; x++)
							System.out.println(cont++ + ") " + ins_scl[x].getNome());
						System.out.println("Inserisci il numero corrispondente alla materia a cui si vuole accedere\n-> ");
						byte res = input.nextByte();
						if (res > (ins_def.length + ins_scl.length))
							System.out.println("Utilizza solo numeri <= di " + cont);
						else if (res < ins_def.length) menuOrario(ins_def[res - 1]);
						else menuOrario(ins_scl[res - ins_def.length - 1]);
					}
					case "m", "M":
						VisualizzaMaterie();
						break;
					case "e", "E":
						f = false;
						break;
					default:
						System.out.println("Utilizza solo i caratteri consentiti!\n");
				}
			} while (f);
			System.out.println("[---]");
		}

	public void menuStudente() {
		Scanner input = new Scanner(System.in);

		System.out.print("Inserisci la matricola: ");
		String matricola = input.nextLine();

		if(!login(matricola))
			System.out.println("Tentativi esauriti, login non effettuato!");
		else {
			byte scelta = 99;

			do {
				System.out.println("\nCosa vuoi fare?");
				System.out.println("1) Stampa esami superati");
				System.out.println("2) Prenota un esame");
				System.out.println("3) Stampa media voti");
				System.out.println("4) Visualizza orario di una materia");
				System.out.println("0) Esci");
				System.out.print("Inserisci il numero corrispondente alla tua scelta: ");

				try {
					scelta = input.nextByte();
				} catch (InputMismatchException e) {
					System.out.println("\nHai inserito '" + input.nextLine() + "', che non è un numero.");
					continue;
				}
				input.nextLine();// prendo l'invio dopo il netByte andato a buon fine

				if (scelta < 0 || scelta > 4)
					System.out.println("\nInserisci un numero compreso tra 0 e 4");
				else {
					switch (scelta) {
						case 0:
							break;
						case 1: {

						}
						case 2: {

						}
						case 3: {

						}
						case 4: {

						}
					}
				}

			} while (scelta != 0);
		}
	}

	private boolean login(String matricola) {
		// TODO: controlliamo password (basandoci sulla matricola inserita)
		// 		Se studente, assegnamo a "studLoggto" lo studente con le credenziali corrispondenti
		//		Se professore, assegnamo a profLoggato
	}

	private void menuProf() {
		Scanner input = new Scanner(System.in);

		System.out.print("Inserisca matricola: ");
		String matricola = input.nextLine();

		if(!login(matricola))
			System.out.println("Tentativi esauriti, login non effettuato!");
		else {
			byte scelta = 99, scelta1 = 99;
			do {
				System.out.println("Cosa desidera fare?");
				System.out.println("1) Aggiungere un esame");
				System.out.println("2) Inserire il voto di uno studente per un esame");
				System.out.println("0) Uscire");
				System.out.print("Numero corrispondente alla scelta: ");
				try {
					scelta = input.nextByte();
				} catch (InputMismatchException e) {
					System.out.println("\nHai inserito '" + input.nextLine() + "', che non è un numero.");
					continue;
				}
				input.nextLine();// prendo l'invio dopo il netByte andato a buon fine

				if(scelta < 0 || scelta > 1)
					System.out.println("Inserisci numeri compresi tra 0 e 1");
				else {
					switch (scelta) {
						case 0:
							break;
						case 1: {
							byte cont = 0;
							do {
								System.out.println();
								for (Materia m : ins_def)
									if(profLoggato.getMatricola().equals(m.getProf().getMatricola()))
										System.out.println(++cont + ") " + m.getNome());
								for (Materia m : ins_scl)
									if(profLoggato.getMatricola().equals(m.getProf().getMatricola()))
										System.out.println(++cont + ") " + m.getNome());
								System.out.print("Scelga il numero affianco a uno dei suoi insegnamenti, oppure zero per uscire: ");
								try {
									scelta1 = input.nextByte();
								} catch (InputMismatchException e) {
									System.out.println("\nHa inserito '" + input.nextLine() + "', che non è un numero.");
									continue;
								}
								input.nextLine(); // prendo l'invio dopo il nextDouble andato a buon fine
								if(scelta1 < 0 || scelta1 > cont)
									System.out.println("\nInserisca numeri compresi tra 0 e " + cont);
								else {
									if(scelta1 <= ins_def.length)
										ins_def[scelta1 - 1].aggiungiEsame();
									else
										ins_scl[scelta1 - 1 - ins_def.length].aggiungiEsame();
								}

							} while (scelta1 != 0);
						}
						case 2: {

						}
					}
				}

			} while (scelta != 0);
		}
	}

}
