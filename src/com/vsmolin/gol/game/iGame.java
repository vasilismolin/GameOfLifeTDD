package com.vsmolin.gol.game;

import com.vsmolin.gol.board.*;
import com.vsmolin.gol.flow.iGameFlow;
import com.vsmolin.gol.rules.*;

public interface iGame
{
    public void start();
    public void stop();
    public void progress();
    public long getCurrentRound();

    //TODO Are getters and setters in intefaces bad?
    //the force me to cast when dealing with concrete classeses
    public void setBoard(iBoard stateBoard);
    public iBoard getBoard();

    public void setRules(iGameRules gameRules);
    public iGameRules getGameRules();

    public void setGameFlow(iGameFlow controllingFlow);
    public iGameFlow getGameFlow();
}
