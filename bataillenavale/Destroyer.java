package bataillenavale;
public class Destroyer extends Navire{
    protected int fusee;
    
    public Destroyer (int positionLigne, int positionColonne){
        this.sens = 0;
        this.taille = 3;
        this.puissance = 1;
        this.fusee = 1;
        this.posX = positionLigne;
        this.posY = positionColonne;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne])&& !"s".equals(tab[ligne][colonne]))
            tab[ligne][colonne] = "X";
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        posX = ligne;
        posY = colonne;
    }
    
}