package Insegnamenti;

public class GiornoEsame extends Giorno {
	
	private String data, orario; //data formato gg/mm/aa
	
	public GiornoEsame(nomeGiorno nome, String data, String orario) {
		super(nome);
		this.data = data;
		this.orario = orario;
	}

	public nomeGiorno getNome() {
		return nome;
	}

	public String getData() {
		return data;
	}

	public String getOrario() {
		return orario;
	}

	public void setData(String data) {
		this.data = data;
	}

	public void setOrario(String orario) {
		this.orario = orario;
	}

	public String toString() {
		return "Data: " + data + " alle ore: " + orario;
	}
}
