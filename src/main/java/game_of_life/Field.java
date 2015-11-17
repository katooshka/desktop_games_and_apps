package game_of_life;

import java.util.Random;

/**
 * Author: katooshka
 * Date: 11/18/15.
 */
public class Field {
    private int size;
    private boolean[][] cells;

    public Field(int size, int densityPercentage) {
        this.size = size;
        cells = getFirstCellsGeneration(size, densityPercentage);
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
                if (cells[i][j]) {
                    newCellsGeneration[i][j] = checkAliveCells(cells, i, j) == 2 || checkAliveCells(cells, i, j) == 3;
                } else {
                    if (checkAliveCells(cells, i, j) == 3) {
                        newCellsGeneration[i][j] = true;
                    }
                }
            }
        }
        cells = newCellsGeneration;
    }

    private int checkAliveCells(boolean[][] cells, int x, int y) {
        boolean[][] newField = createFieldFrame(cells);
        int aliveCellsNumber = 0;
        boolean[] neighbourCells = {
                newField[x][y],
                newField[x][y + 1],
                newField[x][y + 2],
                newField[x + 1][y],
                newField[x + 1][y + 2],
                newField[x + 2][y],
                newField[x + 2][y + 1],
                newField[x + 2][y + 2]};
        for (boolean neighbourCell : neighbourCells) {
            if (neighbourCell) {
                aliveCellsNumber += 1;
            }
        }
        return aliveCellsNumber;
    }

    private boolean[][] createFieldFrame (boolean[][] cells) {
        boolean[][] framedCells = new boolean[cells.length + 2][cells.length + 2];
        for (int i = 1; i < framedCells.length - 1; i++) {
            for (int j = 1; j < framedCells.length - 1; j++) {
                framedCells[i][j] = cells[i - 1][j - 1];
            }
        }
        return framedCells;
    }

    public boolean[][] getCells() {
        return cells;
    }
}
