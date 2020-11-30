/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;

import com.sun.org.apache.xml.internal.security.encryption.Serializer;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.util.Pair;

/**
 *
 * @author kimro
 */
public class Plateau {

    String[][] grille;
    Navire[] navires;

    public Plateau() {
        this.grille = new String[16][32];
        this.navires = new Navire[10];
    }

    public Plateau(Navire[] tabNav) {
        this.navires = tabNav;
    }

    public String[][] initGrille() {
        int n = 0;
        char c1 = 'a';
        char c2 = 'A';
        // remplissage de la colonne avec les lettres
        grille[0][0] = " ";
        for (int k = 1; k < 16; k++) {
            grille[k][0] = Character.toString(c1);
            c1++;
        }

        // remplissage de la colonne avec les lignes
        for (int col = 1; col < 32; col++) {
            if (col % 2 == 0) {
                if (n > 9) {
                    grille[0][col] = Character.toString(c2);
                    c2++;
                } else {
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

    public void initNavire() {
        Cuirasse cuirasse = new Cuirasse();
        Croiseur c1 = new Croiseur();
        Croiseur c2 = new Croiseur();
        Destroyer d1 = new Destroyer();
        Destroyer d2 = new Destroyer();
        Destroyer d3 = new Destroyer();
        SousMarin sm1 = new SousMarin();
        SousMarin sm2 = new SousMarin();
        SousMarin sm3 = new SousMarin();
        SousMarin sm4 = new SousMarin();
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
        for (int k = 0; k < 10; k++) {
            navires[k].sens = (int) (Math.random() * 2);
            aleatoire = 1 + (Math.random() * 15);
            ligne = (int) aleatoire;
            aleatoire = Math.random() * 31;
            colonne = (int) aleatoire;
            while (colonne == 0 || colonne % 2 != 0) {
                aleatoire = Math.random() * 31;
                colonne = (int) aleatoire;
            }
            if (navires[k].sens == 0) {
                // navire vertical
                while (checkPlacement(ligne, colonne, navires[k], navires[k].sens) == false) {
                    aleatoire = 1 + (Math.random() * 15);
                    ligne = (int) aleatoire;
                    aleatoire = Math.random() * 31;
                    colonne = (int) aleatoire;
                    while (colonne == 0 || colonne % 2 != 0) {
                        aleatoire = Math.random() * 31;
                        colonne = (int) aleatoire;
                    }
                }

                for (int i = ligne; i < ligne + navires[k].taille; i++) {
                    switch (navires[k].taille) {
                        case 1:
                            grille[i][colonne] = "s";
                            navires[k].tabPos.add(new Pair(i, colonne));
                            break;
                        case 3:
                            grille[i][colonne] = "d";
                            navires[k].tabPos.add(new Pair(i, colonne));
                            break;
                        case 5:
                            grille[i][colonne] = "c";
                            navires[k].tabPos.add(new Pair(i, colonne));
                            break;
                        case 7:
                            grille[i][colonne] = "C";
                            navires[k].tabPos.add(new Pair(i, colonne));
                            break;
                    }
                }
            } else {
                //navire horizontal

                while (checkPlacement(ligne, colonne, navires[k], navires[k].sens) == false) {
                    aleatoire = 1 + (Math.random() * 15);
                    ligne = (int) aleatoire;
                    aleatoire = Math.random() * 31;
                    colonne = (int) aleatoire;
                    while (colonne == 0 || colonne % 2 != 0) {
                        aleatoire = Math.random() * 31;
                        colonne = (int) aleatoire;
                    }
                }
                for (int j = colonne; j < (colonne + (navires[k].taille * 2)); j += 2) {
                    switch (navires[k].taille) {
                        case 1:
                            grille[ligne][j] = "s";
                            navires[k].tabPos.add(new Pair(ligne, j));
                            break;
                        case 3:
                            grille[ligne][j] = "d";
                            navires[k].tabPos.add(new Pair(ligne, j));
                            break;
                        case 5:
                            grille[ligne][j] = "c";
                            navires[k].tabPos.add(new Pair(ligne, j));
                            break;
                        case 7:
                            grille[ligne][j] = "C";
                            navires[k].tabPos.add(new Pair(ligne, j));
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

    public void lien(String[][] tab) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                if ("X".equals(tab[i][j])) {
                    grille[i][j] = "X";
                }
            }
        }
    }

    public boolean checkPlacement(int ligne, int colonne, Navire n, int sens) {
        if (n.taille == 1 && !" ".equals(grille[ligne][colonne])) {
            return false;
        }
        if (sens == 0) {
            for (int i = ligne; i < ligne + n.taille; i++) {
                if (!" ".equals(grille[i][colonne])) {
                    return false;
                }
                if (i == 15 && n.taille > 1 && i + 1 < ligne + n.taille) {
                    return false;
                }
            }
        } else {
            for (int j = colonne; j < (colonne + (n.taille * 2)); j += 2) {
                if (!" ".equals(grille[ligne][j])) {
                    return false;
                }
                if (j == 30 && n.taille > 1 && j + 2 < (colonne + (n.taille * 2))) {
                    return false;
                }
            }
        }
        return true;
    }

    public void verifierPartieFinie() {
        Boolean isFinie = true;
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 32; j++) {
                if (grille[i][j] == "d" || grille[i][j] == "c" || grille[i][j] == "C" || grille[i][j] == "s") {
                    isFinie = false;
                }
            }
        }

        if (isFinie == true) {
            System.out.println("La partie est terminée");
            return;
        }
    }

    public String savePartie(Plateau p) {
        File fichierSauvegarde = new File("partie.txt");

        try {
            FileWriter writer = new FileWriter(fichierSauvegarde);
            for (int i = 0; i < 10; i++) {
//                writer.write("Navire : " + p.navires[i].nom + "\n");
//                writer.write("Sens : " + p.navires[i].sens + "\n");
//                writer.write("Taille : " + p.navires[i].taille + "\n");
//                writer.write("Puissance : " + p.navires[i].puissance + "\n");
//
//                for (int j = 0; j < p.navires[i].taille; j++) {
//                    writer.write(p.navires[i].tabPos.get(j).getKey() + ":" + p.navires[i].tabPos.get(j).getValue() + " ");
//                }
//                writer.write("\n \n");

                writer.write(p.navires[i].nom + "\n");
                writer.write(p.navires[i].sens + "\n");
                writer.write(p.navires[i].taille + "\n");
                writer.write(p.navires[i].puissance + "\n");

                for (int j = 0; j < p.navires[i].taille; j++) {
                    writer.write(p.navires[i].tabPos.get(j).getKey() + " " + p.navires[i].tabPos.get(j).getValue() + " ");
                }
                writer.write("\n \n");

            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(fichierSauvegarde.getAbsolutePath());

        return fichierSauvegarde.getAbsolutePath();

    }

    //A TESTER
    public Plateau chargerPartie(String chemin, Plateau p) {
        int i = 1;
        String[] tab = new String[4];
        ArrayList<Pair> tabPos = new ArrayList<Pair>();
        Navire navire = null;
        ArrayList<Navire> listeNavires = new ArrayList<Navire>();
        Navire[] tabNavire = new Navire[10];

        Plateau plateau = null;

        try {
            File f = new File(chemin);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String ligne = null; // contiendra chaque ligne

            while ((ligne = br.readLine()) != null) {
                if (i % 5 == 1) {
                    tab[0] = ligne;
                    System.out.println(ligne);
                }
                if (i % 5 == 2) {
                    System.out.println(ligne);
                    tab[1] = ligne;
                }
                if (i % 5 == 3) {
                    System.out.println(ligne);
                    tab[2] = ligne;
                }
                if (i % 5 == 4) {
                    System.out.println(ligne);
                    tab[3] = ligne;

                }
                if (i % 5 == 0) {
                    System.out.println(ligne);

                    //recuperation des positions
                    String[] positions = ligne.split(" ");
                    for (int k = 0; k < positions.length - 1; k = k + 2) {
                        tabPos.add(new Pair(positions[k], positions[k + 1]));
                    }

                    //creation des objetcs navire
                    if (tab[0].equals("Croiseur")) {
                        navire = new Croiseur(tabPos);
                    } else if (tab[0].equals("Cuirasse")) {
                        navire = new Cuirasse(tabPos);
                    } else if (tab[0].equals("Destroyer")) {
                        navire = new Destroyer(tabPos);
                    } else if (tab[0].equals("Sous-Marin")) {
                        navire = new SousMarin(tabPos);
                    }
                    //ajout du navire dans l'arraylist
                    listeNavires.add(navire);
                }
                i++;
            }

            br.close();

            for (int k = 0; k < 10; k++) {
                tabNavire[k] = listeNavires.get(k);
            }
            plateau = new Plateau(tabNavire);

        } catch (IOException e) {
            System.out.println("Impossible de charger le plateau");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plateau;
    }
}
