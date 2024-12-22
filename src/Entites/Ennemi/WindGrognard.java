package Entites.Ennemi;

import java.awt.Color;

import General.Case;

public class WindGrognard extends Ennemi{
    public WindGrognard(double posX, double posY, Case positionCase, double positionChemin ){
        super(2,1,1,"Wind Grognard",7,2,5,"WIND",posX,posY,positionCase,positionChemin,6, Color.LIGHT_GRAY);
    }
}
