package com.vsmolin.gol.rules;

import com.vsmolin.gol.game.iGame;
import com.vsmolin.gol.pieces.GridCells;

import java.util.ArrayList;

//TODO there is no reason to have a GOL specific rules class
//and by extension, probably an interface too. This could have been an abstract
//class representing a rule list.
public class GameOfLifeRules<R extends iApplied>
        implements iGameRules<R>
{
    ArrayList<R> _rulesList;

    public ArrayList<R> getRulesList()
    {
        return _rulesList;
    }
    public void setRulesList(ArrayList<R> rulesList)
    {
        _rulesList = rulesList;
    }

    public void apply(Object o)
    {
        for(R rule : _rulesList)
        {
            rule.apply(o);
        }
        /*GridCells<?> grid = (GridCells<?>) o;
        for(int coll = 0; coll < grid.getGridSize(); coll++)
        {
            for(int row = 0; row < grid.getGridSize(); row++)
            {
                for(R rule : _rulesList)
                {
                    rule.apply(grid, coll, row);
                }
            }
        }*/
    }
}
