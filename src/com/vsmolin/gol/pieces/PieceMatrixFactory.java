package com.vsmolin.gol.pieces;

import java.util.ArrayList;

public class PieceMatrixFactory
{
    //TODO no consitency checks between given size and patern in the factory
    public static <P extends iGamePiece> ArrayList<ArrayList<P>>
        buildPieceMatrix(int size, iPieceFactory factory)
    {
        if(size <= 0)
            throw new IllegalArgumentException("Grid size must be positive integer");
        if(factory == null)
            throw new IllegalArgumentException("Piece factory must be given");

        try
        {
            ArrayList<ArrayList<P>> matrix = new ArrayList<ArrayList<P>>(size);
            for (int width = 0; width < size; width++)
            {
                ArrayList<P> column = new ArrayList<P>(size);
                matrix.add(column);
                for (int length = 0; length < size; length++)
                {
                    iGamePiece piece = factory.createPiece();
                    column.add((P)piece);   //TODO hack to not have to deal with genrics
                }
            }
            return matrix;
        }
        catch(Exception e)
        {
            System.err.println("Exceptin while building cell matrix: "+e.getMessage());
            return null;
        }
    }
}