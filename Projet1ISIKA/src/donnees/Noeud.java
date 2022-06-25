package donnees;

public class Noeud {
	// attributs

		private String prenom; // clé
		private Noeud filsGauche;
		private Noeud filsDroit;

		// constructeur

		public Noeud(String prenom) {
			super();
			this.prenom = prenom;
			this.filsDroit = null;
			this.filsGauche = null;

		}

		// getters/setters
		public String getPrenom() {
			return prenom;
		}

		public void setPrenom(String prenom) {
			this.prenom = prenom;
		}

		public Noeud getFilsGauche() {
			return filsGauche;
		}

		public void setFilsGauche(Noeud filsGauche) {
			this.filsGauche = filsGauche;
		}

		public Noeud getFilsDroit() {
			return filsDroit;
		}

		public void setFilsDroit(Noeud filsDroit) {
			this.filsDroit = filsDroit;
		}

		// methodes spécifiques

		public String toString() {
			String result = " ";
			if (this.filsGauche != null) {
				result += this.filsGauche.toString();
			}
			result += " " + prenom;
			if (this.filsDroit != null) {
				result += this.filsDroit.toString();
			}
			return result;
		}

		public void ajouterValeur(String prenomAAjouter) {
			if (this.prenom.compareTo(prenomAAjouter) > 0) {
				if (this.filsGauche == null) {
					this.filsGauche = new Noeud(prenomAAjouter);
				} else {
					this.filsGauche.ajouterValeur(prenomAAjouter);
				}
			} else {
				if (this.filsDroit == null) { // espace pour inserer le nouveau noeud
					this.filsDroit = new Noeud(prenomAAjouter);
				} else {
					this.filsDroit.ajouterValeur(prenomAAjouter);
				}
			}
		}

		public String toStringPrefixe() {
			String result = " ";
			result += " " + prenom;
			if (this.filsGauche != null) {
				result += this.filsGauche.toString();
			}
			if (this.filsDroit != null) {
				result += this.filsDroit.toString();
			}
			return result;
		}

		public String toStringInfixe() {
			String result = " ";
			if (this.filsGauche != null) {
				result += this.filsGauche.toString();
			}
			if (this.filsDroit != null) {
				result += this.filsDroit.toString();
			}
			result += " " + prenom;
			return result;
		}

		public int hauteur() { // taille du plus grand chemin de la racine a une feuille
			if (this.filsGauche == null && this.filsDroit == null) {
				return 0;
			} else if (this.filsDroit == null) {
				return 1 + this.filsGauche.hauteur();
			} else if (this.filsGauche == null) {
				return 1 + this.filsDroit.hauteur();
			} else {
				return 1 + Math.max(this.filsDroit.hauteur(), this.filsGauche.hauteur());

			}
		}

		public boolean rechercherPrenom(String prenomARechercher) {
			if (prenomARechercher.compareTo(this.prenom) < 0) {
			if (this.filsGauche == null) {
				return false;
			} else if (prenomARechercher == this.filsGauche.getPrenom()) {
				return true;
			} else {
				return this.filsGauche.rechercherPrenom(prenomARechercher);
			}
		}
			if (prenomARechercher.compareTo(this.prenom) > 0) {
				if (this.filsDroit == null) {
					return false;
				} else if (prenomARechercher == this.filsDroit.getPrenom()) {
					return true;
				} else {
					return this.filsDroit.rechercherPrenom(prenomARechercher);
					}
			} else {
				return true;
			}
		}
		
		public int nbNoeuds() {
			if (this.filsDroit == null && this.filsGauche == null) {
				return 0;
			} else if (this.filsDroit != null && this.filsGauche == null) {
				return 1 + filsDroit.nbNoeuds();
			}else if (this.filsDroit == null && this.filsGauche != null) {
				return 1 + filsGauche.nbNoeuds();
			} else { 
				return 1 + filsGauche.nbNoeuds() + filsDroit.nbNoeuds();
			}
		}

	/*

	public Noeud supprimerNoeud(String valeurASupprimer) {
		if (this.prenom == (valeurASupprimer)) {
			return this;
		}
		else if (valeurASupprimer)) {
			return this.filsDroit;
			
		}
		
	}
	*/ 
		
	public Noeud noeudSuccesseur() {
		Noeud noeudCourant = filsDroit;
		while (noeudCourant.filsGauche != null){
			noeudCourant = filsGauche;
		}
		return noeudCourant;
	}

	/*
		public Noeud supprimerRacine() {
			// pas de descendants
			if (filsGauche == null && filsDroit == null) {
				return null;
			}
			// un seul descendant
			else if (filsGauche != null || filsDroit != null) {
				if (filsGauche != null) {
					return filsGauche;
				} else if (filsDroit != null) {
					return filsDroit;
				}
				// deux descendants
			} else {
				this.filsDroit = this;
				return this.filsDroit.noeudSuccesseur();
			}
		}

	}
	*/

}
