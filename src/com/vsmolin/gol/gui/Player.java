package com.vsmolin.gol.gui;

import com.vsmolin.gol.game.GameFactory;
import com.vsmolin.gol.game.GameOfLife;
import com.vsmolin.gol.game.iGame;
import com.vsmolin.gol.pieces.CellMatrix;
import com.vsmolin.gol.pieces.CellMatrixFactory;
import com.vsmolin.gol.pieces.GameOfLifeCell;
import com.vsmolin.gol.pieces.GameOfLifeCellFactory;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;

public class Player extends Application
{
    private GameOfLife modelGame;
    private GOLController golController;
    private int screenWidth;
    private int screenHeight;

    //TODO: Ideally player  should take instance of
    //iControlled and ask for a set of controll
    //objects which it would trigger.
    //Each controll object would describe what
    //it is for.
    //Player should be initialised outside of game
    //and when player.join() is called it should
    //tell teh game to register itself since game is iControlled
    public Player()
    {

    }

    @Override
    public void start(Stage primaryStage) throws Exception
    {
        int gameSize = 100;
        CellMatrix<GameOfLifeCell> testCells =
                CellMatrixFactory.buildCellMatrix(gameSize, new GameOfLifeCellFactory());
        modelGame = GameFactory.buildGameOfLife(100, testCells);

        BorderPane root = new BorderPane();
        FXMLLoader listLoader = new FXMLLoader(getClass().getResource("golLayout.fxml"));
        root.setCenter(listLoader.load());
        golController = listLoader.getController();
        golController.initModel(modelGame, screenWidth, screenHeight);
        golController.drawField();

        primaryStage.setTitle("Conway's Game of Life");
        primaryStage.setScene(new Scene(root, screenWidth, screenHeight));
        primaryStage.show();
    }

    public static void main(String[] args) {
        launch(args);
    }
}