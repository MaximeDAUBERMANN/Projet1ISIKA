package donnees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.ArrayList;
import java.util.List;
import donnees.Stagiaire;
public class ListeAffichage {
	private static Stagiaire stagiaire; //Déclarartion du stagiare
	public static RandomAccessFile raf; //Déclaration du fichier .bin
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		try {
			raf = new RandomAccessFile("src/arbreStagiaires.bin", "rw");  //création du fichier .bin vide
			FileReader donnneesStagiaires = new FileReader("src/Stagiaires.txt"); // lire le fichier .txt des Donnees
			BufferedReader br = new BufferedReader(donnneesStagiaires);
			List<Stagiaire> lesStagiaires = new ArrayList<>(); // créer une liste avec Donnees à travers le txt
			// ****Etape 1 : Parcours de la liste complète****
			while (br.ready()) {
				Stagiaire stagiaire = new Stagiaire(null, null, null, null, null);
				stagiaire.setNom(br.readLine());
				stagiaire.setPrenom(br.readLine());
				stagiaire.setDepartement(br.readLine());
				stagiaire.setPromo(br.readLine());
				stagiaire.setAnnee(br.readLine());
				lesStagiaires.add(stagiaire);
				br.readLine();
			}
			// ****Etape 2 : Affichage de la liste pour vérifier la lecture du fichier .txt *****
			for (Stagiaire stage : lesStagiaires) {
				System.out.println(stage);
			}
			donnneesStagiaires.close();
			// **** Etape 3 : Création de l'arbre binaire****
			Noeud stagiairesArbre = new Noeud(stagiaire); // créer les noeuds de l'arbre binaire
			for (Stagiaire stage : lesStagiaires) {
				// ajouter un noeud
				stagiairesArbre.ajouterNoeud(stage);
			}
			
			// ****Vérification des index ****
			stagiairesArbre.infixeArbre(); // afficher les index pour chaque stagiaire
			Stagiaire stage1 = new Stagiaire("LACROIX", "Pascale", "91", "BOBI 5", "2008");
			//stagiairesArbre.rechercherStagiaire(stage1);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
