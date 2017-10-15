package com.vsmolin.gol.flow;

import com.sun.jmx.snmp.SnmpStatusException;
import com.vsmolin.gol.game.iGame;

import java.util.concurrent.*;
import java.util.concurrent.atomic.AtomicBoolean;

public class LoopFlow implements iGameFlow
{
    private iGame gameState;
    private int stepPeriod;
    private long stepCount;

    private iGame gameReference;

    private ScheduledExecutorService loop;
    private ScheduledFuture activeLoop;
    private CountDownLatch advanceSteps;

    public LoopFlow(iGame game, int stepLength)
    {
        gameState = game;
        stepPeriod = stepLength;
        stepCount = 0;
        advanceSteps = null;

        loop = Executors.newSingleThreadScheduledExecutor();
        activeLoop = null;
    }
    public void start()
    {
        if(activeLoop == null)
        {
            activeLoop = loop.scheduleAtFixedRate(new FlowTask(advanceSteps), 0,
                        stepPeriod, TimeUnit.MILLISECONDS);
            if(advanceSteps != null)
            {
                try
                {
                    advanceSteps.await();
                }
                catch (InterruptedException ie)
                {
                    System.err.println("Latch got interupted.");
                }
                activeLoop.cancel(true);
                advanceSteps = null;
                activeLoop = null;
            }
        }
    }
    public void stop()
    {
        if(activeLoop != null)
        {
            activeLoop.cancel(true);
            activeLoop = null;
        }
    }

    public void setStepsToAdvance(int numSteps)
    {
        advanceSteps = new CountDownLatch(numSteps);
    }
    public long getStepNumber() { return stepCount; }
    public boolean isRunning() { return activeLoop != null; }

    private class FlowTask implements Runnable
    {
        private CountDownLatch latch = null;
        public FlowTask(CountDownLatch stepLimit)
        {
            latch = stepLimit;
        }

        public void run()
        {
            stepCount++;
            //gameReference.progress();
            if(latch != null) latch.countDown();
        }
    }
}
