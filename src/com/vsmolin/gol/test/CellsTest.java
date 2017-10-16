package com.vsmolin.gol.test;

import com.vsmolin.gol.pieces.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class CellsTest
{
    private GameOfLifeCellFactory defaultCellFactory = new GameOfLifeCellFactory();

    @Test
    public void initGridCells()
    {
        CellMatrix<GameOfLifeCell> testedPieces = new CellMatrix<>();
        ArrayList<ArrayList<GameOfLifeCell>> cellMatrix = new ArrayList<>();

        int size = 10;
        for(int i = 0; i < size; i++)
        {
            cellMatrix.add(new ArrayList<>());
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
        CellMatrix<GameOfLifeCell> testedCells = CellMatrixFactory.buildCellMatrix(gridSize, defaultCellFactory);
        Assert.assertEquals(gridSize, testedCells.getGridSize());
        testedCells.setCell(gridSize+1, gridSize, new GameOfLifeCell());
    }

    @Test (expected = IllegalArgumentException.class)
    public void setGridCellOnEmptyMatrixTest()
    {
        CellMatrix<GameOfLifeCell> testedCells = new CellMatrix<>();
        Assert.assertEquals(null, testedCells.getGridSize());
        GameOfLifeCell testCell = new GameOfLifeCell();
        testCell.setAlive(true);
        testedCells.setCell(2, 3, testCell);
    }

    @Test (expected = IllegalArgumentException.class)
    public void setGridCellAtNegativeCoordinatesTest()
    {
        CellMatrix<GameOfLifeCell> testedCells = CellMatrixFactory.buildCellMatrix(4, defaultCellFactory);
        GameOfLifeCell testCell = new GameOfLifeCell();
        testCell.setAlive(true);
        testedCells.setCell(-2, 3, testCell);
    }

    @Test (expected = IllegalArgumentException.class)
    public void testGridCellsSquareness()
    {
        int testSize = 5;

        CellMatrix<GameOfLifeCell> testedCells =
                CellMatrixFactory.<GameOfLifeCell>buildCellMatrix(testSize, defaultCellFactory);

        ArrayList<ArrayList<GameOfLifeCell>> testMatrix = testedCells.getCellMatrix();
        int middle = testSize/2;
        testMatrix.get(testSize/2).add(null);
        testedCells.setCellMatrix(testMatrix);
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
        ArrayList<String> testPattern = new ArrayList<>(Arrays.asList(
                "0101",
                "1111",
                "0000",
                "1100"
        ));
        ArrayList<Boolean> expectedResult = new ArrayList<>(Arrays.asList(
                false, true, false, true,
                true, true, true, true,
                false, false, false, false,
                true, true, false, false
        ));
        GameOfLifeCellFactory testFactory = new GameOfLifeCellFactory(testPattern);
        for(Boolean checkValue : expectedResult)
        {
            GameOfLifeCell newCell = (GameOfLifeCell) testFactory.createPiece();
            Assert.assertEquals(checkValue,newCell.getAlive());
        }
    }

    @Test
    public void testGameOfLifeCellFactoryWithDefault()
    {
        ArrayList<Boolean> expectedResult = new ArrayList<>(Arrays.asList(
                false, false, false, false, false, false
        ));
        GameOfLifeCellFactory testFactory = new GameOfLifeCellFactory();
        for(Boolean checkValue : expectedResult)
        {
            Assert.assertEquals(checkValue, ((GameOfLifeCell)testFactory.createPiece()).getAlive());
        }
    }

    @Test
    public void testGridCellsFactoryExceedPattern()
    {
        ArrayList<String> testPattern = new ArrayList<>(Arrays.asList(
                "01",
                "11"
        ));
        int testSize = 3;
        CellMatrix<GameOfLifeCell> testCells =
                CellMatrixFactory.buildCellMatrix(testSize, new GameOfLifeCellFactory(testPattern));

        ArrayList<String> expectedPattern = new ArrayList<>(Arrays.asList(
                "011",
                "100",
                "000"
        ));
        CellMatrix<GameOfLifeCell> expectedCells =
                CellMatrixFactory.buildCellMatrix(testSize, new GameOfLifeCellFactory(expectedPattern));

        for(int coll = 0; coll < testSize; coll++)
        {
            for(int row = 0; row < testSize; row++)
            {
                Assert.assertEquals(expectedCells, testCells);
            }
        }
    }
}