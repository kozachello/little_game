package com.home;

/**
 * Created by Козак on 05.04.2017.
 */

import javafx.application.Application;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

public class FourInARow extends Application {

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("MyApp");
        BorderPane root = new BorderPane();
        VBox vbox = new VBox(10);

        HBox hbox1 = new HBox(10);
        HBox hbox2 = new HBox(10);
        HBox hbox3 = new HBox(10);
        HBox hbox4 = new HBox(10);
        HBox hbox5 = new HBox(10);
        HBox hbox6 = new HBox(10);
        HBox hbox7 = new HBox(10);
        hbox1.setAlignment(Pos.CENTER);
        hbox2.setAlignment(Pos.CENTER);
        hbox3.setAlignment(Pos.CENTER);
        hbox4.setAlignment(Pos.CENTER);
        hbox5.setAlignment(Pos.CENTER);
        hbox6.setAlignment(Pos.CENTER);
        hbox7.setAlignment(Pos.CENTER);

        vbox.setAlignment(Pos.BASELINE_CENTER);

        String buttonNumbers[] = {"1","2","3","4","5","6","7"};
        Button buttons[] = new Button[7];
        int i=0;
        while(i<buttons.length && i<buttonNumbers.length) {
            for (Button b : buttons) {
                b = addButton(buttonNumbers[i]);
                hbox1.getChildren().add(b);
                i++;
            }
        }

        vbox.getChildren().addAll();

        root.setTop(hbox1);

        Scene scene = new Scene(root,400,300);
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    private Button addButton(String name) {
        Button btn = new Button(name);
        btn.setPrefSize(100,20);
        return btn;
    }

    public static void main(String args[]) {
        launch(args);
    }
}
