package gestioneSegreteria;

public class GiornoLezione extends Giorno {
	
	private String oraInizio;
	private String oraFine;
	
	public GiornoLezione(nomeGiorno nome , String oraInizio, String oraFine) {
		
		super(nome);
		this.oraInizio = oraInizio;
		this.oraFine = oraFine;	
	}
	
	public nomeGiorno getNome() {
		return nome;
	}
	
	public String getOraInizio() {
		return oraInizio;
	}
	
	public void setOraInizio(String orario) {
		oraInizio = orario;
	}
	
	public String getOraFine() {
		return oraFine;
	}
	
	public void setOraFine(String orario) {
		oraFine = orario;
	}
	
}
