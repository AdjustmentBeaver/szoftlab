package controller;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuItem;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;
import model.Game;

import java.io.File;

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
    
    @FXML
    Menu newGameMenu;
    @FXML
    Menu loadGameMenu;
    @FXML
    Menu startMenu;
    @FXML
    TextField saveTextField;

    @FXML
    public void saveMenuEventHandler(ActionEvent actionEvent) {
        game.saveGame(saveTextField.getText());
        boolean exists = false;
        String text = saveTextField.getText(); 
        //Új mentés hozzáadása a load game menühöz
        for (MenuItem mi : loadGameMenu.getItems())
            if (mi.getText().equals(text)) 
                exists = true;
        if (!exists)
            loadGameMenu.getItems().add(new MenuItem(saveTextField.getText()));
        saveTextField.setText("");
    }

    @FXML
    public void initialize() {
        //Pályanevek betöltése a menübe
        File levels = new File("./levels");
        for (File f : levels.listFiles()) {
            if (f.getName().contains(".xml")) {
                MenuItem mItem = new MenuItem(f.getName().replace(".xml", ""));
                newGameMenu.getItems().add(mItem);
                //Elindítja a kiválasztott pályát
                mItem.setOnAction(event ->  {
                    game.newGame(mItem.getText());
                });
            }
        }
        
        //Mentések betöltése a menübe
        File saves = new File(".");
        for (File f : saves.listFiles()) {
            if (f.getName().contains(".save")) {
                MenuItem mItem = new MenuItem(f.getName().replace(".save", ""));
                loadGameMenu.getItems().add(mItem);
                //Elindítja a kiválasztott pályát
                mItem.setOnAction(event ->  {
                    game.loadGame(mItem.getText());
                });
            }
        }

        //Start/Stop menüre kattintva indítja vagy megállítja a játékot
        startMenu.showingProperty().addListener(new ChangeListener<Boolean>() {
            @Override
            public void changed(ObservableValue<? extends Boolean> observableValue, Boolean oldValue, Boolean newValue)
            {
                if(newValue.booleanValue()) {
                    if (game.isRunning())
                        game.stopGame();
                    else
                        game.startGame();
                    startMenu.getItems().get(0).fire();
                }
            }

        });
        
    }
    
}
