package com.vsmolin.gol.rules;

import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GridCell;

public class GameOfLifeRuleOverpopulation extends GridCellRule<GameOfLifeCell>
{
    public boolean apply(GameOfLifeCell cell)
    {
        int aliveNeigbours = 0;
        for(GridCell neighbour : cell.getNeighbours())
        {
            GameOfLifeCell trueNeigb = (GameOfLifeCell)neighbour;
            if(trueNeigb.getAlive())
                ++aliveNeigbours;
        }
        if(cell.getAlive() && aliveNeigbours > 3)
        {
            cell.setNewAliveState(false);
            return true;
        }
        else
        {
            return false;
        }
    }
}
