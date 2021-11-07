package gestioneSegreteria;

public class Giorno {
	
	protected enum nomeGiorno {
		
		Lunedi, Martedi, Mercoledi, Giovedi, Venerdi, Sabato, Domenica;
		
		// Returns the enumeration constant in position n in its delcaration.
		// The initial enum constant (lunedi) is in position 0.
		protected static nomeGiorno getPosition(byte n) {
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
	
	public String toString() {
		return nome + "";
	}
	
}
