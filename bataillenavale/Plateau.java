package bataillenavale;

import com.sun.org.apache.xml.internal.serializer.Serializer;
import java.io.*;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.util.Pair;


/**
*
* @author Louis DUTTIER, Benjamin ROBSON, Kim ROJAS
* 
*/

public class Plateau {
    String[][] grille;
    ArrayList<Navire> navires;

    public Plateau() {
        this.grille = new String[16][32];
        this.navires = new ArrayList();
    }

    public Plateau(ArrayList<Navire> tabNav) {
        this.grille = new String[16][32];
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
        navires.add(new Cuirasse("C"));
        for (int i=1; i<3; i++)
            navires.add(new Croiseur("c"+i));
        for (int j=1; j<4;  j++)
            navires.add(new Destroyer("d"+j));
        for (int k=1; k<5; k++)
            navires.add(new SousMarin("s"+k));
    }

    public void initPlacementNavires() {
        int ligne;
        int colonne;
        double aleatoire;

        // initialisation des navires
        initNavire();

        // placement des navires
        for (int k = 0; k < 10; k++) {
            navires.get(k).sens = (int) (Math.random() * 2);
            aleatoire = 1 + (Math.random() * 15);
            ligne = (int) aleatoire;
            aleatoire = Math.random() * 31;
            colonne = (int) aleatoire;
            while (colonne == 0 || colonne % 2 != 0) {
                aleatoire = Math.random() * 31;
                colonne = (int) aleatoire;
            }
            if (navires.get(k).sens == 0) {
                // navire vertical
                while (checkPlacement(ligne, colonne, navires.get(k), navires.get(k).sens) == false) {
                    aleatoire = 1 + (Math.random() * 15);
                    ligne = (int) aleatoire;
                    aleatoire = Math.random() * 31;
                    colonne = (int) aleatoire;
                    while (colonne == 0 || colonne % 2 != 0) {
                        aleatoire = Math.random() * 31;
                        colonne = (int) aleatoire;
                    }
                }

                for (int i = ligne; i < ligne + navires.get(k).taille; i++) {
                    switch (navires.get(k).taille) {
                        case 1:
                            grille[i][colonne] = "s";
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                        case 3:
                            grille[i][colonne] = "d";
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                        case 5:
                            grille[i][colonne] = "c";
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                        case 7:
                            grille[i][colonne] = "C";
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                    }
                }
            } else {
                //navire horizontal

                while (checkPlacement(ligne, colonne, navires.get(k), navires.get(k).sens) == false) {
                    aleatoire = 1 + (Math.random() * 15);
                    ligne = (int) aleatoire;
                    aleatoire = Math.random() * 31;
                    colonne = (int) aleatoire;
                    while (colonne == 0 || colonne % 2 != 0) {
                        aleatoire = Math.random() * 31;
                        colonne = (int) aleatoire;
                    }
                }
                for (int j = colonne; j < (colonne + (navires.get(k).taille * 2)); j += 2) {
                    switch (navires.get(k).taille) {
                        case 1:
                            grille[ligne][j] = "s";
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                        case 3:
                            grille[ligne][j] = "d";
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                        case 5:
                            grille[ligne][j] = "c";
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                        case 7:
                            grille[ligne][j] = "C";
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                    }
                }
            }
        }
    }

