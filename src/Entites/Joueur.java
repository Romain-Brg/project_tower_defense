package Entites;

import libraries.StdDraw;

public class Joueur {
    private int pv;
    private double money;
    
    
    public Joueur(){
        this.pv=100;
        this.money=50;
    }
    
    public int getPv() {
        return pv;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public double getMoney() {
        return money;
    }

    public void setMoney(double money) {
        this.money = money;
    }

    public void affichageJoueur(){
        // Dessiner le cœur
        double x = 960; // Coordonnée X du centre du cœur
        double y = 641; // Coordonnée Y du centre du cœur
        double size = 20; // Taille du cœur (rayon des cercles)

        StdDraw.setPenColor(StdDraw.RED);
        // Dessiner les deux cercles supérieurs
        StdDraw.filledCircle(x - size / 2, y + size / 2, size / 2); // Cercle gauche
        StdDraw.filledCircle(x + size / 2, y + size / 2, size / 2); // Cercle droit

        // Dessiner le triangle inférieur
        double[] xCoords = { x - size, x, x + size };
        double[] yCoords = { y + size / 2, y - size, y + size / 2 };
        StdDraw.filledPolygon(xCoords, yCoords);

        // Dessiner le numéro
        StdDraw.setPenColor(StdDraw.BLACK);
        StdDraw.text(x, y, Double.toString(pv));
    }

    
    

}
