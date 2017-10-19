package com.vsmolin.gol.pieces;

import java.util.ArrayList;
import java.util.Arrays;

public class CellMatrixFactory
{
    //TODO no consitency checks between given size and patern in the factory
    //TODO abstractions could probalby be simplified
    public static <C extends GridCell> CellMatrix<C> buildCellMatrix(int size, iPieceFactory factory)
    {
        if(size <= 0)
            throw new IllegalArgumentException("Grid size must be positive integer");
        if(factory == null)
            throw new IllegalArgumentException("Piece factory must be given");

        ArrayList<ArrayList<C>> matrix = new ArrayList<ArrayList<C>>(size);
        try
        {
            for (int coll = 0; coll < size; coll++)
            {
                ArrayList<C> column = new ArrayList<C>(size);
                matrix.add(column);
                for (int row = 0; row < size; row++)
                {
                    iGamePiece piece = factory.createPiece();
                    column.add((C)piece);   //TODO hack to not have to deal with genrics
                }
            }
        }
        catch(Exception e)
        {
            System.err.println("Exceptin while building cell matrix: "+e.getMessage());
            return null;
        }

        CellMatrix<C> newGridCells = new CellMatrix<C>();
        newGridCells.setGridSize(size);
        newGridCells.setCellMatrix(matrix);
        for(int coll = 0; coll < size; coll++)
        {
            for(int row = 0; row < size; row++)
            {
                newGridCells.getCell(coll, row).setNeighbours(getNeighbours(coll, row, newGridCells));
            }
        }

        return newGridCells;
    }

    private static <C extends GridCell> ArrayList<C> getNeighbours(int coll, int row, CellMatrix<C> matrix)
    {
        ArrayList<C> neighbours = new ArrayList<>();
        int[] indexes = {-1,0,1};
        for(int collIndex : indexes)
        {
            for(int rowIndex : indexes)
            {
                if (collIndex == 0 && rowIndex == 0)
                    continue;
                C nearCell = matrix.getCell(coll+collIndex, row+rowIndex);
                if(nearCell != null)
                    neighbours.add(nearCell);
            }
        }

        return neighbours; //not the best solution here.
    }
}