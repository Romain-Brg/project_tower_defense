package Entites.Ennemi;

import java.awt.Color;
import java.util.LinkedList;
import Entites.Entite;
import General.Affichage;
import General.Case;
import libraries.StdDraw;

public abstract class Ennemi extends Entite{

    private double speedMove;
    private int reward;
    private double positionChemin;
    private int radius;
    private Color couleur;
    private Case positionCase;


    public Ennemi(double speedMove, int reward,int pv, String nom, int atk, double atkSpeed, double range, String element, double posX, double posY, Case positionCase, double positionChemin, int radius, Color couleur){
        super(pv, nom, atk, atkSpeed, range, element, posX, posY);
        this.speedMove = speedMove;
        this.reward = reward;
        this.positionChemin = positionChemin;
        this.radius = radius;
        this.couleur = couleur;
        this.positionCase = positionCase;
    }

    public double getSpeedMove() {
        return speedMove;
    }

    public int getReward() {
        return reward;
    }

    public Case getPositionCase() {
        return positionCase;
    }

    public void setPositionCase(Case positionCase) {
        this.positionCase = positionCase;
    }

    public void setSpeedMove(double speedMove) {
        this.speedMove = speedMove;
    }

    public void setReward(int reward) {
        this.reward = reward;
    }

    public void affichageEnnemi(double posX, double posY){
        double doublePV = (double) this.pv;
        double x_PV = ((10-doublePV)/10);
        double halfWidth_PV = (doublePV/10);



        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(posX, posY, this.radius);


        StdDraw.setPenColor(Color.BLACK);

        StdDraw.rectangle(posX, posY+10, 15, 4);
        StdDraw.setPenColor(Color.GREEN);
        StdDraw.filledRectangle(posX-(15*x_PV), posY+10,(15*halfWidth_PV) , 4);
    }

    public void update(double deltaTimeSec, LinkedList<Case> chemin){
        int positionCheminInt = (int) this.positionChemin;

        if (positionCheminInt+1 >= chemin.size()){
            return;
        }

        Case actuelle = chemin.get(positionCheminInt);
        Case next = chemin.get(positionCheminInt+1);

        if(next.getCentreX() - actuelle.getCentreX() != 0){
            this.posX += ((next.getCentreX() - actuelle.getCentreX()) * this.speedMove * deltaTimeSec)/2;
            this.positionChemin += (Math.abs(((next.getCentreX() - actuelle.getCentreX()) * this.speedMove * deltaTimeSec)/(next.getCentreX() - actuelle.getCentreX())))/2;
        }
        else if (next.getCentreY() - actuelle.getCentreY() != 0){
            this.posY += ((next.getCentreY() - actuelle.getCentreY()) * this.speedMove * deltaTimeSec)/2;
            this.positionChemin += (Math.abs(((next.getCentreY() - actuelle.getCentreY()) * this.speedMove * deltaTimeSec)/(next.getCentreY() - actuelle.getCentreY())))/2;
        }
        
        affichageEnnemi(posX, posY);
    }

    public double getPositionChemin() {
        return (int) this.positionChemin;
    }

    

    
}
