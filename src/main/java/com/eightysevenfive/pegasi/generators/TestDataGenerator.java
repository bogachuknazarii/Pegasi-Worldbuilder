package com.eightysevenfive.pegasi.generators;

import java.util.Random;

public class TestDataGenerator {

    /**
     * Generates a 2-dimensional array of numbers with an upper bound of 255 (inclusive)
     *
     * @param dataHeight
     * @param dataWidth
     * @return
     */
    public static int[][] create2DTestArray(int dataHeight, int dataWidth) {

        int[][] testArray = new int[dataHeight][dataWidth];
        Random random = new Random();

        for (int i = 0; i < dataHeight; i++) {

            for (int j = 0; j < dataWidth; j++) {

                testArray[i][j] = random.nextInt(256);

            }

        }

        return testArray;

    }

}
