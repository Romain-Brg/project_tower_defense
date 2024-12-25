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
        double x_coeur = 960; // Coordonnée X du centre du cœur
        double x_money = 740; // Coordonnée X de l'argent
        double y = 641; // Coordonnée Y du centre du cœur
        double size = 20; // Taille du cœur (rayon des cercles)

        StdDraw.setPenColor(StdDraw.RED);
        // Dessiner les deux cercles supérieurs
        StdDraw.filledCircle(x_coeur - size / 2, y + size / 2, size / 2); // Cercle gauche
        StdDraw.filledCircle(x_coeur + size / 2, y + size / 2, size / 2); // Cercle droit

        // Dessiner le triangle inférieur
        double[] xCoords = { x_coeur - size, x_coeur, x_coeur + size };
        double[] yCoords = { y + size / 2, y - size, y + size / 2 };
        StdDraw.filledPolygon(xCoords, yCoords);

        // Dessiner le numéro
        StdDraw.text(x_coeur-size - 25, y, Double.toString(pv));

        StdDraw.setPenColor(StdDraw.LIGHT_GRAY);
        StdDraw.filledCircle(x_money, y, size*1.15);

        // Dessiner le centre doré
        StdDraw.setPenColor(StdDraw.YELLOW);
        StdDraw.filledCircle(x_money, y, size*1.15 * 0.7);

        // Dessiner le numéro
        StdDraw.text(x_money-size + 65, y, Double.toString(money));


    }

    
    

}
