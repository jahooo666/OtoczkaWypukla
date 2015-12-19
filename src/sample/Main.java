package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;

public class Main extends Application {
    ArrayList<Point2D> punkty = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception{
        primaryStage.setTitle("Otoczka wypukła");
        Group root = new Group();
        Canvas canvas = new Canvas(700, 500);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dodajPunkty();
        rysujPunkty(punkty,gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public void findLowestPoint(){
       //znajduje najniższy punkt
        Point2D lowest= punkty.get(0);
        for (Point2D punkt: punkty) {
            if((punkt.getY()>lowest.getY())
                    ||((punkt.getY()==lowest.getY())&&(punkt.getY()==lowest.getY()))){
                lowest = punkt;
            }
        }
    }


    public void dodajPunkty(){
        this.punkty = new PointsFromFileReader().read("dane.txt");
    }

    public void rysujPunkty(ArrayList<Point2D> punkty,GraphicsContext gc){
        for(Point2D punkt : punkty){
            gc.fillOval(punkt.getX(),punkt.getY(),3,3);
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
