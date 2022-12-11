package main;

import static org.junit.Assert.*;

import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


public class TraducteurTest {
	public static boolean estTrie(String[] tab) {
		for (int i = 1; i < tab.length; i++) {
			if(tab[i].compareTo(tab[i - 1]) < 0) return false;
		}
		return true;
	}
	
	@Test
	public void testEstTrie1() {
		String[] tab = {"a", "b", "c"};
		assertTrue(estTrie(tab));
	}

	@Test
	public void testEstTrie2() {
		String[] tab = {"b", "a", "c"};
		assertFalse(estTrie(tab));
	}

	@Test
	public void testEstTrie3() {
		String[] tab = {};
		assertTrue(estTrie(tab));
	}

	@Test
	public void testIndiceClef1() {
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "nuit";
		int indice = 1;
		assertEquals(table.indiceClef(clef), indice);
	}
	
	@Test
	public void testIndiceClef2() {
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "dsvssbsbsb";
		assertEquals(table.indiceClef(clef), -1);
	}

	@Test
	public void testIndiceClefRapide1() {
		String[] clefs = new String[] {"a"};
		String[] valeurs = new String[] {"a"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "a";
		int indice = 0;
		assertEquals(table.indiceClefRapide(clef), indice);
	}

	@Test
	public void testIndiceClefRapide2() {
		String[] clefs = new String[] {"a"};
		String[] valeurs = new String[] {"a"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "b";
		int indice = -1;
		assertEquals(table.indiceClefRapide(clef), indice);
	}

	@Test
	public void testIndiceClefRapide3() {
		String[] clefs = new String[] {"a", "b"};
		String[] valeurs = new String[] {"a", "b"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "a";
		assertEquals(table.indiceClef(clef), 0);
	}

	@Test
	public void testIndiceClefRapide4() {
		String[] clefs = new String[] {"a", "b"};
		String[] valeurs = new String[] {"a", "b"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "b";
		assertEquals(table.indiceClef(clef), 1);
	}

	@Test
	public void testIndiceClefRapide5() {
		String[] clefs = new String[] {"a", "b"};
		String[] valeurs = new String[] {"a", "b"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "c";
		assertEquals(table.indiceClef(clef), -1);
	}

	@Test
	public void testIndiceClefRapide6() {
		String[] clefs = new String[] {"a", "b", "c", "d", "e"};
		String[] valeurs = new String[] {"a", "b", "c", "d", "e"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "d";
		assertEquals(table.indiceClef(clef), 3);
	}

	@Test
	public void testIndiceClefRapide7() {
		String[] clefs = new String[] {"a", "b", "c", "d", "e"};
		String[] valeurs = new String[] {"a", "b", "c", "d", "e"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "e";
		assertEquals(table.indiceClef(clef), 4);
	}

	@Test
	public void testIndiceClefRapide8() {
		String[] clefs = new String[] {"a", "b", "c", "d", "e"};
		String[] valeurs = new String[] {"a", "b", "c", "d", "e"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "a";
		assertEquals(table.indiceClef(clef), 0);
	}
	
	@Test
	public void testRecuperer1(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "arbre";
		String valeur = "tree";
		assertEquals(table.recuperer(clef).toLowerCase(), valeur);
	}
	
	@Test
	public void testRecuperer2(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "nuit";
		String valeur = "night";
		assertEquals(table.recuperer(clef).toLowerCase(), valeur);
	}
	
	@Test
	public void testRecuperer3(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs);
		
		String clef = "dpjdsklns";
		assertNull(table.recuperer(clef));
	}

	@Test
	public void testIndicePlusProche1() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		assertEquals(0, table.indicePlusProche("bonjour"));
	}

	@Test
	public void testIndicePlusProche2() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit", "soleil", "zombie"};
		String[] valeurs = new String[] {"tree", "yellow", "night", "sun", "zombie"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		assertEquals(3, table.indicePlusProche("somnenbulle"));
	}


	@Test
	public void testIndicePlusProche3() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		assertEquals(0, table.indicePlusProche("jaune"));
	}

	@Test
	public void testIndicePlusProche4() {
		String[] clefs = new String[] {};
		String[] valeurs = new String[] {};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		assertEquals(-1, table.indicePlusProche("jaune"));
	}

	@Test
	public void testIndicePlusProche5() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		assertEquals(2, table.indicePlusProche("zombie"));
	}

	@Test
	public void testIndicePlusProche6() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		assertEquals(-1, table.indicePlusProche("aaaaa"));
	}

	@Test
	public void testerCompleterTabParTab1() {
		String[] aCompleter = {};
		String[] complement = {};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {}, aCompleter);
		assertArrayEquals(new String[] {}, complement);
	}

	@Test
	public void testerCompleterTabParTab2() {
		String[] aCompleter = {null};
		String[] complement = {};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {null}, aCompleter);
		assertArrayEquals(new String[] {}, complement);
	}
	
	@Test
	public void testerCompleterTabParTab3() {
		String[] aCompleter = {null};
		String[] complement = {"je"};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {"je"}, aCompleter);
		assertArrayEquals(new String[] {"je"}, complement);
	}

