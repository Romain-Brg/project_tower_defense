package Entites.Ennemi;

import java.awt.Color;
import General.Case;

public class Minion extends Ennemi {

    public Minion(double posX, double posY, Case positionCase, double positionChemin){
        super(1, 1, 10, "Minion", 3, 0, 0, "NONE", posX, posY, positionCase, positionChemin, 5, Color.BLUE);
        affichage();
    }

}
