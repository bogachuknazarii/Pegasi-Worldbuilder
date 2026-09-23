package com.eightysevenfive.pegasi.ui;

import com.eightysevenfive.pegasi.generators.PerlinGenerator;
import com.eightysevenfive.pegasi.generators.TestDataGenerator;
import javafx.application.Application;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.layout.StackPane;
import javafx.scene.paint.Color;
import javafx.stage.Stage;

import java.util.Random;

public class App extends Application {

    int VHYPERCELLAMT = 10;
    int HHYPERCELLAMT = 10;
    int HYPERCELLSIZE = 10;
    int CELLSIZE = 10;

    private Parent createContent() {

        //Generate Data
        PerlinGenerator generator = new PerlinGenerator(VHYPERCELLAMT, HHYPERCELLAMT, HYPERCELLSIZE);
        generator.generatePerlin();

        StackPane stackPane = createStackPane(generator);

        return stackPane;

    }

    private StackPane createStackPane(PerlinGenerator generator) {

        //Canvas init
        Canvas canvas = new Canvas(1200,800);
        GraphicsContext gc = canvas.getGraphicsContext2D();

        //Draw cells
        gc.setStroke(Color.TRANSPARENT);

        for (int y = 0; y < HHYPERCELLAMT*HYPERCELLSIZE; y++) {

            for (int x = 0; x < VHYPERCELLAMT*HYPERCELLSIZE; x++) {

                int transformedValue = (int) ((generator.values[y][x] / 2 + 0.5)*255);

                gc.setFill(Color.grayRgb(transformedValue));
                gc.fillRect(HYPERCELLSIZE*CELLSIZE + x*CELLSIZE, HYPERCELLSIZE*CELLSIZE + y*CELLSIZE,CELLSIZE,CELLSIZE);

            }

        }

        /*//Draw Hypergrid
        gc.setFill(Color.DARKBLUE);
        gc.setStroke(Color.DARKRED);

        for (int i = 1; i <= HHYPERCELLAMT; i++) {

            for (int j = 1; j <= VHYPERCELLAMT; j++) {

                gc.strokeRect(i*HYPERCELLSIZE*CELLSIZE, j*HYPERCELLSIZE*CELLSIZE,100,100);

            }

        }

        //Draw Hypergradients
        gc.setFill(Color.DARKBLUE);
        gc.setStroke(Color.DARKRED);

        for (int y = 1; y <= HHYPERCELLAMT; y++) {

            for (int x = 1; x <= VHYPERCELLAMT; x++) {

                gc.strokeLine(x*HYPERCELLSIZE*CELLSIZE, y*HYPERCELLSIZE*CELLSIZE,x*HYPERCELLSIZE*CELLSIZE + generator.gradients[y-1][x-1].getX()*25,y*HYPERCELLSIZE*CELLSIZE + generator.gradients[y-1][x-1].getY()*25);

            }

        }*/

        return new StackPane(canvas);

    }

    @Override
    public void start(Stage stage) throws Exception {
        stage.setScene(new Scene(createContent(), 1200, 800));
        stage.show();
    }

     public static void main(String[] args) {
        launch(args);
    }
}
