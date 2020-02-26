package tp.Poo;

import javafx.beans.Observable;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;


import java.awt.event.ActionEvent;
import java.util.Iterator;
import java.util.Scanner;
import java.util.SortedSet;
import java.util.TreeSet;
import java.io.PrintStream;

public class ImmoEsi {
	public static SortedSet<Bien> listeBiens = new TreeSet<Bien>();
	public static SortedSet<Bien> BiensArchives = new TreeSet<>();
	//static Proprietaire[] listeProprietaires = new Proprietaire[500];
	public static  SortedSet<Proprietaire> listeProprietaires = new TreeSet<>();
	public static String[] boiteMessages = new String[10000];
	static int nbProp=0;
	private static int nbMsg = 0;
	private static String admin = "lina";
	public static  String getAdmin(){return  admin;}
	private static String code = "123";
	public static String getCode(){ return code;}

	public static int getNbMsg() {
		return nbMsg;
	}

	public SortedSet<Bien> getListeBiens()
		{return this.listeBiens;}
	
	public void consulter() {
		int j = 1;
		Iterator<Bien> it = listeBiens.iterator();
			while(it.hasNext())	
			{
				Bien b = it.next();
				System.out.println("********\nBien numero "+ "["+j+"]"+"\n********");
				j++;
				b.afficherSansDetails();
			}
	}

/*	public void consulterG() {
		int j = 1;
		System.out.print("");
		Iterator<Bien> it = listeBiens.iterator();
		System.out.print("");
		while(it.hasNext())
		{
			Bien b = it.next();

			System.out.print("");
			System.out.println("********\nBien numero "+ "["+j+"]"+"\n********");
			ViewBien.setBien(b);
			//j++;
			b.afficherSansDetails();
		}
	}*/


	//---------------------------------
	public Bien selectionner(int numBien) {
		Bien b;
		int i = 1;
		if (numBien > listeBiens.size() || numBien< 1)
		{
			System.out.println("numero du bien inexistant");
			return null;
		}
		else {
		Iterator<Bien> iter = listeBiens.iterator();
		b = iter.next();
		while( (iter.hasNext()) && (i < numBien)) {
			b = iter.next();
			//b.afficherSansDetails();
			i++;
			} }
		return b;
	}
	public static Bien selectionner(int numBien, SortedSet<Bien> bList){
		Bien b;
		int i = 1;
		if (numBien > bList.size() || numBien< 1)
		{
			System.out.println("numero du bien inexistant");
			return null;
		}
		Iterator<Bien> iter = bList.iterator();
		b = iter.next();
		while( (iter.hasNext()) && (i < numBien)) {
			b = iter.next();
			//b.afficherSansDetails();
			i++;
			}
		return b;
	}
	
	public static Proprietaire selectionnerP(int numBien, SortedSet<Proprietaire> bList){
		Proprietaire b;
		int i = 1;
		if (numBien > bList.size() || numBien< 1)
		{
			System.out.println("numero du bien inexistant");
			return null;
		}
		Iterator<Proprietaire> iter = bList.iterator();
		b = iter.next();
		while( (iter.hasNext()) && (i < numBien)) {
			b = iter.next();
			//b.afficherSansDetails();
			i++;
			}
		return b;
	}
	
	public static void consulter(SortedSet<Bien> bList) // Pour afficher des listes des biens
	{
		int j = 1;
		Iterator<Bien> it = bList.iterator();
		while(it.hasNext())	
		{
			Bien b = it.next();
			System.out.println("********\nBien numero "+ "["+j+"]"+"\n********");
			j++;
			b.afficherSansDetails();
		}
	}
	
	public Criteres critere(int i)
	{
		Criteres obj  = null ;
		switch(i)
		{
			case 1:
			obj = new CritereTransaction();
			break;
			case 2:
			obj = new CritereWilaya();
			break;
			case 31: 
			obj = new CriterePrixMin();
			break;
			case 32:
			obj = new CriterePrixMax();
			break;
			case 4:
			obj = new CritereBien();
			break;
			case 5:
			obj = new CritereSuperficieMinimale();
			break;
			case 6:
			obj = new CritereNbrPieces();
			break;
			default:
			break;
		}
		return obj;
	}
	
