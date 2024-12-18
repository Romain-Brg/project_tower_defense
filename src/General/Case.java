package General;
import java.awt.Color;
import Entites.Ennemi.Ennemi;
import Entites.Tours.Tours;

public class Case {

    private char type; 
    private Color couleur;
    private int matriceX;
    private int matriceY;
    private double centreX;
    private double centreY;
    private double halfWidth;
    private double halfHeight;
    private double[] coordX;
    private double[] coordY;
    private boolean tourAutorisee; // position autorisée tour
    private boolean ennemiAutorisee; // position autorisée ennemi
    private Tours tour;
    private Ennemi ennemi;

   
    public Case(Character typeCase, int i, int j, double x, double y, int tailleLigne, int tailleColonne){
        

        this.centreX = 0+x/2 + x*j;
        this.centreY = 700-y/2 - y*i;
        this.halfWidth = x/2;
        this.halfHeight = y/2;
        this.coordX= new double[] {this.centreX-this.halfWidth, this.centreX+halfWidth};
        this.coordY= new double[] {this.centreY-this.halfHeight, this.centreY+halfHeight};
        this.tour = null;
        this.ennemi = null;
        this.matriceX = j;
        this.matriceY = i;
        
        if(typeCase == 'X'){  
            this.type = 'X';
            this.couleur = new Color(11, 102, 35);
            this.tourAutorisee = false;
            this.ennemiAutorisee = false;
        }
        if(typeCase == 'R'){
            this.type = 'R';
            this.couleur = new Color(198, 178, 128);
            this.tourAutorisee = false;
            this.ennemiAutorisee = true;
        }
        if(typeCase == 'S'){
            this.type = 'S';
            this.couleur = Color.RED;
            this.tourAutorisee = false;
            this.ennemiAutorisee = true;
        }
        if(typeCase == 'C'){
            this.type = 'C';
            this.couleur = Color.LIGHT_GRAY;
            this.tourAutorisee = true;
            this.ennemiAutorisee = false;
        }
        if(typeCase == 'B'){
            this.type = 'B';
            this.couleur = Color.ORANGE;
            this.tourAutorisee =false;
            this.ennemiAutorisee = true;
        }
        
    }

    public void setColor(Color couleur){
        this.couleur = couleur;
    }

    public double[] getCoordX() {
        return coordX;
    }

    public double[] getCoordY() {
        return coordY;
    }

    public char getType() {
        return type;
    }

    public Color getCouleur() {
        return this.couleur;
    }

    public Color getColorIni(){
     switch (this.type){
        case 'X':
            return new Color(11, 102, 35);
        case 'R':
            return new Color(198, 178, 128);
        case 'S':
            return Color.RED;
        case 'C':
            return Color.LIGHT_GRAY;
        case 'B':
            return Color.ORANGE;
        default:
            return Color.BLACK;

        }

    }

    public double getCentreX() {
        return centreX;
    }

    public double getCentreY() {
        return centreY;
    }

    public double getHalfWidth() {
        return halfWidth;
    }

    public double getHalfHeight() {
        return halfHeight;
    }

    public boolean isTourAllowed() {
        return tourAutorisee;
    }

    public boolean isEnnemiAllowed() {
        return ennemiAutorisee;
    }

    public int getMatriceX() {
        return matriceX;
    }

    public int getMatriceY() {
        return matriceY;
    }



    public String toString(){
        String s = "";
        s += "type : " + this.type; 
        s += " couleur : " + this.couleur;
        s += " matriceX : " + this.matriceX;
        s += " matriceY : " + this.matriceY;
        s += " centreX : " + this.centreX;
        s += " centreY : " + this.centreY;
        s += " halfWidth : " + this.halfWidth;
        s += " halfHeight : " + this.halfHeight;
        for(double x : this.coordX){
            s += "[" + x + "]";
        }
        for(double x : this.coordX){
            s += "[" + x + "]";
        }
        s += " tourAutorisee : " + this.tourAutorisee; // position autorisée tour
        s += " ennemiAutorisee : " + this.ennemiAutorisee; // position autorisée ennemi
        s += " tour : " + this.tour;
        s += " ennemi : " + this.ennemi;
        return s;
    }

}