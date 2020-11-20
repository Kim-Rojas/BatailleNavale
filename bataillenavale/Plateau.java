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
        Navire[] navires = new Navire[10];

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
					grille[i][j] = " ";
				}
			}
		}
		return grille;
	}
        
        public void initNavire()
        {
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
		double alea1;
                int sens;
		
                // initialisation des navires
                initNavire();
                
                // placement des navires

                for (int k = 0; k < 10; k++)
                {
                    sens = (int)(Math.random() * 2);
                    alea1 =  1+ (Math.random()*13);
                    ligne = (int) alea1;
                    alea1 =  Math.random() * 13;
                    colonne = (int) alea1;
                    while (colonne == 0 || colonne % 2 != 0)
                    {
                        alea1 =  Math.random() * 13;
                        colonne = (int) alea1;
                    }
                    if (sens == 0)
                    {
                        // navire vertical
                        while (checkPlacement(ligne, colonne, navires[k], sens) == false)
                        {
                            alea1 =  1+ (Math.random()*13);
                            ligne = (int) alea1;
                            alea1 =  Math.random() * 13;
                            colonne = (int) alea1;
                            while (colonne == 0 || colonne % 2 != 0)
                            {
                                alea1 =  Math.random() * 13;
                                colonne = (int) alea1;
                            }
                        }
                        System.out.println("colonne : " + colonne);
                        for (int i = ligne; i < ligne+navires[k].taille; i++)
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
                    else
                    {
                        //navire horizontal
                        
                    }
                }
                
		//System.out.println("Ligne : " + ligne);
		//System.out.println("Colonne : " + colonne);
                
		/*for (int i = colonne; i < 30; i++) {
			if(i%2==0) {
				grille[ligne][i + 2] = "c";
			} 
		}*/

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
        
        public boolean checkPlacement(int ligne, int colonne, Navire n, int sens)
        {
            if (sens == 0)
            {
                for (int i = ligne; i < ligne+n.taille; i++)
                {
                    if ("c".equals(grille[i][colonne]))
                        return false;
                    if (i == 15 && n.taille > 1 && i + 1 < ligne+n.taille)
                        return false;
                }
            }
            else
            {
                for (int j = colonne; j < (colonne + n.taille)*2; j+=2)
                {
                    if ("c".equals(grille[ligne][j]))
                        return false;
                    if (j == 30 &&  n.taille > 1 && j + 2 < (colonne + n.taille)*2)
                        return false;
                }
            }
            return true;
        }
}