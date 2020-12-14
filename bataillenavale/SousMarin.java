package bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 * Classe SousMarin qui étend la classe abstraite Navire. 
 * Elle crée des objets de type SousMarin, et implémente leur fonction de tir et de déplacement
 * 
 * @author Benjamin ROBSON, Kim ROJAS *
 */
public class SousMarin extends Navire {

    /**
     * Constructeur sans parametres
     */
    public SousMarin() {
        this.nom = "Sous-Marin";
        this.sens = 0;
        this.taille = 1;
        this.puissance = 1;
        this.coordonnes = new Pair(0,0);
        this.indice = 0;
    }

    /**
     * Constructeur initialisant un SousMarin avec les positions et son sens 
     * 
     * @param tabPos l'ensemble des coordonnées du navire sur la grille
     * @param sensNav son orientation (horizontal ou vertical)
     */
    public SousMarin(ArrayList<Pair> tabPos, int sensNav){
        this.nom = "Sous-Marin";
        this.sens = sensNav;
        this.taille = 1;
        this.puissance = 1;
        this.tabPos = tabPos;
    }

    /**
     * tir du sous-marin qui dévoile 1 case dans la grille adverse
     * 
     * @param ligne 
     * @param colonne
     * @param tab 
     */
    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]))
            tab[ligne][colonne]="X";
    }
}