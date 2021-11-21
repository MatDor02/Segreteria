package Persone;

public class Professore extends Persona {

//Attributi
	private String matricola = "P";
	private static byte contatoreP = 0;
	
//Costruttore
	public Professore(String nomeProfessore)
	{
		super (nomeProfessore);
		this.matricola += ++contatoreP;
	}

	public String getNome() {
		return nome;
	}

	public String getMatricola() {
		return matricola;
	}
}
