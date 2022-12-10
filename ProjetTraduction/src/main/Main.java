package main;

import util.ACX;

public class Main {
	public static final TableAssociation tableTraductions = TableAssociation.tableDictionnaireNiveau3();
	public static final TableAssociation tableRacines = TableAssociation.tableRacinesNiveau3();

	public static String [] traduire(String [] texte){
		String[] traductions = new String[texte.length];
		for (int i = 0; i < texte.length; i++) {
			String trad = tableTraductions.recuperer(texte[i].toLowerCase());
			if (trad == null) {
				traductions[i] = texte[i];
			}
			else {
				traductions[i] = trad;
			}
		}
		return traductions;
	}

	public static String[] traduireNiveau2(String[] texte) {
		String[] traductions = new String[texte.length];
		for (int i = 0; i < texte.length; i++) {
			String trad = tableTraductions.recuperer(texte[i].toLowerCase());
			if (trad == null) {
				String racine = tableRacines.recuperer(texte[i].toLowerCase());
				System.out.println(texte[i] + " est pas dans le dico, sa racine est " + racine);
				if (racine != null) {
					trad = tableTraductions.recuperer(racine);
				}
			}

			if (trad == null) {
				traductions[i] = texte[i];
			}
			else {
				traductions[i] = trad;
			}
		}
		return traductions;
	}  

	public static String[] traduireNiveau3(String[] texte) {
		String[] traductions = new String[texte.length];
		for (int i = 0; i < texte.length; i++) {
			String trad = tableTraductions.recuperer(texte[i].toLowerCase());
			if (trad == null) {
				String racine = tableRacines.recupererRapide(texte[i].toLowerCase());
				System.out.println(texte[i] + " est pas dans le dico, sa racine est " + racine);
				if (racine != null) {
					trad = tableTraductions.recupererRapide(racine);
				}
			}

			if (trad == null) {
				traductions[i] = texte[i];
			}
			else {
				traductions[i] = trad;
			}
		}
		return traductions;
	}
	
	public static void main(String[] args) {
		//Trie des tables d'associations
		// tableTraductions.trier();
		// tableTraductions.enregistrer("frenchEnglishTrie");
		// tableRacines.trier();
		// tableRacines.enregistrer("racinesTrie");

		ACX.interfaceTraduction("traduireNiveau3");
	}
}