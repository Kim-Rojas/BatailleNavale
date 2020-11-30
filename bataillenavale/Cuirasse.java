package bataillenavale;

public class Cuirasse extends Navire{

    public Cuirasse(int positionLigne,int positionColonne){
        this.sens = 0;
        this.taille = 7;
        this.puissance = 9;
        this.posX = positionLigne;
        this.posY = positionColonne;
    }

    @Override
    public void tirer(int ligne, int colonne, String[][] tab) {
        for (int i = ligne; i < ligne+3; i++){
            for (int j = colonne; j< colonne+6; j+=2){
                if (!" ".equals(tab[i][j]) && !"s".equals(tab[i][j]) && tab[i][j] != null)
                    tab[i][j] = "X";
            }
        }
    }

    @Override
    public void deplacer(int ligne, int colonne) {
        posX = ligne;
        posY = colonne;
    }
}