	public SortedSet <Bien> filtrer()
	{
		int N = 0;
		SortedSet<Bien> b1 = listeBiens;
		SortedSet<Bien> b2 = new TreeSet<>();
		System.out.println(b2.size());
		System.out.println(listeBiens.size());
		
		//int tChoix[];
		
		Scanner sc = new Scanner(System.in);
		int choix =0;
		do{
			System.out.println("Veuillez choisir le ou les critères :( C'est un ET entre les critères choisis )");
			System.out.println("1)Type de Transaction\n2)Wilaya\n3)Prix Minimal et/ou Maximal\n4)Type du bien\n5)Superficie minimale\n6)Nombre minimal des pieces(pour les biens habitables seulement\n0)fin");
			System.out.print("Choix (0-6)->");
			choix = sc.nextInt();
			if (choix == 3)
			{
				System.out.println("Veuillez choisir : 1)Prix Minimal\n2)Prix Maximal \n3)Prix Minimal ET Maximal\n4)Prix Minimal OU Maximal");
				choix = sc.nextInt();
				switch(choix)
				{
					case 1:
					b2 = critere(31).rechercher(b1);
					b1 = b2;
					N = critere(31).nbElements;
					break;
					case 2:
					b2 = critere(32).rechercher(b1);
					b1 = b2;
					N = critere(32).nbElements;
					break;
					case 3: // c'est le ET
					//b2 = new  Bien[critere(31).nbElements];
					b2 = critere(31).rechercher(b1);
					SortedSet<Bien> b3 = new TreeSet<>();
					b3 = critere(32).rechercher(b2);
					b1 = b3;
					N  = critere(32).nbElements;
					
					break;
					case 4:
					b2 = critere(32).rechercher(b1);
					N = critere(32).nbElements;
					SortedSet<Bien> b4 = new TreeSet<>();
					b4 = critere(32).rechercher(b1);
					int j = N;
					Iterator<Bien> it = b4.iterator();
					while(it.hasNext())	
					{
						Bien b = it.next();
						b2.add(b);
						j++;
					}
					N = N+j;
					b1 = b2;
					break;
					default:
					System.out.println("Choix invalide");
					break;
				}
			}
			else if (choix <= 6 && choix>=1)
			{
				
				b2 = critere(choix).rechercher(b1);
				b1 = b2;
				N = critere(choix).nbElements;
			}
		}while(choix != 0);
		System.out.println("Resultat Final :");
		System.out.println("nbr elements : "+ b2.size());
		consulter(b2);//Parcourir la liste des resulats finale;
		return b2;
	}

			
			
	//---------------------------------
	public void visualiser(Bien b) {
			try{
				b.afficher();
			}
			catch (NullPointerException e)
			{
				System.out.println("pointeur null ! ");
			}
	}
	//---------------------------------
	public boolean ajouterBien(Bien b) {
	boolean bool;
		try {
			bool=listeBiens.add(b);
		}
		catch( NullPointerException e){
			System.out.println("Insertion d'un pointeur null ! ");
				
		}
		finally {bool=false;
		}
		return bool;
	}	
	//---------------------------------
	/*
	public void ajouterProprietaire(Proprietaire p) {
		listeProprietaires[nbProp] = p;
		nbProp++;
	}	*/
	public static boolean ajouterProprietaire(Proprietaire p){
		 if (listeProprietaires.add(p))
		 {
			 nbProp ++ ;
			 return true;
		 }
		 return  false;

	}
	//---------------------------------
	/*
	public  void afficherProprietaire() {
		int j = 1;
		for (int i=0; i< nbProp; i++) {
			System.out.println("----------------------");
			System.out.println("Proprietaire numero "+ "["+j+"]");
			System.out.println("----------------------\n");
			j++;
			listeProprietaires[i].afficherCoord();
		}
	}*/

	public static void afficherProprietaire(){
		int j = 1;
		Iterator<Proprietaire> it = listeProprietaires.iterator();
		while(it.hasNext())	
		{
			Proprietaire p = it.next();
			System.out.println("********\nProprietaire numero "+ "["+j+"]"+"\n********");
			j++;
			p.afficherCoord();
		}

	}
	//---------------------------------
	public static void supprimerBien(Bien b) {
		listeBiens.remove(b);
		b.p.supprimerBienP(b);
	}
	//---------------------------------
	public  static void archiverBien(Bien b) {
		BiensArchives.add(b);
		listeBiens.remove(b);
	}
	
	//---------------------------------
	public void afficherArchives() {
		Iterator<Bien> it = BiensArchives.iterator();
			while(it.hasNext())	
			{
				Bien b = it.next();
				b.afficherSansDetails();
			}
		
	}

	//---------------------------------
	public  static void envoyerMessage(String msg, Bien b) {
	boiteMessages[nbMsg]= msg;
	nbMsg++;
	}
	//---------------------------------
	public void afficherMessages() {
	System.out.println("Messages des clients : ");
	for (int i=0; i< nbMsg; i++) {
	System.out.println("- "+boiteMessages[i]);
		}
	}

	public  static void   supprimerProp(Proprietaire p)
	{
		listeProprietaires.remove(p);
		for(Bien b: p.getListeBienP())
		{
			listeBiens.remove(b);
		}

	}
	//---------------------------------
	public void modifier(Bien b) {
		int choix;
		Scanner sc = new Scanner(System.in);
		System.out.println("Que voulez vous modifiez ");
		System.out.println("1- Prix du bien.");
		System.out.println("2- Type de transaction.");
		System.out.println("3- Descriptif.");
		System.out.print("---> ");
		choix = sc.nextInt();
		switch(choix) {
		case 1:
		
			System.out.print("Veuillez entrer le nouveau prix : ");
			double nvprix = sc.nextDouble();
			b.prix = nvprix;
		
		break;
		case 2:
			System.out.print("Veuillez entrer la nouvelle transaction : ");
			String nvtransac = sc.next();
			b.transac = Transaction.valueOf(nvtransac.toUpperCase());
		
		break;
		case 3:
		System.out.print("Veuillez entrer le nouveau descriptif : ");
		String nvdes= sc.next();
		b.ajouterDescriptif(nvdes);
		break;
		default : System.out.println("choix invalide");
		}
		
	}
	
