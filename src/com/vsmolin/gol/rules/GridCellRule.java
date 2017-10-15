package com.vsmolin.gol.rules;

import com.vsmolin.gol.pieces.CellMatrix;

//TODO cheating here, don't know how to apply
//rules in generic way to different parts of the board
//where apply method might have different arguments.
public abstract class GridCellRule implements iApplied
{
    public void apply(Object o) {}
    public abstract void apply(CellMatrix<?> cells, int coll, int row);


}
