package com.vsmolin.gol.test;

import org.junit.runner.JUnitCore;
import org.junit.runner.Result;
import org.junit.runner.notification.Failure;

public class TestRunner {
    public static void main(String[] args)
    {
        Result result = JUnitCore.runClasses(
                GameTest.class,
                BoardTest.class,
                CellsTest.class,
                RulesTest.class//,
                //FlowTest.class
        );

        for (Failure failure : result.getFailures())
        {
            System.err.println(failure.toString());
        }

        if (result.wasSuccessful())
        {
            System.out.println("Tests passed.");
            System.exit(0);
        }
        else
        {
            System.err.println("Tests failed! See above.");
            System.exit(1);
        }
    }
}