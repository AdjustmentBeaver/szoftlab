package model;

import controller.Controller;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.stage.Stage;
import view.View;

import java.io.File;
import java.io.IOException;
import java.io.Serializable;

/**
 * Inicializálja a játékhoz szükséges objektumokat.
 * <p>
 * Létrehozza a model.SimulationTimer és a model.MapManager objektumokat.
 * Rajra keresztül állítható a játék állapota.
 */
public class Game extends Application implements Serializable {
    private SimulationTimer timer;
    private MapManager mapManager;
    private boolean wasRunning = false;
    private boolean simRunning = false;
    private View view;

    /**
     * Instantiates a new model.Game.
     * <p>
     * Init szekvencia.
     * </p>
     */
    public Game() {
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("game.fxml"));
        Parent root = null;
        try {
            root = fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
            return;
        }
        Controller ctrl = fxmlLoader.getController();
        ctrl.setModel(this);
        timer = new SimulationTimer();
        mapManager = new MapManager(timer, this);
        view = new View(mapManager, ctrl);
        view.setScene(new Scene(root));
        timer.setView(view);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) throws Exception {
        primaryStage.setTitle("Szoftlab");
        primaryStage.setScene(view.getScene());
        primaryStage.show();
        view.reset();
    }

    public void activate(double x, double y) {
        timer.addEvent("activate " + x + " " + y);
    }

    public void loadGame(String level) {
        stopGame();
        mapManager.loadMap(level);
    }

    public void saveGame(String level) {
        stopGame();
        mapManager.saveMap(level);
    }

    /**
     * Start game.
     * <p>
     * Elindítja a szimulációt. A SimulationTimernek start üzenetet küld, aminek hatására elindul az időzítés.
     * </p>
     */
    public void startGame() {
        if (mapManager.getMap()==null) return;
        wasRunning = simRunning;
        timer.start();
        simRunning = true;
    }

    /**
     * Stop game.
     * <p>
     * Megállítja a szimulációt. A SimulationTimernek stop üzenetet küld, aminek a hatására az időzítés megáll.
     * </p>
     */
    public void stopGame() {
        wasRunning = simRunning;
        timer.stop();
        simRunning = false;
    }

    /**
     * Resume game.
     * <p>
     * A játék előző állapota alapján dönt. Ha futott a játék akkor újra elindítja azt.
     * </p>
     */
    public void resumeGame() {
        if (wasRunning)
            timer.start();
    }

    /**
     *  Megadja, hogy jelenleg fut-e a játék
     */
    public boolean isRunning() { return simRunning; }

    /**
     * New game.
     * <br>
     * Megállítja a szimulációt, majd szól a MapManagernek, hogy hozzon létre egy új pályát, végül pedig elindítja azt.
     *
     * @param nextLevel the next level
     */
    public void newGame(String nextLevel) {
        stopGame();
        mapManager.newMap(nextLevel);
        startGame();
    }

    /**
     * A játék megnyerése esetén hívjuk a függvényt (ha kiürült minden kocsi)
     */
    public void won() {
        new MediaPlayer(new Media(new File("sound/applause.mp3").toURI().toString())).play();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Train Simulator 2017");
        alert.setHeaderText(null);
        alert.setContentText("Gratulálunk, nyertél!");
        ButtonType nextButton = new ButtonType("Következő pálya");
        alert.getButtonTypes().setAll(nextButton);
        alert.showAndWait();
        mapManager.nextMap();
        startGame();
    }


    /**
     * A játék elvesztése esetén hívjuk a függvényt (ha felrobbant egy kocsi)
     */
    public void lost() {
        new MediaPlayer(new Media(new File("sound/explosion.mp3").toURI().toString())).play();
        Alert alert = new Alert(Alert.AlertType.INFORMATION);
        alert.setTitle("Train Simulator 2017");
        alert.setHeaderText(null);
        alert.setContentText("Sajnálom, vesztettél!");
        alert.showAndWait();
        mapManager.newMap(mapManager.getMap().getMapName());
        startGame();
    }

    public void step(int n) {
        if (mapManager.getMap() != null) {
            timer.step(n);
        }
    }
}
