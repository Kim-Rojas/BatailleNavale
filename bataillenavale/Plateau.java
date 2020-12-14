package bataillenavale;

import com.sun.org.apache.xml.internal.serializer.Serializer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

import javafx.util.Pair;

/**
 * Classe Plateau, initialise un plateau de jeu
 *
 * @author Louis DUTTIER, Benjamin ROBSON, Kim ROJAS
 *
 */
public class Plateau {

    String[][] grille;
    ArrayList<Navire> navires;

    /**
     * Constructeur sans paramètres initialisant un objet de type Plateau
     */
    public Plateau() {
        this.grille = new String[16][32];
        this.navires = new ArrayList();
    }

    /**
     * Constructeur avec paramètre initialisant un objet de type Plateau
     *
     * @param tabNav une arraylist contenant les navires
     */
    public Plateau(ArrayList<Navire> tabNav) {
        this.grille = new String[16][32];
        this.navires = tabNav;
    }

    /**
     * Initialise la grille du plateau de jeu Complète la première ligne et la
     * première colonne avec des coordonnées, ainsi que le lignes de séparation
     * des colonnes
     *
     * @return la grille du jeu
     *
     */
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

    /**
     * Complète la liste de navires
     */
    public void initNavire() {
        navires.add(new Cuirasse());
        for (int i = 1; i < 3; i++) {
            navires.add(new Croiseur());
        }
        for (int j = 1; j < 4; j++) {
            navires.add(new Destroyer());
        }
        for (int k = 1; k < 5; k++) {
            navires.add(new SousMarin());
        }
    }

