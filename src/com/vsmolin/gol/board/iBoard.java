package com.vsmolin.gol.board;

import com.vsmolin.gol.pieces.iGamePieces;
import com.vsmolin.gol.rules.iApplied;

public interface iBoard
{
    public void applyRules(iApplied[] rulesToApply);

    public void setField(iField playingField);
    public iField getField();

    public void setGamePieces(iGamePieces gamePieces);
    public iGamePieces getGamePieces();

}
