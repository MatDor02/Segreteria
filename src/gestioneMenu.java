import CorsiDiLaurea.CorsoLaurea;
import Insegnamenti.*;
import Persone.*;
import CorsiDiLaurea.*;

import java.util.InputMismatchException;
import java.util.Scanner;

public class gestioneMenu {

    private static Studente studLoggato;
    private static Professore profLoggato;

    public static void menuCorsoLaurea(CorsoLaurea corso) {
              // MENU'
		// TODO: per prima cosa leggiamo da file (CON SERIALIZZAZIONE) tutti gli attributi di questa classe.
		// 		Al termine della funzione scriviamo gli attributi sui relativi file
			System.out.println("CORSO DI " + corso.get_nomeCorso() + "[anno accademico 2021/2022]\n" + corso.getTipo());
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
						corso.Iscrizione(nome);
					}
					break;
					case "c", "C": {
						System.out.println("Inserisci la matricola dello studente\n-> ");
						String mat = input.next();
						corso.CercaStudente(mat, studenti);
					}
					break;
					case "r", "R": {
						System.out.println("Inserisci la matricola dello studente\n-> ");
						String matr = input.next();
						corso.Ritiro(matr);
					}
					break;
					case "v", "V":
						corso.VisualizzaStudenti();
						break;
					case "l", "L": {
						System.out.println("Inserisci la matricola dello studente\n-> ");
						String matri = input.next();
						System.out.println("Inserisci il voto finale da dare allo studente\n-> ");
						byte voto = input.nextByte();
						corso.LaureaStudente(CercaStudente(matri, studenti), voto);
					}
					break;
					case "u", "U":
						corso.VisualizzaLaureati();
						break;
					case "o", "O": {
						System.out.println("Inserisci la matricola dello studente laureato\n-> ");
						String matric = input.next();
						corso.CercaStudente(matric, laureati);
					}
					break;
					case "y", "Y":
						corso.In_ins_def();
						break;
					case "g", "G":
						corso.Out_ins_def();
						break;
					case "j", "J":
						corso.In_ins_scl();
						break;
					case "x", "X":
						corso.Out_ins_scl();
						break;
					case "w", "W": {
						byte cont = 1;
						for (byte x = 0; x < corso.get_ins_def().length; x++)
							System.out.println(cont++ + ") " + corso.get_ins_def()[x].getNome());
						for (byte x = 0; x < corso.get_ins_scl().length; x++)
							System.out.println(cont++ + ") " + corso.get_ins_scl()[x].getNome());
						System.out.println("Inserisci il numero corrispondente alla materia a cui si vuole accedere\n-> ");
						byte res = input.nextByte();
						if (res > (corso.get_ins_def().length + corso.get_ins_scl().length))
							System.out.println("Utilizza solo numeri <= di " + cont);
						else if (res < corso.get_ins_def().length) menuOrario(corso.get_ins_def()[res - 1]);
						else menuOrario(corso.get_ins_scl()[res - corso.get_ins_def().length - 1]);
					}
					case "m", "M":
						corso.VisualizzaMaterie();
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
  

    public static void menuStudente(CorsoLaurea corso) {
        Scanner input = new Scanner(System.in);

        System.out.print("Inserisci la matricola: ");
        String matricola = input.nextLine();

        if(!login(matricola, corso))
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
                    System.out.println("\nHai inserito '" + input.nextLine() + "', che non e' un numero.");
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

    public static void menuProf(CorsoLaurea corso) {
        Scanner input = new Scanner(System.in);

        System.out.print("Inserisca matricola: ");
        String matricola = input.nextLine();

        if(!login(matricola, corso))
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
                    System.out.println("\nHai inserito '" + input.nextLine() + "', che non e' un numero.");
                    continue;
                }
                input.nextLine();// prendo l'invio dopo il netByte andato a buon fine

                if(scelta < 0 || scelta > 1)
                    System.out.println("\nInserisci numeri compresi tra 0 e 1");
                else {
                    switch (scelta) {
                        case 0:
                            break;
                        case 1: {
                            Materia materiaScelta = scegliMateriaProf(corso);
                            materiaScelta.aggiungiEsame();
                        }
                        case 2: {
                            Materia materiaScelta = scegliMateriaProf(corso);

                        }
                    }
                }

            } while (scelta != 0);
        }
    }

    private static Materia scegliMateriaProf(CorsoLaurea corso) {
        Scanner input = new Scanner(System.in);
        byte scelta = -1;
        byte cont;
        boolean inCatch;
        do {
            cont = 0;
            inCatch = false;
            System.out.println();
            for (Materia m : corso.get_ins_def())
                if(profLoggato.getMatricola().equals(m.getProf().getMatricola()))
                    System.out.println(++cont + ") " + m.getNome());
            for (Materia m : corso.get_ins_scl())
                if(profLoggato.getMatricola().equals(m.getProf().getMatricola()))
                    System.out.println(++cont + ") " + m.getNome());
            System.out.print("Scelga il numero affianco a uno dei suoi insegnamenti, oppure zero per uscire: ");
            try {
                scelta = input.nextByte();
                if(scelta < 0 || scelta > cont)
                    throw new IndexOutOfBoundsException("\nInserisca numeri compresi tra 0 e " + cont);
            } catch (InputMismatchException e) {
                System.out.println("\nHa inserito '" + input.nextLine() + "', che non e' un numero.");
                inCatch = true;
                continue;
            } catch (IndexOutOfBoundsException e) {
                System.out.println(e.getMessage());
                inCatch = true;
                continue;
            }
            input.nextLine(); // prendo l'invio dopo il nextDouble andato a buon fine

        } while (inCatch);

        if (scelta <= corso.get_ins_def().length)
            return corso.get_ins_def()[scelta - 1];
        else
            return corso.get_ins_scl()[scelta - 1 - corso.get_ins_def().length];
    }

//  TODO: controlliamo password (basandoci sulla matricola inserita)
//  	  Se studente, assegnamo a "studLoggto" lo studente con le credenziali corrispondenti
//     	  Se professore, assegnamo a profLoggato
    private static boolean login(String matricola, CorsoLaurea corso) {
        //corso serve ad accedere ai vettori di studenti o di professori
    }

}
