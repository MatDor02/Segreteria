package segreteria;
import java.util.Scanner;

public class CorsoLaurea {
// ATTRIBUTI
	private String nomeCorso;
	private String tipo = "Corso a Ciclo unico";
	private byte min_cfu;
	private Studente [] studenti = new Studente [200];
	private Materia [] ins_def;
	private Materia [] ins_scl;
	private Studente [] laureati = new Studente [200];
	private Studente [] fuoricorso = new Studente [200];
	Scanner input = new Scanner (System.in);
//COSTRUTTORE	
	public CorsoLaurea (String nomeCorso, byte min_cfu, byte n_def, byte n_scl) {
		this.nomeCorso = nomeCorso;
		this.min_cfu = min_cfu;
		ins_def = new Materia [n_def];
		ins_scl = new Materia [n_scl];
	}
//GETTER	
	public String get_nomeCorso () {
		return nomeCorso;
	}
	public Byte get_min_cfu () {
		return min_cfu;
	}
	public Materia [] get_ins_def () {
		return ins_def;
	}
	public Materia [] get_ins_scl () {
		return ins_scl;
	}
	
// METODI AUSILIARI
	public boolean vett_pieno (Object [] vettore) {
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
	public boolean Iscrizione (String nome) {
		if (vett_pieno (studenti)) {
			System.out.println("Impossibile iscriversi, posti pieni!");
			return false;
		}
		else {
			byte i = 0;
			while (i < studenti.length && studenti[i] != null) i++;
			studenti[i] = new Studente (nome);
			if (studenti [i] != null) { 
				System.out.println("Iscrizione avvenuta con successo!");
				return true;
				}
			else {
				System.out.println("Iscrizione non effettuata!");
				return false;
			}
		}	
	}
	
	public Studente CercaStudente (String matricola, Studente[] dove) {
		byte i = 0;
		while (i < dove.length) {
			if (dove[i].get_matricola().equals(matricola)) 
				return dove[i];
			else i++;
		}
		if (i == dove.length) {
			System.out.println("Non esistono studenti iscritti al corso selezionato "
					+ "corrispondenti alla matricola inserita!");
			return false;
		}
	}
	
	public boolean Ritiro (String matricola) {
		boolean c = true;
		do {
			System.out.println("Sei sicuro di voler disiscrivere lo studente da questo corso? "
					+ "[S] o [N]");
			String risposta;
			switch (risposta = input.next()) {
				case "S": CercaStudente (matricola) = null; c = false; break;
				case "N": return false;
				default: System.out.println("Utilizza solo i caratteri consentiti!");
			}
		} while (c);
	}
	
	public boolean LaureaStudente (Studente candidato, byte votofinale) {
		if (CercaStudente (candidato.get_matricola, studenti)) {
			if (candidato.get_CFU >= this.min_cfu) {
				byte i = 0;
				while (i < laureati.length && laureati[i] != null) i++;
				candidato.set_voto_finale(votofinale);
				laureati[i] = candidato;
				CercaStudente (candidato.get_matricola, studenti) = null;
				return true;
		}
		else return false;	
	}
	
	public boolean FuoriCorso (String matricola) {
		if (matricola.get_anno > 5) return true;
		else return false;
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
		if (vett_pieno (ins_def)) {
			System.out.println("Numero massimo di materie raggiunto!");
			return false;
		}
		else {
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
					prof = new Professore (nomeprof);
					System.out.print("Inserire i CFU :\n-> ");
					crediti = input.nextByte();
					System.out.print("Inserire il nome dell'esercitatore (se c'é altrimenti premere 0):\n-> ");
					String nomeex = input.next();
					if (!nomeex.equals("0")) {
						ex = new Esercitatore (nomeex);
						ins_def[i] = new Materia (nome, crediti, prof, ex);
					}
					else ins_def[i] = new Materia (nome, crediti, prof);
				}
			}
		}
		return true;
	}
	
