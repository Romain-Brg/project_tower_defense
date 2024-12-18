package General;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;

public class Carte {

    private ArrayList<Case> carte;
    private LinkedList<Case> chemin;
    int colonne;
    int ligne;


    public Carte(String path){
        this.carte = new ArrayList<>();
        ArrayList<String> list = loadCarte(path);
        this.colonne = list.size();
        this.ligne = list.get(0).length();
        cases(list, this.carte, ligne, colonne);
        this.chemin = chemin(carte);
    }

    public Carte(String path, int hauteur, int longueur){
        this.carte = new ArrayList<>();
        ArrayList<String> list = loadCarte(path);
        this.colonne = list.size();
        this.ligne = list.get(0).length();
        cases2(list, this.carte, ligne, colonne, hauteur, longueur);
        this.chemin = chemin(carte);
    }


    private ArrayList<String> loadCarte(String path){
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

    public void cases(ArrayList<String> list, ArrayList<Case> cases, int m, int n){
        double x = 700/m;
        double y = 700/n;

        for(int i=0; i<n; i++){
            String s = list.get(i);
            for (int j=0; j<m; j++){
                char c = s.charAt(j);
                Case z = new Case(c, i, j, x, y, ligne, colonne);
                cases.add(z);
            }
            
        }
    }

    public void cases2(ArrayList<String> list, ArrayList<Case> cases, int m, int n, int hauteur, int longueur){
        double x = longueur/m;
        double y = hauteur/n;

        for(int i=0; i<n; i++){
            String s = list.get(i);
            for (int j=0; j<m; j++){
                char c = s.charAt(j);
                Case z = new Case(c, i, j, x, y, ligne, colonne);
                cases.add(z);
            }
            
        }
    }

    public LinkedList<Case> chemin(ArrayList<Case> carte){
        LinkedList<Case> chemin = new LinkedList<>();
        for(Case tile : carte){
            if (tile.getType() == 'S'){
                chemin.add(tile);
            }
        }
        int size;
        int posX = 0;
        int posY = 0;
        Case previous;
        Case gauche;
        Case droite;
        Case dessus;
        Case dessous;
        while(chemin.getLast().getType() != 'B'){
            size = chemin.size();
            if(size>1){previous = chemin.get(size-2);}
            else{previous= chemin.getLast();}
            posX = chemin.getLast().getMatriceX();
            posY = chemin.getLast().getMatriceY();
            gauche = carte.get(posX + posY*this.ligne - 1);
            droite = carte.get(posX + posY*this.ligne + 1);
            dessus = carte.get(posX + (posY-1)*this.ligne);
            dessous = carte.get(posX + (posY+1)*this.ligne);

            if((gauche.getType() == 'R' || gauche.getType() == 'B') && gauche != previous){
                chemin.add(gauche);
            }
            if((droite.getType() == 'R' || droite.getType() == 'B') && droite != previous){
                chemin.add(droite);
            }
            if((dessus.getType() == 'R' || dessus.getType() == 'B') && dessus != previous){
                chemin.add(dessus);
            }
            if((dessous.getType() == 'R' || dessous.getType() == 'B') && dessous != previous){
                chemin.add(dessous);
            }
        }
        return chemin;
    }


    public ArrayList<Case> getCarte() {
        return carte;
    }

    public int getLigne() {
        return ligne;
    }

    public int getColonne() {
        return colonne;
    }

    public LinkedList<Case> getChemin() {
        return chemin;
    }

}