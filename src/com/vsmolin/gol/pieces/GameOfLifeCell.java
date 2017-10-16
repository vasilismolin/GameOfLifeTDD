package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;
import com.vsmolin.gol.rules.iApplied;

import java.util.ArrayList;

public class GameOfLifeCell extends GridCell
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

    public boolean equals(Object otherCell)
    {
        if(otherCell == null) return false;
        if(otherCell == this) return true;
        if(!(otherCell instanceof GameOfLifeCell)) return false;

        return this.getAlive() == ((GameOfLifeCell) otherCell).getAlive();
    }

    public void applyRules(iApplied[] rules)
    {

    }


    public void draw()
    {
        //TODO implement
    }
}
