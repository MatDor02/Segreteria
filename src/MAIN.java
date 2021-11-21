
import java.util.*;

import Persone.*;
import Insegnamenti.*;
import CorsiDiLaurea.*;

public class MAIN {

	public static CorsoLaurea[] corsi;
	
	public static boolean ins_corso(CorsoLaurea[] corsi)
	{
		Scanner input = new Scanner(System.in);
		if(CorsoLaurea.vett_pieno(corsi))
		{
			System.out.println("Non è possibile aggiungere corsi si laurea!");
			return false;
		}
		boolean c = true;
		for(int k = 0; k<corsi.length && c; k++)
		{
			if(corsi[k] == null)
			{	
				System.out.println("Inserire il nome corso di laurea: ");
				String nome = input.next();
				System.out.println("Inserire il numero di cfu minimi per potersi laureare: ");
				byte cfu = input.nextByte();
				
				System.out.println("Inserire il numero di insegnamenti di base: ");
				byte ins_b = input.nextByte();
				System.out.println("Inserire il numero di insegnamenti a scelta: ");
				byte ins_s = input.nextByte();
				corsi[k] = new CorsoLaurea(nome, cfu, ins_b, ins_s);
				c =false;
			}
		}
		return true;
	}
	
	public static boolean rimuovi_corso()
	{
		Scanner input = new Scanner(System.in);

		byte t = 0;
		boolean c = true;
		while(t < corsi.length && c)
		{
			if(corsi[t] == null)
				t++;
			else
				c = false;
		}
		if(!c)
		{
			System.out.println("Non è possibile rimuovere corsi di laurea!");
			return false;
		}
		boolean r = true; 
		String nome;
		
		do {
			System.out.println("Quale corso si desidera eliminare?");
			nome = input.next();
			byte i = 0;
			boolean u = true;
			while(i<corsi.length && u)
			{
				if(corsi[i] == null)
					i++;
				else
					if(corsi[i].get_nomeCorso().equals(nome))
					{
						corsi[i] = null;
						u = false;
					}					
					else
						i++;
			}
			if(i == corsi.length)
				System.out.println("Nessun corso corrisponde al nome inserito!");
			else
				r = false;
		}while(r);
		
		return true;
	}

	// TODO public void menuStudente()
	// 		login (scorro vettore studenti finchè non combaciano credenziali)
	// 		opzioni: prenotaEsame (stampa le materie del piano di studi, ne fa scegliere una e stampa i relativi appelli.
	//							   Selezionare quello a cui iscriversi e passarlo a studenteAttuale.prenotaEsame())
	
	public static void main(String[] args) {
		
	Scanner input = new Scanner(System.in);
	byte n_corsi;
	boolean t = true;
	
		do {
			System.out.println("Benvenuto/a!");
			System.out.println("Qunati corsi può ospitare l'ateneo?");
			n_corsi = input.nextByte();
			corsi = new CorsoLaurea[n_corsi];
			ins_corso(corsi);
			
			System.out.println("MENU COMANDI:");
			System.out.println("A) aggiungi corso");
			System.out.println("E) elimina corso");
			System.out.println("M) apri menù corso");
			System.out.println("U) esci dal programma");
			System.out.print("Scelta:\n--> ");
			String scelta = input.next();
			
			switch(scelta)
			{
			case "A", "a":
					ins_corso(corsi);
					break;
			case "E", "e":
					rimuovi_corso();
					break;
			case "M", "m":
					System.out.println("Corsi: ");
					for(CorsoLaurea n : corsi)
						System.out.println(n);
					System.out.println("Scrivere il nome del corso a cui accedere: ");
					String risposta = input.next();
					byte w = 0;
					boolean z = true;
					
					while(w<corsi.length && z)
					{
						if(corsi[w].get_nomeCorso().equals(risposta))
							z = false;
					}
					if(w == corsi.length)
						System.out.println("Non esiste alcun corso con il nome inserito!");
					else 
						corsi[w].menu_corso();
					break;
			case "U", "u":
					t = false;
					break;
			default:
					System.out.println("Usa solo caratteri consentiti!");
			}
			
		}while(t);

	}

}
