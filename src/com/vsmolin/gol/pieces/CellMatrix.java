package com.vsmolin.gol.pieces;

import com.vsmolin.gol.gui.iDrawable;
import com.vsmolin.gol.rules.iApplied;
import com.vsmolin.gol.rules.iRulled;

import java.util.ArrayList;

/*
 * Represents a collection of Game Of Life cells in unison.
 * Decoupled from GridField in order to simplify Field's
 * construction.
 */
public class CellMatrix<P extends GridCell>
        implements iGamePieces<P>, iDrawable, iRulled
{
    private ArrayList<ArrayList<P>> cellMatrix;
    private Integer gridSize = null;

    public CellMatrix() {}

    public void setCellMatrix(ArrayList<ArrayList<P>> matrix)
    {
        //Test for squareness
        if(!isSquare(matrix))
            throw new IllegalArgumentException("Given cell matrix is not square.");

        gridSize = matrix.size();
        cellMatrix = matrix;
    }

    public ArrayList<ArrayList<P>> getCellMatrix()
    {
        return cellMatrix;
    }

    public Integer getGridSize()
    {
        return gridSize;
    }

    public void setGridSize(int size)
    {
        if(size <= 0) throw new IllegalArgumentException("Grid size must be positive integer");
        gridSize = size;
    }

    public void setCell(int coll, int row, P cell)
    {
        if(coll < 0 || row < 0)
            throw new IllegalArgumentException("Cannot set a cell at negative coordinates");
        if(gridSize == null)
            throw new IllegalArgumentException("This object must be initialised with a matrix.");
        if(coll >= gridSize || row >= gridSize)
            throw new IllegalArgumentException("Given column or row argument is beyound grid size.");

        cellMatrix.get(coll).set(row, cell);
    }

    public P getCell(int coll, int row)
    {
        if(coll >= gridSize || row >= gridSize || coll < 0 || row < 0) return null;
        return cellMatrix.get(coll).get(row);
    }

    public boolean equals(Object otherMatrix)
    {
        if(otherMatrix == null) return false;
        if(otherMatrix == this) return true;
        if(!(otherMatrix instanceof CellMatrix)) return false;

        CellMatrix otherCellMatrix = (CellMatrix)otherMatrix;
        if (this.gridSize != otherCellMatrix.gridSize)
            return false;
        for(int coll = 0; coll < this.gridSize; coll++)
        {
            for(int row = 0; row < this.gridSize; row++)
            {
                if(!this.getCell(coll,row).equals(otherCellMatrix.getCell(coll, row)))
                    return false;
            }
        }
        return true;
    }

    public void draw()
    {
        for(ArrayList<P> column : cellMatrix)
        {
            for(P cell : column)
            {
                cell.draw();
            }
        }
    }

    //Asumes rules are mutually exclusive
    //TODO needs a better abstraction around rules
    //how to restrict to GOL related rules
    public void applyRules(iApplied[] rulesToApply)
    {
     /*   for(int coll = 0; coll < gridSize; coll++)
        {
            for(int row = 0; row < gridSize; row++)
            {
                rulesToApply.
                for(iApplied rule : rulesToApply.getRulesList())
                {
                    rule.setGrid(this)
                    rule.appyly(cell);
                    cell.applyRules(rulesToApply)
                }
            }
        }*/
    }

    protected boolean isSquare(ArrayList<ArrayList<P>> matrix)
    {
        int width = matrix.size();
        for(ArrayList<P> column : matrix)
        {
            if (column.size() != width) return false;
        }

        return true;
    }

    //TODO Each cell needs to keep track of its nieghbors at creation time
    //but I need more abstraction between game peice and GOLCell
    //and this class needs to use that
    /*private ArrayList<iGamePiece> getNeighbours(int coll, int row)
    {
        ArrayList<iGamePiece> neighbours = new ArrayList<iGamePiece>();
        int maxEdge = row -1;
        int minEdge = 0;
        //inner cells
        if(coll > minEdge && coll < maxEdge && row > minEdge && row < maxEdge)
        {
            neighbours.add(getCell(coll - 1, row - 1));
            neighbours.add(getCell(coll, row -1));
            neighbours.add(getCell(coll + 1, row -1));
            neighbours.add(getCell(coll - 1, row));
            neighbours.add(getCell(coll + 1, row));
            neighbours.add(getCell(coll - 1, row + 1));
            neighbours.add(getCell(coll, row + 1));
            neighbours.add(getCell(coll + 1, row + 1));
        }
        if((coll == minEdge && row == minEdge) ||
           (coll == minEdge && row == maxEdge) ||
           (coll == maxEdge && row == minEdge) ||
           (coll == maxEdge && row == maxEdge) )
        {

        }


    }*/
}
