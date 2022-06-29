package donnees;

import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;

public class Noeud {
	// ****Attributs*****

	private Stagiaire stagiaire; // clé
	private int filsGauche;
	private int filsDroit;
	private List<Stagiaire> lesStagiaires;
	private int octet = 124;

	// *****Constructeur*****

	public Noeud(Stagiaire stagiaire) {
		super();
		this.stagiaire = stagiaire; // 5 attributs
		this.filsGauche = -1;
		this.filsDroit = -1;
	}
	// *****GETTERS ET SETTERS****

	public Stagiaire getStagiaire() {
		return stagiaire;
	}

	public void setStagiaire(Stagiaire stagiaire) {
		this.stagiaire = stagiaire;
	}

	public int getFilsGauche() {
		return filsGauche;
	}

	public void setFilsGauche(int filsGauche) {
		this.filsGauche = filsGauche;
	}

	public int getFilsDroit() {
		return filsDroit;
	}

	public void setFilsDroit(int filsDroit) {
		this.filsDroit = filsDroit;
	}

	// ****Ecriture des noeuds stagaires******

	public void creerNoeud(Stagiaire stage) throws IOException {

		ListeStagiaire.raf.writeChars(stage.agrandirNom());
		ListeStagiaire.raf.writeChars(stage.agrandirPrenom());
		ListeStagiaire.raf.writeChars(stage.agrandirDepartement());
		ListeStagiaire.raf.writeChars(stage.agrandirPromo());
		ListeStagiaire.raf.writeChars(stage.agrandirAnnee());
		ListeStagiaire.raf.writeInt(-1);
		ListeStagiaire.raf.writeInt(-1);

	}

	// ****Lecture des noeuds stagaires******

	public Noeud lireNoeud() throws IOException {

		// je lis les x caractères de l'attribut string
		String nom = "";
		for (int k = 0; k < Stagiaire.TAILLE_nom; k++) {
			nom += ListeStagiaire.raf.readChar();
		}
		String prenom = "";
		for (int k = 0; k < Stagiaire.TAILLE_prenom; k++) {
			prenom += ListeStagiaire.raf.readChar();
		}
		String departement = "";
		for (int k = 0; k < Stagiaire.TAILLE_departement; k++) {
			departement += ListeStagiaire.raf.readChar();
		}

		String promo = "";
		for (int k = 0; k < Stagiaire.TAILLE_promo; k++) {
			promo += ListeStagiaire.raf.readChar();
		}
		String annee = "";

		for (int k = 0; k < Stagiaire.TAILLE_annee; k++) {
			annee += ListeStagiaire.raf.readChar();
		}
		Stagiaire stage = new Stagiaire(nom, prenom, departement, promo, annee);
		int filsGauche = ListeStagiaire.raf.readInt();
		int filsDroit = ListeStagiaire.raf.readInt();
		Noeud noeud = new Noeud(stage);
		noeud.setFilsGauche(filsGauche);
		noeud.setFilsDroit(filsDroit);

		return noeud;
	}

