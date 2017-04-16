/**
 * Created by Istvan Telek on 3/14/2017.
 */

/**
 * Inicializálja a játékhoz szükséges objektumokat.
 * <p>
 * Létrehozza a SimulationTimer és a MapManager objektumokat.
 * Rajra keresztül állítható a játék állapota.
 */
public class Game {
    private SimulationTimer timer;
    private MapManager mapManager;

    /**
     * Instantiates a new Game.
     * <p>
     * Init szekvencia.
     * </p>
     */
    public Game() {
        timer = new SimulationTimer();
        mapManager = new MapManager(timer, this);
    }

    /**
     * The entry point of application.
     *
     * @param args the input arguments
     */
    public static void main(String[] args) {
        Game game = new Game();
        game.loop();
    }

    /**
     * Main loop for CLI
     */
    private void loop() {
        // Input thread, getting CLI lines, starting simulation thread etc.
        newGame("levels/palya_betoltese.xml");
        mapManager.saveMap("sav1");
        mapManager.loadMap("sav1");
    }

    /**
     * Start game.
     * <p>
     * Elindítja a szimulációt. A SimulationTimernek start üzenetet küld, aminek hatására elindul az időzítés.
     * </p>
     */
    public void startGame() {
        timer.start();
    }

    /**
     * Stop game.
     * <p>
     * Megállítja a szimulációt. A SimulationTimernek stop üzenetet küld, aminek a hatására az időzítés megáll.
     * </p>
     */
    public void stopGame() {
        timer.stop();
    }

    /**
     * Resume game.
     * <p>
     * A játék előző állapota alapján dönt. Ha futott a játék akkor újra elindítja azt.
     * </p>
     */
    public void resumeGame() {
        timer.start();
    }

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
}
