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
    public void gameOfLifeTest()
    {
        int gameSize = 5;
        ArrayList<String> startingState = new ArrayList<>(Arrays.asList(
                "00000",
                "00000",
                "01110",
                "00000",
                "00000"
        ));
        CellMatrix<GameOfLifeCell> testCells =
                CellMatrixFactory.buildCellMatrix(gameSize, new GameOfLifeCellFactory(startingState));
        GameOfLife testedGame = GameFactory.buildGameOfLife(500, testCells);

        testedGame.step(1);
        Assert.assertEquals(1, testedGame.getCurrentRound());

        ArrayList<String> expectedState = new ArrayList<>(Arrays.asList(
                "00000",
                "00100",
                "00100",
                "00100",
                "00000"
        ));
        CellMatrix<GameOfLifeCell> expectedCells =
                CellMatrixFactory.buildCellMatrix(gameSize, new GameOfLifeCellFactory(expectedState));

        CellMatrix<GameOfLifeCell> testGameCells = (CellMatrix<GameOfLifeCell>)testedGame.getBoard().getGamePieces();
        //if(!testGameCells.equals(expectedCells))
        //    Assert.fail("Game cells after one step are not correct");

    }

}
