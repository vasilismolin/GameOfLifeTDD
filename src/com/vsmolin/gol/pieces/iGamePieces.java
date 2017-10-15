package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;

/*
 * Represents all game pieces on the board.
 */
public interface iGamePieces <P extends iGamePiece>
{
    //do not force all collections to be 2D matrix
    //public void setCellMatrix(ArrayList<ArrayList<P>> cellMatrix);
    //public ArrayList<ArrayList<P>> getCellMatrix();
}
