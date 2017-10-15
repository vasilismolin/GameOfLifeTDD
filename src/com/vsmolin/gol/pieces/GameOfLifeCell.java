package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;

import java.util.ArrayList;

public class GameOfLifeCell implements iGamePiece
{

    //TODO setAlive(bool) vs setState(bool) vs setAlive/setDead vs state enum
    //TODO does it need to have position

    private boolean alive;
    private ArrayList<GameOfLifeCell> neighbours;

    public GameOfLifeCell()
    {
        alive = false;
    }

    public void setAlive(boolean alive)
    {
        this.alive = alive;
    }
    public boolean getAlive()
    {
        return alive;
    }

    public boolean equals(iGamePiece otherPiece)
    {
        if(!(otherPiece instanceof GameOfLifeCell))
            return false;

        return this.getAlive() == ((GameOfLifeCell) otherPiece).getAlive();
    }

    public void draw()
    {
        //TODO implement
    }
}
