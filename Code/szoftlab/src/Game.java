/**
 * Created by Istvan Telek on 3/14/2017.
 */

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

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
                    newGame(cmd[1]);
                    break;
                case "load":
                    mapManager.loadMap(cmd[1]);
                    break;
                case "save":
                    mapManager.saveMap(cmd[1]);
                    break;
                case "stop":
                    stopGame();
                    break;
                case "start":
                    startGame();
                    break;
                case "exit":
                    running=false;
                    break;
                case "step":
                    try {
                        for (int i = 0 ; i < Integer.parseInt(cmd[1]); i++)
                            timer.step();

                    } catch (NumberFormatException e){
                        System.out.println("ERROR");
                        break;
                    }
                    break;
                case "activate":
                    System.out.println("Gonna activate");
                    break;
                case "listNodes":
                    break;
                case "listTrains":
                    break;
                default:
                    System.out.println("KYS");
                    break;
            }
        }
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
