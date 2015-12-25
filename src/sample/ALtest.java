package sample;

import javafx.application.Application;
import javafx.stage.Stage;

import java.util.ArrayList;

//testowanie arraylist
public class ALtest extends Application {
    ArrayList<Integer> arrayList = new ArrayList<Integer>();

    @Override
    public void start(Stage primaryStage) throws Exception {
        arrayList.add(10);
        arrayList.add(20);
        arrayList.add(3);
        arrayList.add(6);
        arrayList.add(0,6);
        arrayList.add(0,7);

        System.out.println(arrayList);
    }
}
