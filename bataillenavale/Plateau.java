/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;

/**
 *
 * @author kimro
 */
public class Plateau {
	private int dimensionX;
	private int dimensionY;
	String[][] grille = new String[16][32];

	public Plateau() {
		this.dimensionX = 16;
		this.dimensionY = 32;
	}

	public String[][] initGrille() {
		int n = 0;
		// remplissage de la colonne avec les lettres
		grille[0][0] = " ";
		grille[1][0] = "a";
		grille[2][0] = "b";
		grille[3][0] = "c";
		grille[4][0] = "d";
		grille[5][0] = "e";
		grille[6][0] = "f";
		grille[7][0] = "g";
		grille[8][0] = "h";
		grille[9][0] = "i";
		grille[10][0] = "j";
		grille[11][0] = "k";
		grille[12][0] = "l";
		grille[13][0] = "m";
		grille[14][0] = "n";
		grille[15][0] = "o";

		// remplissage de la colonne avec les lignes
		for (int col = 1; col < 32; col++) {
			if (col % 2 == 0) {
				grille[0][col] = String.valueOf(n);
				n++;
			} else {
				grille[0][col] = "|";
			}
		}

		// remplissage des autres cases
		for (int j = 1; j < 32; j++) {
			if (j % 2 == 1) {
				for (int i = 1; i < 16; i++) {
					grille[i][j] = "|";
				}
			} else {
				for (int i = 1; i < 16; i++) {
					grille[i][j] = "x";
				}
			}
		}

		return grille;
	}

	public void initPlacementNavires() {
		int ligne;
		int colonne;
		double aleatoire;

		// placement du cuirasse
		aleatoire =  1+ (Math.random()*13);
        ligne = (int) aleatoire;
        aleatoire =  Math.random() * 13;
        colonne = (int) aleatoire;
        
        //NE MARCHE PAS
		System.out.println("Ligne : " + ligne);
		System.out.println("Colonne : " + colonne);
		for (int i = colonne; i < 14; i++) {
			if(i%2==0) {
				grille[ligne][i + 2] = "c";
			} else {
				continue;
			}
		}

	}

	public void afficherPlateau() {
		// affichage dans la console du tableau
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 32; j++) {
				System.out.print(grille[i][j]);
			}
			System.out.println(" ");
		}
	}
}
