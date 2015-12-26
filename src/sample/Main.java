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

import static sample.MonteCarlo.monteCarloCalculate;
import static sample.MonteCarlo.stackToArrayList;
import static sample.PointsFromFileReader.readFromFile;
import static sample.Rysowanie.rysujOtoczke;
import static sample.Rysowanie.rysujPunktyPro;
import static sample.TestyDokladnosci.testuj;
import static sample.ZnajdowanieOtoczki.znajdzOtoczke;

public class Main extends Application {
    static ArrayList<Point2D> punkty = new ArrayList<>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Otoczka wypukła");
        Group root = new Group();
        Canvas canvas = new Canvas(1000, 800);
        GraphicsContext gc = canvas.getGraphicsContext2D();
        this.punkty = dodajPunkty("dane.txt");
        Stack<Point2D> wierzcholki = znajdzOtoczke(punkty);
        root.getChildren().add(canvas);
        rysujPunktyPro(punkty, canvas);
        //System.out.printf("Pole figury to: %f\n",monteCarloCalculate(canvas,wierzcholki,10000));
        rysujOtoczke(wierzcholki,canvas);
        primaryStage.setScene(new Scene(root));
        primaryStage.show();

        //testuj(canvas);

        //ArrayList<Point2D> otoczka = stackToArrayList(wierzcholki);

    }

    public static ArrayList<Point2D> dodajPunkty(String fileName) {
        ArrayList<Point2D> punkty = readFromFile(fileName);
        return punkty;
    }

    public static void main(String[] args) {
        launch(args);
    }
}
