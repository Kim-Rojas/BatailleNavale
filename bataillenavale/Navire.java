/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package bataillenavale;

/**
 *
 * @author kimro
 */
public abstract class Navire {
    protected int taille;
    protected int puissance;
    protected int posX;
    protected int posY;
    protected boolean estTouche;
    protected boolean estCoule;
    
    public abstract void tirer(int ligne, int colonne);
    
    public abstract void deplacer(int ligne, int colonne);
}