    /**
     * Place aléatoirement les 10 navires du jeu
     */
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
                            navires.get(k).coordonnes = new Pair(i, colonne);
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                        case 3:
                            grille[i][colonne] = "d";
                            navires.get(k).coordonnes = new Pair(i, colonne);
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                        case 5:
                            grille[i][colonne] = "c";
                            navires.get(k).coordonnes = new Pair(i, colonne);
                            navires.get(k).tabPos.add(new Pair(i, colonne));
                            break;
                        case 7:
                            grille[i][colonne] = "C";
                            navires.get(k).coordonnes = new Pair(i, colonne);
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
                            navires.get(k).coordonnes = new Pair(ligne, j);
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                        case 3:
                            grille[ligne][j] = "d";
                            navires.get(k).coordonnes = new Pair(ligne, j);
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                        case 5:
                            grille[ligne][j] = "c";
                            navires.get(k).coordonnes = new Pair(ligne, j);
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                        case 7:
                            grille[ligne][j] = "C";
                            navires.get(k).coordonnes = new Pair(ligne, j);
                            navires.get(k).tabPos.add(new Pair(ligne, j));
                            break;
                    }
                }
            }
        }
    }

    /**
     * Vérifie si un navire est coulé
     */
    public void checkNavireCoule() { // pas encore complètement opérationnelles
        int tmpx = 0;
        int tmpy = 0;
        int cpt = 0;
        for (int i = 0; i < navires.size(); i++) {
            tmpx = Integer.parseInt(navires.get(i).tabPos.get(navires.get(i).taille - 1).getKey().toString());
            tmpy = Integer.parseInt(navires.get(i).tabPos.get(navires.get(i).taille - 1).getValue().toString());
            cpt = 0;
            if (navires.get(i).sens == 0) {
                switch (navires.get(i).taille) {
                    case 1:
                        if (grille[tmpx][tmpy].equals("X") && navires.get(i).indice != 1) {
                            System.out.println("Sous-marin coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                    case 3:
                        for (int j = tmpx; j > tmpx - navires.get(i).taille; j--) {
                            if (grille[j][tmpy].equals("X")) {
                                cpt++;
                            }
                        }
                        if (cpt == navires.get(i).taille && navires.get(i).indice != 1) {
                            System.out.println("Destroyer coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                    case 5:
                        for (int j = tmpx; j > tmpx - navires.get(i).taille; j--) {
                            if (grille[j][tmpy].equals("X")) {
                                cpt++;
                            }
                        }
                        if (cpt == navires.get(i).taille && navires.get(i).indice != 1) {
                            System.out.println("Croiseur coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                    case 7:
                        for (int j = tmpx; j > tmpx - navires.get(i).taille; j--) {
                            if (grille[j][tmpy].equals("X")) {
                                cpt++;
                            }
                        }
                        if (cpt == navires.get(i).taille && navires.get(i).indice != 1) {
                            System.out.println("Cuirasse coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                }
            } else {
                switch (navires.get(i).taille) {
                    case 1:
                        if (grille[tmpx][tmpy].equals("X") && navires.get(i).indice != 1) {
                            System.out.println("Sous-marin coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                    case 3:
                        for (int j = tmpy; j > tmpy - (navires.get(i).taille * 2); j -= 2) {
                            if (grille[tmpx][j].equals("X")) {
                                cpt++;
                            }
                        }
                        if (cpt == navires.get(i).taille && navires.get(i).indice != 1) {
                            System.out.println("Destroyer coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                    case 5:
                        for (int j = tmpy; j > tmpy - (navires.get(i).taille * 2); j -= 2) {
                            if (grille[tmpx][j].equals("X")) {
                                cpt++;
                            }
                        }
                        if (cpt == navires.get(i).taille && navires.get(i).indice != 1) {
                            System.out.println("Croiseur coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                    case 7:
                        for (int j = tmpy; j > tmpy - (navires.get(i).taille * 2); j -= 2) {
                            if (grille[tmpx][j].equals("X")) {
                                cpt++;
                            }
                        }
                        if (cpt == navires.get(i).taille && navires.get(i).indice != 1) {
                            System.out.println("Cuirasse coulé !\n");
                            navires.get(i).indice = 1;
                        }
                        break;
                }
            }
        }
        for (int i = 0; i < navires.size(); i++) {
            if (navires.get(i).indice == 1) {
                navires.remove(i);
            }
        }
    }

    /**
     * permet de replacer les navires dans la grille en fonction de leurs
     * positions se trouvant dans l'attribut tabPos du navire sur lequel on
     * appelle cette méthode
     */
    public void placementNaviresCharges() {
        int k = 0;
        for (int i = 0; i < this.navires.size(); i++) {
            //si le navire est place verticalement
            if (this.navires.get(i).sens == 0) {
                int ligne = Integer.parseInt((String) this.navires.get(i).tabPos.get(0).getKey());
                int colonne = Integer.parseInt((String) this.navires.get(i).tabPos.get(0).getValue());
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

            } // si le navire est place horizontalement
            else {
                int col = Integer.parseInt((String) this.navires.get(i).tabPos.get(0).getValue());
                int ligne = Integer.parseInt((String) this.navires.get(i).tabPos.get(0).getKey());
                for (int j = col; j < col + navires.get(i).taille * 2; j = j + 2) {

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

    /**
     * Affiche le plateau de jeu
     */
    public void afficherPlateau() {
        // affichage dans la console du tableau

        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                System.out.print(grille[i][j]);
            }
            System.out.println("");
        }
    }

    /**
     *
     * @param tab
     */
    public void lien(String[][] tab) {
        for (int i = 0; i < 16; i++) {
            for (int j = 0; j < 32; j++) {
                if ("X".equals(tab[i][j])) {
                    grille[i][j] = "X";
                }
                if ("o".equals(tab[i][j])) {
                    grille[i][j] = "o";
                }
                if ("n".equals(tab[i][j])) {
                    grille[i][j] = "n";
                }
            }
        }
    }

    /**
     * Vérifie si le placement du navire est possible Vérifie que le navire ne
     * dépasse pas du plateau
     *
     * @param ligne
     * @param colonne
     * @param n
     * @param sens
     * @return true si le placement est correct
     */
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

    /**
     * parcours la grille de jeu pour vérifier s'il reste des navires non
     * touchés
     *
     * @return isFinie : true lorque tous les navires d'un plateau sont coulés
     * ou qu'il n'y a plus de sous-marins false sinon
     */
    public boolean verifierPartieFinie() {
        Boolean isFinie = true;
        int cptS = 0;

        for (int i = 1; i < 16; i++) {
            for (int j = 1; j < 32; j++) {
                if (grille[i][j] == "d" || grille[i][j] == "c" || grille[i][j] == "C" || grille[i][j] == "s") {
                    isFinie = false;
                    cptS++;
                }
            }
        }

        if (isFinie == true) {
            System.out.println("La partie est terminée");
        }

        return isFinie;
    }

    /**
     * sauvegarde une partie en cours
     *
     * @param p le plateau à sauvegarder
     * @param nomFichier le nom du fichier de destination
     * @return le chemin du fichier texte correspondant à la partie sauvegardée
     */
    public static String savePartie(Plateau p, String nomFichier) {

        File fichierSauvegarde = new File(nomFichier + ".txt");

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

    /**
     * sauvegarde le plateau d'aperçu des navires touchés chez l'adversaire
     *
     * @param p le plateau d'aperçu
     * @param nomFichier le nom du fichier de destination
     * @return le chemin du fichier texte contenant les coordonnées des points
     * touchés des navires
     */
    public static String saveApercu(Plateau p, String nomFichier) {
        File fichierSauvegarde = new File(nomFichier + ".txt");

        try {
            FileWriter writer = new FileWriter(fichierSauvegarde);
            for (int i = 0; i < 16; i++) {
                for (int j = 0; j < 32; j++) {
                    if (p.grille[i][j].equals("X")) {
                        writer.write(i + "\n");
                        writer.write(j + "\n");
                    }
                }

            }
            writer.close();
        } catch (IOException ex) {
            Logger.getLogger(Serializer.class.getName()).log(Level.SEVERE, null, ex);
        }

        System.out.println(fichierSauvegarde.getAbsolutePath());

        return fichierSauvegarde.getAbsolutePath();
    }

    /**
     * charge le plateau de jeu à partir des informations stockées dans le
     * fichier texte
     *
     * @param chemin du fichier texte contenant les informations sur le plateau
     *
     * @return un plateau initialisé avec une liste de navires récupérer dans le
     * fichier
     */
    public static Plateau chargerPartie(String chemin) {
        int i = 1;
        String[] tab = new String[4];
        ArrayList<Pair> tabPos;
        Navire navire = null;
        ArrayList<Navire> listeNavires = new ArrayList<Navire>();

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
                } else if (i % 5 == 2) {
                    tab[1] = ligne;
                } else if (i % 5 == 3) {
                    tab[2] = ligne;
                } else if (i % 5 == 4) {
                    tab[3] = ligne;

                } else if (i % 5 == 0) {
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

            plateau = new Plateau(listeNavires);

        } catch (IOException e) {
            System.out.println("Impossible de charger le plateau");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return plateau;
    }

    /**
     * charge le plateau de l'aperçu
     *
     * @param chemin du fichier texte contenant les positions des X sur l'aperçu
     *
     * @return une arraylist les coordonnées de chaque X
     */
    public static ArrayList chargerApercu(String chemin) {
        ArrayList<Pair> tabPosN = new ArrayList<Pair>();
        ArrayList<Integer> tabX = new ArrayList<Integer>();
        ArrayList<Integer> tabY = new ArrayList<Integer>();
        int i = 1;

        int posX = 0;
        int posY;

        Plateau plateau = null;

        try {
            File f = new File(chemin);
            FileReader fr = new FileReader(f);
            BufferedReader br = new BufferedReader(fr);
            String ligne = null; // contiendra chaque ligne
            //int h = 0;

            while ((ligne = br.readLine()) != null) {
                if (i % 2 == 1) {
                    tabX.add(Integer.parseInt(ligne));
                } else if (i % 2 == 0) {
                    tabY.add(Integer.parseInt(ligne));
                }
                i++;
            }

            br.close();

            plateau = new Plateau();

            for (int j = 0; j < tabX.size(); j++) {
                tabPosN.add(new Pair(tabX.get(j), tabY.get(j)));
            }

        } catch (IOException e) {
            System.out.println("Impossible de charger le plateau");
        } catch (Exception e) {
            e.printStackTrace();
        }

        return tabPosN;
    }

    /**
     * rempli le plateau de l'aperçu en mettant les X dans les bonnes cases
     *
     * @param p le plateau de l'aperçu
     * @param listeX liste des coordonnées
     */
    public static void remplirCasesX(Plateau p, ArrayList<Pair> listeX) {

        for (int i = 0; i < listeX.size(); i++) {
            for (int j = 1; j < 16; j++) {
                for (int k = 2; k < 30; k = k + 2) {
                    if (listeX.get(i).getKey().equals(j) && listeX.get(i).getValue().equals(k)) {
                        p.grille[j][k] = "X";
                    }
                }
            }
        }
    }
}
