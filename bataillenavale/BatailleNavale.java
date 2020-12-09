package bataillenavale;

import java.io.IOException;
import java.util.InputMismatchException;
import java.util.Scanner;

import javafx.application.Application;
import javafx.stage.Stage;

/**
 *
 * @author Louis DUTTIER, Benjamin ROBSON, Kim ROJAS
 *
 */
public class BatailleNavale {

    /**
     *
     * @author Louis DUTTIER, Benjamin ROBSON, Kim ROJAS
     *
     */

    final static String cheminFinal = "";
    private static String cheminJ1;
    private static String cheminIA;
    private static String cheminApercu;

    public static void afficherJeu() {
        Plateau p1 = new Plateau();
        Plateau p2 = new Plateau();
        Plateau p3 = new Plateau();
        int a;
        String c;

        String[][] grilleJoueur = new String[32][32];
        String[][] grilleApercuIA = new String[32][32];
        String[][] grilleIA = new String[32][32];

        grilleJoueur = p1.initGrille();
        p1.initPlacementNavires();
        grilleApercuIA = p2.initGrille();

        grilleIA = p3.initGrille();
        p3.initPlacementNavires();
        p1.afficherPlateau();
        p1.navires.get(6).tirer(8, 16, grilleIA);
        p1.navires.get(0).tirer(6, 8, grilleIA);
        p1.navires.get(0).tirer(2, 4, grilleIA);
        p2.lien(grilleIA);
        System.out.println("\n");
        p2.afficherPlateau();
        System.out.println("\n");
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("##################################");
            System.out.println("tour suivant");
            System.out.println("Entrez \"q\" pour quitter la partie en cours");
            System.out.println("##################################");
            System.out.println("");
            System.out.println("Entrer une lettre : ");
            c = s.next();
            if(c.equals("q")){
                String nom = "";
                System.out.println("Entre le nom du fichier de sauvergarde: ");
                nom = s.next();
                cheminJ1 = Plateau.savePartie(p1, nom);
                cheminApercu = Plateau.savePartie(p1, nom+"Apercu");
                cheminIA = Plateau.savePartie(p3, nom+"IA");
                return;
            }
            System.out.println("Entrer un  nombre : ");
            a = s.nextInt();
        }
    }

    /**
     *
     * @author Kim ROJAS
     *
     */
    public static void afficherJeuCharge(Plateau p1, Plateau p3) {
        Plateau p2 = new Plateau();
        int a;
        String c;
        
        String[][] grilleJoueur = new String[32][32];
        String[][] grilleApercuIA = new String[32][32];
        String[][] grilleIA = new String[32][32];

        grilleJoueur = p1.initGrille();
        //p1.initPlacementNavires();
        p1.placementNaviresCharges();
        //grilleApercuIA = p2.initGrille();

       
        p1.afficherPlateau();
       
       // p2.lien(grilleIA);
        System.out.println("\n");
        //p2.afficherPlateau();
        System.out.println("\n");
        Scanner s = new Scanner(System.in);
        while (true) {
            System.out.println("");
            System.out.println("##################################");
            System.out.println("tour suivant");
            System.out.println("Entrez \"q\" pour quitter la partie en cours");
            System.out.println("##################################");
            System.out.println("");
            System.out.print("Entrer une lettre : ");
            c = s.next();
            if(c.equals("q")){
                String nom = "";
                System.out.println("Entre le nom du fichier de sauvergarde: ");
                nom = s.next();
                cheminJ1 = Plateau.savePartie(p1, nom);
                cheminApercu = Plateau.savePartie(p2, nom+"Apercu");
                cheminIA = Plateau.savePartie(p3, nom+"IA");
                System.out.println(cheminJ1);
                return;
            }
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
        System.out.println("Veuillez saisir un Nombre : ");
    }
    
    /**
     *
     * @author Louis DUTTIER
     *
     */
    
    public static void Menu() {

        boolean success = false;
        String chemin = "";
        Plateau essai = new Plateau();
        Scanner choix = new Scanner(System.in);

        while (!success) {
            try {
                afficherMenu(); int incertionChoix = choix.nextInt();
                switch (incertionChoix) {
                    case 1:
                        afficherJeu();
                        break;
                    case 2:
                        System.out.println("Choisir le ficher en question :");
                        System.out.println(" ");
                        //Plateau.chargerPartie(chemin, essai);
                        System.out.println("-------------------------------------------------------------------------------------");
                        Plateau pJ1 = Plateau.chargerPartie("aa.txt");
		                //Plateau pApercu = Plateau.chargerPartie("aaApercu.txt");
		                Plateau pIA = Plateau.chargerPartie("aaIA.txt");                
		                afficherJeuCharge(pJ1, pIA);
                        Menu();
                        break;


                    case 3:
                        System.out.println("###############################################");
                        System.out.println("#                                             #");
                        System.out.println("# Bienvenue dans BNM (Bataille Navale Mobile) #");
                        System.out.println("#                                             #");
                        System.out.println("###############################################");
                        System.out.println("  ");
                        System.out.println("Petites explications concernant le déroulement du jeu : ");
                        System.out.println("Une grille de 15x15 sera initialisée pour vous et l'ordinateur.");
                        System.out.println("Sur cette même grille apparaîtra aléatoirement 4 types de bateau : 1 Cuirassé , 2 Croiseurs, 3 Destroyers et 4 Sous-Marins");
                        System.out.println("  ");
                        System.out.println("Voici les spécificitées des différents bateaux :");
                        System.out.println("- Cuirassé ");
                        System.out.println("    Taille = 7 cases ; Puissance de tir : 9 cases");
                        System.out.println("  ");
                        System.out.println("- Croiseur ");
                        System.out.println("    Taille = 5 cases ; Puissance de tir : 4 cases");
                        System.out.println("  ");
                        System.out.println("- Destroyer ");
                        System.out.println("    Taille = 3 cases ; Puissance de tir : 1 case");
                        System.out.println("    Particularité : son premier tir dévoile un  carré  de  4*4  cases  dans  la  grille  adverse");
                        System.out.println("    La zone démarre à partir du  coin  haut  et  gauche.");
                        System.out.println("    Les navires adverses de ce carré ne seront visibles que lors du tour du jeu, soit pendant quelques secondes");
                        System.out.println("  ");
                        System.out.println("- Cuirassé ");
                        System.out.println("    Taille = 1 case ; Puissance de tir : 1 case");
                        System.out.println("  ");
                        System.out.println (" Possibilité de déplacer un de ses navires, mais seulement d'UNE seule case (qu'à l'horizontale ou à la verticale");
                        System.out.println(" Si vous êtes touché ou vous avez un obstacle devant vous, impossible de se déplacer");
                        System.out.println(" ");
                        System.out.println("Vous savez tout ! Maintenant, à vous de jouer !");
                        System.out.println("  ");
                        System.out.println("-------------------------------------------------------------------------------------");
                        Menu();
                        break;
                    case 4:
                        System.out.println("Au revoir :)");
                        System.exit(0);
                        break;
                    default:
                        System.out.println("Choissisez entre 1 et 4");
                        System.out.println(" ");
                        Menu();
                        break;
                }
                Menu();
            } catch (InputMismatchException ime) {
                System.out.println("Erreur dans votre saisi");
                Menu();
            }
            success = true;
        }
    }

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        Menu();
    }
}