	/*
	public void afficherCritere() {
		System.out.println("Liste des criteres : ");
		for (int i=0; i< nbCrit; i++) {
		System.out.println(criteres[i]);
		}
	}*/
			

	
	public void Menu(){
		int choix = 0;
		do{
		System.out.println("vEUILLEZ CHOISIR");
		System.out.println("1.Afficher la liste des Bien");
		System.out.println("2.Modifier Un bien");
		System.out.println("3.Supprimer un Bien");
		System.out.println("4.Recherche par Critere");
		System.out.println("5.Afficher la liste des Biens d'un proprietaire");
		System.out.println("6.Archiver Un bien");
		System.out.println("7.Selectionner un Bien");
		System.out.println("8.Afficher Les prix finaux pour chaque bien");
		System.out.println("9.Envoyer un Message");
		System.out.println("10.Afficher Les Messages");
		System.out.println("0.finir");
		Scanner sc = new Scanner(System.in);
		choix = sc.nextInt();
		switch(choix){
			case 1:
			this.consulter();
			break;
			case 2: // Modifier un Bien
			System.out.println("Entrez le numero du bien");
			int n = sc.nextInt();
			Bien b = selectionner(n);
			System.out.println("affichage du bien detaille avant modification ");
			b.afficher();
			this.modifier(b);
			System.out.println("affichage du bien detaille apres modification ");
			b.afficher();
			break;
			case 3:
			System.out.println("Entrez le numero du bien");
			int n1 = sc.nextInt();
			Bien b1 = selectionner(n1);
			this.supprimerBien(b1);
			break;
			case 4:
			SortedSet<Bien> lb = this.filtrer(); 
			System.out.println("Entrez le numero du bien");
			int n4 = sc.nextInt();
			Bien b4 = selectionner(n4,lb);
			this.visualiser(b4);
			break;
			case 5:
			System.out.println("Veuillez choisir le numero d un Proprietaire \n1.prop1\n2.prop2\n3.prop3\n4.prop4");
			Scanner sc5 = new Scanner(System.in);
			int c5 = sc5.nextInt();
			Proprietaire p = selectionnerP(c5,listeProprietaires);
			p.afficherBienP();
			break;
			case 6: //Archiver un bien
				consulter();
				Scanner sc6 = new Scanner(System.in);
				int n6 = sc6.nextInt();
				Bien b6 = selectionner(n6);
				this.archiverBien(b6);
			break;
			case 7 :
			this.consulter();
			System.out.println("Entrez le numero du bien");
			int n7 = sc.nextInt();
			Bien b7 = selectionner(n7);
			this.visualiser(b7);
			break;
			case 8: //Prix finaux

			int j = 1;
			Iterator<Bien> it = this.listeBiens.iterator();
			while(it.hasNext())	
			{
				Bien bh = it.next();
				System.out.print("Bien numero "+ "["+j+"] : ");
				System.out.println(bh.calculerPrix()+" DA");
				j++;
				
			}
			break;
			case 9: //Envoyer un Message:
				System.out.println("Entrez le numero du bien");
				int n9 = sc.nextInt();
				Bien b9 = selectionner(n9);
				System.out.println("Saisir le Message");
				String Message = sc.nextLine();
				String Message1 = new String("Le bien" + b9.getClass().getSimpleName() +" "+b9.getAdresse()+" "+ b9.getTransaction() +" "+b9.getsuperficie()+" " + Message);
				this.envoyerMessage((Message1), b9);

			break;
			case 10://Afficher Les message
				this.afficherMessages();
			break;
			default:
			System.out.println("Choix invalide");
			break;

		}
		
		}while (choix != 0);
	
		
	
	}

/*	@FXML
	private Label transac;

	@FXML
	private Label typeBien;

	@FXML
	private Label prix;

	@FXML
	private Label wilaya;
	@FXML
	private Label adresse;
	@FXML
	public Button next;

	@FXML
	private ListView<Bien> listView = new ListView<>();
	@FXML
	public void start() {
		int j = 0;
		//listView.setItems(listeBiens);
		//listView.setEditable(true);
		//listView.getItems().addAll("lili");
		//listView.getItems().setAll(listeBiens);
		System.out.println(listeBiens.size());
		for (Bien b : listeBiens) {
			//System.out.println("Boucle");
			j++;
			listView.getItems().add(b);
		}
		System.out.println("Fin");


	}

	@FXML
	public void afficherBien()
	{

		listView.getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Bien>() {
			@Override
			public void changed(ObservableValue<? extends Bien> observableValue, Bien bien, Bien t1) {
				typeBien.setText(t1.getClass().getSimpleName());
				transac.setText(t1.getTransaction().toString());
				wilaya.setText(t1.getwilaya().toString());
				prix.setText(Double.toString(t1.getPrix()));
			}});
	}*/



	}






