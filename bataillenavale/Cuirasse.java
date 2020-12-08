package com.mycompany.bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
*
* @author Benjamin ROBSON, Kim ROJAS
* 
*/

public class Cuirasse extends Navire {

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