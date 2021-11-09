package segreteria;

public class TestCorsoLaurea {
	static CorsoLaurea [] corsi = new CorsoLaurea[1];
	public static CorsoLaurea [] getcorsi () {
		return corsi;
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		String nome = "inf";
		byte cfu = 13;	
		byte n = 1, m = 1;
		getcorsi() [0] = new CorsoLaurea(nome, cfu, n, m);
		System.out.println(getcorsi ()[0]); 		
	}

}
