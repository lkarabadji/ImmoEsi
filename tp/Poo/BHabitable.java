package tp.Poo;
import java.util.Scanner;

public abstract class BHabitable extends Bien {
	protected int nbpieces;
	protected boolean meuble;
	protected double superficiceHabitable;
	public BHabitable(  String transac,double prix, Proprietaire p, String w,double sup,String adr, int nbpieces,String id) {
		super(transac, prix, p, w, sup, adr,id);
		this.nbpieces = nbpieces;
	}
	public BHabitable(Proprietaire p)
	{
		super(p);
		System.out.println("Nombre de pieces : ");
		Scanner sc = new Scanner(System.in);
		int nbP = sc.nextInt();
		this.nbpieces = nbP;
		System.out.println("Superficie Habitable: ");
		this.superficiceHabitable = sc.nextDouble();
		System.out.println("Meuble : (o: oui /n: Non)");
		String s = sc.nextLine();
		if (s.compareToIgnoreCase("o") == 0)
			this.meuble = true;
		else this.meuble = false;
	}

	public int getNbpieces() {
		return nbpieces;
	}

	public int getnbpieces(){return this.nbpieces;}

	public void setMeuble(Boolean v)
	{
		this.meuble = v;
	}

	public void setNbpieces(int nbpieces) {
		this.nbpieces = nbpieces;
	}

	public boolean isMeuble() {
		return meuble;
	}

	public void setMeuble(boolean meuble) {
		this.meuble = meuble;
	}

	public double getSuperficiceHabitable() {
		return superficiceHabitable;
	}

	public void setSuperficiceHabitable(double superficiceHabitable) {
		this.superficiceHabitable = superficiceHabitable;
	}

	public void afficherSansDetails()
	{
		super.afficherSansDetails();
		System.out.println("Nombre de pieces : " +this.getnbpieces() );
	}

}
