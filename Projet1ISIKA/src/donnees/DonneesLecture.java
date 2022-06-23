package donnees;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

public class DonneesLecture {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		
try {
	FileReader donnneesStagiaires = new FileReader("src/Stagiaires.txt");
	
	BufferedReader br = new BufferedReader(donnneesStagiaires);
	
	String contenu = "";
	while (br.ready()) {
		contenu += br.readLine() + "\n";
	}
	System.out.println(contenu);
		
	donnneesStagiaires.close();
} catch (IOException e) {
	e.printStackTrace();
}
	}

}
