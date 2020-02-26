package tp.Poo;

public enum Wilaya {
	
	WILAYA1(100000), WILAYA2(30000), WILAYA3(60000),
	OUMBOUAGHI(4), BATNA(5), BEJAIA(6), 
	BISKRA(7), BECHAR(8), BLIDA(9),
	BOUIRA(10), TAMANRASSET(11), TEBESSA(12),
	TLEMCEN(13), TIARET(1000), TIZIOUZOU(1500), 
	ALGER(5200), DJELF(1000), JIJEL(1000);

	private int prixM2;
	Wilaya(int prixM2){
		this.prixM2=prixM2;
		
	}
	public long getPrixM2() {
		return prixM2;
	}
	
	
	
}
