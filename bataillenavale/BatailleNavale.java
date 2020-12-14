package bataillenavale;

import bataillenavale.Navire;
import bataillenavale.Plateau;
import java.util.InputMismatchException;
import java.util.Scanner;

import java.util.ArrayList;
import java.util.Random;

import javafx.util.Pair;

/**
 *
 * @author Louis DUTTIER, Benjamin ROBSON, Kim ROJAS
 *
 */
public class BatailleNavale {

    final static String cheminFinal = "";
    private static String cheminJ1;
    private static String cheminIA;
    private static String cheminApercu;

    /**
     * Permet à l'IA de jouer
     * L'utilisateur est en attente du choix de l'IA 
     * 
     * @param p1 plateau du joueur
     * @param p3 plateau de l'IA
     */
    public static void IA(Plateau p1, Plateau p3) {
        Random r = new Random();
        int x = 0;
        int y = 0;
        char[] d = new char[4];
        d[0] = 'N';
        d[1] = 'S';
        d[2] = 'E';
        d[3] = 'W';
        int NS = r.nextInt((1 - 0) + 1) + 0;
        int WE = r.nextInt((3 - 2) + 1) + 2;
        int choixN = r.nextInt(p3.navires.size());
        int choixO = r.nextInt(2);

        System.out.println("Navire choisi : " + p3.navires.get(choixN).nom);
        try {
            Thread.sleep(3000);
        } catch (InterruptedException e) {
            System.out.println(e);
        }
        if (choixO == 1) { // tir IA
            System.out.println("Il s'apprête à tirer..");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            x = r.nextInt((15 - 1) + 1) + 1;
            y = r.nextInt(31);
            while (y == 0 || y % 2 != 0) {
                y = r.nextInt(31);
            }
            System.out.println("Coordonnées de tir (" + intToChar(x) + "/" + ((y / 2) - 1) + ")");
            System.out.println("");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            p3.navires.get(choixN).tirer(x, y, p1.grille);
        } else { // déplacement IA
            System.out.println("Il s'apprête à déplacer son navire..\n");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            if (p3.navires.get(choixN).sens == 0) {
                p3.navires.get(choixN).deplacer(d[NS], p3.grille, 2);
            } else {
                p3.navires.get(choixN).deplacer(d[WE], p3.grille, 2);
            }
        }
    }

    /**
     * converti un caractère de type char en int
     *
     * @param c
     * @return x le caractère converti
     */
    public static int charToInt(char c) {
        int x = 1;

        for (char tmp = 'a'; tmp <= 'o'; tmp++) {
            if (c == tmp) {
                break;
            }
            x++;
        }
        return x;
    }

    /**
     * converti un caractère de type int en char
     * 
     * @param x
     * @return c le caractère converti
     */
    public static char intToChar(int x) {
        int test = 1;
        char c = 'a';

        for (char tmp = 'a'; tmp <= 'o'; tmp++) {
            c = tmp;
            if (test == x) {
                break;
            }
            test++;
        }
        return c;
    }

