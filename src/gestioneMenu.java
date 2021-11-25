import CorsiDiLaurea.CorsoLaurea;
import Insegnamenti.*;
import Persone.*;
import CorsiDiLaurea.*;
import myInputReader.*;

import java.time.*;
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
        myScanner input = new myScanner(System.in);

        System.out.print("Inserisca matricola: ");
        String matricola = input.nextLine("");

        if(!login(matricola, corso))
            System.out.println("Tentativi esauriti, login non effettuato!");
        else {
            byte scelta = 99, scelta1 = 99;
            do {
                System.out.println("Cosa desidera fare?");
                System.out.println("1) Aggiungere un appello d'esame");
                System.out.println("2) Rimuovere un appello d'esame");
                System.out.println("3) Inserire il voto di uno studente per un esame");
                System.out.println("0) Uscire");
                scelta = input.nextByte("\nInserisca il numero corrispondente: ");
                input.nextLine("");// prendo l'invio dopo il netByte andato a buon fine

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
                            if(materiaScelta.getAppelli() == null || nessunAppelloFuturo(materiaScelta))
                                System.out.println("\nNessun appello futuro per questo insegnamento.");
                            else {
                                byte cont = 0, scelta2 = -1;
                                do {
                                    System.out.println("Ecco gli appelli d'esame futuri:");
                                    cont = 0;
                                    for (Esame e : materiaScelta.getAppelli())
                                        if (e.getDataEOra().compareTo(LocalDateTime.now()) > 0)
                                            System.out.println(++cont + ") " + e);
                                    scelta2 = input.nextByte("\nSelezioni il numero corrispondente: ");

                                    if(scelta2 < 1 || scelta2 > cont)
                                        System.out.println("\nErrore: inserisca un numero compreso tra 1 e " + cont);
                                    else {
                                        byte b = 0;
                                        while(materiaScelta.getAppelli().get(b).getDataEOra().compareTo(LocalDateTime.now()) <= 0)
                                            b++;
                                        materiaScelta.getAppelli().remove(b - 1 + cont);
                                        System.out.println("\n Appello rimosso!");
                                    };
                                } while(scelta2 < 1 || scelta2 > cont);
                            }
                        }
                        case 3: {
                            Materia materiaScelta = scegliMateriaProf(corso);
                            if(materiaScelta.getAppelli() == null ||
                               materiaScelta.getAppelli().get(0).getDataEOra().compareTo(LocalDateTime.now()) > 0)
                            {
                                System.out.println("\nNon ci sono appelli passati per " + materiaScelta.getNome());
                            }
                            else {
                                System.out.println("\nEcco l'ultimo appello:");
                                Esame ultimoAppello;
                                int i = 0;
                                while(i < materiaScelta.getAppelli().size() &&
                                      materiaScelta.getAppelli().get(i).getDataEOra().compareTo(LocalDateTime.now()) <= 0)
                                    i++;
                                ultimoAppello = materiaScelta.getAppelli().get(i - 1);
                                System.out.println(ultimoAppello);
                                boolean trovato = false;
                                Studente studTrovato = null;
                                do {
                                    System.out.print("\nInserisca la matricola dello studente a cui assegnare il voto: ");
                                    String matricolaStud = input.nextLine("");

                                    for (Studente s : ultimoAppello.getStudenti())
                                        if (s.get_matricola().equals(matricolaStud)) {
                                            trovato = true;
                                            studTrovato = s;
                                        }
                                    if (!trovato)
                                        System.out.println("\nNessuno studente iscritto a questo appello ha quella matricola!");
                                } while(!trovato);

                                System.out.println("\nStudente trovato!");
                                System.out.println(studTrovato);
                                byte voto = input.nextByte("\nInserisca la votazione: ");

                                for(Esame e : studTrovato.getEsamiPrenotati())
                                    if(e.equals(ultimoAppello) && !e.isSuperato())
                                        e.setVoto(voto);
                            }
                            
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

    public static boolean nessunAppelloFuturo(Materia materia) {
        boolean nessuno = true;
        for (Esame e : materia.getAppelli())
            if(e.getDataEOra().compareTo(LocalDateTime.now()) > 0)
                nessuno = false;
        return nessuno;
    }

//  TODO: controlliamo password (basandoci sulla matricola inserita)
//  	  Se studente, assegnamo a "studLoggto" lo studente con le credenziali corrispondenti
//     	  Se professore, assegnamo a profLoggato
    private static boolean login(String matricola, CorsoLaurea corso) {
        //corso serve ad accedere ai vettori di studenti o di professori
    }

}
