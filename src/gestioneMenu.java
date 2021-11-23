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
        corso.menu_corso();
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
                    System.out.println("\nHai inserito '" + input.nextLine() + "', che non è un numero.");
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
                System.out.println("\nHa inserito '" + input.nextLine() + "', che non è un numero.");
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
