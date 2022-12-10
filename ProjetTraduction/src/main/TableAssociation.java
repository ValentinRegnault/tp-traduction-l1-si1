package main;

import util.ACX;

public class TableAssociation {
	public String[] clefs;
	public String[] valeurs;
	public int maxAssoc;
	public int nbAssoc;
	
	/**
	 * Constructeur de la table d'association. Cette fonction charge le dictionnaire
	 * français anglais, et créer une table d'association des mots français vers les
	 * mots anglais. Il laisse la place pour l'ajout d'un couple mot/traduction.
	 */
	public TableAssociation(String[] clefs, String[] valeurs, int maxAssoc, int nbAssoc) {
		this.clefs = clefs;
		this.valeurs = valeurs;
		this.maxAssoc = maxAssoc;
		this.nbAssoc = nbAssoc;
	}
	
	/**
	 * Créer une table d'association associant les mot racines français à
	 * leur traduction anglaise, en utilisant la version non triée du fichier
	 * du dictionnaire.
	 * @return
	 */
	public static TableAssociation tableDictionnaireNiveau1() {
		String[] dico = ACX.lectureDico("lib/frenchEnglish.txt");

		int maxAssoc = dico.length/2 + 1; // On peut ajouter une clef
		String[] clefs = new String[maxAssoc]; 
		String[] valeurs = new String[maxAssoc];

		for (int i = 0; i< dico.length; i++) {
			if (i % 2 == 0) {
				clefs[i/2] = dico[i].toLowerCase();
			}
			else {
				valeurs[i/2] = dico[i].toLowerCase();
			}
		}
		
		return new TableAssociation(clefs, valeurs, maxAssoc, dico.length/2);
	}

	/**
	 * Créer une table d'association associant les mot racines français à
	 * leur traduction anglaise, en utilisant la version triée du dictionnaire
	 * @return
	 */
	public static TableAssociation tableDictionnaireNiveau3() {
		String[] dico = ACX.lectureDico("lib/frenchEnglishTrie.txt");

		int maxAssoc = dico.length/2 + 1; // On peut ajouter une clef
		String[] clefs = new String[maxAssoc]; 
		String[] valeurs = new String[maxAssoc];

		for (int i = 0; i< dico.length; i++) {
			if (i % 2 == 0) {
				clefs[i/2] = dico[i].toLowerCase();
			}
			else {
				valeurs[i/2] = dico[i].toLowerCase();
			}
		}
		
		return new TableAssociation(clefs, valeurs, maxAssoc, dico.length/2);
	}

	/**
	 * Créer une table d'association associant les mot français à
	 * leur racine, en utilisant la version non triée du fichier racine.
	 * @return
	 */
	public static TableAssociation tableRacinesNiveau1() {
		String[] dico = ACX.lectureDico("lib/racines.txt");
	
		int maxAssoc = dico.length;
		String[] clefs = new String[maxAssoc]; 
		String[] valeurs = new String[maxAssoc];

		for (int i = 0; i< dico.length; i++) {
			String[] mots = ACX.mots(dico[i]);
			clefs[i] = mots[1].toLowerCase();
			valeurs[i] = mots[2].toLowerCase();
		}

		return new TableAssociation(clefs, valeurs, maxAssoc, clefs.length);
	}

	/**
	 * Créer une table d'association associant les mot français à
	 * leur racine, en utilisant la version triée des racines.
	 * @return
	 */
	public static TableAssociation tableRacinesNiveau3() {
		String[] dico = ACX.lectureDico("lib/racinesTrie.txt");
	
		int maxAssoc = dico.length/2; // On peut ajouter une clef
		String[] clefs = new String[maxAssoc]; 
		String[] valeurs = new String[maxAssoc];

		for (int i = 0; i< dico.length; i++) {
			if (i % 2 == 0) {
				clefs[i/2] = dico[i].toLowerCase();
			}
			else {
				valeurs[i/2] = dico[i].toLowerCase();
			}
		}
		
		return new TableAssociation(clefs, valeurs, maxAssoc, dico.length/2);
	}
	
