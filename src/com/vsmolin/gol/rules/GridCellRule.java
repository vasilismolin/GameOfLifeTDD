package com.vsmolin.gol.rules;

import com.vsmolin.gol.game.iGame;
import com.vsmolin.gol.pieces.GridCells;
import com.vsmolin.gol.pieces.iGamePiece;
import com.vsmolin.gol.pieces.iGamePieces;

import java.util.ArrayList;

//TODO cheating here, don't know how to apply
//rules in generic way to different parts of the board
//where apply method might have different arguments.
public abstract class GridCellRule implements iApplied
{
    public void apply(Object o) {}
    public abstract void apply(GridCells<?> cells, int coll, int row);


}
