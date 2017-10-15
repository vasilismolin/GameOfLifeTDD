package com.vsmolin.gol.test;

import com.vsmolin.gol.pieces.*;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CellsTest
{
    private GameOfLifeCellFactory defaultCellFactory = new GameOfLifeCellFactory();

    @Test
    public void initGridCells()
    {
        GridCells<GameOfLifeCell> testedPieces =
                new GridCells<GameOfLifeCell>();
        ArrayList<ArrayList<GameOfLifeCell>> cellMatrix =
                new ArrayList<ArrayList<GameOfLifeCell>>();

        int size = 10;
        for(int i = 0; i < size; i++)
        {
            cellMatrix.add(new ArrayList<GameOfLifeCell>());
            for(int j = 0; j < size; j++)
            {
                GameOfLifeCell cell = new GameOfLifeCell();
                if(i%2 == 0 && j%2 == 0)
                    cell.setAlive(true);
                cellMatrix.get(i).add(cell);
            }
        }

        testedPieces.setCellMatrix(cellMatrix);
        Assert.assertEquals(cellMatrix, testedPieces.getCellMatrix());
        for(int i =0; i < size; i++)
        {
            for(int j = 0; j < size; j++)
            {
                if(i%2 == 0 &&  j%2 == 0)
                {
                    Assert.assertTrue(testedPieces.getCellMatrix().get(i).get(j).getAlive());
                }
                else
                {
                    Assert.assertFalse(testedPieces.getCellMatrix().get(i).get(j).getAlive());
                }
            }
        }
    }

    @Test(expected = IllegalArgumentException.class)
    public void setGridCellOutOfBoundsTest()
    {
        Integer gridSize = 10;
        GridCells<GameOfLifeCell> testedCells = new GridCells<GameOfLifeCell>(gridSize, defaultCellFactory);
        Assert.assertEquals(gridSize, testedCells.getGridSize());
        testedCells.setCell(gridSize+1, gridSize, new GameOfLifeCell());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setGridCellEmptyMatrixTest()
    {
        GridCells<GameOfLifeCell> testedCells = new GridCells<GameOfLifeCell>();
        Assert.assertEquals(null, testedCells.getGridSize());
        GameOfLifeCell testCell = new GameOfLifeCell();
        testCell.setAlive(true);
        testedCells.setCell(2, 3, testCell);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setGridCellNegativeCoordinatesTest()
    {
        GridCells<GameOfLifeCell> testedCells = new GridCells<GameOfLifeCell>(4, defaultCellFactory);
        GameOfLifeCell testCell = new GameOfLifeCell();
        testCell.setAlive(true);
        testedCells.setCell(-2, 3, testCell);
    }

    @Test
    public void testGridCellsSquareness()
    {
        class DummyGridCells<P extends iGamePiece> extends GridCells<P>
        {
            public boolean testSquare(ArrayList<ArrayList<P>> matrix)
            {
                return isSquare(matrix);
            }
        }
        int testSize = 5;

        ArrayList<ArrayList<GameOfLifeCell>> testMatrix = null;

        try
        {
            testMatrix = PieceMatrixFactory.buildPieceMatrix(testSize, defaultCellFactory);
        }
        catch (Exception e)
        {
            Assert.fail("boardDrawTest() failed to build cell matrix.");
        }

        DummyGridCells<GameOfLifeCell> testedCells = new DummyGridCells<GameOfLifeCell>();
        Assert.assertTrue(testedCells.testSquare(testMatrix));

        int middle = testSize/2;
        testMatrix.get(testSize/2).add(null);
        Assert.assertFalse(testedCells.testSquare(testMatrix));
    }

    @Test
    public void testGameOfLifeCell()
    {
        GameOfLifeCell testCell = new GameOfLifeCell();
        testCell.setAlive(true);
        Assert.assertTrue(testCell.getAlive());

        testCell.setAlive(false);
        Assert.assertFalse(testCell.getAlive());
    }

    @Test
    public void testGameOfLifeCellFactoryWithPattern()
    {
        ArrayList<String> testPattern = new ArrayList<String>(Arrays.asList(
                "0101",
                "1111",
                "0000",
                "1100"
        ));
        ArrayList<Boolean> expectedResult = new ArrayList<Boolean>(Arrays.asList(
                false, true, false, true,
                true, true, true, true,
                false, false, false, false,
                true, true, false, false
        ));
        GameOfLifeCellFactory testFactory = new GameOfLifeCellFactory(testPattern);
        for(Boolean checkValue : expectedResult)
        {
            GameOfLifeCell newCell = (GameOfLifeCell) testFactory.createPiece();
            Assert.assertEquals(checkValue.booleanValue(),newCell.getAlive());
        }
    }

    @Test
    public void testGameOfLifeCellFactoryWithDefault()
    {
        ArrayList<Boolean> expectedResult = new ArrayList<Boolean>(Arrays.asList(
                false, false, false, false, false, false
        ));
        GameOfLifeCellFactory testFactory = new GameOfLifeCellFactory();
        for(Boolean checkValue : expectedResult)
        {
            Assert.assertEquals(checkValue.booleanValue(),
                    ((GameOfLifeCell)testFactory.createPiece()).getAlive());
        }
    }

    @Test
    public void testPieceMatrixFactoryExceedPattern()
    {
        ArrayList<String> testPattern = new ArrayList<String>(Arrays.asList(
                "01",
                "11"
        ));
        int testSize = 3;
        ArrayList<ArrayList<GameOfLifeCell>> testArray =
                PieceMatrixFactory.buildPieceMatrix(testSize, new GameOfLifeCellFactory(testPattern));

        ArrayList<String> expectedPattern = new ArrayList<String>(Arrays.asList(
                "011",
                "100",
                "000"
        ));
        ArrayList<ArrayList<GameOfLifeCell>> expectedArray =
                PieceMatrixFactory.buildPieceMatrix(testSize, new GameOfLifeCellFactory(expectedPattern));

        for(int coll = 0; coll < testSize; coll++)
        {
            for(int row = 0; row < testSize; row++)
            {

                //TODO seems to be a bug in JUnit here
                //Assert.assertEquals(expectedArray.get(coll).get(row), testArray.get(coll).get(row));
                if(!expectedArray.get(coll).get(row).equals(testArray.get(coll).get(row)))
                    Assert.fail("Cells not equal");
            }
        }
    }
}