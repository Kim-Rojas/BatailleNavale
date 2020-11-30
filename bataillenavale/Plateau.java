/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;
import java.io.*;

/**
 *
 * @author kimro
 */
public class Plateau {
	String[][] grille = new String[16][32];
        Navire[] navires = new Navire[10];

	public Plateau() {
	}

	public String[][] initGrille() {
		int n = 0;
                char c1 = 'a';
                char c2 = 'A';
		// remplissage de la colonne avec les lettres
		grille[0][0] = " ";
		for (int k = 1; k < 16; k++){
                    grille[k][0] = Character.toString(c1);
                    c1++;
                }
                
		// remplissage de la colonne avec les lignes
		for (int col = 1; col < 32; col++) {
			if (col % 2 == 0) {
                                if (n > 9){
                                    grille[0][col] = Character.toString(c2);
                                    c2++;
                                }
                                else{
                                    grille[0][col] = String.valueOf(n);
                                    n++;
                                }
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
					grille[i][j] = " ";
				}
			}
		}
		return grille;
	}
        
        public void initNavire(){
            Cuirasse cuirasse = new Cuirasse(0,0);
            Croiseur c1 = new Croiseur(0,0);
            Croiseur c2 = new Croiseur(0,0);
            Destroyer d1 = new Destroyer(0,0);
            Destroyer d2 = new Destroyer(0,0);
            Destroyer d3 = new Destroyer(0,0);
            SousMarin sm1 = new SousMarin(0,0);
            SousMarin sm2 = new SousMarin(0,0);
            SousMarin sm3 = new SousMarin(0,0);
            SousMarin sm4 = new SousMarin(0,0);
            navires[0] = cuirasse;
            navires[1] = c1;
            navires[2] = c2;
            navires[3] = d1;
            navires[4] = d2;
            navires[5] = d3;
            navires[6] = sm1;
            navires[7] = sm2;
            navires[8] = sm3;
            navires[9] = sm4;
        }

	public void initPlacementNavires() {
		int ligne;
		int colonne;
		double aleatoire;
		
                // initialisation des navires
                initNavire();
                
                // placement des navires

                for (int k = 0; k < 10; k++){
                    navires[k].sens = (int)(Math.random() * 2);
                    aleatoire =  1+ (Math.random()*15);
                    ligne = (int) aleatoire;
                    aleatoire =  Math.random() * 31;
                    colonne = (int) aleatoire;
                    while (colonne == 0 || colonne % 2 != 0){
                        aleatoire =  Math.random() * 31;
                        colonne = (int) aleatoire;
                    }
                    if (navires[k].sens == 0){
                        // navire vertical
                        while (checkPlacement(ligne, colonne, navires[k], navires[k].sens) == false){
                            aleatoire =  1+ (Math.random()*15);
                            ligne = (int) aleatoire;
                            aleatoire =  Math.random() * 31;
                            colonne = (int) aleatoire;
                            while (colonne == 0 || colonne % 2 != 0){
                                aleatoire =  Math.random() * 31;
                                colonne = (int) aleatoire;
                            }
                        }
                        
                        for (int i = ligne; i < ligne+navires[k].taille; i++){
                            switch (navires[k].taille) {
                                case 1:
                                    grille[i][colonne] = "s";
                                    break;
                                case 3:
                                    grille[i][colonne] = "d";
                                    break;
                                case 5:
                                    grille[i][colonne] = "c";
                                    break;
                                case 7:
                                    grille[i][colonne] = "C";
                                    break;
                            }
                        }
                    }
                    else{
                        //navire horizontal

                            while (checkPlacement(ligne, colonne, navires[k], navires[k].sens) == false){
                                aleatoire =  1+ (Math.random()*15);
                                ligne = (int) aleatoire;
                                aleatoire =  Math.random() * 31;
                                colonne = (int) aleatoire;
                                while (colonne == 0 || colonne % 2 != 0){
                                    aleatoire =  Math.random() * 31;
                                    colonne = (int) aleatoire;
                                }
                            }
                            for (int j = colonne; j < (colonne + (navires[k].taille*2)); j+=2){
                                switch (navires[k].taille) {
                                case 1:
                                    grille[ligne][j] = "s";
                                    break;
                                case 3:
                                    grille[ligne][j] = "d";
                                    break;
                                case 5:
                                    grille[ligne][j] = "c";
                                    break;
                                case 7:
                                    grille[ligne][j] = "C";
                                    break;
                                }
                            }
                        }
                }
	}

	public void afficherPlateau() {
		// affichage dans la console du tableau
                
		for (int i = 0; i < 16; i++) {
			for (int j = 0; j < 32; j++) {
				System.out.print(grille[i][j]);
			}
			System.out.println("");
		}
	}
        
public void lien(String[][] tab){
    for (int i = 0; i < 16; i++) {
        for (int j = 0; j < 32; j++) {
            if ("X".equals(tab[i][j]))
                grille[i][j] = "X";
        }
    }
}
        
        public boolean checkPlacement(int ligne, int colonne, Navire n, int sens){
            if (n.taille == 1 && !" ".equals(grille[ligne][colonne]))
                return false;
            if (sens == 0){
                for (int i = ligne; i < ligne+n.taille; i++){
                    if (!" ".equals(grille[i][colonne]))
                        return false;
                    if (i == 15 && n.taille > 1 && i + 1 < ligne+n.taille)
                        return false;
                }
            }
            else{
                for (int j = colonne; j < (colonne + (n.taille*2)); j+=2){
                    if (!" ".equals(grille[ligne][j]))
                        return false;
                    if (j == 30 &&  n.taille > 1 && j + 2 < (colonne + (n.taille*2)))
                        return false;
                }
            }
            return true;
        }
}