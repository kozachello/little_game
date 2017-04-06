package com.home;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;

/**
 * Created by KO3AK on 06-04-2017.
 */

public class PlayArea extends GridPane {

    int i=0, j=0;

    public PlayArea() {
    }

    public static GridPane add(Button b, int i, int j) {
        GridPane gp = new GridPane();
        gp.add(b, i, j);
        return gp;
    }

}