    /**
     * selectionne un navire en fonction des coordonées rentrées par
     * l'utilisateur L'utilisateur entre une lettre correspondant à la ligne
     * choisi, et un chiffre pour la colonne, L'entrée correspondant à la ligne
     * est converti en chiffre L'entrée correspondant à la colonne est
     * recalculée pour correspondre à une case de la grille
     *
     * @param s
     * @param p1 plateau de jeu J1
     * @param p2 plateau de l'aperçu
     * @param p3 plateau de l'IA
     * @return un tableau contenant les coordonées du navire sélectionné
     */
    public static int[] selectionNavire(Scanner s, Plateau p1, Plateau p2, Plateau p3) {

        String tmp = "";
        int[] ans = new int[2];
        int x = 0;
        int y = 0;
        int tmpy = 0;
        int tmpy2 = 0;
        int test = 0;
        int test2 = 0;

        try {
            System.out.print("Sélection d'un navire, ligne : ");
            tmp = s.next();
            //si l'utilisateur rentre "q" la partie se sauvegarde
            if ("q".equals(tmp)) {
                String nom = "";
                System.out.println("Entre le nom du fichier de sauvegarde: ");
                nom = s.next();
                cheminJ1 = Plateau.savePartie(p1, nom);
                cheminApercu = Plateau.saveApercu(p2, nom + "Apercu");
                cheminIA = Plateau.savePartie(p3, nom + "IA");
                System.exit(0);
            }
            System.out.print("colonne : ");
            y = s.nextInt();
            tmpy = y;
        } catch (InputMismatchException ime) {
            System.out.println("Erreur dans votre saisie !");
            test = 1;
        }
        if (test == 0) {
            if (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || y < 0 || y > 14) {
                System.out.println("Erreur dans votre saisie !");
            } else {
                x = charToInt(tmp.charAt(0));
                y = (y * 2) + 2;
                if (!"C".equals(p1.grille[x][y]) && !"c".equals(p1.grille[x][y]) && !"d".equals(p1.grille[x][y]) && !"s".equals(p1.grille[x][y])) {
                    System.out.println("Navire inexistant !");
                    test2 = 1;
                } else {
                    test2 = 0;
                }
            }
        }
        if (test == 0 && test2 == 0 && tmp.charAt(0) >= 'a' && tmp.charAt(0) <= 'o' && tmp.length() == 1 && tmpy >= 0 && tmpy <= 14) {
            ans[0] = x;
            ans[1] = y;
        } else {
            while (test == 1 || test2 == 1 || (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || tmpy2 < 0 || tmpy2 > 14)) {
                try {
                    System.out.print("Sélection d'un navire, ligne : ");
                    tmp = s.next();
                    if ("q".equals(tmp)) {
                        String nom = "";
                        System.out.println("Entre le nom du fichier de sauvegarde: ");
                        nom = s.next();
                        cheminJ1 = Plateau.savePartie(p1, nom);
                        cheminApercu = Plateau.saveApercu(p2, nom + "Apercu");
                        cheminIA = Plateau.savePartie(p3, nom + "IA");
                        System.exit(0);
                    }
                    System.out.print("colonne : ");
                    tmpy = s.nextInt();
                    test = 0;
                } catch (InputMismatchException ime) {
                    System.out.println("Erreur dans votre saisie !");
                    test = 1;
                }
                if (test == 0) {
                    if (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || tmpy < 0 || tmpy > 14) {
                        System.out.println("Erreur dans votre saisie !");
                    } else {
                        x = charToInt(tmp.charAt(0));
                        tmpy2 = tmpy;
                        tmpy = (tmpy * 2) + 2;
                        if (!"C".equals(p1.grille[x][tmpy]) && !"c".equals(p1.grille[x][tmpy]) && !"d".equals(p1.grille[x][tmpy]) && !"s".equals(p1.grille[x][tmpy])) {
                            System.out.println("Navire inexistant !");
                            test2 = 1;
                        } else {
                            test2 = 0;
                        }
                    }
                }
            }
            ans[0] = x;
            ans[1] = tmpy;
        }
        return ans;
    }

