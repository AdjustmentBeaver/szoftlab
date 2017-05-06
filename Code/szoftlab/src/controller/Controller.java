package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.MenuItem;
import javafx.scene.input.MouseEvent;
import javafx.stage.FileChooser;
import model.Game;

import java.io.File;

/**
 * Created by moriss on 4/23/2017.
 */
public class Controller {
    private Game game;

    @FXML
    private Canvas cvGame;

    public void setModel(Game game) {
        this.game = game;
    }

    public GraphicsContext getCanvasGC() {
        return cvGame.getGraphicsContext2D();
    }

    @FXML
    public void stepEventHandler(ActionEvent actionEvent) {
        game.step(1);
    }

    @FXML
    public void newEventHandler(ActionEvent actionEvent) {
        game.newGame("cvtest");
    }

    @FXML
    public void step10EventHandler(ActionEvent actionEvent) {
        game.step(10);
    }

    @FXML
    public void canvasClickHandler(MouseEvent mouseEvent) {
        game.activate(mouseEvent.getX(), mouseEvent.getY());
    }

    @FXML
    public void newGameEventHandler(ActionEvent actionEvent) {
        String level = ((MenuItem) actionEvent.getSource()).getId();
        game.newGame(level);
    }

    @FXML
    public void saveGameEventHandler(ActionEvent actionEvent) {
        boolean wasRunning = game.isRunning();
        game.stopGame();
        File f = createSaveLoadFileChooser("Save Map").showSaveDialog(cvGame.getScene().getWindow());
        if (f != null) {
            game.saveGame(f.getPath());
        }
        if (wasRunning) game.startGame();
    }

    @FXML
    public void loadGameEventHandler(ActionEvent actionEvent) {
        game.stopGame();
        File f = createSaveLoadFileChooser("Load Saved Map").showOpenDialog(cvGame.getScene().getWindow());
        if (f != null) {
            game.loadGame(f.getPath());
            // redraw
            game.step(0);
        }
    }

    private FileChooser createSaveLoadFileChooser(String title) {
        return createCustomFileChooser(title, "saves", "save1.save", "Saved Maps (*.save)", "*.save");
    }

    private FileChooser createCustomFileChooser(String title, String path, String initFileName, String extDescription, String... extensions) {
        FileChooser fileChooser = new FileChooser();
        fileChooser.setTitle(title);
        fileChooser.setInitialDirectory(new File(path));
        fileChooser.setInitialFileName(initFileName);
        fileChooser.getExtensionFilters().add(new FileChooser.ExtensionFilter(extDescription, extensions));
        return fileChooser;
    }

    @FXML
    public void exitEventHandler(ActionEvent actionEvent) {
        game.stopGame();
        Platform.exit();
    }

    @FXML
    public void startStopEventHandler(ActionEvent actionEvent) {
        if (game.isRunning())
            game.stopGame();
        else
            game.startGame();
    }

    @FXML
    public void initialize() {
        File saves = new File("saves");
        if (!saves.exists())
            saves.mkdir();
    }

    @FXML
    public void newCustomGameEventHandler(ActionEvent actionEvent) {
        game.stopGame();
        File f = createCustomFileChooser("New Custom Game", "levels", "level_1.xml",
                "Levels (*.xml)", "*.xml")
                .showOpenDialog(cvGame.getScene().getWindow());
        if (f != null) {
            game.newGame(f.getName().split(".xml")[0]);
        }
    }
}
