package Entites.Tours;

import Entites.Entite;

public class Tours extends Entite {
     
    protected int cost;

    public Tours(int cost,int pv, String nom, int atk, double atkSpeed, double range, String element, double posX, double posY){
        super(pv, nom, atk, atkSpeed, range, element,  posX, posY);
        this.cost = cost;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }
    


}
