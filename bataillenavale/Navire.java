package bataillenavale;

import java.awt.event.KeyEvent;
import java.util.ArrayList;
import javafx.util.Pair;

/**
* Classe abstraite Navire
* Contient les attributs nécessaires pour décrire un navire et les méthodes à implémenter
* 
*/

public abstract class Navire {
    protected String nom;
    protected int sens;
    protected int taille;
    protected int puissance;
    protected int posX;
    protected int posY;
    protected Pair coordonnes;
    protected int indice;
    protected ArrayList<Pair> tabPos = new ArrayList<>();
    
    /**
     * permet à un navire de tirer sur une case de l'adversaire choisie
     * 
     * @param ligne
     * @param colonne
     * @param tab
     */
    public abstract void tirer(int ligne, int colonne, String[][] tab);
    
    
    /**
     * déplacement du croiseur dans une direction donnée
     * 
     * @param e
     * @param direction
     * @param ligne
     * @param colonne 
     */
    public int deplacer(char direction, String[][] tab, int joueur){
        int tmpx = Integer.parseInt(this.tabPos.get(this.taille-1).getKey().toString());
        int tmpy = Integer.parseInt(this.tabPos.get(this.taille-1).getValue().toString());
        if (this.sens == 0){ // déplacement vertical
            for (int j = tmpx; j > tmpx-this.taille; j--){
                if (tab[j][tmpy].equals("X")){
                    if (joueur == 1)
                        System.out.println("Navire endommagé, déplacemennt impossible !");
                    return 1;
                }
            }
            switch (direction){
                case 'N':
                    if (tmpx-this.taille == 0){
                        if (joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else if (tmpx-this.taille > 0 && !" ".equals(tab[tmpx-this.taille][tmpy])){
                        if (joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else{
                        this.coordonnes = new Pair(tmpx-1, tmpy);
                        tab[tmpx][tmpy] = " ";
                        switch (this.nom){
                            case "Cuirasse":
                                tab[tmpx-this.taille][tmpy] = "C"; 
                                break;
                            case "Croiseur":
                                tab[tmpx-this.taille][tmpy] = "c"; 
                                break;
                            case "Destroyer":
                                tab[tmpx-this.taille][tmpy] = "d"; 
                                break;
                            case "Sous-Marin":
                                tab[tmpx-this.taille][tmpy] = "s";
                             break;
                        }
                    }
                    break;
                case 'S':
                    if (tmpx == 15){
                        if(joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else if (tmpx < 15 &&  !" ".equals(tab[tmpx+1][tmpy])){
                        if(joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else{
                        this.coordonnes = new Pair(tmpx+1, tmpy);
                        tab[tmpx-(this.taille-1)][tmpy] = " ";
                        switch (this.nom){
                            case "Cuirasse":
                                tab[tmpx+1][tmpy] = "C"; 
                                break;
                            case "Croiseur":
                                tab[tmpx+1][tmpy] = "c"; 
                                break;
                            case "Destroyer":
                                tab[tmpx+1][tmpy] = "d"; 
                                break;
                            case "Sous-Marin":
                                tab[tmpx+1][tmpy] = "s";
                                break;
                        }
                    }
                    break;
            }
        }
        else{ // déplacement horizontal
            for (int j = tmpy; j > tmpy-(this.taille*2); j-=2){
                if (tab[tmpx][j].equals("X")){
                    if (joueur == 1)
                        System.out.println("Navire endommagé, déplacement impossible !");
                    return 1;
                }
            }
            switch (direction){
                case 'W':
                    if (tmpy-(this.taille*2) == 0){
                        if (joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else if (tmpy-(this.taille*2) > 0 && !" ".equals(tab[tmpx][tmpy-(this.taille*2)])){
                        if (joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else{
                        this.coordonnes = new Pair(tmpx, tmpy-2);
                        tab[tmpx][tmpy] = " ";
                        switch (this.nom){
                            case "Cuirasse":
                                tab[tmpx][tmpy-(this.taille*2)] = "C"; 
                                break;
                            case "Croiseur":
                                tab[tmpx][tmpy-(this.taille*2)] = "c"; 
                                break;
                            case "Destroyer":
                                tab[tmpx][tmpy-(this.taille*2)] = "d"; 
                                break;
                            case "Sous-Marin":
                                tab[tmpx][tmpy-(this.taille*2)] = "s"; 
                                break;
                        }
                    }
                    break;
                case 'E':
                    if (tmpy == 30){
                        if(joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else if (tmpy < 30 && !" ".equals(tab[tmpx][tmpy+2])){
                        if(joueur == 1)
                            System.out.println("Déplacement impossible !");
                        return 2;
                    }
                    else{
                        this.coordonnes = new Pair(tmpx, tmpy+2);
                        tab[tmpx][tmpy-((this.taille*2)-2)] = " ";
                        switch (this.nom){
                            case "Cuirasse":
                                tab[tmpx][tmpy+2] = "C";
                                break;
                            case "Croiseur":
                                tab[tmpx][tmpy+2] = "c";
                                break;
                            case "Destroyer":
                                tab[tmpx][tmpy+2] = "d";
                                break;
                            case "Sous-Marin":
                                tab[tmpx][tmpy+2] = "s";
                                break;
                        }
                    }
                    break;
            }
        }
        return 0;
    }
    
    public boolean estTouche(String[][]tab, int joueur){
        int tmpx = (int)this.coordonnes.getKey();
        int tmpy = (int)this.coordonnes.getValue();
        if (this.sens == 0){
            for (int j = (int)tmpx; j > tmpx-this.taille; j--){
                if (tab[j][tmpy].equals("X")){
                    if (joueur == 1)
                        System.out.println("Navire endommagé, déplacemennt impossible !");
                    return true;
                }
            }
        }
        else{
            for (int j = tmpy; j > tmpy-(this.taille*2); j-=2){
                if (tab[tmpx][j].equals("X")){
                    if (joueur == 1)
                        System.out.println("Navire endommagé, déplacement impossible !");
                    return true;
                }
            }
        }
        return false;
    }
}