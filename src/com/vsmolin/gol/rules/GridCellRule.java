package com.vsmolin.gol.rules;

import com.vsmolin.gol.pieces.GridCell;

public abstract class GridCellRule<R> implements iApplied<R>
{
    public abstract boolean apply(R cell);
}
