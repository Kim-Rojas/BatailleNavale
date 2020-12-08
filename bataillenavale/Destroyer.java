package com.mycompany.bataillenavale;

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
    }

    public Destroyer(ArrayList<Pair> tabPos) {
        this.nom = "Destroyer";
        this.sens = 0;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]) && !"s".equals(tab[ligne][colonne]) && tab[ligne][colonne] != null) {
            tab[ligne][colonne] = "X";
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
