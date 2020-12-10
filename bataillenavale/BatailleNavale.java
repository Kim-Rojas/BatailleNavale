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
     * @author Louis DUTTIER, Benjamin ROBSON
     *
     */
    
    final static String cheminFinal = "";
    private static String cheminJ1;
    private static String cheminIA;
    private static String cheminApercu;
    
    public static int charToInt(char c){
        int x=1;
        
        for (char tmp = 'a'; tmp <= 'o'; tmp++){
            if (c == tmp)
                break;
            x++;
        }
        return x;
    }
    
    public static int[] selectionNavire(Scanner s, Plateau p){
        boolean success = false;
        String tmp = "";
        int[] ans = new int[2];
        int x = 0;
        int y = 0;
        
        while (success == false){
                try {
                    System.out.print("Sélection d'un navire, ligne : ");
                    tmp = s.next();
                    System.out.print(" colonne : ");
                    y = s.nextInt();
                    if (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || y < 0 || y > 14){
                        System.out.println("Erreur dans votre saisi");
                        selectionNavire(s,p);
                    }
                    x = charToInt(tmp.charAt(0));
                    y = (y * 2) + 2;
                    if (!"C".equals(p.grille[x][y]) && !"c".equals(p.grille[x][y]) && !"d".equals(p.grille[x][y]) && !"s".equals(p.grille[x][y])){
                        System.out.println("Sélectionnez un navire !");
                        selectionNavire(s,p);
                    }
                }catch (InputMismatchException ime) {
                    System.out.println("Erreur dans votre saisi");
                    selectionNavire(s,p);
                }
                success = true;
        }
        ans[0] = x;
        ans[1] = y;
        return  ans;
    }
    
    public static String selectionOption(Scanner s, int[] nav, Plateau p){
        boolean success = false;
        String option = "";
        String name = "";
        
        switch (p.grille[nav[0]][nav[1]]){
            case "C":
                name = "Cuirasse";
                break;
            case "c":
                name = "Croiseur";
                break;
            case "d":
                name = "Destroyer";
                break;
            case "s":
                name = "Sous-marin";
                break;
        }
        while (success == false){
                try {
                    System.out.print("Navire : "+name+". Voulez-vous tirer ou déplacer le navire (t/d) : ");
                    option = s.next();
                    if (!option.equals("t") && !option.equals("d")){
                        System.out.println("Erreur dans votre saisi");
                        selectionOption(s, nav, p);
                    }
                }catch (InputMismatchException ime){
                    System.out.println("Erreur dans votre saisi");
                    selectionOption(s, nav, p);
                }
                success = true;
        }
        return option;
    }
    
    public static int[] selectionTire(Scanner s){
        boolean success = false;
        String tmp = "";
        int[] ans = new int[2];
        int l = 0;
        int c = 0;
        
        while (success == false){
                try {
                    System.out.print("Coordonnées de tire, ligne : ");
                    tmp = s.next();
                    System.out.print(" colonne : ");
                    c = s.nextInt();
                    if (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || c < 0 || c > 14){
                        System.out.println("Erreur dans votre saisi");
                        selectionTire(s);
                    }
                    l = charToInt(tmp.charAt(0));
                    c = (c * 2) + 2;
                }catch (InputMismatchException ime) {
                    System.out.println("Erreur dans votre saisi");
                    selectionTire(s);
                }
                success = true;
        }
        ans[0] = l;
        ans[1] = c;
        return  ans;
    }

    public static void afficherJeu() {
        Plateau p1 = new Plateau();
        Plateau p2 = new Plateau();
        Plateau p3 = new Plateau();
        Scanner s = new Scanner(System.in);
        String[][] grilleJoueur = new String[32][32];
        String[][] grilleApercuIA = new String[32][32];
        String[][] grilleIA = new String[32][32];
        String option = "";
        int[] choixN = new int[2];
        int[] choixT = new int[2];

        grilleJoueur = p1.initGrille();
        p1.initPlacementNavires();
        grilleApercuIA = p2.initGrille();

        grilleIA = p3.initGrille();
        p3.initPlacementNavires();
        while (p1.verifierPartieFinie() == false && p3.verifierPartieFinie() == false){ //boucle de jeu
            p2.lien(grilleIA);
            p1.afficherPlateau();
            System.out.println("");
            p2.afficherPlateau();
            System.out.println("");
            p3.afficherPlateau();
            System.out.println("");
            p3.checkNavireCoule(); // ne fonctionne pas encore correctement
            choixN = selectionNavire(s, p1);
            option = selectionOption(s, choixN, p1);
            if (option.equals("t")){
                choixT = selectionTire(s);
                System.out.println("");
                switch(p1.grille[choixN[0]][choixN[1]]){
                    case "C":
                        for (Navire n : p1.navires){
                            if(n.nom.charAt(0) == 'C'){
                                System.out.println("x="+choixT[0]+" y="+choixT[1]);
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "c":
                        for (Navire n : p1.navires){
                            if(n.nom.charAt(0) == 'c'){
                                System.out.println("x="+choixT[0]+" y="+choixT[1]);
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "d":
                        for (Navire n : p1.navires){
                            if(n.nom.charAt(0) == 'd'){
                                System.out.println("x="+choixT[0]+" y="+choixT[1]);
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "s":
                        for (Navire n : p1.navires){
                            if(n.nom.charAt(0) == 's'){
                                System.out.println("x="+choixT[0]+" y="+choixT[1]);
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                }
            }
            else{
                //déplacer
            }
        }
        
    }
    
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
        System.out.print("Veuillez saisir un Nombre : ");
    }
    
    /**
     *
     * @author Louis DUTTIER
     *
     */
    
    public static void Menu() {

        boolean success = false;
        Scanner choix = new Scanner(System.in);

        while (!success) {
            try {
                afficherMenu(); int incertionChoix = choix.nextInt();
                switch (incertionChoix) {
                    case 1:
                        System.out.println("");
                        afficherJeu();
                        break;
                    case 2:
                        System.out.println("Choisir le ficher en question :");
                        System.out.println(" ");
                        System.out.println("-------------------------------------------------------------------------------------");
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