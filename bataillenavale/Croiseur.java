package com.mycompany.bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
*
* @author Benjamin ROBSON, Kim ROJAS
*/

public class Croiseur extends Navire {
    
	public Croiseur(){
        this.nom = "Croiseur";
        this.sens = 0;
        this.taille = 5;
        this.puissance = 4;
    }
    
    public Croiseur(ArrayList<Pair> tabPos, int sensNav){
        this.nom = "Croiseur";
        this.sens = sensNav;
        this.taille = 5;
        this.puissance = 4;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]) && !"s".equals(tab[ligne][colonne]) && tab[ligne][colonne] != null)
            tab[ligne][colonne] = "X";
        if (!" ".equals(tab[ligne][colonne+2]) && !"s".equals(tab[ligne][colonne+2]) && tab[ligne][colonne+2] != null)
            tab[ligne][colonne+1] = "X";
        if (!" ".equals(tab[ligne+1][colonne]) && !"s".equals(tab[ligne+1][colonne]) && tab[ligne+1][colonne] != null)
            tab[ligne+1][colonne] =  "X";
        if  (!" ".equals(tab[ligne+1][colonne+2]) && !"s".equals(tab[ligne+1][colonne+2]) && tab[ligne+1][colonne+2] != null)
            tab[ligne+1][colonne+2] = "X";
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