package com.vsmolin.gol.board;

import com.vsmolin.gol.rules.iApplied;
import com.vsmolin.gol.rules.iRulled;

//TODO relation of field size and GamePieces size and num of cells
public class GridField implements iField,iRulled
{
    private int _sizeSize;

    public void setFieldSize(int sideSize)
    {
        _sizeSize = sideSize;
    }
    public int getFieldSize()
    {
        return _sizeSize;
    }

    public void draw()
    {
        //TODO implement
    }

    public void applyRules(iApplied[] rulesToApply)
    {
        //to nothing
    }

    public void flipState()
    {

    }
}
