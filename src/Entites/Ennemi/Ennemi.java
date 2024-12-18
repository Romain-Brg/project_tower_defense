package Entites.Ennemi;

import java.awt.Color;
import java.util.LinkedList;
import Entites.Entite;
import General.Affichage;
import General.Case;

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
        
        Affichage.affichageEnnemi(posX, posY, radius, couleur);
    }

    public double getPositionChemin() {
        return (int) this.positionChemin;
    }

    

    
}
