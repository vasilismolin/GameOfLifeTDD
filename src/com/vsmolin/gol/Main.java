package com.vsmolin.gol;

import com.vsmolin.gol.flow.LoopFlow;
import com.vsmolin.gol.game.GameFactory;
import com.vsmolin.gol.game.GameOfLife;
import com.vsmolin.gol.gui.Player;
import com.vsmolin.gol.pieces.CellMatrix;
import com.vsmolin.gol.pieces.CellMatrixFactory;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GameOfLifeCellFactory;
import javafx.application.Application;

public class Main {

    public static void main(String[] args) {
	// write your code here
        int gameSize = 100;
        CellMatrix<GameOfLifeCell> testCells =
                CellMatrixFactory.buildCellMatrix(gameSize, new GameOfLifeCellFactory());
        GameOfLife testedGame = GameFactory.buildGameOfLife(100, testCells);

        Application p = new Player();
        //p.play(args);
        //p.join(testedGame);
        //p.
    }
}
