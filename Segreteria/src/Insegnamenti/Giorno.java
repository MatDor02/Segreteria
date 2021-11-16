package Insegnamenti;

import java.util.Scanner;

public class Giorno {
	
	public enum nomeGiorno {
		
		Lunedi, Martedi, Mercoledi, Giovedi, Venerdi, Sabato, Domenica;
		
		// Returns the enumeration constant in position n in its delcaration.
		// The initial enum constant (lunedi) is in position 0.
		public static nomeGiorno getPosition(byte n) {
			for(nomeGiorno g : nomeGiorno.values())
				if (g.ordinal() == n)
					return g;
			return Lunedi;
			
		}
	};
	protected nomeGiorno nome;
	
	public Giorno(nomeGiorno nome) {
		this.nome = nome;
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

	public String toString() {
		return nome + "";
	}
}
