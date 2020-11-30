package bataillenavale;

import java.util.ArrayList;
import javafx.util.Pair;

public class SousMarin extends Navire{
    
    public SousMarin(){
        this.nom = "Sous-Marin";
        this.sens = 0;
        this.taille = 1;
        this.puissance = 1;
    }
    
    public SousMarin(ArrayList<Pair> tabPos){
        this.nom = "Sous-Marin";
        this.sens = 0;
        this.taille = 1;
        this.puissance = 1;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]) && tab[ligne][colonne] != null)
            tab[ligne][colonne] = "X";
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        this.posX = ligne;
        this.posY = colonne;
    }
    
}