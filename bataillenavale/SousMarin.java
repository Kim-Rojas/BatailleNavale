package bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Benjamin ROBSON, Kim ROJAS
 *
 */
public class SousMarin extends Navire {

    public SousMarin() {
        this.nom = "Sous-Marin";
        this.sens = 0;
        this.taille = 1;
        this.puissance = 1;
        this.coordonnes = new Pair(0,0);
        this.indice = 0;
    }

    public SousMarin(ArrayList<Pair> tabPos, int sensNav){
        this.nom = "Sous-Marin";
        this.sens = sensNav;
        this.taille = 1;
        this.puissance = 1;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]))
            tab[ligne][colonne]="X";
    }
}