package com.vsmolin.gol.test;

import com.vsmolin.gol.game.*;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GameOfLifeCellFactory;
import com.vsmolin.gol.pieces.CellMatrix;
import com.vsmolin.gol.pieces.CellMatrixFactory;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTest
{
    @Test
    @Ignore
    public void gameOfLifeTestStraightLine()
    {
        ArrayList<String> startingState = new ArrayList<>(Arrays.asList(
                "00000",
                "00000",
                "01110",
                "00000",
                "00000"
        ));
        ArrayList<String> expectedState = new ArrayList<>(Arrays.asList(
                "00000",
                "00100",
                "00100",
                "00100",
                "00000"
        ));
        genericGameTest(5,200,1,startingState,expectedState);
    }

    @Test
    public void gameOfLifeTestGlider()
    {
        ArrayList<String> startingState = new ArrayList<>(Arrays.asList(
                "0000000000",
                "0100100000",
                "0000010000",
                "0100010000",
                "0011110000",
                "0000000000",
                "0000000000",
                "0000000000",
                "0000000000",
                "0000000000"
        ));
        ArrayList<String> expectedState = new ArrayList<>(Arrays.asList(
                "0000000000",
                "0000000000",
                "0001111000",
                "0010001000",
                "0000001000",
                "0010010000",
                "0000000000",
                "0000000000",
                "0000000000",
                "0000000000"
        ));
        genericGameTest(10,200,2,startingState,expectedState);
    }

    private void genericGameTest(int gameSize, int stepLength, int numSteps, ArrayList<String> startingState,
                                 ArrayList<String> expectedState)
    {
        CellMatrix<GameOfLifeCell> testCells =
                CellMatrixFactory.buildCellMatrix(gameSize, new GameOfLifeCellFactory(startingState));
        GameOfLife testedGame = GameFactory.buildGameOfLife(stepLength, testCells);

        testedGame.step(numSteps);
        try
        {
            Thread.sleep(stepLength+100);
        }
        catch (InterruptedException e)
        {
            e.printStackTrace();
        }
        Assert.assertEquals(numSteps, testedGame.getCurrentRound());

        CellMatrix<GameOfLifeCell> expectedCells =
                CellMatrixFactory.buildCellMatrix(gameSize, new GameOfLifeCellFactory(expectedState));
        CellMatrix<GameOfLifeCell> testGameCells = (CellMatrix<GameOfLifeCell>)testedGame.getBoard().getGamePieces();

        /*System.out.println("EXEPECTED CELLS");
        expectedCells.draw();

        System.out.println("REAL CELLS");
        testGameCells.draw();*/
        Assert.assertEquals(expectedCells, testGameCells);
    }

}
