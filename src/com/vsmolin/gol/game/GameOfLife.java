package com.vsmolin.gol.game;

import com.vsmolin.gol.board.*;
import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.flow.iGameFlow;
import com.vsmolin.gol.rules.*;
import org.junit.Assert;

public class GameOfLife implements iGame
{
    private GameOfLifeBoard stateBoard;
    private GameOfLifeRules gameRules;
    private LoopFlow gameFlow;

    public void start()
    {
        gameFlow.start();
    }

    public void stop()
    {
        gameFlow.stop();
    }

    public void progress()
    {
        stateBoard.applyRules(gameRules);
    }

    public void step(int numSteps)
    {
        gameFlow.setStepsToAdvance(numSteps);
        gameFlow.start();
    }

    public long getCurrentRound()
    {
        return gameFlow.getStepNumber();
    }

    //Dirty casting. See the comment ini iGame
    public void setBoard(iBoard stateBoard)
    {
        this.stateBoard = (GameOfLifeBoard)stateBoard;
    }
    public iBoard getBoard()
    {
        return stateBoard;
    }

    public void setRules(iGameRules rules)
    {
        gameRules = (GameOfLifeRules)rules;
    }
    public iGameRules getGameRules()
    {
        return gameRules;
    }

    public void setGameFlow(iGameFlow controllingFlow) {gameFlow = (LoopFlow)controllingFlow; }
    public iGameFlow getGameFlow() { return gameFlow; }

}
