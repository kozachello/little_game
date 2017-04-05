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

    final int size=800, dot_size=40, right=2, down=3, left=4;
    int speeddelay=500, length=1, route=3, ball_x, ball_y;
    Canvas canvas;
    GraphicsContext gc;
    int x[] = new int[size*size];
    int y[] = new int[size*size];
    Thread game;
    boolean ballIsLocated = false;

    @Override
    public void start(Stage primaryStage) throws Exception {
        //Button button = new Button("STOP");
        //ImageView image = new ImageView("home/anonymous.jpg");
        StackPane root = new StackPane();
        canvas = new Canvas(size,size);
        gc = canvas.getGraphicsContext2D();
        canvas.setFocusTraversable(true);
        //root.getChildren().add(image);
        root.getChildren().add(canvas);
        startGame();

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
        if(!ballIsLocated) {
            //gc.setFill(Paint.valueOf("green"));
            //gc.fillOval(dot_size, dot_size);
            gc.setFill(Paint.valueOf("red"));
            gc.fillOval(x[0], y[0], dot_size, dot_size);
            //gc.setFill(Paint.valueOf("orange"));

            for(int i=0; i<length; i++) { // must check here!!!
                gc.fillOval(x[i], y[i], dot_size, dot_size);
            }

        } else {
            gc.setFill(Paint.valueOf("black"));
            gc.fillText("Game Over", size/2-50, size/2-15);
            game.stop();
        }
    }

    private void startGame() {
        length = 1;
        for(int i=0; i<length; i++) { // must check here!!!
            x[i] = 300-i*dot_size;
            y[i] = 30;
        }
        //locateBall();
        game = new Thread(new Runnable() {

            @Override
            public void run() {
                while(true){
                    if(!ballIsLocated){
                        checkBall();
                        checkCollision();
                        move();
                    }
                    draw(gc);
                    try{
                        Thread.sleep(speeddelay);
                    } catch(Exception e){
                        e.printStackTrace();
                    }
                }
            }

        });

        game.start();
    }

    public static void main(String[] args) {
        launch(args);
    }

    private void locateBall() {
        ball_x = x[0];
        ball_y = y[0];
        ballIsLocated = true;
    }

    private void checkBall() {
        if(y[0]==490) {
            //length++;
            locateBall();
        }
    }

    private void checkCollision() {
        if(x[0] == size) ballIsLocated = true;
        if(y[0] == size) ballIsLocated = true;
        //if(x[0] < 0) ballIsLocated = true;
        //if(y[0] < 0) ballIsLocated = true;
        //for(int i=3; i<length; i++)
            //if(x[0]==x[i] && y[0]==y[i]) ballIsLocated = true;
    }

    private void move() {
        for(int i=length; i>0; i--) { // must check here!!!
            x[i] = x[i-1];
            y[i] = y[i-1];
        }

        if(route==down) y[0]+=dot_size;
        if(route==right) {
            x[0]+=dot_size;
            y[0]+=dot_size;
        }
        if(route==left) {
            x[0]-=dot_size;
            y[0]+=dot_size;
        }
    }

}