	// ****** Ajouter stagiaire*****

//ajouter stagiaire
	public void ajouterStagiaire(Stagiaire stagiaireAAjouter) throws IOException {
		System.out.println(this.stagiaire);
		if (this.stagiaire.getNom().compareTo(stagiaireAAjouter.getNom()) > 0) {
			if (this.filsGauche == -1) {

				ListeStagiaire.raf.seek(ListeStagiaire.raf.getFilePointer() - 8); // position pointeur
				ListeStagiaire.raf.writeInt((int) (ListeStagiaire.raf.length()) / (octet)); // fils gauche aller dans
																							// .bin
				// pour modifier l'index
				ListeStagiaire.raf.seek(ListeStagiaire.raf.length());// aller à la fin du fichier
				this.creerNoeud(stagiaireAAjouter);
			} else {
				ListeStagiaire.raf.seek(this.filsGauche * (octet));
				Noeud noeudCourant = lireNoeud();
				noeudCourant.ajouterStagiaire(stagiaireAAjouter);
			}
		} else {
			if (this.filsDroit == -1) {
				ListeStagiaire.raf.seek(ListeStagiaire.raf.getFilePointer() - 4); // position pointeur
				ListeStagiaire.raf.writeInt((int) (ListeStagiaire.raf.length()) / (octet));
				ListeStagiaire.raf.seek(ListeStagiaire.raf.length());// aller à la fin du fichier
				this.creerNoeud(stagiaireAAjouter);

			} else {
				ListeStagiaire.raf.seek(this.filsDroit * (octet));
				Noeud noeudCourant = lireNoeud();
				noeudCourant.ajouterStagiaire(stagiaireAAjouter);
			}
		}
	}
//ajouter noeud stagiaire à l'arbre à travers la méthode ajouterStagiaire

	public void ajouterNoeud(Stagiaire noeud) throws IOException {
		if ((ListeStagiaire.raf.length() == 0)) {
			ListeStagiaire.raf.seek(0);
			creerNoeud(noeud);
		} else {
			ListeStagiaire.raf.seek(0);
			this.lireNoeud().ajouterStagiaire(noeud);
		}

	}

