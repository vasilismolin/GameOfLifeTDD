package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;

public abstract class GridCell implements iGamePiece, iDrawable
{
    public abstract boolean equals(iGamePiece otherPiece);
    public abstract void draw();
}
