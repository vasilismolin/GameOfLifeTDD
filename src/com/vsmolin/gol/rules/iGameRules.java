package com.vsmolin.gol.rules;

import java.util.ArrayList;
import com.vsmolin.gol.game.*;

public interface iGameRules<R extends iApplied> extends iApplied
{
    public ArrayList<R> getRulesList();
    public void setRulesList(ArrayList<R> rules);
    public void apply(Object o);
}
