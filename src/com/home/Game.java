package com.home;

import javafx.application.Application;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Paint;
import javafx.stage.Stage;

/**
 * Created by Козак on 04.04.2017.
 */

public class Game extends Application {

    final int size=500, dot_size=20, right=2, down=3, left=4;
    int speed=250, length=3, route=3;
    Canvas canvas;
    GraphicsContext gc;
    int x[] = new int[size*size];
    int y[] = new int[size*size];
    Thread game;
    boolean lost = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        StackPane root = new StackPane();
        canvas = new Canvas(size,size);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        root.getChildren().add(canvas);
        //startGame();

        canvas.setOnMouseClicked(new EventHandler<MouseEvent>() {
            @Override
            public void handle(MouseEvent event) {

            }
        });

        canvas.setOnKeyPressed(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent e) {
                KeyCode key = e.getCode();
                //if(key.equals(KeyCode.UP)) route = up;
                if(key.equals(KeyCode.DOWN)) route = down;
                if(key.equals(KeyCode.LEFT)) route = left;
                if(key.equals(KeyCode.RIGHT)) route = right;
                // В этом участке кода просто добавляем проверки на dir != down (в случае, если KeyCode.UP) и так далее.
            }
        });

        Scene scene = new Scene(root, size, size);
        primaryStage.setTitle("MyGame");
        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void draw(GraphicsContext gc) {
        gc.clearRect(0, 0, size, size);
        if(!lost) {
            //gc.setFill(Paint.valueOf("green"));
            //gc.fillOval(dot_size, dot_size);
            gc.setFill(Paint.valueOf("red"));
            gc.fillOval(x[0], y[0], dot_size, dot_size);
            gc.setFill(Paint.valueOf("orange"));

            for(int i=1; i<length; i++) {
                gc.fillOval(x[i], y[i], dot_size, dot_size);
            }

        } else {
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("Game Over", size/2-50, size/2-15);
            game.stop();
        }
    }

    private void startGame() {
        length = 3;
        for(int i=0; i<length; i++){
            x[i] = 250-i*dot_size;
            y[i] = 30;
        }
        //locateFood();
        game = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    if(!lost){
                        //checkFood();
                        //checkCollision();
                        move();
                    }
                    draw(gc);
                    try{
                        Thread.sleep(speed);
                    } catch(Exception e){};
                }
            }

        });

        game.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void move() {
        for(int i=length-1; i>0; i--) {
            x[i] = x[i-1];
            y[i] = y[i-1];
        }
        //if(route==up) y[0]-=dot_size;
        if(route==down) y[0]+=dot_size;
        if(route==right) x[0]+=dot_size;
        if(route==left) x[0]-=dot_size;
    }

}