    /**
     * choix de l'action que le navire sélectionné va effectuer (tirer ou se
     * déplacer)
     *
     * @param s
     * @param nav
     * @param p
     *
     * @return l'option choisie
     */
    public static String selectionOption(Scanner s, int[] nav, Plateau p) {
        int test = 0;
        int test2 = 0;
        String option = "";
        String name = "";

        switch (p.grille[nav[0]][nav[1]]) {
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
        try {
            System.out.print("Navire : " + name + ". Voulez-vous tirer ou déplacer le navire (t/d) : ");
            option = s.next();
        } catch (InputMismatchException ime) {
            System.out.println("Erreur dans votre saisie !");
            test = 1;
        }
        if (!option.equals("t") && !option.equals("d")) {
            System.out.println("Erreur dans votre saisie !");
            test2 = 1;
        }
        while (test == 1 || test2 == 1) {
            try {
                System.out.print("Navire : " + name + ". Voulez-vous tirer ou déplacer le navire (t/d) : ");
                option = s.next();
                test = 0;
            } catch (InputMismatchException ime) {
                System.out.println("Erreur dans votre saisie !");
                test = 1;
            }
            if (!option.equals("t") && !option.equals("d")) {
                System.out.println("Erreur dans votre saisie !");
                test2 = 1;
            } else {
                test2 = 0;
            }
        }
        return option;
    }

    /**
     * choix de l'emplacement du plateau adverse où le joueur souhaite tirer Le
     * joueur doit entrer les coordonnées désirées
     *
     * @param s
     *
     * @return un tableau contenant les coordonnées de la ligne et de la colonne
     * choisis
     */
    public static int[] selectionTir(Scanner s) {
        int test = 0;
        int test2 = 0;
        String tmp = "";
        int[] ans = new int[2];
        int l = 0;
        int c = 0;

        try {
            System.out.print("Coordonnées de tir, ligne : ");
            tmp = s.next();
            System.out.print("colonne : ");
            c = s.nextInt();
        } catch (InputMismatchException ime) {
            System.out.println("Erreur dans votre saisie !");
            test = 1;
        }
        if (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || c < 0 || c > 14) {
            System.out.println("Erreur dans votre saisie !");
            test2 = 1;
        }
        while (test == 1 || test2 == 1) {
            try {
                System.out.print("Coordonnées de tir, ligne : ");
                tmp = s.next();
                System.out.print("colonne : ");
                c = s.nextInt();
                test = 0;
            } catch (InputMismatchException ime) {
                System.out.println("Erreur dans votre saisie !");
                test = 1;
            }
            if (tmp.charAt(0) < 'a' || tmp.charAt(0) > 'o' || tmp.length() > 1 || c < 0 || c > 14) {
                System.out.println("Erreur dans votre saisie !");
                test2 = 1;
            } else {
                test2 = 0;
            }
        }
        l = charToInt(tmp.charAt(0));
        c = (c * 2) + 2;
        ans[0] = l;
        ans[1] = c;
        return ans;
    }

    /**
     * Permet à l'utilisateur de choisir dans quelle direction il souhaite déplacer son navire
     * @param s
     * @param n
     * @param p1 
     */
    public static void selectionDeplacement(Scanner s, Navire n, Plateau p1) {
        String rep = "";
        int test = 0;
        int test2 = 0;
        int test3 = 0;
        int d = 0;

        if (n.sens == 0) {
            try {
                System.out.print("Navire vertical, déplacement souhaité (N/S) : ");
                rep = s.next();
            } catch (InputMismatchException ime) {
                System.out.println("Erreur dans votre saisie !");
                test = 1;
            }
            if (!"N".equals(rep) && !"S".equals(rep)) {
                System.out.println("Erreur dans votre saisie !");
                test2 = 1;
            }
            if ((d = n.deplacer(rep.charAt(0), p1.grille, 1)) != 0) {
                test3 = 1;
            }
            if (d == 1) {
                System.out.println("Navire endommagé, déplacemennt impossible !");
                System.out.println("");
                return;
            }
            while (test == 1 || test2 == 1 || test3 == 1) {
                try {
                    System.out.print("Navire vertical, déplacement souhaité (N/S) : ");
                    rep = s.next();
                    test = 0;
                } catch (InputMismatchException ime) {
                    System.out.println("Erreur dans votre saisie !");
                    test = 1;
                }
                if (!"N".equals(rep) && !"S".equals(rep)) {
                    System.out.println("Erreur dans votre saisie !");
                    test2 = 1;
                } else {
                    test2 = 0;
                }
                if ((d = n.deplacer(rep.charAt(0), p1.grille, 1)) != 0) {
                    test3 = 1;
                } else {
                    test3 = 0;
                }
                if (d == 1) {
                    System.out.println("Navire endommagé, déplacemennt impossible !");
                    System.out.println("");
                    return;
                }
            }
        } else {
            try {
                System.out.print("Navire horizontal, déplacement souhaité (W/E) : ");
                rep = s.next();
            } catch (InputMismatchException ime) {
                System.out.println("Erreur dans votre saisie !");
                test = 1;
            }
            if (!"W".equals(rep) && !"E".equals(rep)) {
                System.out.println("Erreur dans votre saisie !");
                test2 = 1;
            }
            if ((d = n.deplacer(rep.charAt(0), p1.grille, 1)) != 0) {
                test3 = 1;
            }
            if (d == 1) {
                System.out.println("Navire endommagé, déplacemennt impossible !");
                System.out.println("");
                return;
            }
            while (test == 1 || test2 == 1 || test3 == 1) {
                try {
                    System.out.print("Navire horizontal, déplacement souhaité (W/E) : ");
                    rep = s.next();
                    test = 0;
                } catch (InputMismatchException ime) {
                    System.out.println("Erreur dans votre saisie !");
                    test = 1;
                }
                if (!"W".equals(rep) && !"E".equals(rep)) {
                    System.out.println("Erreur dans votre saisie !");
                    test2 = 1;
                } else {
                    test2 = 0;
                }
                if ((d = n.deplacer(rep.charAt(0), p1.grille, 1)) != 0) {
                    test3 = 1;
                } else {
                    test3 = 0;
                }
                if (d == 1) {
                    System.out.println("Navire endommagé, déplacemennt impossible !");
                    System.out.println("");
                    return;
                }
            }
        }
        System.out.println("");
    }

    /**
     * Affiche le jeu en cours Le joueur voit son plateau avec le placement de
     * ses navires, ainsi que le plateau d'aperçu des navires tu=ouchés chez
     * l'adversaire
     */
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
        while (p1.verifierPartieFinie() == false && p3.verifierPartieFinie() == false) { //boucle de jeu
            p2.lien(grilleIA);
            p1.afficherPlateau();
            System.out.println("");
            p2.afficherPlateau();
            System.out.println("");
            p1.checkNavireCoule();
            choixN = selectionNavire(s, p1, p2, p3);
            option = selectionOption(s, choixN, p1);
            if (option.equals("t")) { // choix du tir
                choixT = selectionTir(s);
                System.out.println("");
                switch (p1.grille[choixN[0]][choixN[1]]) {
                    case "C":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "c":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C' && n.nom.charAt(1) == 'r') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "d":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'D') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "s":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'S') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                }
            } else { // choix du déplacemennt
                switch (p1.grille[choixN[0]][choixN[1]]) {
                    case "C":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C' && n.nom.charAt(1) == 'u') {
                                selectionDeplacement(s, n, p1);
                                break;
                            }
                        }
                        break;
                    case "c":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C' && n.nom.charAt(1) == 'r') {
                                if (n.sens == 0) {
                                    for (int i = (int) n.coordonnes.getKey(); i > (int) n.coordonnes.getKey() - n.taille; i--) {
                                        if (choixN[0] == i && choixN[1] == (int) n.coordonnes.getValue()) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int j = (int) n.coordonnes.getValue(); j > (int) n.coordonnes.getValue() - (n.taille * 2); j -= 2) {
                                        if (choixN[1] == j && choixN[0] == (int) n.coordonnes.getKey()) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "d":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'D') {
                                if (n.sens == 0) {
                                    for (int i = (int) n.coordonnes.getKey(); i > (int) n.coordonnes.getKey() - n.taille; i--) {
                                        if (choixN[0] == i && choixN[1] == (int) n.coordonnes.getValue()) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int j = (int) n.coordonnes.getValue(); j > (int) n.coordonnes.getValue() - (n.taille * 2); j -= 2) {
                                        if (choixN[1] == j && choixN[0] == (int) n.coordonnes.getKey()) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "s":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'S') {
                                if (n.sens == 0) {
                                    for (int i = (int) n.coordonnes.getKey(); i > (int) n.coordonnes.getKey() - n.taille; i--) {
                                        if (choixN[0] == i && choixN[1] == (int) n.coordonnes.getValue()) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int j = (int) n.coordonnes.getValue(); j > (int) n.coordonnes.getValue() - (n.taille * 2); j -= 2) {
                                        if (choixN[1] == j && choixN[0] == (int) n.coordonnes.getKey()) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            p2.lien(grilleIA);
            p1.afficherPlateau();
            System.out.println("");
            p2.afficherPlateau();
            System.out.println("");
            p3.checkNavireCoule();
            System.out.println("L'IA est entrain de jouer...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            IA(p1, p3);
        }
        if (p1.verifierPartieFinie() == true)
            System.out.println("VOUS AVEZ GAGNE !");
        if (p3.verifierPartieFinie() == true)
            System.out.println("L'IA A GAGNE !");
    }

    /**
     * Methode permettant d'afficher le plateau de jeu à partir des plateaux des
     * deux joueurs et d'une arraylist contenant les positions des cases touchés
     * nécessaire à l'affichage de l'apercu
     *
     * @param p1 le plateau du joueur
     * @param p2 le plateau de l'IA
     * @param listeX la liste des positions des X dans l'apercu, soit les
     * coordonnées des cases des navires touchés
     */
    public static void afficherJeuCharge(Plateau p1, ArrayList listeX, Plateau p3) {
        Plateau p2 = new Plateau();
        int a;
        String c;
        Scanner s = new Scanner(System.in);
        String option = "";
        int[] choixN = new int[2];
        int[] choixT = new int[2];

        String[][] grilleJoueur = new String[32][32];
        String[][] grilleApercuIA = new String[32][32];
        String[][] grilleIA = new String[32][32];

        grilleJoueur = p1.initGrille();
        //p1.initPlacementNavires();
        p1.placementNaviresCharges();
        grilleApercuIA = p2.initGrille();
        Plateau.remplirCasesX(p2, listeX);

        p1.afficherPlateau();

        p2.lien(grilleIA);
        System.out.println("\n");
        p2.afficherPlateau();
        System.out.println("\n");
        grilleIA = p3.initGrille();
        p3.placementNaviresCharges();
        Plateau.remplirCasesX(p3, listeX);

        while (p1.verifierPartieFinie() == false && p3.verifierPartieFinie() == false) { //boucle de jeu
            p2.lien(grilleIA);
            p1.afficherPlateau();
            System.out.println("");
            p2.afficherPlateau();
            System.out.println("");
            p3.afficherPlateau();
            System.out.println("");
            p3.checkNavireCoule();
            choixN = selectionNavire(s, p1, p2, p3);
            option = selectionOption(s, choixN, p1);
            if (option.equals("t")) {
                choixT = selectionTir(s);
                System.out.println("");
                switch (p1.grille[choixN[0]][choixN[1]]) {
                    case "C":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "c":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C' && n.nom.charAt(1) == 'r') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "d":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'D') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                    case "s":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'S') {
                                n.tirer(choixT[0], choixT[1], grilleIA);
                                break;
                            }
                        }
                        break;
                }
            } else { //choix du deplacement
                switch (p1.grille[choixN[0]][choixN[1]]) {
                    case "C":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C' && n.nom.charAt(1) == 'u') {
                                selectionDeplacement(s, n, p1);
                                break;
                            }
                        }
                        break;
                    case "c":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'C' && n.nom.charAt(1) == 'r') {
                                if (n.sens == 0) {
                                    for (int i = Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString()); i > Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString()) - n.taille; i--) {
                                        if (choixN[0] == i && choixN[1] == Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString())) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int j = Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString()); j > Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString()) - (n.taille * 2); j -= 2) {
                                        if (choixN[1] == j && choixN[0] == Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString())) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "d":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'D') {
                                if (n.sens == 0) {
                                    for (int i = Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString()); i > Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString()) - n.taille; i--) {
                                        if (choixN[0] == i && choixN[1] == Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString())) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int j = Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString()); j > Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString()) - (n.taille * 2); j -= 2) {
                                        if (choixN[1] == j && choixN[0] == Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString())) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                    case "s":
                        for (Navire n : p1.navires) {
                            if (n.nom.charAt(0) == 'S') {
                                if (n.sens == 0) {
                                    for (int i = Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString()); i > Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString()) - n.taille; i--) {
                                        if (choixN[0] == i && choixN[1] == Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString())) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                } else {
                                    for (int j = Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString()); j > Integer.parseInt(n.tabPos.get(n.taille - 1).getValue().toString()) - (n.taille * 2); j -= 2) {
                                        if (choixN[1] == j && choixN[0] == Integer.parseInt(n.tabPos.get(n.taille - 1).getKey().toString())) {
                                            selectionDeplacement(s, n, p1);
                                            break;
                                        }
                                    }
                                }
                            }
                        }
                        break;
                }
            }
            p2.lien(grilleIA);
            p1.afficherPlateau();
            System.out.println("");
            p2.afficherPlateau();
            System.out.println("");
            p3.afficherPlateau();
            System.out.println("");
            p3.checkNavireCoule();
            System.out.println("L'IA est entrain de jouer...");
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                System.out.println(e);
            }
            IA(p1, p3);
        }
    }

    /**
     *
     * Affiche le menu du jeu
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
     * Recupere le choix du joueur
     *
     */
    public static void Menu() {
        boolean success = false;
        String chemin = "";
        Plateau essai = new Plateau();
        Scanner choix = new Scanner(System.in);
        Scanner in = new Scanner(System.in);

        String audioFilePath = "C:\\Users\\Benji\\Desktop\\ECE\\java\\bataillenavale\\Son1.wav";
        Audio player = new Audio();

        while (!success) {
            try {
                player.play(audioFilePath);
                afficherMenu();
                int incertionChoix = choix.nextInt();
                switch (incertionChoix) {
                    case 1:
                        System.out.println("");
                        afficherJeu();
                        break;
                    case 2:
                        System.out.println("Choisir le fichier en question :");
                        System.out.println(" ");
                        String nom = in.next();
                        Plateau pJ1 = Plateau.chargerPartie(nom + ".txt");
                        ArrayList<Pair> pApercu = Plateau.chargerApercu(nom + "Apercu.txt");
                        Plateau pIA = Plateau.chargerPartie(nom + "IA.txt");
                        afficherJeuCharge(pJ1, pApercu, pIA);
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
                        System.out.println("    (Carré de 3x3 commençant en haut à gauche de celui-ci)");
                        System.out.println("  ");
                        System.out.println("- Croiseur ");
                        System.out.println("    Taille = 5 cases ; Puissance de tir : 4 cases");
                        System.out.println("    (Carré de 2x2 commençant en haut à gauche de celui-ci)");
                        System.out.println("  ");
                        System.out.println("- Destroyer ");
                        System.out.println("    Taille = 3 cases ; Puissance de tir : 1 case");
                        System.out.println("    Particularité : son premier tir dévoile un  carré  de  4*4  cases  dans  la  grille  adverse");
                        System.out.println("    La zone démarre à partir du  coin  haut  et  gauche.");
                        System.out.println("    Les navires adverses de ce carré ne seront visibles que lors du tour du jeu, soit pendant quelques secondes");
                        System.out.println("  ");
                        System.out.println("- Sous-Marin ");
                        System.out.println("    Taille = 1 case ; Puissance de tir : 1 case");
                        System.out.println("  ");
                        System.out.println("Possibilité de déplacer un de ses navires, mais seulement d'UNE seule case (qu'à l'horizontale ou à la verticale");
                        System.out.println("Le déplacement s'effectuera avec les fléches directives.");
                        System.out.println("Si vous êtes touché ou vous avez un obstacle devant vous, impossible de se déplacer");
                        System.out.println(" ");
                        System.out.println("Vous savez tout ! Maintenant, à vous de jouer !");
                        System.out.println("  ");
                        System.out.println(" Commandes :");
                        System.out.println("- taper le numéro de la ligne");
                        System.out.println("- taper le numéro de la colonne");
                        System.out.println("- choisir entre 't' pour tirer ou 'd' pour déplacer");
                        System.out.println("- taper la ligne et la colonne de l'endroit où tirer");
                        System.out.println(" ");
                        System.out.println("-------------------------------------------------------------------------------------");
                        Menu();
                        break;
                    case 4:
                        System.out.println("À bientôt sur le jeu !");
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
                System.out.println("Erreur dans votre saisie");
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
