package com.vsmolin.gol.test;

import com.sun.corba.se.spi.activation.LocatorOperations;
import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.flow.iGameFlow;
import com.vsmolin.gol.game.*;
import com.vsmolin.gol.board.*;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GameOfLifeCellFactory;
import com.vsmolin.gol.pieces.GridCells;
import com.vsmolin.gol.rules.*;
import org.junit.*;

import java.util.ArrayList;
import java.util.Arrays;

public class GameTest
{

    @Test
    public void initGame()
    {
        iGame testedGame = new GameOfLife();

        iBoard testedState = new GameOfLifeBoard();
        testedGame.setBoard(testedState);
        Assert.assertEquals(testedState, testedGame.getBoard());

        iGameRules testedRules = new GameOfLifeRules();
        testedGame.setRules(testedRules);
        Assert.assertEquals(testedRules, testedGame.getGameRules());

        iGameFlow testedFlow = new LoopFlow(testedGame, 1000);
        testedGame.setGameFlow(testedFlow);
        Assert.assertEquals(testedFlow, testedGame.getGameFlow());
    }

    @Test
    @Ignore
    public void gameOfLifeTest()
    {
        int gameSize = 5;
        ArrayList<String> startingState = new ArrayList<String>(Arrays.asList(
                "00000",
                "00000",
                "01110",
                "00000",
                "00000"
        ));
        GridCells<GameOfLifeCell> testCells =
                new GridCells<GameOfLifeCell>(gameSize, new GameOfLifeCellFactory(startingState));
        GameOfLife testedGame = GameFactory.buildGameOfLife(500, testCells);

        testedGame.step(1);
        Assert.assertEquals(1, testedGame.getCurrentRound());

        ArrayList<String> expectedState = new ArrayList<String>(Arrays.asList(
                "00000",
                "00100",
                "00100",
                "00100",
                "00000"
        ));
        GridCells<GameOfLifeCell> expectedCells =
                new GridCells<GameOfLifeCell>(gameSize, new GameOfLifeCellFactory(expectedState));

        GridCells<GameOfLifeCell> testGameCells = (GridCells<GameOfLifeCell>)testedGame.getBoard().getGamePieces();
        //if(!testGameCells.equals(expectedCells))
        //    Assert.fail("Game cells after one step are not correct");

    }

}
