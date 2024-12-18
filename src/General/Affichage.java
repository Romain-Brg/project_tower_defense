package General;
import java.awt.Color;

import libraries.StdDraw;

public class Affichage {

    public Affichage(Carte carte){
        affichageCanvas(carte);
    }

    public static void affichageCases(Carte carte, int x, int y){
        StdDraw.enableDoubleBuffering();
        for(Case tile : carte.getCarte()){
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.rectangle(tile.getCentreX()+x, tile.getCentreY()-y, tile.getHalfWidth(), tile.getHalfHeight());
            StdDraw.setPenColor(tile.getCouleur());
            StdDraw.filledRectangle(tile.getCentreX()+x, tile.getCentreY()-y, tile.getHalfWidth(), tile.getHalfHeight()); 
        }
    }


    public void affichageCanvas(Carte carte){
        StdDraw.enableDoubleBuffering();
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(350, 350, 350, 350); //Affichage Carte
        StdDraw.rectangle(856, 688, 144, 12); //Affichage Niveau
        StdDraw.rectangle(856, 641, 144, 25); //Affichage Joueur
        StdDraw.rectangle(856, 303, 144, 303); //Affichage Magasin
        affichageCases(carte, 0, 0);
    }

    
    public void redrawCarte(Carte carte) {
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.rectangle(350, 350, 350, 350); //Affichage Carte
        StdDraw.rectangle(856, 688, 144, 12); //Affichage Niveau
        StdDraw.rectangle(856, 641, 144, 25); //Affichage Joueur
        StdDraw.rectangle(856, 303, 144, 303); //Affichage Magasin
        for (Case tile : carte.getCarte()) {
            // Dessiner le contour de la case
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.rectangle(tile.getCentreX(), tile.getCentreY(), tile.getHalfWidth(), tile.getHalfHeight());
            // Dessiner la case avec sa couleur actuelle
            StdDraw.setPenColor(tile.getCouleur());
            StdDraw.filledRectangle(tile.getCentreX(), tile.getCentreY(), tile.getHalfWidth(), tile.getHalfHeight());
        }
        //StdDraw.show(); // Afficher les changements
    }


    public static void affichageEnnemi(double posX, double posY, int radius, Color couleur){
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(posX, posY, radius);
    }

    public static void affichageTour(double posX, double posY, Color couleur){
        StdDraw.enableDoubleBuffering();
        StdDraw.setPenColor(couleur);
        StdDraw.filledCircle(posX, posY, 10);
    }


    // public void mouseOverTours(Carte carte){
    //     double sourisX = StdDraw.mouseX();
    //     double sourisY = StdDraw.mouseY();
    //     for (Case tile : carte.getCarte()) {
    //         if(sourisX<= tile.getCoordX()[1] && sourisX>=tile.getCoordX()[0] && sourisY<=tile.getCoordY()[1] && sourisY>=tile.getCoordY()[0] && tile.getType()=='C'){
    //             tile.setColor(Color.MAGENTA);
    //         }
    //         else{
    //             tile.setColor(tile.getColorIni());
    //         }
    //     }
    //     redrawCarte(carte);
    // }


    
    
}
