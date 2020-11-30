package bataillenavale;

import java.util.Scanner;

public class BatailleNavale {
 
    public static void main(String[] args) {
        Plateau p1 = new Plateau();
        Plateau p2 = new Plateau();
        Plateau p3 = new Plateau();
        int a;
        String c;
        String chemin = "";
        Plateau essai = new Plateau();

        String[][] grilleJoueur = new String[32][32];
        String[][] grilleApercuIA = new String[32][32];
        String[][] grilleIA = new String[32][32];

        grilleJoueur = p1.initGrille();
        p1.initPlacementNavires();
        grilleApercuIA = p2.initGrille();
        
        //Test sauvegarde partie
        chemin = p1.savePartie(p1);
        essai = p1.chargerPartie(chemin, essai);

        grilleIA = p3.initGrille();
        p3.initPlacementNavires();
        p1.afficherPlateau();
        p1.navires[6].tirer(8, 16, grilleIA);
        p1.navires[0].tirer(6, 8, grilleIA);
        p1.navires[0].tirer(2, 4, grilleIA);
        p2.lien(grilleIA);
        System.out.println("\n");
        p2.afficherPlateau();
        System.out.println("\n");
        Scanner s = new Scanner(System.in);
        while (true){
            System.out.println("");
            System.out.println("##################################");
            System.out.println("tour suivant");
            System.out.println("##################################");
            System.out.println("");
            System.out.print("Entrer une lettre : ");
            c = s.next();
            System.out.print("Entrer un  nombre : ");
            a = s.nextInt();
        }
        
    }
}