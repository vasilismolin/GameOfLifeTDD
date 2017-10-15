package com.vsmolin.gol.test;

import com.vsmolin.gol.game.*;
import com.vsmolin.gol.rules.*;
import org.junit.Assert;
import org.junit.Test;
import java.util.Arrays;
import java.util.ArrayList;

public class RulesTest
{
    @Test
    public void testGenericRuleAppply()
    {
        class DummyRule implements iApplied
        {
            int _val;
            public DummyRule(int val) {_val = val;}
            public void apply(Object o){_val += 10;}
            public int check() {return _val;}
        }

        //ArrayList<DummyRule> dummyRuleList = new ArrayList<DummyRule>();

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
}
