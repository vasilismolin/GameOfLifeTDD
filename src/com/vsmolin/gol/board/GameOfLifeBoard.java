package com.vsmolin.gol.board;

import com.vsmolin.gol.gui.iDrawable;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GridCells;
import com.vsmolin.gol.pieces.iGamePieces;
import com.vsmolin.gol.rules.iGameRules;
import com.vsmolin.gol.rules.iRulled;

public class GameOfLifeBoard implements iBoard, iDrawable, iRulled
{
    GridField playingField;
    GridCells<? extends GameOfLifeCell> gamePieces;

    public void progress()
    {//TODO implement
    }

    public void setField(iField playingField)
    {
        this.playingField = (GridField)playingField;
    }
    public iField getField()
    {
        return playingField;
    }

    public void setGamePieces(iGamePieces gamePieces)
    {
        this.gamePieces = (GridCells<? extends GameOfLifeCell>)gamePieces;
    }
    public iGamePieces getGamePieces()
    {
        return gamePieces;
    }

    public void draw()
    {
        playingField.draw();
        gamePieces.draw();
    }

    public void applyRules(iGameRules rulesToApply)
    {
        playingField.applyRules(rulesToApply);
        gamePieces.applyRules(rulesToApply);
    }
}
