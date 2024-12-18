package General;
import java.awt.Color;
import java.awt.Font;
import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.Scanner;
import Entites.Ennemi.Ennemi;
import Entites.Ennemi.Minion;
import libraries.StdDraw;

public class Game {

    private ArrayList<String> level;
    private LinkedList<Ennemi> ennemis;
    private ArrayList<String> wave;
    private double tempsDeJeu;
    private int levelPosition;
    private int wavePosition;
    private boolean waveLoad;
    private Carte carte;
    private Affichage affichage;
    private Case actuel;
    private Case precedent;
    private boolean clique;
    private ArrayList<Case> cliquable;

    public Game(){
        launch();
    }
    
    public void launch (){
        init ();
        long previousTime = System.currentTimeMillis ();
        while ( isGameRunning ()){
            if(!this.waveLoad){
                this.wave = loadFile("resources/waves/" + level.get(levelPosition) + ".wve");
                this.waveLoad = true;
                this.wavePosition = 0;
                this.levelPosition ++;
                System.out.println("j'ai load");
                tempsDeJeu = 0;
            }
            long currentTime = System.currentTimeMillis ();
            double deltaTimeSec = ( double )( currentTime - previousTime ) /1000;
            previousTime = currentTime ;
            update(deltaTimeSec);
            if(clique){
                System.out.println(actuel);
            }
        }
    }


    private boolean isGameRunning (){
        if(this.wavePosition >= this.wave.size() && this.ennemis.size() == 0 && this.levelPosition == this.level.size()){
            System.out.println("jeu arrêté");
            return false;
        }
        else {return true;}
    }


    private void init (){
        String s = choixCarte();
        this.carte = new Carte(s, 700, 700);
        switch (s){
            case "resources//maps//5-8.mtp" : s = "resources//levels//level1.lvl";
            break;
            case "resources//maps//10-3.mtp" : s = "resources//levels//level2.lvl";
            break;
            case "resources//maps//10-10.mtp" : s = "resources//levels//level3.lvl";
            break;
        }
        this.level = loadFile(s);
        this.levelPosition = 1;
        this.affichage = new Affichage(this.carte);
        this.wave = loadFile("resources/waves/" + level.get(levelPosition) + ".wve");
        this.waveLoad = true;
        this.wavePosition = 0;
        this.levelPosition ++;
        this.ennemis = new LinkedList<>();
        this.cliquable = initCliquable();
    }


    private void update ( double deltaTimeSec ){
        
        this.tempsDeJeu += deltaTimeSec;
        
        StdDraw.clear();
        StdDraw.enableDoubleBuffering();
        //this.affichage.mouseOverTours(this.carte);
        mouseSelection(this.cliquable);
        this.affichage.redrawCarte(this.carte);
        for(Ennemi e : this.ennemis){
            e.update(deltaTimeSec, this.carte.getChemin());
        }
        StdDraw.show();
        StdDraw.pause(16);

        if(ennemis.size() > 0 && ennemis.getFirst().getPositionChemin() == this.carte.getChemin().size()-1){
            ennemis.removeFirst();
            // TODO faire perdre les pv du joueur ici !!!
        }

        if(this.wavePosition >= this.wave.size() && this.ennemis.size() == 0){
            this.waveLoad = false;
            return;
        }
        if(this.wavePosition >= this.wave.size()){
            return;
        }
        if(this.tempsDeJeu < Double.parseDouble(this.wave.get(this.wavePosition).substring(0,this.wave.get(this.wavePosition).indexOf("|"))) + 0.1 && this.tempsDeJeu > Double.parseDouble(this.wave.get(this.wavePosition).substring(0,this.wave.get(this.wavePosition).indexOf("|"))) - 0.1 ){
            String ennemi = this.wave.get(this.wavePosition).substring(this.wave.get(this.wavePosition).indexOf("|")+1);
            // switch (ennemi){
            //     case "Minion" : {
            //         Minion e = new Minion(this.carte.getChemin().getFirst().getCentreX(), this.carte.getChemin().getFirst().getCentreY(), this.carte.getChemin().getFirst(), 0); 
            //         this.ennemis.add(e);
            //         break;
            //     }
            // }
            Minion e = new Minion(this.carte.getChemin().getFirst().getCentreX(), this.carte.getChemin().getFirst().getCentreY(), this.carte.getChemin().getFirst(), 0); 
            this.ennemis.add(e);
            this.wavePosition ++;
        }

    }

    public ArrayList<Case> initCliquable(){
        ArrayList<Case> cliquable = new ArrayList<>();
        for(Case tile : this.carte.getCarte()){
            if(tile.getType() == 'C'){cliquable.add(tile);}
        }
        return cliquable;
    }

    public void mouseSelection(ArrayList<Case> cliquable){
        this.precedent = this.actuel;
        double sourisX = StdDraw.mouseX();
        double sourisY = StdDraw.mouseY();
        boolean modif = false;
        for (Case tile : cliquable) {
            if(sourisX<= tile.getCoordX()[1] && 
                sourisX>=tile.getCoordX()[0] && 
                sourisY<=tile.getCoordY()[1] && 
                sourisY>=tile.getCoordY()[0] && 
                StdDraw.isMousePressed()){
                this.clique = true;
                this.actuel = tile;
                modif = true;
            }
            else if(sourisX<= tile.getCoordX()[1] && 
                sourisX>=tile.getCoordX()[0] && 
                sourisY<=tile.getCoordY()[1] && 
                sourisY>=tile.getCoordY()[0]){
                this.clique = false;
                this.actuel = tile;
                modif = true;
            }
        }
        if(this.actuel == null || this.precedent == null){
            return;
        }
        else if (modif && this.precedent != this.actuel){
            this.precedent.setColor(this.precedent.getColorIni());
            this.actuel.setColor(Color.MAGENTA);
        }
        else if (modif && this.precedent == this.actuel){
            this.actuel.setColor(Color.MAGENTA);
        }
        else if (!modif){
            this.precedent.setColor(this.precedent.getColorIni());
            this.actuel.setColor(this.actuel.getColorIni());
        }
        modif = false;
    }

