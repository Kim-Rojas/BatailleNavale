package bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
*
* @author Benjamin ROBSON, Kim ROJAS
* 
*/

public class Cuirasse extends Navire {

	public Cuirasse(String nom){
        this.nom = nom;
        this.sens = 0;
        this.taille = 7;
        this.puissance = 9;
        this.coordonnes = new Pair(0,0);
        this.indice = 0;
    }
    
    public Cuirasse(ArrayList<Pair> tabPos, int sensNav) {
        this.nom = "Cuirasse";
        this.sens = sensNav;
        this.taille = 7;
        this.puissance = 9;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        int cptl = 3;
        int cptc = 6;
        switch(ligne){
            case 14:
                cptl -= 1;
                break;
            case 15:
                cptl -= 2;
                break;
        }
        switch(colonne){
            case 28:
                cptc -= 2;
                break;
            case 30:
                cptc -= 4;
                break;
        }
        for (int i = ligne; i<ligne+cptl; i++){
            for (int j = colonne; j<colonne+cptc; j+=2){
                if (!tab[i][j].equals(" ") && !tab[i][j].equals("s"))
                    tab[i][j] = "X";
            }
        }
    }

    @Override
    public void deplacer(KeyEvent e, int direction, int ligne, int colonne) {
        this.posX = ligne;
        this.posY = colonne;

        /**
         * switch (direction) { 
         * // Haut 
         * case 1: 
         *      this.posX++; 
         *      break;
         * // Bas 
         * case 2: 
         *      this.posX--; 
         *      break; 
         * // Gauche
         * case 3: 
         *      this.posY++;
         *      break;
         * // Droite
         * case 4: 
         *      this.posY--;
         *      break;
         * 
         * default: 
         *      System.out.println("Ceci n'est pas une direction valide.");
         * } *
         */
        switch (e.getKeyCode()) {
            case KeyEvent.VK_RIGHT:
		this.posX++;
                break;
            case KeyEvent.VK_LEFT:
                this.posX--;
                break;
            case KeyEvent.VK_UP:
                this.posY++;
                break;
            case KeyEvent.VK_DOWN:
                this.posY--;
                break;
            default :
                System.out.println("Ceci n'est pas une direction valide.");
        }
    }
}