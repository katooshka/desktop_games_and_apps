package game_of_life;

import org.junit.Test;

import static org.junit.Assert.*;

/**
 * Author: katooshka
 * Date: 11/21/15.
 */
public class GameOfLifeTest {

    @Test
    public void testGameOfLife() {

        boolean[][] firstGen = {
                {false, false, false},
                {true, true, true},
                {false, false, false}};

        Field field = new Field(3, firstGen);
        field.nextGeneration();

        boolean[][] expected = {
                {false, true, false},
                {false, true, false},
                {false, true, false}};

        assertArrayEquals(expected, field.getCells());

        field.nextGeneration();

        assertArrayEquals(firstGen, field.getCells());

        
    }
}
