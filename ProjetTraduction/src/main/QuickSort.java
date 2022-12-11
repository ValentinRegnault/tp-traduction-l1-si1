package main;

public class QuickSort {

    /**
     * Utilise l'algorithme du tri rapide pour trier un tableau (clefs) dans 
     * l'ordre croissant lexicographique, sur un interval, en préservant 
     * les correspondance avec un deuième tableau (valeurs).
     * Les deux tableaux seront triés par rapport au premier, c'est a dire que
     * si un élement à l'indice i dans clefs est déplacé par le tri à l'indice i' dans clef, 
     * alors l'élément à l'indice i dans valeurs est déplacé en i' dans valeurs.
     * Les deux tableaux doivent être de longueures égales.
     * @param clefs le tableau de référence, qui sera trié dans l'ordre croissant
     * @param valeurs le tableau dont les correspondance avec clefs seront préservées
     * @param low la borne inférieur (incluse) de l'interval à trier 
     * (0 pour trier tout le tableau)
     * @param high la borne supérieur (incluse) de l'interval à trier 
     * (clefs.length-1 pour trier tout le tableau)
     */
    public static void quicksortAssociation(String[] clefs, String[] valeurs, int low, int high) {
        if (low >= high) return;

        int j = partitionAssociation(clefs, valeurs, low, high);
        quicksortAssociation(clefs, valeurs, j+1, high);
        quicksortAssociation(clefs, valeurs, low, j-1);
    }

    /**
     * Fonction de partition nécessaire à l'algorithme trie rapide.
     * Le partitionnment consiste en : 
     * 1) prendre un tableau et un intervale
     * 2) choisir un élement pivot (ici le premier element de l'interval)
     * 3) modifier l'interval pour que tout les élements inférieurs ou égaux au
     * pivot soit avant le pivot, et que tout les éléments strictement supérieur au
     * pivot soit après le pivot.
     * Cette fonctions partionne le premier tableau et préserve les correspondances
     * avec le deuxième, et fait partie de l'algorithme du tri rapide qui peut être
     * utilisé avec la fonction quicksortAssociation.
     * @see quicksortAssociation
     * @param clefs 
     * @param valeurs
     * @param low
     * @param high
     * @return
     */
    public static int partitionAssociation(String[] clefs, String[] valeurs, int low, int high) {
        int i = low + 1;
        int j = high;

        String pivot = clefs[low];

        while (true) {
            while (i <= high && clefs[i].compareTo(pivot) <= 0) {
                i++;
            }

            while (j >= low && clefs[j].compareTo(pivot) > 0) {
                j--;
            }

            if (i >= j) break;
            
            echangerAssociation(clefs, valeurs, i, j);
        }

        echangerAssociation(clefs, valeurs, low, j);

        return j;
    }

    /**
     * Echange les éléments à l'indice i dans les tableaux clefs et valeurs,
     * respectivement avec les élements à l'indice j dans les tableaux clefs et
     * valeurs.
     * @param clefs
     * @param valeurs
     * @param i 
     * @param j
     */
    public static void echangerAssociation(String[] clefs, String[] valeurs, int i, int j) {
        String temp_clef = clefs[i];
        clefs[i] = clefs[j];
        clefs[j] = temp_clef;

        String temp_valeur = valeurs[i];
        valeurs[i] = valeurs[j];
        valeurs[j] = temp_valeur;
    }
}
