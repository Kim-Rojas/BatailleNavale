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
public class BatailleNavale {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Plateau p = new Plateau();
        String[][] grille = new String[32][32];
        grille = p.initGrille();
        
        p.initPlacementNavires();
        
        p.afficherPlateau();
        
        
    }
    
}
