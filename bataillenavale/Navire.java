package com.mycompany.bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
*
* @author Kim ROJAS
* 
*/

public abstract class Navire {
	protected String nom;
    protected int sens;
    protected int taille;
    protected int puissance;
    protected int posX;
    protected int posY;
    protected boolean estTouche;
    protected boolean estCoule;
    protected ArrayList<Pair> tabPos = new ArrayList<Pair>();
    
    /**
     *
     * @param ligne
     * @param colonne
     * @param tab
     */
    public abstract void tirer(int ligne, int colonne, String[][] tab);
    
    public abstract void deplacer(KeyEvent e, int direction, int ligne, int colonne);
}