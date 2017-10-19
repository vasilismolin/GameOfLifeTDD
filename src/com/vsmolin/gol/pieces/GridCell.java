package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;
import com.vsmolin.gol.rules.iApplied;
import com.vsmolin.gol.rules.iRulled;

import java.util.ArrayList;

public abstract class GridCell implements iGamePiece, iDrawable, iRulled
{
    public abstract boolean equals(Object otherCell);
    public abstract void draw();
    public abstract void applyRules(iApplied[] rules);

    private GridCell[] neighbours;
    public void setNeighbours(GridCell[] otherCells)
    {
        neighbours = otherCells;
    }
    public GridCell[] getNeighbours()
    {
        return neighbours;
    }
    public <C extends GridCell> void setNeighbours(ArrayList<C> otherCells)
    {
        neighbours = new GridCell[otherCells.size()];
        for(int i = 0; i < otherCells.size(); i++)
        {
            neighbours[i] = otherCells.get(i);
        }
    }
}
