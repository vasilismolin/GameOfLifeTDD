package com.vsmolin.gol.test;

import com.vsmolin.gol.board.*;
import com.vsmolin.gol.pieces.*;
import org.junit.*;

import java.util.concurrent.atomic.AtomicInteger;

public class BoardTest
{
    /*
     *Initializing a GameOfLifeBoard and all of its components.
     */
    @Test
    public void initBoard()
    {
        GameOfLifeBoard testedBoard = new GameOfLifeBoard();

        iField testedField = new GridField();
        testedBoard.setField(testedField);
        Assert.assertEquals(testedField, testedBoard.getField());

        iGamePieces testedCells = new GridCells();
        testedBoard.setGamePieces(testedCells);
        Assert.assertEquals(testedCells, testedBoard.getGamePieces());
    }

    @Test
    public void intiGridField()
    {
        GridField testedField = new GridField();
        testedField.setFieldSize(100);
        Assert.assertEquals(100, testedField.getFieldSize());
    }


    @Test
    public void boardDrawTest()
    {
        AtomicInteger fieldTestDriver = new AtomicInteger(0);
        class TestGridField extends GridField
        {
            private AtomicInteger testDriver;
            public TestGridField(AtomicInteger driver) {testDriver = driver;}
            public void draw() {testDriver.set(testDriver.get()+1);}
        }

        AtomicInteger cellTestDriver = new AtomicInteger(0);
        class TestGameOfLifeCell extends GameOfLifeCell
        {
            private AtomicInteger testDriver;
            public TestGameOfLifeCell(AtomicInteger driver) {testDriver = driver;}
            public void draw() {testDriver.set(testDriver.get()+1);}
        }
        class TestCellFactory extends GameOfLifeCellFactory
        {
            public iGamePiece createPiece()
            {
                return new TestGameOfLifeCell(cellTestDriver);
            }
        }

        GameOfLifeBoard testedBoard = new GameOfLifeBoard();
        TestGridField testField = new TestGridField(fieldTestDriver);
        testedBoard.setField(testField);

        int gridSize = 100;
        GridCells<TestGameOfLifeCell> testCells =
                new GridCells<TestGameOfLifeCell>(gridSize, new TestCellFactory());
        testedBoard.setGamePieces(testCells);

        testedBoard.draw();

        Assert.assertEquals(1, fieldTestDriver.get());
        Assert.assertEquals(gridSize*gridSize, cellTestDriver.get());
    }

}