    public String choixCarte(){
        Font font = new Font("Arial", Font.BOLD, 24);
        StdDraw.setFont(font);
        StdDraw.setCanvasSize(1024, 720);
        StdDraw.setXscale(-12, 1012);
        StdDraw.setYscale(-10, 710);
        StdDraw.setPenColor(Color.BLACK);
        StdDraw.text(500, 690, "Choisissez la map sur laquelle jouer !");
        StdDraw.rectangle(500, 600, 475, 70);
        StdDraw.rectangle(175, 250, 150, 250);
        StdDraw.rectangle(500, 250, 150, 250);
        StdDraw.rectangle(825, 250, 150, 250);
        StdDraw.text(500, 600, "Map Aléatoire");
        StdDraw.text(175, 250, "Map 5-8");
        StdDraw.text(500, 250, "Map 10-3");
        StdDraw.text(825, 250, "Map 10-10");
        String random = "";
                switch ((int) (Math.random() * (3 - 1 + 1)) + 1) {
                    case 1 : random = "resources//maps//5-8.mtp";
                    break;
                    case 2 : random = "resources//maps//10-3.mtp";
                    break;
                    case 3 : random = "resources//maps//10-10.mtp";
                    break;
                }
        Carte a = new Carte("resources//maps//5-8.mtp", 500, 300);
        Carte b = new Carte("resources//maps//10-3.mtp", 500, 300);
        Carte c = new Carte("resources//maps//10-10.mtp", 500, 300);
        Carte d = new Carte(random, 140, 950);
        Affichage.affichageCases(d, 25, 30);
        Affichage.affichageCases(a, 25, 200);
        Affichage.affichageCases(b, 350, 200);
        Affichage.affichageCases(c, 675, 200);
        StdDraw.enableDoubleBuffering();
        
        while(true){

            double sourisX = StdDraw.mouseX();
            double sourisY = StdDraw.mouseY();

            if(sourisX<= 975 && sourisX>=25 && sourisY<=670 && sourisY>=530) {
                StdDraw.setPenColor(Color.MAGENTA);
                StdDraw.rectangle(500, 600, 475, 70);
                StdDraw.text(500, 600, "Map Aléatoire");
            } else {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.rectangle(500, 600, 475, 70);
                StdDraw.text(500, 600, "Map Aléatoire");
            }
            if(sourisX<= 325 && sourisX>=25 && sourisY<=500 && sourisY>=5) {
                StdDraw.setPenColor(Color.MAGENTA);
                StdDraw.rectangle(175, 250, 150, 250);
                StdDraw.text(175, 250, "Map 5-8");
            } else {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.rectangle(175, 250, 150, 250);
                StdDraw.text(175, 250, "Map 5-8");
            }
            if(sourisX<= 650 && sourisX>=350 && sourisY<=500 && sourisY>=5) {
                StdDraw.setPenColor(Color.MAGENTA);
                StdDraw.rectangle(500, 250, 150, 250);
                StdDraw.text(500, 250, "Map 10-3");
            } else {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.rectangle(500, 250, 150, 250);
                StdDraw.text(500, 250, "Map 10-3");
            }
            if(sourisX<= 975 && sourisX>=675 && sourisY<=500 && sourisY>=5) {
                StdDraw.setPenColor(Color.MAGENTA);
                StdDraw.rectangle(825, 250, 150, 250);
                StdDraw.text(825, 250, "Map 10-10");
            } else {
                StdDraw.setPenColor(Color.BLACK);
                StdDraw.rectangle(825, 250, 150, 250);
                StdDraw.text(825, 250, "Map 10-10");
            }
            
            if(sourisX<= 975 && sourisX>=25 && sourisY<=670 && sourisY>=530 && StdDraw.isMousePressed()) {
                switch ((int) (Math.random() * (3 - 1 + 1)) + 1) {
                    case 1 : return "resources//maps//5-8.mtp";
                    case 2 : return "resources//maps//10-3.mtp";
                    case 3 : return "resources//maps//10-10.mtp";
                }
            }
            if(sourisX<= 325 && sourisX>=25 && sourisY<=500 && sourisY>=5 && StdDraw.isMousePressed()) {
                return "resources//maps//5-8.mtp";
            }
            if(sourisX<= 650 && sourisX>=350 && sourisY<=500 && sourisY>=5 && StdDraw.isMousePressed()) {
                return "resources//maps//10-3.mtp";
            }
            if(sourisX<= 975 && sourisX>=675 && sourisY<=500 && sourisY>=5 && StdDraw.isMousePressed()) {
                return "resources//maps//10-10.mtp";
            }
            StdDraw.setPenColor(Color.BLACK);
            StdDraw.text(500, 690, "Choisissez la map sur laquelle jouer !");
            StdDraw.show();
            StdDraw.pause(16);
        }
    }

    private ArrayList<String> loadFile(String path){
        try {
            File file = new File(path);
            Scanner myReader = new Scanner(file);
            ArrayList<String> list = new ArrayList<>();
            while (myReader.hasNextLine()) {
                String data = myReader.nextLine();
                list.add(data);
            }
            myReader.close();
            return list;
        } catch (FileNotFoundException e) {
            System.out.println("An error occurred.");
            e.printStackTrace();
            return null;
        }
    }

}
