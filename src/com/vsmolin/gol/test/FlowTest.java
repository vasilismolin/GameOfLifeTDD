package com.vsmolin.gol.test;

import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.game.GameOfLife;
import org.junit.Assert;
import org.junit.Ignore;
import org.junit.Test;

public class FlowTest
{

    class NullGameOfLife extends GameOfLife
    {
        public void progress() {}
    }

    @Test
    public void testLoopFlowStartStop()
    {
        int waitTime = 400;
        int stepLength = 100;
        long expectedStepNumber = waitTime/stepLength;

        GameOfLife testGame = new NullGameOfLife();
        LoopFlow testFlow = new LoopFlow(testGame, stepLength);
        testFlow.start();
        try
        {
            Thread.sleep(waitTime);
        } catch (Exception e){}
        testFlow.stop();
        Assert.assertFalse(testFlow.isRunning());
        Assert.assertEquals(expectedStepNumber, testFlow.getStepNumber());

        //TODO vs need to figure out repeated runs
        testFlow.start();
        try
        {
            Thread.sleep(stepLength*2);
        } catch (Exception e){}
        testFlow.stop();
        Assert.assertEquals(expectedStepNumber+2, testFlow.getStepNumber());
    }

    @Test
    public void testLoopFlowSteps()
    {
        int numSteps = 4;
        int stepLength = 100;

        GameOfLife testGame = new NullGameOfLife();
        LoopFlow testFlow = new LoopFlow(testGame, stepLength);
        testFlow.setStepsToAdvance(numSteps);
        testFlow.start();
        try
        {
            Thread.sleep((numSteps*2)*stepLength);
        } catch (Exception e){}
        Assert.assertFalse(testFlow.isRunning());
        Assert.assertEquals(numSteps, testFlow.getStepNumber());

        testFlow.start();
        try
        {
            Thread.sleep(stepLength*2);
        } catch (Exception e){}
        testFlow.stop();
        Assert.assertFalse(testFlow.isRunning());
        Assert.assertEquals(numSteps+2, testFlow.getStepNumber());
    }
}
