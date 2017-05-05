package controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;
import model.Game;

/**
 * Created by moriss on 4/23/2017.
 */
public class Controller {
    @FXML
    Canvas cvGame;

    Game game;

    public void setModel(Game game) {
        this.game = game;
    };

    public GraphicsContext getCanvasGC() {
        return cvGame.getGraphicsContext2D();
    }

    public void stepEventHandler(ActionEvent actionEvent){
        game.step(1);
    }

    public void newEventHandler(ActionEvent actionEvent) {
        game.newGame("cvtest");
    }

    public void step10EventHandler(ActionEvent actionEvent) {
        game.step(10);
    }

    public void canvasClickHandler(MouseEvent mouseEvent) {
        game.activate(mouseEvent.getX(), mouseEvent.getY());
    }
}
