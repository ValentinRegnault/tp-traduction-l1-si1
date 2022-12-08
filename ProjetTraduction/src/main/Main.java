package main;

import util.ACX;

public class Main {
	public static final TableAssociation table = new TableAssociation();

	public static String [] traduire(String [] texte){
		String[] traductions = new String[texte.length];
		for (int i = 0; i < texte.length; i++) {
			String trad = table.recuperer(texte[i]);
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
		
		ACX.interfaceTraduction("traduire");
	}
}