	public boolean In_ins_scl () {
		if (vett_pieno (ins_scl)) {
			System.out.println("Numero massimo di materie raggiunto!");
			return false;
		}
		else {
			String nome, prof;
			byte crediti;
			for (byte i = 0; i < ins_scl.length; i++) {
				if (ins_scl[i] == null) {
					System.out.print("Inserire il nome della materia:\n-> ");
					nome = input.next();
					System.out.print("Inserire il nome del professore:\n-> ");
					prof = input.next();
					System.out.print("Inserire i CFU :\n-> ");
					crediti = input.nextByte();
					ins_scl[i] = new Materia (nome, prof, crediti);
				}
			}
		}
		return true;
	}
	
	public boolean Out_ins_scl () {
		if (!vett_pieno (ins_scl)) {
			System.out.println("Non ci sono materie registrate!");
			return false;
		}
		else {
			boolean c = true;
			do {
				System.out.print("Inserire il nome della materia:\n-> ");
				String nome = input.next();
				byte i = 0;
				while ( i < ins_scl.length) {
					if (ins_scl[i] != null && ins_scl[i].getNome.equals(nome)) {
						ins_scl[i] = null;
						c = false;
					}
					else i++;
				}
				if (i == ins_scl.length) System.out.println("Non esiste nessuna materia con il nome inserito!");
			} while(c);
			
		}	
		return true;
	}

	public boolean Out_ins_def () {
		if (!vett_pieno (ins_def)) {
			System.out.println("Non ci sono materie registrate!");
			return false;
		}
		else {
			boolean c = true;
			do {
				System.out.print("Inserire il nome della materia:\n-> ");
				String nome = input.next();
				byte i = 0;
				while (i < ins_def.length) {
					if (ins_def[i] != null && ins_def[i].getNome.equals(nome)) {
						ins_def[i] = null;
						c = false;
					}
					else i++;
				}
				if (i == ins_def.length) System.out.println("Non esiste nessuna materia con il nome inserito!");
			} while(c);	
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
	
// MENU'
	public void menu_corso () {
		System.out.println("CORSO DI " + this.nomeCorso + "[anno accademico 2021/2022]\n" + this.tipo);
		boolean f = true;
		do {
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
			System.out.println("M) Visualizza le materie del corso");
			System.out.println("\nE) Torna al menù precedente");
			
			String ris;
			switch (ris) {
				case "i", "I": {
					System.out.println("Inserisci il nome e l'anno a cui iscrivere lo studente:\n-> ");
					String nome = input.next();
					Iscrizione (nome);	
				}
				break;
				case "c", "C": {
					System.out.println("Inserisci la matricola dello studente\n-> ");
					String mat = input.next();
					CercaStudente (mat, studenti);
				}
				break;
				case "r", "R": {
					System.out.println("Inserisci la matricola dello studente\n-> ");
					String matr = input.next();
					Ritiro (matr);
				}
				break;
				case "v", "V": VisualizzaStudenti(); break;
				case "l", "L": {
					System.out.println("Inserisci la matricola dello studente\n-> ");
					String matri = input.next();
					System.out.println("Inserisci il voto finale da dare allo studente\n-> ");
					byte anno = input.nextByte();
					LaureaStudente (matri, studenti);	
				}
				break;
				case "u", "U": VisualizzaLaureati(); break;
				case "o", "O": {
					System.out.println("Inserisci la matricola dello studente laureato\n-> ");
					String matric = input.next();
					CercaStudente (matric, laureati);
				}
				break;
				case "y", "Y": In_ins_def(); break;
				case "g", "G": Out_ins_def(); break;
				case "j", "J": In_ins_scl(); break;
				case "x", "X": Out_ins_scl(); break;
				case "m", "M": VisualizzaMaterie(); break;
				case "e", "E": f = false; break;
				default: System.out.println("Utilizza solo i caratteri consentiti!\n");
			}	
		} while (f);
		System.out.println("[---]");
	}
	
}
