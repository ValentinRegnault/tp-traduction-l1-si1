package main;

import util.ACX;

/**
 * TableAssociation est une classe qui stocke deux tableaux de String. Elle 
 * représente un ensemble de couple clef/valeurs. Chaque couple a un indice,
 * la clef du couple est stockée à cette indice dans clef, un attribut de type 
 * String[]. La valeur est stockée au même indice dans un autre attribut de type
 * String[], le tableau valeurs.
 * J'ai fait le choix de ne pas laisser de place supplémentaire, mais de 
 * modifier la taile du tableau au moment de l'appel des méthodes ajouterCouple
 * et ajouterAssociations
 */
public class TableAssociation {
	public String[] clefs;
	public String[] valeurs;
	
	/**
	 * Constructeur de la table d'association. Cette fonction charge le dictionnaire
	 * français anglais, et créer une table d'association des mots français vers les
	 * mots anglais.
	 */
	public TableAssociation(String[] clefs, String[] valeurs) {
		this.clefs = clefs;
		this.valeurs = valeurs;
	}
	
	/**
	 * Créer une table d'association associant les mot racines français à
	 * leur traduction anglaise, en utilisant la version non triée du fichier
	 * du dictionnaire.
	 * @return
	 */
	public static TableAssociation tableDictionnaireNiveau1() {
		String[] dico = ACX.lectureDico("lib/frenchEnglish.txt");

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
		
		return new TableAssociation(clefs, valeurs);
	}

	/**
	 * Créer une table d'association associant les mot racines français à
	 * leur traduction anglaise, en utilisant la version triée du dictionnaire
	 * @return
	 */
	public static TableAssociation tableDictionnaireNiveau3() {
		String[] dico = ACX.lectureDico("lib/frenchEnglishTrie.txt");

		int maxAssoc = dico.length/2;
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
		
		return new TableAssociation(clefs, valeurs);
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

		return new TableAssociation(clefs, valeurs);
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
		
		return new TableAssociation(clefs, valeurs);
	}
	
	/**
	 * Retourne l'indice de la clef dans le tableau des clefs, ou -1 si elle n'y est pas. 
	 * Cette indice est aussi l'indice de la valeur correspondant à la clef, dans le tableau
	 * des valeurs.
	 * @param clef la clef dont on cherche l'indice.
	 * @return l'indice de la clef si elle est présente dans la table, ou -1.
	 */
	public int indiceClef (String clef) {
		for (int i = 0; i < clefs.length; i++) {
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
		int max = clefs.length - 1;

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
	 * Tri la table d'association, dans l'ordre croissant lexicographique des
	 * clefs, avec l'algorithme du tri rapide.
	 * @see QuickSort.quicksortAssociation
	 */
	public void trier() {
		QuickSort.quicksortAssociation(clefs, valeurs, 0, clefs.length - 1);
	}

	/**
	 * Enregistre la table dans un fichier à l'emplacement lib/{nom}.txt du
	 * projet. Un couple clef/valeur est représenté sur deux lignes : la premiere
	 * pour la clef, la deuxième pour la valeur. Les lignes paires correspondent
	 * donc aux clefs, les lignes impaires aux valeurs.
	 * @param nom nom du fichier dans lequel la table est enregister.
	 */
	public void enregistrer(String nom) {
		String[] tab = new String[clefs.length*2];
		for (int i = 0; i < clefs.length; i++) {
			tab[i*2] = clefs[i];
			tab[i*2 + 1] = valeurs[i];
		}
		ACX.ecritureFichierString(tab, "lib/" + nom + ".txt");
	}

	/**
	 * Remplace les élement null dans le premier tableau aCompleter par les
	 * élément de complement, un élément par null, en conservant l'ordre.
	 * Par exemple pour :
	 *  aCompleter = {null, "soleil", null};
	 * 	complement = {"nuit", "jour"};
	 * La fonction modifiera aCompleter en {"nuit", "soleil", "jour"}
	 * @param aCompleter
	 * @param complement
	 */
	public static void completerTabParTab(String[] aCompleter, String[] complement) {
		int j = 0;
		for (int i = 0; i < aCompleter.length; i++) {
			if (complement.length <= j) return;
			if (aCompleter[i] == null) aCompleter[i] = complement[j++];
		}
	}

	/**
	 * Retourne l'indice de la clef précendente dans le tableau des clefs de la 
	 * clef passée en paramètre. Si cette clef doit être placée en tout premier
	 * élément, la fonction retourne -1. Si la clef est déjà dans le tableau des
	 * clefs, la fonction retourne l'indice de la clef précédente (-1 si c'est
	 * la premiere), sinon, elle retourne l'indice de la clef inférieure la plus 
	 * proche (en distance lexicographique).
	 * @param clef 
	 * @return 
	 */
	public int indicePlusProche(String clef) {
		int min = -1;
		int max = clefs.length;

		while (max - min > 1) {
			int millieu = (min + max) / 2;
			if (clefs[millieu].compareTo(clef) > 0) {
				max = millieu;
			}
			else if (clefs[millieu].compareTo(clef) < 0) {
				min = millieu;
			}
			else {
				return millieu-1;
			}
		}
		return min;
	}

	/**
	 * Ajoute plusieurs associations clef/valeurs. On suppose que les clefs ne
	 * sont pas déjà dans la table d'association. 
	 * @param clefsSup un tableau de toute les clefs à ajouter. La clef à 
	 * l'indice i dans clefsSup sera associé a la valeur à l'indice i dans
	 * valeurSup
	 * @param valeursSup un tableau de toute les valeurs à ajouter. La valeur à 
	 * l'indice i dans valeursSup sera associé a la clef à l'indice i dans
	 * clefsSup
	 */
	public void ajouterAssociations (String[] clefsSup, String[] valeursSup) {
		String[] nouvellesClefs = new String[clefs.length + clefsSup.length];
		String[] nouvellesValeurs = new String[valeurs.length + valeursSup.length];

		//on trie les associations par leur clefs
		QuickSort.quicksortAssociation(clefsSup, valeursSup, 0, clefsSup.length - 1);

		for (int i = 0; i < clefsSup.length; i++) {
			int plusProche = indicePlusProche(clefsSup[i]) + 1;
			while (nouvellesClefs[plusProche] != null) {
				// si on a deja ajouté une clef a l'indice plusProche,
				//on est sur qu'elle est avant celle qu'on essaye d'ajouter
				//maintenant car on a trier les clefs suplementaires.
				//on peut simplement la mettre a l'indice suivant.
				plusProche++;
			} 
			nouvellesClefs[plusProche] = clefsSup[i];
			nouvellesValeurs[plusProche] = valeursSup[i];
		}

		completerTabParTab(nouvellesClefs, clefs);
		completerTabParTab(nouvellesValeurs, valeurs);

		clefs = nouvellesClefs;
		valeurs = nouvellesValeurs;
	}

	/**
	 * Ajoute un couple d'association clef/valeur. On suppose que la clef n'est
	 * pas déjà dans la table d'association.
	 * @param clef une clef
	 * @param valeur la valeur associé à la clef
	 */
	public void ajouterUnCouple(String clef, String valeur) {
		ajouterAssociations(new String[] {clef}, new String[] {valeur});
	}
	
}
