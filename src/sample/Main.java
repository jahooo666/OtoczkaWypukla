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
        Canvas canvas = new Canvas(300, 250);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dodajPunkty();
        rysujPunkty(punkty,gc);
        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
    }


    public void dodajPunkty(){
        Point2D p1 = new Point2D(70,30);
        Point2D p2 = new Point2D(100,20);
        Point2D p3 = new Point2D(10,200);
        Point2D p4 = new Point2D(260,80);
        this.punkty.add(p1);
        this.punkty.add(p2);
        this.punkty.add(p3);
        this.punkty.add(p4);
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
