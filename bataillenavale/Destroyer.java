package bataillenavale;

/**
*
* @author Benjamin ROBSON, Kim ROJAS
* 
*/

public class Destroyer extends Navire {
protected int fusee;
    
    public Destroyer (){
        this.nom = "Destroyer";
        this.sens = 0;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
    }
    
    public Destroyer (ArrayList<Pair> tabPos){
        this.nom = "Destroyer";
        this.sens = 0;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne])&& !"s".equals(tab[ligne][colonne]) && tab[ligne][colonne] != null)
            tab[ligne][colonne] = "X";
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        posX = ligne;
        posY = colonne;
    }
    
}
