package bataillenavale;

/**
*
* @author Benjamin ROBSON, Kim ROJAS
*/

public class Croiseur extends Navire {
    
	public Croiseur(){
        this.nom = "Croiseur";
        this.sens = 0;
        this.taille = 5;
        this.puissance = 4;
    }
    
    public Croiseur(ArrayList<Pair> tabPos){
        this.nom = "Croiseur";
        this.sens = 0;
        this.taille = 5;
        this.puissance = 4;
        this.tabPos = tabPos;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]) && !"s".equals(tab[ligne][colonne]) && tab[ligne][colonne] != null)
            tab[ligne][colonne] = "X";
        if (!" ".equals(tab[ligne][colonne+2]) && !"s".equals(tab[ligne][colonne+2]) && tab[ligne][colonne+2] != null)
            tab[ligne][colonne+1] = "X";
        if (!" ".equals(tab[ligne+1][colonne]) && !"s".equals(tab[ligne+1][colonne]) && tab[ligne+1][colonne] != null)
            tab[ligne+1][colonne] =  "X";
        if  (!" ".equals(tab[ligne+1][colonne+2]) && !"s".equals(tab[ligne+1][colonne+2]) && tab[ligne+1][colonne+2] != null)
            tab[ligne+1][colonne+2] = "X";
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        this.posX = ligne;
        this.posY = colonne;
    }
    
}

