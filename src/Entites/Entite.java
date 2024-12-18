package Entites;

import libraries.StdDraw;

public abstract class Entite{
    
    protected int pv;
    protected String nom;
    protected int atk;
    protected double atkSpeed;
    protected double range;
    protected String element;
    protected double posX;
    protected double posY;


    public Entite(int pv, String nom, int atk, double atkSpeed, double range, String element, double posX, double posY) {
        this.pv = pv;
        this.nom = nom;
        this.atk = atk;
        this.atkSpeed = atkSpeed;
        this.range = range;
        this.element = element;
        this.posX = posX;
        this.posY = posY;
    }

    public int getPv() {
        return pv;
    }

    public String getNom() {
        return nom;
    }

    public int getAtk() {
        return atk;
    }

    public double getAtkSpeed() {
        return atkSpeed;
    }

    public double getRange() {
        return range;
    }

    public String getElement() {
        return element;
    }

    public void setPv(int pv) {
        this.pv = pv;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setAtk(int atk) {
        this.atk = atk;
    }

    public void setAtkSpeed(double atkSpeed) {
        this.atkSpeed = atkSpeed;
    }

    public void setRange(double range) {
        this.range = range;
    }

    public void setElement(String element) {
        this.element = element;
    }

    public void setPosX(double posX) {
        this.posX = posX;
    }

    public void setPosY(double posY) {
        this.posY = posY;
    }

    public void affichage(){
        StdDraw.circle(posX, posY, 10);
    }

    

    

}