	/**
	 * Retourne l'indice de la clef dans le tableau des clefs, ou -1 si elle n'y est pas. 
	 * Cette indice est aussi l'indice de la valeur correspondant à la clef, dans le tableau
	 * des valeurs.
	 * @param clef la clef dont on cherche l'indice.
	 * @return l'indice de la clef si elle est présente dans la table, ou -1.
	 */
	public int indiceClef (String clef) {
		for (int i = 0; i < nbAssoc; i++) {
			if (clef.equals(clefs[i])) {
				return i;
			}
		}
		return -1;
	}

	/**
	 * Retourne l'indice de la clef dans le tableau des clefs, ou -1 si elle n'y est pas. 
	 * Cette indice est aussi l'indice de la valeur correspondant à la clef, dans le tableau
	 * des valeurs.
	 * Cette fonction utilise la dichotomie et suppose que les clefs de la table sont triées
	 * @see trier pour trier la table avec l'algorithme tri rapide
	 * @param clef la clef dont on cherche l'indice.
	 * @return l'indice de la clef si elle est présente dans la table, ou -1.
	 */
	public int indiceClefRapide (String clef) {
		int min = 0;
		int max = nbAssoc - 1;

		while (max - min >= 0) {
			int millieu = (min + max) / 2;
			if (clefs[millieu].compareTo(clef) > 0) {
				max = millieu - 1;
			}
			else if (clefs[millieu].compareTo(clef) < 0) {
				min = millieu + 1;
			}
			else {
				return millieu;
			}
		}
		return -1;
	}
	
	/**
	 * Retourne la valeur correspondant à la clef, ou null si cette clef n'est associée
	 * à aucune valeur.
	 * @param clef la clef dont on cherche la valeur correspondante.
	 * @return la valeur correspondant à la clef, ou null si cette clef n'est associée a aucune valeur.
	 */
	public String recuperer(String clef) {
		int index = indiceClef(clef);
		if (index == -1) return null;
		return valeurs[index];
	}

	/**
	 * Retourne la valeur correspondant à la clef, ou null si cette clef n'est associée
	 * à aucune valeur.
	 * Cette fonction fait appel a indiceClefRapide(), qui utilise la dichotomie et suppose que
	 * les clefs de la tables sont triées.
	 * @see indiceClefRapide pour rechercher un indice avec la dichotomie
	 * @see trier pour trier la table avec l'algorithme tri rapide
	 * @param clef la clef dont on cherche la valeur correspondante.
	 * @return la valeur correspondant à la clef, ou null si cette clef n'est associée a aucune valeur.
	 */
	public String recupererRapide(String clef) {
		int index = indiceClefRapide(clef);
		if (index == -1) return null;
		return valeurs[index];
	}
	
	/**
	 * Ajoute un couple si il reste de la place dans la table d'association, sinon ne
	 * fait rien.
	 * 
	 * @see nbAssoc, maxAssoc
	 * 
	 * @param clef La clef du couple qui va être ajouté.
	 * @param valeur la valeur du couple qui va être ajouté.
	 */
	public void ajouterCouple(String clef, String valeur) {
		if (nbAssoc >= maxAssoc) return; //impossible d'ajouter un couple, la table est pleine.
		if (indiceClef(clef) != -1) return; // cette clef existe déjà !
		
		clefs[nbAssoc] = clef;
		valeurs[nbAssoc] = valeur;
		nbAssoc++;
	}

	public void trier() {
		QuickSort.quicksort_association(clefs, valeurs, 0, nbAssoc - 1);
	}

	public void enregistrer(String nom) {
		String[] tab = new String[nbAssoc*2];
		for (int i = 0; i < nbAssoc; i++) {
			tab[i*2] = clefs[i];
			tab[i*2 + 1] = valeurs[i];
		}
		ACX.ecritureFichierString(tab, "lib/" + nom + ".txt");
	}
}
