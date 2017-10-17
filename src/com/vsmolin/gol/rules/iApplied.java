package com.vsmolin.gol.rules;

import com.vsmolin.gol.game.iGame;
//TODO Game depends on board and board depends on rules
//and rules depend on game. Circular dependency!!!

public interface iApplied<R>
{
    public boolean apply(R target);
}
