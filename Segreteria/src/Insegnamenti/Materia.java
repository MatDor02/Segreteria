package Insegnamenti;
import java.util.*;
import Insegnamenti.Check;
import Insegnamenti.Giorno.nomeGiorno;
import Persone.*;
import myExceptions.*;


public class Materia {

	protected String nome;
	protected byte cfu;
	protected Professore prof;
	protected Esercitatore coProf;
	private GiornoLezione[] orario = new GiornoLezione[5];
	private Vector <Esame> appelli = new Vector <Esame>();

	public Materia(String nomeMateria, byte crediti, Professore prof) {
		nome = nomeMateria;
		cfu = crediti;
		this.prof = prof;
	}

	public Materia(String nomeMateria, byte crediti, Professore prof, Esercitatore coProf) {
		nome = nomeMateria;
		cfu = crediti;
		this.prof = prof;
		this.coProf = coProf;
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

	public Professore getProf() {
		return prof;
	}

	public Esercitatore getEsercitatore() {
		return coProf;
	}

	public Vector<Esame> getAppelli() {
		return appelli;
	}

	public void aggiungiEsame() {
		Scanner input = new Scanner(System.in);
		String data;
		System.out.print("Che giorno sarà l'esame? [gg/mm/aa]: ");
		try {
			data = input.nextLine();
			if(!Check.isData(data))
				throw new DataFormatException;
		} catch (DataFormatException e) {

		}
	}

	// aggiunge un giorno all'orario
	public boolean aggiungiGiorno(nomeGiorno nome, String oraInizio, String oraFine) {

		for (byte b = 0; b < orario.length; b++)
			if (orario[b] == null) {
				orario[b] = new GiornoLezione(nome, oraInizio, oraFine);
				ordinaOrario();
				return true;
			}
		return false;
	}

	// rimuove un giorno dall'orario
	public boolean rimuoviGiorno(nomeGiorno nome) {

		for (byte b = 0; b < orario.length && orario[b] != null; b++)
			if (orario[b].getNome().equals(nome)) {
				orario[b] = null;
				if (!Check.isEmpty(orario)) {
					scorriOrario(b);
					ordinaOrario();
				}
				return true;
			}
		return false;
	}

	// modifica l'orario di un giorno gi� presente
	public boolean modificaGiorno(nomeGiorno daMod, String newBeginning, String newEnd) {

		for (byte b = 0; b < orario.length && orario[b] != null; b++)
			if (orario[b].getNome().equals(daMod)) {

				orario[b].setOraInizio(newBeginning);
				orario[b].setOraFine(newEnd);
				return true;
			}
		return false;
	}

	//scorre l'orario per riempire il "buco" lasciato in posizione b
	private void scorriOrario(byte b) {

		while (b + 1 < orario.length && orario[b + 1] != null)
			orario[b] = orario[++b];
		orario[b] = null;
	}

	private void ordinaOrario() {

		byte b = 0;
		while (b < orario.length && orario[b] != null)
			b++;
		ordina(0, b - 1);
	}

	private void ordina(int s, int d) {
		int pivot = (s + d) / 2;
		int i = s, j = d;
		GiornoLezione temp;

		while (i <= j) {
			while (orario[i].getNome().ordinal() < orario[pivot].getNome().ordinal())
				i++;
			while (orario[j].getNome().ordinal() > orario[pivot].getNome().ordinal())
				j--;

			if (i <= j) {
				temp = orario[i];
				orario[i++] = orario[j];
				orario[j--] = temp;
			}
		}
		if (i < d) ordina(i, d);
		if (j > s) ordina(s, j);
	}

	public void stampaOrario() {

		if (orario[0] != null) {
			System.out.println("");
			for (GiornoLezione g : orario)
				if (g != null)
					System.out.println(g);
		} else
			System.out.println("\nOrario vuoto!");
	}

	public String toString() {

		String daRes = nome + ", " + cfu + " CFU";

		if (prof != null)
			daRes.concat(", prof: " + prof.getNome());

		if (coProf != null)
			daRes.concat(", esercitatore: " + coProf.getNome());

		return daRes;

	}

}
