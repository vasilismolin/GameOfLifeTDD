package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;
import com.vsmolin.gol.rules.iApplied;
import com.vsmolin.gol.rules.iRulled;

public abstract class GridCell implements iGamePiece, iDrawable, iRulled
{
    public abstract boolean equals(Object otherCell);
    public abstract void draw();
    public abstract void applyRules(iApplied[] rules);

    //public void setNeighbours()
}
