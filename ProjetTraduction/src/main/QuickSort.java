package main;

public class QuickSort {
    public static void quicksort_association(String[] clefs, String[] valeurs, int low, int high) {
        if (low >= high) return;

        int j = partition_association(clefs, valeurs, low, high);
        quicksort_association(clefs, valeurs, j+1, high);
        quicksort_association(clefs, valeurs, low, j-1);
    }

    public static int partition_association(String[] clefs, String[] valeurs, int low, int high) {
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
            
            echanger_association(clefs, valeurs, i, j);
        }

        echanger_association(clefs, valeurs, low, j);

        return j;
    }

    public static void echanger_association(String[] clefs, String[] valeurs, int i, int j) {
        String temp_clef = clefs[i];
        clefs[i] = clefs[j];
        clefs[j] = temp_clef;

        String temp_valeur = valeurs[i];
        valeurs[i] = valeurs[j];
        valeurs[j] = temp_valeur;
    }
}
