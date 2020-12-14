package bataillenavale;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Benjamin ROBSON, Kim ROJAS
 *
 */
public class Cuirasse extends Navire {

    /**
     * Constructeur sans paramètres
     */
    public Cuirasse() {
        this.nom = "Cuirasse";
        this.sens = 0;
        this.taille = 7;
        this.puissance = 9;
        this.coordonnes = new Pair(0, 0);
        this.indice = 0;
    }

    /**
     * Constructeur initialisant un Cuirasse avec les positions et son sens
     *
     * @param tabPos l'ensemble des coordonnées du navire sur la grille
     * @param sensNav son orientation (horizontal ou vertical)
     */
    public Cuirasse(ArrayList<Pair> tabPos, int sensNav) {
        this.nom = "Cuirasse";
        this.sens = sensNav;
        this.taille = 7;
        this.puissance = 9;
        this.tabPos = tabPos;
    }

    /**
     * tir du cuirasse qui dévoile un carré de 9 cases dans la grille adverse
     *
     * @param ligne
     * @param colonne
     * @param tab
     *
     * @author Benjamin ROBSON
     */
    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        int cptl = 3;
        int cptc = 6;
        switch (ligne) {
            case 14:
                cptl -= 1;
                break;
            case 15:
                cptl -= 2;
                break;
        }
        switch (colonne) {
            case 28:
                cptc -= 2;
                break;
            case 30:
                cptc -= 4;
                break;
        }
        for (int i = ligne; i < ligne + cptl; i++) {
            for (int j = colonne; j < colonne + cptc; j += 2) {
                if (!tab[i][j].equals(" ") && !tab[i][j].equals("s")) {
                    tab[i][j] = "X";
                }
            }
        }
    }
}