    public void placementNaviresCharges(){
        int k=0;
        for(int i=0; i<this.navires.size(); i++){
            //si le navire est place verticalement
            if(this.navires.get(i).sens == 0){
                int ligne = Integer.parseInt((String) this.navires.get(i).tabPos.get(0).getKey());
                int colonne = Integer.parseInt((String)this.navires.get(i).tabPos.get(0).getValue());
                for (int j = ligne; j < ligne + navires.get(i).taille; j++) {                    
                    switch (navires.get(i).taille) {
                        case 1:
                            grille[j][colonne] = "s";
                            break;
                        case 3:
                            grille[j][colonne] = "d";
                            break;
                        case 5:
                            grille[j][colonne] = "c";
                            break;
                        case 7:
                            grille[j][colonne] = "C";
                            break;
                    }
                }
                
            } 
            // si le navire est plpace horizontalement
            else {
                int col = Integer.parseInt((String)this.navires.get(i).tabPos.get(0).getValue());
                int ligne = Integer.parseInt((String)this.navires.get(i).tabPos.get(0).getKey());
                for (int j = col; j < col + navires.get(i).taille*2; j=j+2) {
                    
                    switch (navires.get(i).taille) {
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
            k++;
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
                if ("X".equals(tab[i][j]))
                    grille[i][j] = "X";
                if ("o".equals(tab[i][j]))
                    grille[i][j] = "o";
                if ("n".equals(tab[i][j]))
                    grille[i][j] = "n";
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

    public boolean verifierPartieFinie() {
        Boolean isFinie = true;
        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 32; j++) {
                if (grille[i][j] == "d" || grille[i][j] == "c" || grille[i][j] == "C" || grille[i][j] == "s") 
                    isFinie = false;
            }
        }

        if (isFinie == true) 
            System.out.println("La partie est terminée");
        return isFinie;
    }

    public static String savePartie(Plateau p, String nomFichier) {
        
        File fichierSauvegarde = new File(nomFichier+".txt");

        try {
            FileWriter writer = new FileWriter(fichierSauvegarde);
            for (int i = 0; i < 10; i++) {

                writer.write(p.navires.get(i).nom + "\n");
                writer.write(p.navires.get(i).sens + "\n");
                writer.write(p.navires.get(i).taille + "\n");
                writer.write(p.navires.get(i).puissance + "\n");

                for (int j = 0; j < p.navires.get(i).taille; j++) {
                    writer.write(p.navires.get(i).tabPos.get(j).getKey() + " " + p.navires.get(i).tabPos.get(j).getValue() + " ");
                }
                writer.write("\n");

            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(fichierSauvegarde.getAbsolutePath());

        return fichierSauvegarde.getAbsolutePath();

    }
   
    public static Plateau chargerPartie(String chemin) {
        int i = 1;
        String[] tab = new String[4];
        ArrayList<Pair> tabPos;
        Navire navire = null;
        ArrayList<Navire> listeNavires = new ArrayList<Navire>();
        Navire[] tabNavire = new Navire[10];

        Plateau plateau = null;

        try {
            File f = new File(chemin);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String ligne = null; // contiendra chaque ligne
            //int h = 0;

            while ((ligne = br.readLine()) != null) {
                if (i % 5 == 1) {
                    tab[0] = ligne;
                    //System.out.println(ligne);
                }
                else if (i % 5 == 2) {
                    //System.out.println(ligne);
                    tab[1] = ligne;
                }
                else if (i % 5 == 3) {
                    //System.out.println(ligne);
                    tab[2] = ligne;
                }
                else if (i % 5 == 4) {
                    //System.out.println(ligne);
                    tab[3] = ligne;

                }
                else if (i % 5 == 0) {
                    //System.out.println(ligne);

                    //recuperation des positions
                    tabPos = new ArrayList<Pair>();
                    String[] positions = ligne.split(" ");
                    for (int k = 0; k < positions.length - 1; k = k + 2) {
                        tabPos.add(new Pair(positions[k], positions[k + 1]));
                    }

                    //creation des objetcs navire
                    if (tab[0].equals("Croiseur")) {
                        navire = new Croiseur(tabPos, Integer.parseInt(tab[1]));
                    } else if (tab[0].equals("Cuirasse")) {
                        navire = new Cuirasse(tabPos, Integer.parseInt(tab[1]));
                    } else if (tab[0].equals("Destroyer")) {
                        navire = new Destroyer(tabPos, Integer.parseInt(tab[1]));
                    } else if (tab[0].equals("Sous-Marin")) {
                        navire = new SousMarin(tabPos, Integer.parseInt(tab[1]));
                    }
                    //ajout du navire dans l'arraylist
                    listeNavires.add(navire);
                    
                }
                //tabPos.clear();
                i++;
            }

            br.close();

            for (int k = 0; k < 10; k++) {
                tabNavire[k] = listeNavires.get(k);
            }
            plateau = new Plateau(listeNavires);

        } catch (IOException e) {
            System.out.println("Impossible de charger le plateau");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plateau;
    }
}