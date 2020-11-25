package bataillenavale;

public class SousMarin extends Navire{
    
    public SousMarin(int positionLigne, int positionColonne){
        this.sens = 0;
        this.taille = 1;
        this.puissance = 1;
        this.posX = positionLigne;
        this.posY = positionColonne;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        if (!" ".equals(tab[ligne][colonne]))
            tab[ligne][colonne] = "X";
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        this.posX = ligne;
        this.posY = colonne;
    }
    
}