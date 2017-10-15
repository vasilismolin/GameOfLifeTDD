package com.vsmolin.gol.game;

import com.vsmolin.gol.board.*;
import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GameOfLifeCellFactory;
import com.vsmolin.gol.pieces.GridCells;
import com.vsmolin.gol.pieces.iGamePieces;
import com.vsmolin.gol.rules.GameOfLifeRules;

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
            GameOfLifeCellFactory testFactory = new GameOfLifeCellFactory();
            GridCells<GameOfLifeCell> newState =
                    new GridCells<GameOfLifeCell>(size, testFactory);
        }

        GameOfLifeRules testedRules = new GameOfLifeRules();
        newGame.setRules(testedRules);
        //TODO need a factory for rule set

        LoopFlow newFlow = new LoopFlow(newGame, stepLength);
        newGame.setGameFlow(newFlow);

        return newGame;
    }
}
