package com.vsmolin.gol.game;

import com.vsmolin.gol.board.*;
import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.pieces.*;
import com.vsmolin.gol.rules.GridCellRule;

public class GameFactory
{
    public static GameOfLife buildGameOfLife(int size, int stepLength)
    {
        return buildGameOfLife(size, stepLength, null);
    }
    public static GameOfLife buildGameOfLife(int stepLength, iGamePieces initialState)
    {
        return buildGameOfLife(0, stepLength, initialState);
    }

    //TODO could be given a pattern array instead
    private static GameOfLife buildGameOfLife(int size, int stepLength, iGamePieces initialState)
    {
        GameOfLife newGame = new GameOfLife();

        GameOfLifeBoard newBoard = new GameOfLifeBoard();
        newGame.setBoard(newBoard);
        newBoard.setField(new GridField());
        if(initialState != null)
        {
            newBoard.setGamePieces(initialState);
        }
        else
        {
            GameOfLifeCellFactory testCellFactory = new GameOfLifeCellFactory();
            CellMatrixFactory testCellsFactory = new CellMatrixFactory();
            CellMatrix<GameOfLifeCell> newState = CellMatrixFactory.buildCellMatrix(size, testCellFactory);
        }

        GridCellRule[] testedRules = new GridCellRule[0];
        newGame.setRules(testedRules);
        //TODO need a factory for rule set

        LoopFlow newFlow = new LoopFlow(newGame, stepLength);
        newGame.setGameFlow(newFlow);

        return newGame;
    }
}
