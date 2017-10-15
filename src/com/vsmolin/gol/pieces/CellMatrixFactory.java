package com.vsmolin.gol.pieces;

import java.util.ArrayList;

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
            for (int width = 0; width < size; width++)
            {
                ArrayList<C> column = new ArrayList<C>(size);
                matrix.add(column);
                for (int length = 0; length < size; length++)
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

        return newGridCells;
    }
}