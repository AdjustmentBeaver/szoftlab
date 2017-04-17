/**
 * Created by Istvan Telek on 3/14/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;

/**
 * Inicializálja a játékhoz szükséges objektumokat.
 * <p>
 * Létrehozza a SimulationTimer és a MapManager objektumokat.
 * Rajra keresztül állítható a játék állapota.
 */
public class Game implements Serializable {
    private transient SimulationTimer timer;
    private transient MapManager mapManager;
    private boolean wasRunning = false;
    private boolean simRunning = false;

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
        boolean running = true;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

        while (running) {
            String in = null;
            String[] cmd = null;
            try {
                in = br.readLine();
            } catch (IOException e) {
                e.printStackTrace();
            }
            if (in == null) {
                running = false;
                break;
            }
            cmd = in.split(" ");
            switch (cmd[0]) {
                case "new":
                    try {
                        newGame(cmd[1]);
                    } catch (ArrayIndexOutOfBoundsException e){
                        System.err.println("format: new <mapName>");
                    }
                    break;
                case "load":
                    stopGame();
                    try {
                        mapManager.loadMap(cmd[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("format: save <mapName>");
                    }
                    startGame();
                    break;
                case "save":
                    stopGame();
                    try {
                        mapManager.saveMap(cmd[1]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("format: save <mapName>");
                    }
                    resumeGame();
                    break;
                case "stop":
                    stopGame();
                    break;
                case "start":
                    startGame();
                    break;
                case "exit":
                    stopGame();
                    running = false;
                    break;
                case "step":
                    try {
                        timer.step(Integer.parseInt(cmd[1]));
                    } catch (NumberFormatException | ArrayIndexOutOfBoundsException e) {
                        System.err.println("Error number of step must be number");
                    }
                    break;
                case "activate":
                    try {
                        timer.addEvent("activate " + cmd[1] + " " + cmd[2]);
                    } catch (ArrayIndexOutOfBoundsException e) {
                        System.err.println("format: activate <x> <y>");
                    }
                    break;
                case "listNodes":
                    timer.addEvent("listNodes");
                    break;
                case "listTrains":
                    timer.addEvent("listTrains");
                    break;
                default:
                    System.out.println("NINCS ILYEN PARANCS");
                    break;
            }
            timer.step(0);
        }
    }

    /**
     * Start game.
     * <p>
     * Elindítja a szimulációt. A SimulationTimernek start üzenetet küld, aminek hatására elindul az időzítés.
     * </p>
     */
    public void startGame() {
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
     * New game.
     * <br>
     * Megállítja a szimulációt, majd szól a MapManagernek, hogy hozzon létre egy új pályát, végül pedig elindítja azt.
     *
     * @param nextLevel the next level
     */
    public void newGame(String nextLevel) {
        stopGame();
        mapManager.newMap("levels/" + nextLevel + ".xml");
        startGame();
    }

    /**
     * A játék megnyerése esetén hívjuk a függvényt (ha kiürült minden kocsi)
     */
    public void won() {
        //System.out.println("MAP_COMPLETED");
    }

    /**
     * A játék elvesztése esetén hívjuk a függvényt (ha felrobbant egy kocsi)
     */
    public void lost() {
        System.out.println("EXPLODE");
    }
}
