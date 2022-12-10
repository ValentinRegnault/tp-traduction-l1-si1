package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TraducteurTest {
	@Test
	public void testIndiceClef1() {
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3, 3);
		
		String clef = "nuit";
		int indice = 1;
		assertEquals(table.indiceClef(clef), indice);
	}
	
	@Test
	public void testIndiceClef2() {
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3, 3);
		
		String clef = "dsvssbsbsb";
		assertEquals(table.indiceClef(clef), -1);
	}

	@Test
	public void testIndiceClefRapide1() {
		String[] clefs = new String[] {"a"};
		String[] valeurs = new String[] {"a"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 1, 1);
		
		String clef = "a";
		int indice = 0;
		assertEquals(table.indiceClefRapide(clef), indice);
	}

	@Test
	public void testIndiceClefRapide2() {
		String[] clefs = new String[] {"a"};
		String[] valeurs = new String[] {"a"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 1, 1);
		
		String clef = "b";
		int indice = -1;
		assertEquals(table.indiceClefRapide(clef), indice);
	}

	@Test
	public void testIndiceClefRapide3() {
		String[] clefs = new String[] {"a", "b"};
		String[] valeurs = new String[] {"a", "b"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 2, 2);
		
		String clef = "a";
		assertEquals(table.indiceClef(clef), 0);
	}

	@Test
	public void testIndiceClefRapide4() {
		String[] clefs = new String[] {"a", "b"};
		String[] valeurs = new String[] {"a", "b"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 2, 2);
		
		String clef = "b";
		assertEquals(table.indiceClef(clef), 1);
	}

	@Test
	public void testIndiceClefRapide5() {
		String[] clefs = new String[] {"a", "b"};
		String[] valeurs = new String[] {"a", "b"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 2, 2);
		
		String clef = "c";
		assertEquals(table.indiceClef(clef), -1);
	}

	@Test
	public void testIndiceClefRapide6() {
		String[] clefs = new String[] {"a", "b", "c", "d", "e"};
		String[] valeurs = new String[] {"a", "b", "c", "d", "e"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 5, 5);
		
		String clef = "d";
		assertEquals(table.indiceClef(clef), 3);
	}

	@Test
	public void testIndiceClefRapide7() {
		String[] clefs = new String[] {"a", "b", "c", "d", "e"};
		String[] valeurs = new String[] {"a", "b", "c", "d", "e"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 5, 5);
		
		String clef = "e";
		assertEquals(table.indiceClef(clef), 4);
	}

	@Test
	public void testIndiceClefRapide8() {
		String[] clefs = new String[] {"a", "b", "c", "d", "e"};
		String[] valeurs = new String[] {"a", "b", "c", "d", "e"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 5, 5);
		
		String clef = "a";
		assertEquals(table.indiceClef(clef), 0);
	}
	
	@Test
	public void testRecuperer1(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3, 3);
		
		String clef = "arbre";
		String valeur = "tree";
		assertEquals(table.recuperer(clef).toLowerCase(), valeur);
	}
	
	@Test
	public void testRecuperer2(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3, 3);
		
		String clef = "nuit";
		String valeur = "night";
		assertEquals(table.recuperer(clef).toLowerCase(), valeur);
	}
	
	@Test
	public void testRecuperer3(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3, 3);
		
		String clef = "dpjdsklns";
		assertNull(table.recuperer(clef));
	}
	
	@Test
	public void testAjouterClef1() {
		int maxAssoc = 1;
		int nbAssoc = 0;
		String[] clefs = new String[maxAssoc];
		String[] valeurs = new String[maxAssoc] ;
		TableAssociation table = new TableAssociation(clefs, valeurs, maxAssoc, nbAssoc);
		
		//On fait plusieurs test ici, car JUnit effectue les tests en parallèle
		//et on veut éviter les interférence entre ces tests.
		String clef = "eat";
		String valeur = "manger";
		table.ajouterCouple(clef, valeur);
		assertEquals(table.recuperer(clef), valeur);
		
		String clef2 = "drink";
		String valeur2 = "boire";
		table.ajouterCouple(clef2, valeur2); // le couple ne sera pas ajoutée car la 
		//table est pleine
		assertNull(table.recuperer(clef2));
		
		String clef3 = "eat";
		String valeur3 = "boire";
		table.ajouterCouple(clef3, valeur3); // le couple ne sera pas ajoutée car la clef 
		//est déjà associé à "manger"
		//On s'attend a recuperer "manger" pour la clef "eat", et pas "boire"
		assertEquals(table.recuperer(clef3), "manger");
	}
	
	@Test
	public void testTableDictionnaire() {
		TableAssociation table = TableAssociation.tableDictionnaireNiveau1();
		
		assertEquals(table.recuperer("bonjour"), "good morning");
		assertEquals(table.recuperer("manger"), "eat");
		assertEquals(table.recuperer("boire"), "drink");
		assertNull(table.recuperer("qgfdbdb"));
		
		table.ajouterCouple("tatata", "tototo");
		assertEquals(table.recuperer("tatata"), "tototo");
		
		table.ajouterCouple("tititi", "tututu");
		assertNull(table.recuperer("tititi"));
	}
	
	@Test
	public void testTraduire1(){
		String [] t1= {"je","chercher"};
		String [] t1Attendu= {"i","look for"};
		
		String [] t1Traduit= Main.traduire(t1);
		assertArrayEquals(t1Attendu, t1Traduit);
	}

	//Test du QuickSort

	@Test
	public void testEchangerAssociation1() {
		String[] clefs = new String[] {"a", "b", "c"};
		String[] valeurs = new String[] { "a", "b", "c"};

		String[] clefsAttendu = new String[] {"c", "b", "a"};
		String[] valeursAttendu = new String[] { "c", "b", "a"};

		QuickSort.echanger_association(clefs, valeurs, 0, 2);
		assertArrayEquals(clefs, clefsAttendu);
		assertArrayEquals(valeurs, valeursAttendu);
	}

	@Test
	public void testEchangerAssociation2() {
		String[] clefs = new String[] {"a", "a", "a"};
		String[] valeurs = new String[] { "b", "b", "b"};

		String[] clefsAttendu = new String[] {"a", "a", "a"};
		String[] valeursAttendu = new String[] { "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 3, 3);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 3, 3);

		QuickSort.echanger_association(table.clefs, table.valeurs, 0, 2);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testEchangerAssociation3() {
		String[] clefs = new String[] {"a", "a", "a", "b"};
		String[] valeurs = new String[] { "b", "b", "b", "c"};

		String[] clefsAttendu = new String[] {"a", "a", "a", "b"};
		String[] valeursAttendu = new String[] { "b", "b", "b", "c"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.echanger_association(table.clefs, table.valeurs, 1, 1);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}


	@Test
	public void testPartitionAssociation1() {
		String[] clefs = new String[] {"a", "b", "c", "d"};
		String[] valeurs = new String[] { "a", "b", "c", "d"};

		String[] clefsAttendu = new String[] {"a", "b", "c", "d"};
		String[] valeursAttendu = new String[] {"a", "b", "c", "d"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.partition_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation2() {
		String[] clefs = new String[] {"b", "a", "c", "d"};
		String[] valeurs = new String[] { "b", "a", "c", "d"};

		String[] clefsAttendu = new String[] {"a", "b", "c", "d"};
		String[] valeursAttendu = new String[] {"a", "b", "c", "d"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.partition_association(table.clefs, table.valeurs, 0, 3);

		assertArrayEquals(tableAttendue.clefs, table.clefs);
		assertArrayEquals(tableAttendue.valeurs, table.valeurs);
	}

	@Test
	public void testPartitionAssociation3() {
		String[] clefs = new String[] {"a", "a", "a", "a"};
		String[] valeurs = new String[] { "a", "a", "a", "a"};

		String[] clefsAttendu = new String[] {"a", "a", "a", "a"};
		String[] valeursAttendu = new String[] {"a", "a", "a", "a"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.partition_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation4() {
		String[] clefs = new String[] {"b", "a", "b", "a"};
		String[] valeurs = new String[] { "b", "a", "b", "a"};

		String[] clefsAttendu = new String[] { "a", "a", "b", "b"};
		String[] valeursAttendu = new String[]{ "a", "a", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.partition_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation5() {
		String[] clefs = new String[] {"b"};
		String[] valeurs = new String[] {"b"};

		String[] clefsAttendu = new String[] { "b"};
		String[] valeursAttendu = new String[]{ "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 1, 1);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 1, 1);

		QuickSort.partition_association(table.clefs, table.valeurs, 0, 0);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation6() {
		String[] clefs = new String[] {"b", "a"};
		String[] valeurs = new String[] {"b", "a"};

		String[] clefsAttendu = new String[] {"a", "b"};
		String[] valeursAttendu = new String[]{"a", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 2, 2);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 2, 2);

		QuickSort.partition_association(table.clefs, table.valeurs, 0, 1);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort1() {
		String[] clefs = new String[] {"d", "e", "a", "b"};
		String[] valeurs = new String[] { "d", "e", "a", "b"};

		String[] clefsAttendu = new String[] { "a", "b", "d", "e"};
		String[] valeursAttendu = new String[]{ "a", "b", "d", "e"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.quicksort_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort2() {
		String[] clefs = new String[] {"a", "a", "a", "a"};
		String[] valeurs = new String[] { "b", "b", "b", "b"};

		String[] clefsAttendu = new String[] { "a", "a", "a", "a"};
		String[] valeursAttendu = new String[]{ "b", "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.quicksort_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort3() {
		String[] clefs = new String[] {"a", "a", "c", "b"};
		String[] valeurs = new String[] { "b", "b", "b", "b"};

		String[] clefsAttendu = new String[] { "a", "a", "b", "c"};
		String[] valeursAttendu = new String[]{ "b", "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.quicksort_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort4() {
		String[] clefs = new String[] {"b", "a", "c", "b"};
		String[] valeurs = new String[] { "b", "b", "b", "b"};

		String[] clefsAttendu = new String[] { "a", "b", "b", "c"};
		String[] valeursAttendu = new String[]{ "b", "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 4, 4);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 4, 4);

		QuickSort.quicksort_association(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort5() {
		String[] clefs = new String[] {"b", "c", "a", "g", "e", "f", "d"};
		String[] valeurs = new String[] { "b", "c", "a", "g", "e", "f", "d"};

		String[] clefsAttendu = new String[] { "a", "b", "c", "d", "e", "f", "g"};
		String[] valeursAttendu = new String[]{ "a", "b", "c", "d", "e", "f", "g"};


		TableAssociation table = new TableAssociation(clefs, valeurs, 7, 7);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu, 7, 7);

		QuickSort.quicksort_association(table.clefs, table.valeurs, 0, 6);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}
}
