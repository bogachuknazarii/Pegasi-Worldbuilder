package com.eightysevenfive.pegasi.generators;

import com.eightysevenfive.pegasi.mathutils.Vector;

import java.util.Random;

public class PerlinGenerator {

    int verticalCellAmt;
    int horizontalCellAmt;
    int cellSize;

    public Vector[][] gradients;
    public float[][] values;

    public PerlinGenerator(int verticalCellAmt, int horizontalCellAmt, int cellSize) {
        this.verticalCellAmt = verticalCellAmt;
        this.horizontalCellAmt = horizontalCellAmt;
        this.cellSize = cellSize;
    }

    public void generatePerlin(){

        generateGradients();
        generateValues();

    }

    public void generateGradients(){

        Random rand = new Random();
        Vector[][] gradients = new Vector[this.horizontalCellAmt + 1][this.verticalCellAmt + 1];

        for (int i = 0; i < gradients.length; i++) {
            for (int j = 0; j < gradients[0].length; j++) {

                gradients[i][j] = Vector.normalize(new Vector(rand.nextFloat()*2 - 1,  rand.nextFloat()*2 - 1));
                System.out.println("INFO: Generated gradient with values: " + gradients[i][j].getX() + " " + gradients[i][j].getY());

            }

        }

        this.gradients = gradients;

    }

    public void generateValues(){

        values = new float[this.horizontalCellAmt * this.cellSize][this.verticalCellAmt * this.cellSize];

        for (int y = 0; y < verticalCellAmt * cellSize; y++) {

            for (int x = 0; x < horizontalCellAmt * cellSize; x++) {

                float deltaX = (float) (x % cellSize) / cellSize;
                float deltaY = (float) (y % cellSize) / cellSize;
                Vector delta1 = new Vector(deltaX, deltaY);
                Vector delta2 = new Vector(deltaX, deltaY-1);
                Vector delta3 = new Vector(deltaX-1, deltaY);
                Vector delta4 = new Vector(deltaX-1, deltaY-1);

                float dotProduct1 = Vector.dot(gradients[y/cellSize][x/cellSize], delta1);
                float dotProduct2 = Vector.dot(gradients[y/cellSize+1][x/cellSize], delta2);
                float dotProduct3 = Vector.dot(gradients[y/cellSize][x/cellSize+1], delta3);
                float dotProduct4 = Vector.dot(gradients[y/cellSize+1][x/cellSize+1], delta4);

                float u = fade(deltaX);
                float v = fade(deltaY);
                float dotCrossProduct = lerp(dotProduct1, dotProduct2, v);
                float dotCrossProduct2 = lerp(dotProduct3, dotProduct4, v);
                float dotCrossProductFinal = lerp(dotCrossProduct, dotCrossProduct2, u);

                values[y][x] = dotCrossProductFinal;
                System.out.println("INFO: Generated value with values: " + values[y][x] + "at" + y + " " + x);

            }

        }


    }

    public float fade(float value){
        return (float) (6 * Math.pow(value,5) - 15 * Math.pow(value,4) + 10 * Math.pow(value,3));
    }

    public float lerp(float a, float b, float tolerance){
        return a + tolerance * (b - a);
    }


}
