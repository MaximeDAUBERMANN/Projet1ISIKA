package donnees;

import java.io.IOException;
import java.io.RandomAccessFile;
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

//*****lire les index des stagiaires****
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

//methode lire les index
	public void infixeArbre() {
		try {
			ListeStagiaire.raf.seek(0);
			this.lireNoeud().infixe();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

	}

	// ****Recherche stagiaire*****

	public boolean rechercherStagiaire(Stagiaire stagiaireARechercher) throws IOException {
		if (stagiaireARechercher.getNom().compareTo(this.stagiaire.getNom()) < 0) {
			ListeStagiaire.raf.seek(this.filsGauche * octet); // déplace le curseur à indexFG
			if (this.filsGauche == -1) {
				return false;
			} else if (stagiaireARechercher.getNom() == this.stagiaire.getNom()) {
				return true;
			} else {
				Noeud filsG = lireNoeud();// lire noeud fils gauche
				filsG.rechercherStagiaire(stagiaireARechercher);// appel récursif depuis noeud fils gauche
			}
		} else if (stagiaireARechercher.getNom().compareTo(this.stagiaire.getNom()) > 0) {
			ListeStagiaire.raf.seek(this.filsDroit * octet);// déplace le curseur à indexFD
			if (this.filsDroit == -1) {
				return false;
			} else if (stagiaireARechercher.getNom() == this.getStagiaire().getNom()) {
				return true;
			} else {
				Noeud filsD = lireNoeud();// lire noeud fils droit
				filsD.rechercherStagiaire(stagiaireARechercher);// appel récursif depuis noeud fils droit
			}
		} else
			return true;
		return true;
	}

	//
//	public int hauteur() { // taille du plus grand chemin de la racine a une feuille
//		if (this.filsGauche == null && this.filsDroit == null) {
//			return 0;
//		} else if (this.filsDroit == null) {
//			return 1 + this.filsGauche.hauteur();
//		} else if (this.filsGauche == null) {
//			return 1 + this.filsDroit.hauteur();
//		} else {
//			return 1 + Math.max(this.filsDroit.hauteur(), this.filsGauche.hauteur());
//
//		}
//	}

//	public int nbNoeuds() {
//		if (this.filsDroit == null && this.filsGauche == null) {
//			return 0;
//		} else if (this.filsDroit != null && this.filsGauche == null) {
//			return 1 + filsDroit.nbNoeuds();
//		} else if (this.filsDroit == null && this.filsGauche != null) {
//			return 1 + filsGauche.nbNoeuds();
//		} else {
//			return 1 + filsGauche.nbNoeuds() + filsDroit.nbNoeuds();
//		}
//	}

	/*
	 * 
	 * public Noeud supprimerNoeud(String valeurASupprimer) { if (this.prenom ==
	 * (valeurASupprimer)) { return this; } else if (valeurASupprimer)) { return
	 * this.filsDroit;
	 * 
	 * }
	 * 
	 * }
	 */

//	public Noeud noeudSuccesseur() {
//		Noeud noeudCourant = filsDroit;
//		while (noeudCourant.filsGauche != null) {
//			noeudCourant = filsGauche;
//		}
//		return noeudCourant;
//	}

	/*
	 * public Noeud supprimerRacine() { // pas de descendants if (filsGauche == null
	 * && filsDroit == null) { return null; } // un seul descendant else if
	 * (filsGauche != null || filsDroit != null) { if (filsGauche != null) { return
	 * filsGauche; } else if (filsDroit != null) { return filsDroit; } // deux
	 * descendants } else { this.filsDroit = this; return
	 * this.filsDroit.noeudSuccesseur(); } }
	 * 
	 * }
	 */

}
