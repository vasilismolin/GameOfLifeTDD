package com.vsmolin.gol.pieces;

import com.sun.istack.internal.localization.NullLocalizable;

import java.util.ArrayList;

public class GameOfLifeCellFactory implements iPieceFactory
{
    private ArrayList<String> buildPattern;
    private int currentRowIndex = -1;
    private int currentCollIndex = -1;

    private static final char NO_PATTERN_CHAR = '\n';

    //Creates default dead cells
    public GameOfLifeCellFactory() {}

    //Creates cells based on given pattern
    //TODO not check that the pattern is square
    public GameOfLifeCellFactory(ArrayList<String> pattern)
    {
        if(pattern.size() == 0)
            buildPattern = null;
        else
            buildPattern = pattern;

        if(buildPattern != null)
        {
            currentRowIndex = -1;
            currentCollIndex = -1;
        }
    }

    //TODO will requre casting but avoids having to deal with generics
    public iGamePiece createPiece()
    {
        GameOfLifeCell newCell = new GameOfLifeCell();
        if(buildPattern != null)
        {
            char patternChar = getNextPatternChar();
            if(patternChar == NO_PATTERN_CHAR) {} //If we run out of pattern, return default cell
            else if(patternChar == '1') newCell.setAlive(true);
            else if(patternChar == '0') newCell.setAlive(false);
        }
        return newCell;
    }

    private char getNextPatternChar()
    {
        if(currentRowIndex == buildPattern.size())  //If we run out of pattern
            return NO_PATTERN_CHAR;

        if(currentRowIndex == -1)
            currentRowIndex = 0;

        if(currentCollIndex == buildPattern.get(currentRowIndex).length()-1)
        {
            currentCollIndex = 0;
            ++currentRowIndex;
        }
        else
        {
            ++currentCollIndex;
        }

        if(currentRowIndex == buildPattern.size())  //If we run out of pattern
            return NO_PATTERN_CHAR;
        return buildPattern.get(currentRowIndex).charAt(currentCollIndex);
    }
}
