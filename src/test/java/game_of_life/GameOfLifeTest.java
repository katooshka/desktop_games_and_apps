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
        testBlinker();
        testBlock();
        testTriplet();
    }

    private void testBlinker() {

        boolean[][] firstBlinkerGen = {
                {false, false, false},
                {true, true, true},
                {false, false, false}};

        boolean[][] secondBlinkerGen = {
                {false, true, false},
                {false, true, false},
                {false, true, false}};

        Field blinkerField = new Field(3, firstBlinkerGen);
        blinkerField.nextGeneration();
        assertArrayEquals(secondBlinkerGen, blinkerField.getCells());
        blinkerField.nextGeneration();
        assertArrayEquals(firstBlinkerGen, blinkerField.getCells());
    }

    private void testBlock() {

        boolean[][] firstBlockGen = {
                {false, false, false},
                {false, true, true},
                {false, true, false}};

        boolean[][] secondBlockGen = {
                {false, false, false},
                {false, true, true},
                {false, true, true}};

        Field blockField = new Field(3, firstBlockGen);
        blockField.nextGeneration();
        assertArrayEquals(secondBlockGen, blockField.getCells());
        blockField.nextGeneration();
        assertArrayEquals(secondBlockGen, blockField.getCells());
    }

    private void testTriplet() {

        boolean[][] firstTripletGen = {
                {false, false, true},
                {false, true, false},
                {true, false, false}};

        boolean[][] secondTripletGen = {
                {false, false, false},
                {false, true, false},
                {false, false, false}};

        boolean[][] thirdTripletGen = {
                {false, false, false},
                {false, false, false},
                {false, false, false}};

        Field tripletField = new Field(3, firstTripletGen);
        tripletField.nextGeneration();
        assertArrayEquals(secondTripletGen, tripletField.getCells());
        tripletField.nextGeneration();
        assertArrayEquals(thirdTripletGen, tripletField.getCells());
    }
}
