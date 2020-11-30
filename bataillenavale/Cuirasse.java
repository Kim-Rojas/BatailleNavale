package bataillenavale;

import java.util.ArrayList;
import javafx.util.Pair;

public class Cuirasse extends Navire{

    public Cuirasse(){
        this.nom = "Cuirasse";
        this.sens = 0;
        this.taille = 7;
        this.puissance = 9;
    }
    
    public Cuirasse(ArrayList<Pair> tabPos){
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        for (int i = ligne; i < ligne+3; i++){
            for (int j = colonne; j< colonne+6; j+=2){
                if (!" ".equals(tab[i][j]) && !"s".equals(tab[i][j]) && tab[i][j] != null)
                    tab[i][j] = "X";
            }
        }
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        posX = ligne;
        posY = colonne;
    }
}