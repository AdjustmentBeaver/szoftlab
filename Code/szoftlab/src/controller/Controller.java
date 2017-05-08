package controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.control.Button;
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

    @FXML
    private Button btnStartStop;

    public void setModel(Game game) {
        this.game = game;
    }

    public GraphicsContext getCanvasGC() {
        return cvGame.getGraphicsContext2D();
    }

    @FXML
    public void canvasClickHandler(MouseEvent mouseEvent) {
        if (game.isRunning())
            game.activate(mouseEvent.getX(), mouseEvent.getY());
    }

    @FXML
    public void newGameEventHandler(ActionEvent actionEvent) {
        String level = ((MenuItem) actionEvent.getSource()).getId();
        game.newGame(level);
        btnStartStop.setDisable(false);
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
        boolean wasRunning = game.isRunning();
        game.stopGame();
        File f = createSaveLoadFileChooser("Load Saved Map").showOpenDialog(cvGame.getScene().getWindow());
        if (f != null) {
            game.loadGame(f.getPath());
            btnStartStop.setDisable(false);
            game.step(0);
            btnStartStop.setText("Folytatás");
        }

        if (wasRunning) {
            game.startGame();
            btnStartStop.setText("Szünet");
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
        if (game.isRunning()) {
            btnStartStop.setText("Folytatás");
            game.stopGame();
        } else {
            game.startGame();
            btnStartStop.setText("Szünet");
        }
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
            btnStartStop.setDisable(false);
        }
    }
}
