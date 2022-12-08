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
	public TableAssociation(String[] clefs, String[] valeurs, int maxAssoc) {
		this.clefs = clefs;
		this.valeurs = valeurs;
		this.maxAssoc = maxAssoc;
		this.nbAssoc = this.clefs.length;
	}
	
	public static TableAssociation tableDictionnaire() {
		String[] dico = ACX.lectureDico("lib/frenchEnglish.txt");
	
		int maxAssoc = dico.length/2 + 1; // On peut ajouter une clef
		String[] clefs = new String[maxAssoc]; 
		String[] valeurs = new String[maxAssoc];

		for (int i = 0; i< dico.length; i++) {
			if (i % 2 == 0) {
				clefs[i/2] = dico[i];
			}
			else {
				valeurs[i/2] = dico[i];
			}
		}
		
		return new TableAssociation(clefs, valeurs, maxAssoc);
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
}
