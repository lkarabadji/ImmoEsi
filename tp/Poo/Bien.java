package tp.Poo;

import javafx.fxml.FXML;
import javafx.scene.image.Image;

import java.time.LocalDateTime;
import java.util.Scanner;
import java.util.Set;

public abstract class Bien implements Comparable<Bien> {
	protected String adresse, descriptif="";
	protected LocalDateTime dateAjout;
	protected Wilaya wilaya;
	protected double superficie, prix;
	protected Proprietaire p;
	protected boolean negociable = false;
	protected Set<String> photos;
	protected Transaction transac;
	protected Wilaya WilayaEchange;
	protected String id;
	
	/**************************************/
	public void ajouterPhoto(String s)
	{
		this.photos.add(s);
	}
	public Bien( String transac,double prix,Proprietaire p,String w, double sup, String adresse,String id) {
		this.dateAjout = LocalDateTime.now();
		this.wilaya = Wilaya.valueOf(w.toUpperCase());
		this.superficie = sup;
		this.prix = prix;
		this.transac = Transaction.valueOf(transac);
		this.p = p;
		this.id = id;
		this.adresse = adresse;
		p.ajouterBien(this); //on ajoute le bien la liste des Biens de son propreitaire
	}
	public String getId()
	{
		return this.id;
	}
	public void setPhotos(Set<String> photos)
	{
		this.photos = photos;
	}
	public Set<String> getPhotos(){return this.photos;}
	public Bien(Proprietaire p)
	{
		System.out.println("Creation d'un Bien");
		Scanner sc = new Scanner(System.in);
		System.out.print("la Superficie ->");
		Scanner sc2 = new Scanner(System.in);
		this.superficie  =(double)sc2.nextInt();
		System.out.println("l adresse ->");
		String adr = sc.nextLine();
		this.adresse = adr;
		System.out.println("Transaction : (VENTE,LOCATION,ECHANGE) -> ");
		String tran = sc.nextLine();
		this.transac = Transaction.valueOf(tran.toUpperCase());
		System.out.print("la wilaya ->");
		String w = sc.next();
		this.wilaya = Wilaya.valueOf(w.toUpperCase());
		this.prix = 0;
		
		if (tran.compareToIgnoreCase("ECHANGE") == 0)
		{
			Scanner  sc3 = new Scanner(System.in);
			System.out.print("La wilaya de l'echange ->");
			String wEchange = sc3.nextLine();
			this.WilayaEchange = Wilaya.valueOf(wEchange.toUpperCase());

		}


		//
		//tring tran = sc.nextLine();
		//this.transac = Transaction.valueOf(tran);

		this.p = p;
		p.ajouterBien(this);
	}
	
	


	/**************************************/
	public void ajouterDescriptif(String descriptif)
	{
		//on pourrait modifier la fa�on d'ajouter le descriptif c'est juste temporaire
		this.descriptif = descriptif; 
	}

	public String getDescriptif() {
		return descriptif;
	}

	public void setAdresse(String adresse) {
		this.adresse = adresse;
	}

	public void setDescriptif(String descriptif) {
		this.descriptif = descriptif;
	}

	public void setDateAjout(LocalDateTime dateAjout) {
		this.dateAjout = dateAjout;
	}

	public Wilaya getWilaya() {
		return wilaya;
	}

	public void setWilaya(Wilaya wilaya) {
		this.wilaya = wilaya;
	}

	public double getSuperficie() {
		return superficie;
	}

	public void setSuperficie(double superficie) {
		this.superficie = superficie;
	}

	public Proprietaire getP() {
		return p;
	}

	public void setP(Proprietaire p) {
		this.p = p;
	}

	public boolean isNegociable() {
		return negociable;
	}

	public Transaction getTransac() {
		return transac;
	}

	public void setTransac(Transaction transac) {
		this.transac = transac;
	}

	public void setWilayaEchange(Wilaya wilayaEchange) {
		WilayaEchange = wilayaEchange;
	}

	public void setId(String id) {
		this.id = id;
	}

	/**************************************/

	public void afficherSansDetails()
	{
		System.out.println("------INFORMATIONS BIEN------");
		System.out.println("Type du Bien :"+this.getClass().getSimpleName());
		System.out.println("Type de Transaction :"+this.transac.toString());
		System.out.println("Prix :"+ this.prix);
		System.out.println("Wilaya : "+this.wilaya);
	}
	/**************************************/
	public void afficher() {
		System.out.println("------INFORMATIONS BIEN------");
		afficherSansDetails();
		//System.out.println("Date d'ajout : "+this.dateAjout);

		//System.out.println("Type du bien : "+this.getClass().getSimpleName());
		System.out.println("Descriptif du bien : "+this.descriptif);
		System.out.println("Adresse : "+this.adresse);
		//System.out.println("Wilaya : "+this.wilaya);
		System.out.println("Superficie : "+this.superficie+ "m2");
		//System.out.println("Photos : "+this.photos);
		//System.out.println("prix : "+this.prix+" DA");
		System.out.println("Prix agence"+ this.calculerPrix() +"DA");
		if (this.negociable) System.out.println("Negociable : Oui");
		else System.out.println("Negociable : Non negociable");
		System.out.println("-------------------------");
		System.out.println("Proprietaire : ");
		(this.p).afficherCoord();
		System.out.println("-------------------------");
	}
	
	/**************************************/
	public abstract double calculerPrix();

	/**************************************/
	public abstract void afficherP(Proprietaire p);

	public Proprietaire getProprietaire()
	{
		return this.p;
	}
	public double getPrix()
    {
        return  this.prix;
    }
	/**************************************/
	public Wilaya getWilayaEchange() {
		return WilayaEchange;
	}
	
	/**************************************/
	public void setWilayaEchange(String wilayaEchange) {
		WilayaEchange = Wilaya.valueOf(wilayaEchange);
	}
	
	/**************************************/
	public String getAdresse()
	{
		return this.adresse;
	}
	public void setPrix(double p)
	{
		this.prix = p;
	}
	/**************************************/
	public Transaction getTransaction(){
		return this.transac;
	}
	
	/**************************************/
	public double getsuperficie()
	{
		return this.superficie;
	}
	
	/**************************************/
	public Wilaya getwilaya()
	{ return this.wilaya;}
	
	/**************************************/
	public LocalDateTime getDateAjout()
	{
		return this.dateAjout;
	}
	
	/**************************************/
	public boolean getNegociable() {
		return negociable;
	}

	/**************************************/
	public void setNegociable(boolean negociable) {
		this.negociable = negociable;
	}

	/**************************************/
	/*public String[] getPhotos() {
		return photos;
	}*/

	/**************************************/
	/*public void setPhotos(String[] photos) {
		this.photos = photos;
	}*/
	/**************************************/
	public int compareTo(Bien bien) {
		/*
		if ((this.dateAjout).compareTo(bien.dateAjout)!=0) {
			return (  (this.dateAjout.compareTo(bien.getDateAjout())));
		}
		else {*/

			 if (this.equals(bien)) 
				 return  0;
			 else return (this.dateAjout).compareTo(bien.dateAjout);
		//}
	}

	public String getTransacS()
	{
		return (this.transac.toString());
	}
	/**************************************/
	public boolean equals(Object b) {
		//if (b instanceof Bien && this == b) return  true;
		if ((this.adresse.equals(((Bien)b).adresse)))
			return (this.wilaya.equals(((Bien)b).wilaya)) ;
		else return (this.adresse.equals(((Bien)b).adresse));
	}
	/**************************************/


	
}
	
	
