package com.vsmolin.gol.test;

import com.vsmolin.gol.game.GameOfLife;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GameOfLifeCellFactory;
import com.vsmolin.gol.rules.*;
import org.junit.Assert;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Arrays;

public class RulesTest
{
    @Test
    public void testGenericRuleAppply()
    {
        class DummyRule implements iApplied<GameOfLifeCell>
        {
            int _val;
            public DummyRule(int val) {_val = val;}
            public boolean apply(GameOfLifeCell o){_val += 10; return true;}
            public int check() {return _val;}
        }

        int[] setValues = {1,3,4,6,34};
        int[] checkValues = {11,13,14,16,44};

        DummyRule[] testedRules = new DummyRule[setValues.length];
        for(int i = 0; i < setValues.length; i++)
        {
            testedRules[i] = new DummyRule(setValues[i]);
            testedRules[i].apply(null);
        }

        for(int i = 0; i < setValues.length; i++)
        {
            Assert.assertEquals(checkValues[i], testedRules[i].check());
        }
    }

    @Test
    public void golRulesDeadAnd3LiveNeighbours()
    {
        String pattern = "010101000"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertTrue(testCell.getAlive());

        pattern = "010110";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertTrue(testCell.getAlive());
    }
    @Test
    public void golRulesDeadAnd2LiveNeighbours()
    {
        String pattern = "000101000"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());

        pattern = "0101000";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());
    }
    @Test
    public void golRulesDeadAnd4LiveNeighbours()
    {
        String pattern = "010111000"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());

        pattern = "0101110";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());
    }
    @Test
    public void golRulesAliveAnd1LiveNeighbours()
    {
        String pattern = "100001000"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());

        pattern = "11";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());
    }
    @Test
    public void golRulesAliveAnd2LiveNeighbours()
    {
        String pattern = "100101000"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertTrue(testCell.getAlive());

        pattern = "10111";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertTrue(testCell.getAlive());
    }
    @Test
    public void golRulesAliveAnd3LiveNeighbours()
    {
        String pattern = "100000111"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertTrue(testCell.getAlive());

        pattern = "10000111";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertTrue(testCell.getAlive());
    }
    @Test
    public void golRulesAliveAnd4LiveNeighbours()
    {
        String pattern = "110101010"; //the tested cell and 8 neighbours
        GameOfLifeCell testCell = buildCellWithNeighbours(pattern);
        testCell.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());

        pattern = "110111";
        GameOfLifeCell testCellEdge = buildCellWithNeighbours(pattern);
        testCellEdge.applyRules(buildGOLRules());
        Assert.assertFalse(testCell.getAlive());
    }

    private GameOfLifeCell buildCellWithNeighbours(String pattern)
    {
        GameOfLifeCellFactory factory = new GameOfLifeCellFactory(pattern);
        GameOfLifeCell mainCell = (GameOfLifeCell)factory.createPiece();

        //int numNullNeighbours = pattern.length() - 9;
        //GameOfLifeCell[] neighbours = new GameOfLifeCell[8];
        GameOfLifeCell[] neighbours = new GameOfLifeCell[pattern.length() - 1];
        for(int i=1;i<pattern.length();i++)
        {
            neighbours[i-1] = (GameOfLifeCell)factory.createPiece();
        }
        /*while(numNullNeighbours>0)
        {
            neighbours[pattern.length()-2+numNullNeighbours] = null;
        }*/
        mainCell.setNeighbours(neighbours);
        return mainCell;
    }

    private iApplied<GameOfLifeCell>[] buildGOLRules()
    {
        iApplied<GameOfLifeCell>[] rules = new iApplied[4];
        rules[0] = new GameOfLifeRuleOverpopulation();
        rules[1] = new GameOfLifeRuleSurvival();
        rules[2] = new GameOfLifeRuleStarvation();
        rules[3] = new GameOfLifeRuleReproduction();
        return rules;
    }
}
