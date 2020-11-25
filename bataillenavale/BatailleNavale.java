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
        Plateau p1 = new Plateau();
        Plateau p2 = new Plateau();
        Plateau p3 = new Plateau();

        String[][] grilleJoueur = new String[32][32];
        String[][] grilleApercuIA =new String[32][32];
        String[][] grilleIA = new String[32][32];

        grilleJoueur = p1.initGrille();
        p1.initPlacementNavires();
        grilleApercuIA = p2.initGrille();

        grilleIA = p3.initGrille();
        p3.initPlacementNavires();
        p1.afficherPlateau();
        System.out.println("\n");
        p2.afficherPlateau();
    }
}