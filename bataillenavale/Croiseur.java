package bataillenavale;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Benjamin ROBSON, Kim ROJAS
 */
public class Croiseur extends Navire {

    /**
     * Constructeur sans paramètres
     */
    public Croiseur() {
        this.nom = "Croiseur";
        this.sens = 0;
        this.taille = 5;
        this.puissance = 4;
        this.coordonnes = new Pair(0, 0);
        this.indice = 0;
    }

    /**
     * Constructeur initialisant un Croiseur avec les positions et son sens
     *
     * @param tabPos l'ensemble des coordonnées du navire sur la grille
     * @param sensNav son orientation (horizontal ou vertical)
     */
    public Croiseur(ArrayList<Pair> tabPos, int sensNav) {
        this.nom = "Croiseur";
        this.sens = sensNav;
        this.taille = 5;
        this.puissance = 4;
        this.tabPos = tabPos;
    }

    /**
     * tir du croiseur qui dévoile un carré de 4 cases dans la grille adverse
     *
     * @param ligne
     * @param colonne
     * @param tab
     */
    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        int cptl = 2;
        int cptc = 4;
        if (ligne == 15) {
            cptl -= 1;
        }
        if (colonne == 30) {
            cptc -= 2;
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
