package bataillenavale;

import java.util.Scanner;

/**
 *
 * @author Louis DUTTIER, Benjamin ROBSON, Kim ROJAS
 * 
 */

public class BatailleNavale {
	/**
	 *
	 * @author Louis DUTTIER, Benjamin ROBSON
	 * 
	 */
	public static void afficherJeu() {
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
	
	/**
	 *
	 * @author Louis DUTTIERS
	 * 
	 */
	
	public static void afficherMenu() {
		System.out.println("Mobile Naval");
    	System.out.println("---------------------");
    	System.out.println("1 - Jouer");
    	System.out.println("2 - Charger une partie");
    	System.out.println("3 - Règles du jeu");
    	System.out.println("4 - Quitter Mobile Naval");
    	System.out.println(" ");
	}
	
	/**
	 *
	 * @author Louis DUTTIER
	 * 
	 */
	
	public static void Menu() {
		Scanner choix = new Scanner(System.in);
    	
    	afficherMenu();
    	System.out.print("Veuillez saisir un Nombre : ");
    	int incertionChoix = choix.nextInt() ;

    	switch(incertionChoix) {
    		case 1 : 
    			afficherJeu();
    			break;
    		case 2 : 
    			System.out.println("Choisir le ficher en question :");
    			System.out.println(" ");
    			System.out.println("---------------------");
    			Menu();
    			break;
    		case 3 : 
    			System.out.println("Mobile Naval : règle du jeu");
    			System.out.println(" ");
    			System.out.println("---------------------");
    			Menu();
    			break;
    		case 4 :
    			System.out.println("Au revoir :)");
    			System.exit(1);
    			break;
    		default : 
    			System.out.println("Choissisez entre 1 et 4");
    			System.out.println(" ");
    			Menu();
    			break;
    	}
    	Menu();
	}
	
    /**
     * @param args the 
     * command line arguments
     */
    public static void main(String[] args) {
    	Menu();
    }
}
