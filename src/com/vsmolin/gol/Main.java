package com.vsmolin.gol;

import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.game.GameOfLife;

public class Main {

    public static void main(String[] args) {
	// write your code here
        GameOfLife testGame = new GameOfLife();
        LoopFlow testFlow = new LoopFlow(testGame, 10000);
        testFlow.setStepsToAdvance(2);
        testFlow.start();
    }
}
