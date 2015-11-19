package game_of_life;

import java.util.Random;

/**
 * Author: katooshka
 * Date: 11/18/15.
 */
public class Field {
    private int size;
    private boolean[][] cells;

    public Field(int size, boolean[][] cells) {
        this.size = size;
        this.cells = cells;
    }

    public static Field createField(int size, int densityPercentage) {
        return new Field(size, getFirstCellsGeneration(size, densityPercentage));
    }

    private static boolean[][] getFirstCellsGeneration(int size, int densityPercentage) {
        Random random = new Random();
        boolean[][] firstCellsGeneration = new boolean[size][size];
        for (int i = 0; i < firstCellsGeneration.length; i++) {
            for (int j = 0; j < firstCellsGeneration.length; j++) {
                if (random.nextInt(100) <= densityPercentage) {
                    firstCellsGeneration[i][j] = true;
                }
            }
        }
        return firstCellsGeneration;
    }

    public void nextGeneration() {
        boolean[][] newCellsGeneration = new boolean[size][size];
        for (int i = 0; i < newCellsGeneration.length; i++) {
            for (int j = 0; j < newCellsGeneration.length; j++) {
                int aliveCells = checkAliveCells(i, j);
                if (cells[i][j]) {
                    newCellsGeneration[i][j] = aliveCells == 2 || aliveCells == 3;
                } else {
                    newCellsGeneration[i][j] = aliveCells == 3;
                }
            }
        }
        cells = newCellsGeneration;
    }

    private int checkAliveCells(int x, int y) {
        return cellAt(x - 1, y - 1) + cellAt(x - 1, y) + cellAt(x - 1, y + 1) +
                cellAt(x, y - 1) + cellAt(x, y + 1) +
                cellAt(x + 1, y - 1) + cellAt(x + 1, y) + cellAt(x + 1, y + 1);
    }

    private int cellAt(int x, int y) {
        return (x >= 0 && y >= 0 && x < size && y < size && cells[x][y]) ? 1 : 0;
    }

    public boolean[][] getCells() {
        return cells;
    }
}
