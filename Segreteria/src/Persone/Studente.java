package Persone;
import java.util.*;
import Insegnamenti.*;
import CorsiDiLaurea.*;

public class Studente extends Persona{

//Attributi
	private String matricola = "S";
	public byte def = CorsoLaurea.get_ins_def().length + CorsoLaurea.get_ins_scl().length;
	private Materia[] pianoStudi = new Materia[def];
	private static byte Contatore = 0;
	private byte CFU_acquisiti;
	//private Materia[] Esami = new Materia[def];
	private Esame[] Esami = new Esame[def];
	private byte voto_finale;
	private byte anno;
	
//Costruttore
	public Studente(String nomeStudente)
	{
		super (nomeStudente);
		this.matricola += ++Contatore;	
	}
	
//Getter
	public String get_nomeStudente() {return nome;}
	
	public String get_matricola() {return matricola;}
	
	public Materia[] get_PianoStudi() {return pianoStudi;}
	
	public byte get_CFU() {return CFU_acquisiti;}
	
	public Materia[] get_esami() {return Esami;}
	
	public byte get_voto_finale() {return voto_finale;}
	
	public byte get_anno() {return anno;}

//Setter
	public void set_voto_finale(byte voto_finale) {this.voto_finale = voto_finale;}
	
//Metodi
	public boolean pianoStudi()
	{
		byte i = 18;
		boolean c = true;
		
		for(int j = 0; j<CorsoLaurea[k].get_ins_def().legth; j++)
		{	
			pianoStudi[j] = CorsoLaurea[k].get_ins_def()[j];
		}
		
		while(i < pianoStudi.length && pianoStudi[i] != null)
		{
			i++;
			
			do {
				
				if(scegliMaterie())
				{
					System.out.println("---Operazione avvenuta con successo---");
					c = false;
				}
					else
					System.out.println("---Operazione non riuscita \n Ritentare!---");
				
			}while(c);		
		}
		
		
		if(i == pianoStudi.length)
			return true;
		return false;
	}

	public boolean scegliMaterie()
	{
		System.out.println("Scegli materia da inserire: ");
		Scanner input = new Scanner(System.in);
		String a;
		a = input.next();
		
		for(int i = 0; i < CorsoLaurea[K].get_ins_scl().length; i++)
		{		
			if(CorsoLaurea[k].get_ins_scl()[i].get_nome().equals(a))
			{
				pianoStudi[i] = a;
				return true;
			}
		}
		return false;
	}
	
	public boolean rimuoviMaterie()
	{
		System.out.println("Scegli materia da rimuovere: ");
		Scanner input = new Scanner(System.in);
		String b;
		b = input.next();
		
		for(int p = 0; p<CorsoLaurea[K].get_ins_scl().length; p++)
		{
			if(CorsoLaurea[k].get_ins_scl()[p].get_nome().equals(b))
			{
				pianoStudi[p] = null;
				return true;
			}
		}
		return false;
	}
	
	public void visualizzaEsami()
	{
		System.out.println("Stampa lista esami\n");
		byte i = 0;
		byte somma_voti = 0;
		while(i<Esami.length && Esami[i] != null) {
			if(Esami[i].isSuperato())
				System.out.println(i + " esame: " + Esami[i] + " - voto: " + Esami[i].getVoto());
			i++;
		}
		
		for(int t = 0; t<Esami.length; t++)
			somma_voti += Esami[i].getVoto();
		
		System.out.println("Media voti: " + somma_voti/Esami.length);
	}
	
	public boolean Promuovi(Esame exam, int voto)
	{
		if(!exam.isSuperato())
		{	
			exam.setVoto((byte)voto);
			CFU_acquisiti += exam.getCfu();
			return true;
		}
		return false; 
	}

	
	@Override
	public String toString() {
		return "Studente [matricola=" + matricola + ", CFU_acquisiti=" + CFU_acquisiti
				+ ", voto_finale=" + voto_finale + ", anno=" + anno + "]";
	}

	
}
