package Entites.Tours;

import java.awt.Color;

import Entites.Entite;
import Entites.Ennemi.Ennemi;
import libraries.StdDraw;

public class Tours extends Entite {
     
    protected int cost;
    protected Color couleur;


    public Tours(int cost,int pv, String nom, int atk, double atkSpeed, double range, String element, double posX, double posY, Color color){
        super(pv, nom, atk, atkSpeed, range, element,  posX, posY);
        this.cost = cost;
        this.couleur = color;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public void affichageTour(double posX, double posY){
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(this.couleur);
        StdDraw.filledCircle(posX, posY, 10);
    }

    public boolean detectionEnnemi(Ennemi ennemi){
        return ennemi.getPosX() <= this.posX + this.range &&  ennemi.getPosX() >= this.posX - this.range && ennemi.getPosY() <= this.posX + this.range && ennemi.getPosY() >= this.posX - this.range;
    }

    public void hitEnnemi(Ennemi ennemi){
        if(ennemi.getPv() <= 0){
            return;
        }
        else{
            ennemi.setPv(ennemi.getPv()-this.atk);
        }

    }

    
    


}
