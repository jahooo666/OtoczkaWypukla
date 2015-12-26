package sample;

import javafx.application.Application;
import javafx.geometry.Point2D;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.stage.Stage;

import java.util.ArrayList;
import java.util.Stack;

import static sample.Rysowanie.rysujOtoczke;
import static sample.Rysowanie.rysujPunktyPro;
import static sample.ZnajdowanieOtoczki.znajdzOtoczke;

public class Main extends Application {
    ArrayList<Point2D> punkty = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Otoczka wypukła");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        dodajPunkty();
        Stack<Point2D> wierzcholki = znajdzOtoczke(punkty);

        root.getChildren().add(canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();
        rysujPunktyPro(punkty, canvas);
        rysujOtoczke(wierzcholki,canvas);
    }

    public void dodajPunkty() {
        this.punkty = new PointsFromFileReader().read("dane.txt");
    }




    public static void main(String[] args) {
        launch(args);
    }
}
