package gestioneSegreteria;

import gestioneSegreteria.Giorno.nomeGiorno;
import java.util.*;

public class Materia {
	
	protected String nome;
	protected byte cfu;
	protected Professore prof;
	protected Esercitatore coProf;
	private String sito;
	private GiornoLezione[] orario = new GiornoLezione[5];
	
	public Materia(String nomeMateria, byte crediti) {
		nome = nomeMateria;
		cfu = crediti;
	}
	
	public Materia(String nomeMateria, byte crediti, Professore prof, String sitoCorso) {
		nome = nomeMateria;
		cfu = crediti;
		this.prof = prof;
		sito = sitoCorso;
	}
	
	public Materia(String nomeMateria, byte crediti, Professore prof, Esercitatore coProf, String sitoCorso) {
		nome = nomeMateria;
		cfu = crediti;
		this.prof = prof;
		this.coProf = coProf;
		sito = sitoCorso;
	}
	
	public String getNome() {
		return nome;
	}
	
	public byte getCfu() {
		return cfu;
	}
	
	public void setCfu(byte valore) {
		cfu = valore;
	}
	
	// aggiunge un giorno all'orario
	public boolean aggiungiGiorno(nomeGiorno nome, String oraInizio, String oraFine) {
		
		for (byte i = 0; i < orario.length; i++)
			if(orario[i] == null) {
				orario[i] = new GiornoLezione(nome, oraInizio, oraFine);
				return true;
			}
		return false;
	}
	
	// rimuove un giorno dall'orario
	public boolean rimuoviGiorno(nomeGiorno nome) {
		
		for(byte b = 0; b < orario.length; b++)
			if(orario[b].getNome().equals(nome)) {
				orario[b] = null;
				return true;
			}
		return false;
	}
	
	// modifica l'orario di un giorno già presente
	public boolean modificaGiorno(nomeGiorno daMod, String newBeginning, String newEnd) {
		
		for(byte b = 0; b < orario.length; b++)
			if (orario[b].getNome().equals(daMod)) {
				
				orario[b].setOraInizio(newBeginning);
				orario[b].setOraFine(newEnd);
				return true;
			}
		return false;
	}
	
	public void scegliGiorno() {
		
		Scanner input = new Scanner(System.in);
		byte nGiorno;
		nomeGiorno day;
		do {
			System.out.println("Che giorno? ");
			System.out.println("1) lunedi");
			System.out.println("2) martedi");
			System.out.println("3) mercoledi");
			System.out.println("4) giovedi");
			System.out.println("5) venerdi");
			System.out.print("Inserisci il numero del giorno feriale: ");
			
			if(input.hasNextByte()) {
				nGiorno = input.nextByte();
				input.nextLine(); // raccolgo l'invio
			}
			else {
				input.nextLine();
				System.out.println("Input non valido!");
			}
		} while(nGiorno < 1 || nGiorno > 5);
		
		day = getGiornoScelto(nGiorno);
		
		//crea nuovo giorno con orario ecc.
	}
	
	public nomeGiorno getGiornoScelto(byte posGiorno) {
		
		nomeGiorno giornoScelto;
		for(nomeGiorno g : nomeGiorno.values()) {
			if(g.ordinal() == posGiorno)
				giornoScelto = g;
		}
		return giornoScelto;
	}
	
}
