package com.vsmolin.gol.gui;

import com.vsmolin.gol.game.GameOfLife;
import com.vsmolin.gol.game.iGame;
import com.vsmolin.gol.pieces.CellMatrix;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;

public class GOLController
{

    private final static int CELL_SIZE=10;
    private int boardSize;

    @FXML
    private Canvas fieldCanvas;
    private GraphicsContext gc;

    public void initModel(GameOfLife model, int screenWidth, int screenHeight)
    {
        boardSize = ((CellMatrix)model.getBoard().getGamePieces()).getGridSize();
        fieldCanvas.setHeight(boardSize*CELL_SIZE);
        fieldCanvas.setWidth(boardSize*CELL_SIZE);
        gc = fieldCanvas.getGraphicsContext2D();
    }

    public void drawField()
    {
        for(int col = 0; col<boardSize; col++)
        {
            for (int row = 0; row < boardSize; row++)
            {
                gc.setFill(Color.GREEN);
                gc.strokeRect(col*CELL_SIZE,row*CELL_SIZE,CELL_SIZE,CELL_SIZE);
                gc.fillRect(col*CELL_SIZE,row*CELL_SIZE,CELL_SIZE,CELL_SIZE);
            }
        }
    }

    @FXML
    private void callTestB()
    {
        System.out.println("HIHIHI");
    }

}
