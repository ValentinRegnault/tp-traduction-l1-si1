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
		TableAssociation table = new TableAssociation(clefs, valeurs, 3);
		
		String clef = "nuit";
		int indice = 1;
		assertEquals(table.indiceClef(clef), indice);
	}
	
	@Test
	public void testIndiceClef2() {
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3);
		
		String clef = "dsvssbsbsb";
		int indice = -1;
		assertEquals(table.indiceClef(clef), -1);
	}
	
	@Test
	public void testRecuperer1(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3);
		
		String clef = "arbre";
		String valeur = "tree";
		assertEquals(table.recuperer(clef).toLowerCase(), valeur);
	}
	
	@Test
	public void testRecuperer2(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3);
		
		String clef = "nuit";
		String valeur = "night";
		assertEquals(table.recuperer(clef).toLowerCase(), valeur);
	}
	
	@Test
	public void testRecuperer3(){
		String[] clefs = new String[] {"arbre", "nuit", "jaune"};
		String[] valeurs = new String[] {"tree", "night", "yellow"};
		TableAssociation table = new TableAssociation(clefs, valeurs, 3);
		
		String clef = "dpjdsklns";
		assertNull(table.recuperer(clef));
	}
	
	@Test
	public void ajouterClef1() {
		int maxAssoc = 1;
		String[] clefs = new String[maxAssoc];
		String[] valeurs = new String[maxAssoc] ;
		TableAssociation table = new TableAssociation(clefs, valeurs, maxAssoc);
		
		//On fait plusieurs test ici, car JUnit effectue les tests en parallèle
		//et on veut éviter les interférence entre ces tests.
		String clef = "eat";
		String valeur = "manger";
		table.ajouterCouple(clef, valeur);
		assertEquals(table.recuperer(clef), valeur);
		
		String clef2 = "boire";
		String valeur2 = "drink";
		table.ajouterCouple(clef2, valeur2); // le couple ne sera pas ajoutée car la 
		//table est pleine
		assertNull(table.recuperer(clef2));
		
		String clef3 = "eat";
		String valeur3 = "boire";
		table.ajouterCouple(clef2, valeur2); // le couple ne sera pas ajoutée car la clef 
		//est déjà associé à "good morning"
		//On s'attend a recuperer "good morning" pour la clef "bonjour"
		assertEquals(table.recuperer(clef3), "good morning");
	}
	
	@Test
	public void testTableDictionnaire() {
		TableAssociation table = TableAssociation.tableDictionnaire();
		
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
		String [] t1Attendu= {"I","look for"};
		
		String [] t1Traduit= Main.traduire(t1);
		assertArrayEquals(t1Attendu, t1Traduit);
	}
	
	// A compléter par VOS tests sur TOUTES les fonctions y compris les fonctions
	// intermédiaires!!

}
