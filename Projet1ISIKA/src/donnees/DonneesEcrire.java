package donnees;

import java.io.FileWriter;
import java.io.IOException;

public class DonneesEcrire {
	public static void main(String[] args) {
		// TODO Auto-generated method stub
try {
	FileWriter fw = new FileWriter ("src/Stagiaires.txt", true);
fw.write("  ");
fw.write("  ");
fw.close();
} catch (IOException e) {
	e.printStackTrace();
}
	}
}
