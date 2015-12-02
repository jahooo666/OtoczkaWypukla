package sample;

import javafx.geometry.Point2D;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.Scanner;

public class PointsFromFileReader {
    ArrayList<Point2D> punkty;

    public ArrayList<Point2D> read(String file){
        punkty = new ArrayList<>();
        try {
            Scanner odczyt = new Scanner(new File(file));
            while(odczyt.hasNext()){
                int x = odczyt.nextInt();
                int y = odczyt.nextInt();
                punkty.add(new Point2D(x,y));

            }

        } catch (Exception e) {
            e.printStackTrace();
        }
        return punkty;
    }
}