	// *****lire les index des stagiaires****

// methode ecriture les index
	public void infixe() throws IOException {

		if (this.filsGauche != -1) {
			ListeStagiaire.raf.seek(this.filsGauche * octet); // déplace le curseur à indexFG
			Noeud filsG = lireNoeud();// lire noeud fils gauche
			filsG.infixe();// appel récursif depuis noeud fils gauche
		}
		System.out.println(this.stagiaire + " " + this.filsGauche + " " + this.filsDroit);
		if (this.filsDroit != -1) {
			ListeStagiaire.raf.seek(this.filsDroit * octet);// déplace le curseur à indexFD
			Noeud filsD = lireNoeud();// lire noeud fils droit
			filsD.infixe();// appel récursif depuis noeud fils droit
		}

	}

//methode  racine
	public void infixeArbre() {
		try {
			ListeStagiaire.raf.seek(0);
			this.lireNoeud().infixe();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ****Rechercher stagiaire*****
//recherche stagiaire
	public Stagiaire rechercherStagiaire(String stagiaireARechercher) throws IOException {
		if (stagiaireARechercher.compareTo(this.stagiaire.getNom().trim()) < 0) {

			if (this.filsGauche != -1) {
				ListeStagiaire.raf.seek(this.filsGauche * octet); // déplace le curseur à indexFG
				Noeud filsG = lireNoeud();// lire noeud fils gauche
				return filsG.rechercherStagiaire(stagiaireARechercher);// appel récursif depuis noeud fils gauche
			}

		} else if (stagiaireARechercher.compareTo(this.stagiaire.getNom()) > 0) {

			if (this.filsDroit != -1) {
				ListeStagiaire.raf.seek(this.filsDroit * octet);// déplace le curseur à indexFD
				Noeud filsD = lireNoeud();// lire noeud fils droit
				return filsD.rechercherStagiaire(stagiaireARechercher);// appel récursif depuis noeud fils droit
			}

		} else {
			System.out.println(this.getStagiaire());
			return this.getStagiaire();
		}
		return null;
	}

	// racine
	public void rechercherNoeud(String noeud) throws IOException {
		if (ListeStagiaire.raf.length() != 0) {
			ListeStagiaire.raf.seek(0);
			Noeud racine = lireNoeud();
			racine.rechercherStagiaire(noeud);

		}
	}

// ****Recherche multicritères*****

	// recherche sur les noeuds

	public List<Stagiaire> rechercheMulticritere(Stagiaire stagRecherche, List<Stagiaire> lesStagiairesRecherche)
			throws IOException {
		if (this.filsGauche != -1) {
			ListeStagiaire.raf.seek(this.filsGauche * octet); // déplace le curseur à indexFG
			Noeud filsG = lireNoeud();// lire noeud fils gauche
			filsG.rechercheMulticritere(stagRecherche, lesStagiairesRecherche);// appel récursif depuis noeud
																						// fils gauche
		}
		// noeud
		boolean verif = false; //par défaut
		if (!(this.stagiaire.getNom().equals(" "))) {//LACROIX != null
			if ((stagRecherche.getNom().equals(this.stagiaire.getNom().trim()))) { //" " != LACROIX
				verif = true; //verif = false
				System.out.println("Ajoute a l etape : nom");
				lesStagiairesRecherche.add(this.stagiaire);
			}
		}
		if (!(this.stagiaire.getPrenom().equals(" "))) { // Pascale
			if ((stagRecherche.getPrenom().equals(this.stagiaire.getPrenom().trim()))) { // " " != Pascale
				verif = true; //verif = false
				System.out.println("Ajoute a l etape : prenom ");
				lesStagiairesRecherche.add(this.stagiaire);

			}
		}
		if (!(this.stagiaire.getDepartement().equals(" ")))  { // l'attribut est rempli
			if ((stagRecherche.getDepartement().equals(this.stagiaire.getDepartement().trim()))) { //les attributs du noeud actuel et du noeud arbtre sont différents
				verif = true; //false 
				System.out.println("Ajoute a l etape : departement");
				lesStagiairesRecherche.add(this.stagiaire);

			}
		}
		if (!(this.stagiaire.getPromo().equals(" "))) {
			if ((stagRecherche.getPromo().equals(this.stagiaire.getPromo().trim()))) {
				verif = true; //true
				System.out.println("Ajoute a l etape : promo ");
				lesStagiairesRecherche.add(this.stagiaire);

			}
		}
		if (!(this.stagiaire.getAnnee().equals(" "))) {
			if ((stagRecherche.getAnnee().equals(this.stagiaire.getAnnee().trim()))) {
				verif = true; //false on aura jamais des stagiaires
				System.out.println("Ajoute a l etape : annee");
				lesStagiairesRecherche.add(this.stagiaire);

			}
		}
		//lesStagiairesRecherche.add(this.stagiaire);
		// ajouter dans la liste
		if (verif == true) {
			System.out.println("dans le if de la liste");
			lesStagiairesRecherche.add(this.stagiaire);
		}
		verif = false;
		//System.out.println(lesStagiairesRecherche);

		if (this.filsDroit != -1) {
			ListeStagiaire.raf.seek(this.filsDroit * octet);// déplace le curseur à indexFD
			Noeud filsD = lireNoeud();// lire noeud fils droit
			filsD.rechercheMulticritere(stagRecherche, lesStagiairesRecherche);// appel récursif depuis noeud
																						// fils droit
		}

		return lesStagiairesRecherche;
	}

	// rechercheRacine
	public List<Stagiaire> rechercheMulticritereArbre(Stagiaire stagiaire, List<Stagiaire> lesStagiairesRecherche) {
		try {
			ListeStagiaire.raf.seek(0);
			lesStagiairesRecherche = this.lireNoeud().rechercheMulticritere(stagiaire, lesStagiairesRecherche);
			
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return lesStagiairesRecherche;
	}

//****Methode supprimer****
	// successeur
	public Noeud noeudSuccesseur() throws IOException {
		ListeStagiaire.raf.seek(0);
		ListeStagiaire.raf.seek(this.filsDroit * octet);// déplace le curseur à indexFD
		Noeud noeudCourant = lireNoeud();
		while (noeudCourant.filsGauche != -1) {
			System.out.println("dans le while");
			// ListeStagiaire.raf.seek(this.filsGauche * octet); // déplace le curseur à
			// indexFG
			noeudCourant = lireNoeud();// lire noeud fils gauche
		}
		return noeudCourant;
	}

	//

}
