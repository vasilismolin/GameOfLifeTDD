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
    public void testGameOfLifeRules()
    {
        class DummyRule implements iApplied
        {
            int _val;
            public DummyRule(int val) {_val = val;}
            public void apply(Object o){_val += 10;}
            public int check() {return _val;}
        }

        GameOfLifeRules<DummyRule> testedRules =
                new GameOfLifeRules<DummyRule>();
        ArrayList<DummyRule> dummyRuleList = new ArrayList<DummyRule>();
        testedRules.setRulesList(dummyRuleList);

        ArrayList<Integer> setValues =
                new ArrayList<Integer>(Arrays.asList(1,3,4,6,34));
        ArrayList<Integer> checkValues =
                new ArrayList<Integer>(Arrays.asList(11,13,14,16,44));

        for(Integer setValue:setValues)
        {
            dummyRuleList.add(new DummyRule(setValue));
        }

        testedRules.apply(null);
        for(int i = 0; i < setValues.size(); i++)
        {
            Assert.assertEquals(checkValues.get(i).intValue(),
                    testedRules.getRulesList().get(i).check());
        }
    }
}
