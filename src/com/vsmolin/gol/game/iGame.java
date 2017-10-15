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
}
