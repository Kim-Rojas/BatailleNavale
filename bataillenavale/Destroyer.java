package bataillenavale;

import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Benjamin ROBSON, Kim ROJAS
 *
 */
public class Destroyer extends Navire {

    protected int fusee;

    /**
     * Constructeur sans paramètres
     */
    public Destroyer() {
        this.nom = "Destroyer";
        this.sens = 0;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.coordonnes = new Pair(0, 0);
        this.indice = 0;
    }

    /**
     * Constructeur initialisant un Destroyer avec les positions et son sens
     *
     * @param tabPos l'ensemble des coordonnées du navire sur la grille
     * @param sensNav son orientation (horizontal ou vertical)
     */
    public Destroyer(ArrayList<Pair> tabPos, int sensNav) {
        this.nom = "Destroyer";
        this.sens = sensNav;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.tabPos = tabPos;
    }

    /**
     * tir du destroyer qui dévoile 1 case dans la grille adverse
     *
     * @param ligne
     * @param colonne
     * @param tab
     *
     */
    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]) && !tab[ligne][colonne].equals("s")) {
            tab[ligne][colonne] = "X";
        }
    }
}