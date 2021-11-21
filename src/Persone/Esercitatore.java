package Persone;

public class Esercitatore extends Persona{

//Attributi	
	private String matricola = "E";
	private static byte contatoreE = 0;
	
//Costruttore
	public Esercitatore(String nomeEsercitatore)
	{
		super (nomeEsercitatore);
		this.matricola += ++contatoreE;
	}

	public String getNome() {
		return nome;
	}
}