	@Test
	public void testerCompleterTabParTab4() {
		String[] aCompleter = {null, null};
		String[] complement = {"suis", "plus"};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {"suis", "plus"}, aCompleter);
		assertArrayEquals(new String[] {"suis", "plus"}, complement);
	}

	@Test
	public void testerCompleterTabParTab5() {
		String[] aCompleter = {null, "qu'une"};
		String[] complement = {"moitié"};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {"moitié", "qu'une"}, aCompleter);
		assertArrayEquals(new String[] {"moitié"}, complement);
	}

	@Test
	public void testerCompleterTabParTab6() {
		String[] aCompleter = {"je", null};
		String[] complement = {"suis"};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {"je", "suis"}, aCompleter);
		assertArrayEquals(new String[] {"suis"}, complement);
	}

	@Test
	public void testerCompleterTabParTab7() {
		String[] aCompleter = {null, "plus", null};
		String[] complement = {"que", "moi"};
		TableAssociation.completerTabParTab(aCompleter, complement);

		assertArrayEquals(new String[] {"que", "plus", "moi"}, aCompleter);
		assertArrayEquals(new String[] {"que", "moi"}, complement);
	}

	@Test
	public void testAjouterUnCouple1() {
		String[] clefs = new String[] {};
		String[] valeurs = new String[] {};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		table.ajouterUnCouple("bonjour", "good morning");
		assertArrayEquals(new String[] {"bonjour"}, table.clefs);
		assertArrayEquals(new String[] {"good morning"}, table.valeurs);
	}
	
	@Test
	public void testAjouterUnCouple2() {
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		table.ajouterUnCouple("bonjour", "good morning");
		assertArrayEquals(new String[] {"arbre", "bonjour", "nuit", "jaune"}, table.clefs);
		assertArrayEquals(new String[] {"tree", "good morning", "night", "yellow"}, table.valeurs);
	}

	@Test
	public void testAjouterAssociations1() {
		String[] clefs = new String[] {};
		String[] valeurs = new String[] {};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		String[] clefsSup = new String[] {"abricot"};
		String[] valeursSup = new String[] {"abricot"};

		table.ajouterAssociations(clefsSup, valeursSup);

		assertArrayEquals(new String[] { "abricot"}, table.clefs);
		assertArrayEquals(new String[] {"abricot"}, table.clefs);
	} 

	@Test
	public void testAjouterAssociations2() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		String[] clefsSup = new String[] {"abricot"};
		String[] valeursSup = new String[] {"abricot"};

		table.ajouterAssociations(clefsSup, valeursSup);

		assertArrayEquals(new String[] {"abricot", "arbre" , "jaune", "nuit"}, table.clefs);
		assertArrayEquals(new String[] {"abricot", "tree", "yellow", "night"}, table.valeurs);
	} 

	@Test
	public void testAjouterAssociations3() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		String[] clefsSup = new String[] {};
		String[] valeursSup = new String[] {};

		table.ajouterAssociations(clefsSup, valeursSup);

		assertArrayEquals(new String[] {"arbre", "jaune", "nuit"}, table.clefs);
		assertArrayEquals(new String[] {"tree", "yellow", "night"}, table.valeurs);
	} 

	@Test
	public void testAjouterAssociations4() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		String[] clefsSup = new String[] {"zombie"};
		String[] valeursSup = new String[] {"zombie"};

		table.ajouterAssociations(clefsSup, valeursSup);

		assertArrayEquals(new String[] {"arbre", "jaune", "nuit", "zombie"}, table.clefs);
		assertArrayEquals(new String[] {"tree", "yellow", "night", "zombie"}, table.valeurs);
	}
	
	@Test
	public void testAjouterAssociations5() {
		String[] clefs = new String[] {"arbre", "jaune", "nuit"};
		String[] valeurs = new String[] {"tree", "yellow", "night"};
		TableAssociation table = new TableAssociation(clefs, valeurs);

		String[] clefsSup = new String[] {"lego"};
		String[] valeursSup = new String[] {"lego"};

		table.ajouterAssociations(clefsSup, valeursSup);

		assertArrayEquals(new String[] {"arbre", "jaune", "lego", "nuit"}, table.clefs);
		assertArrayEquals(new String[] {"tree", "yellow", "lego", "night"}, table.valeurs);
	} 
	

	
	@Test
	public void testTableDictionnaire() {
		TableAssociation table = TableAssociation.tableDictionnaireNiveau1();
		
		assertEquals(table.recuperer("bonjour"), "good morning");
		assertEquals(table.recuperer("manger"), "eat");
		assertEquals(table.recuperer("boire"), "drink");
		assertNull(table.recuperer("qgfdbdb"));
		
		table.ajouterUnCouple("tatata", "tototo");
		assertEquals(table.recuperer("tatata"), "tototo");
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

		QuickSort.echangerAssociation(clefs, valeurs, 0, 2);
		assertArrayEquals(clefs, clefsAttendu);
		assertArrayEquals(valeurs, valeursAttendu);
	}

	@Test
	public void testEchangerAssociation2() {
		String[] clefs = new String[] {"a", "a", "a"};
		String[] valeurs = new String[] { "b", "b", "b"};

		String[] clefsAttendu = new String[] {"a", "a", "a"};
		String[] valeursAttendu = new String[] { "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.echangerAssociation(table.clefs, table.valeurs, 0, 2);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testEchangerAssociation3() {
		String[] clefs = new String[] {"a", "a", "a", "b"};
		String[] valeurs = new String[] { "b", "b", "b", "c"};

		String[] clefsAttendu = new String[] {"a", "a", "a", "b"};
		String[] valeursAttendu = new String[] { "b", "b", "b", "c"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.echangerAssociation(table.clefs, table.valeurs, 1, 1);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}


	@Test
	public void testPartitionAssociation1() {
		String[] clefs = new String[] {"a", "b", "c", "d"};
		String[] valeurs = new String[] { "a", "b", "c", "d"};

		String[] clefsAttendu = new String[] {"a", "b", "c", "d"};
		String[] valeursAttendu = new String[] {"a", "b", "c", "d"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.partitionAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation2() {
		String[] clefs = new String[] {"b", "a", "c", "d"};
		String[] valeurs = new String[] { "b", "a", "c", "d"};

		String[] clefsAttendu = new String[] {"a", "b", "c", "d"};
		String[] valeursAttendu = new String[] {"a", "b", "c", "d"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.partitionAssociation(table.clefs, table.valeurs, 0, 3);

		assertArrayEquals(tableAttendue.clefs, table.clefs);
		assertArrayEquals(tableAttendue.valeurs, table.valeurs);
	}

	@Test
	public void testPartitionAssociation3() {
		String[] clefs = new String[] {"a", "a", "a", "a"};
		String[] valeurs = new String[] { "a", "a", "a", "a"};

		String[] clefsAttendu = new String[] {"a", "a", "a", "a"};
		String[] valeursAttendu = new String[] {"a", "a", "a", "a"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.partitionAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation4() {
		String[] clefs = new String[] {"b", "a", "b", "a"};
		String[] valeurs = new String[] { "b", "a", "b", "a"};

		String[] clefsAttendu = new String[] { "a", "a", "b", "b"};
		String[] valeursAttendu = new String[]{ "a", "a", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.partitionAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation5() {
		String[] clefs = new String[] {"b"};
		String[] valeurs = new String[] {"b"};

		String[] clefsAttendu = new String[] { "b"};
		String[] valeursAttendu = new String[]{ "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.partitionAssociation(table.clefs, table.valeurs, 0, 0);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testPartitionAssociation6() {
		String[] clefs = new String[] {"b", "a"};
		String[] valeurs = new String[] {"b", "a"};

		String[] clefsAttendu = new String[] {"a", "b"};
		String[] valeursAttendu = new String[]{"a", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.partitionAssociation(table.clefs, table.valeurs, 0, 1);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort1() {
		String[] clefs = new String[] {"d", "e", "a", "b"};
		String[] valeurs = new String[] { "d", "e", "a", "b"};

		String[] clefsAttendu = new String[] { "a", "b", "d", "e"};
		String[] valeursAttendu = new String[]{ "a", "b", "d", "e"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.quicksortAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort2() {
		String[] clefs = new String[] {"a", "a", "a", "a"};
		String[] valeurs = new String[] { "b", "b", "b", "b"};

		String[] clefsAttendu = new String[] { "a", "a", "a", "a"};
		String[] valeursAttendu = new String[]{ "b", "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.quicksortAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort3() {
		String[] clefs = new String[] {"a", "a", "c", "b"};
		String[] valeurs = new String[] { "b", "b", "b", "b"};

		String[] clefsAttendu = new String[] { "a", "a", "b", "c"};
		String[] valeursAttendu = new String[]{ "b", "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.quicksortAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort4() {
		String[] clefs = new String[] {"b", "a", "c", "b"};
		String[] valeurs = new String[] { "b", "b", "b", "b"};

		String[] clefsAttendu = new String[] { "a", "b", "b", "c"};
		String[] valeursAttendu = new String[]{ "b", "b", "b", "b"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.quicksortAssociation(table.clefs, table.valeurs, 0, 3);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}

	@Test
	public void testQuickSort5() {
		String[] clefs = new String[] {"b", "c", "a", "g", "e", "f", "d"};
		String[] valeurs = new String[] { "b", "c", "a", "g", "e", "f", "d"};

		String[] clefsAttendu = new String[] { "a", "b", "c", "d", "e", "f", "g"};
		String[] valeursAttendu = new String[]{ "a", "b", "c", "d", "e", "f", "g"};


		TableAssociation table = new TableAssociation(clefs, valeurs);
		TableAssociation tableAttendue = new TableAssociation(clefsAttendu, valeursAttendu);

		QuickSort.quicksortAssociation(table.clefs, table.valeurs, 0, 6);
		assertArrayEquals(table.clefs, tableAttendue.clefs);
		assertArrayEquals(table.valeurs, tableAttendue.valeurs);
	}
}
