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
public class Destroyer extends Navire{
    
    public Destroyer (int positionLigne, int positionColonne){
        this.taille = 3;
        this.puissance = 1;
        this.posX = positionLigne;
        this.posY = positionColonne;
    }

    @Override
    public void tirer(int ligne, int colonne) {
        
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        posX = ligne;
        posY = colonne;
    }
    
}
