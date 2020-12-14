package bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
 *
 * @author Benjamin ROBSON, Kim ROJAS
 *
 */
public class Destroyer extends Navire {

    protected int fusee;

    public Destroyer() {
        this.nom = "Destroyer";
        this.sens = 0;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.coordonnes = new Pair(0,0);
        this.indice = 0;
    }

    public Destroyer (ArrayList<Pair> tabPos, int sensNav){
        this.nom = "Destroyer";
        this.sens = sensNav;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        //int cptl = 4;
        //int cptc = 8;
        /*if (fusee == 1){
            switch (cptl){
                case 13:
                    cptl -= 1;
                    break;
                case 14:
                    cptl -= 2;
                    break;
                case 15:
                    cptl -= 3;
                    break;
            }
            switch (cptc){
                case 26:
                    cptc -= 2;
                    break;
                case 28:
                    cptc -= 4;
                    break;
                case 30:
                    cptc -= 6;
                    break;
            }
            for (int i=ligne; i<cptl; i++){
                for (int j=colonne; j<cptc; j+=2){
                    if (" ".equals(tab[i][j]))
                        tab[i][j] = "o";
                    if (!" ".equals(tab[i][j]) && !"X".equals(tab[i][j]))
                        tab[i][j] = "n";
                }
            }
            fusee = 0;
        }*/
            if (!" ".equals(tab[ligne][colonne]) && !tab[ligne][colonne].equals("s"))
                tab[ligne][colonne]="X";
        }
}