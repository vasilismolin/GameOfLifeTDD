package com.vsmolin.gol.rules;

import com.vsmolin.gol.pieces.GridCell;

public abstract class GridCellRule<R extends GridCell> implements iApplied<R>
{
    public abstract void apply(R cell);
}
