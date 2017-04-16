import util.Color;
import util.Coordinate;
import util.Speed;

import java.util.ArrayList;
import java.util.List;

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
        Prompt.printMessage("Game.Game");

        Prompt.addIndent("<<create>>");
        timer = new SimulationTimer();
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        mapManager = new MapManager(timer, this);
        Prompt.removeIndent();
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
     * Skeleton main loop for CLI
     */
    private void loop() {
        boolean run = true;
        // Init magic
        Prompt.supressMessages(true);
        MapManager mapManager = new MapManager(timer, this);
        mapManager.newMap("level1");
        Map map = new MapBuilder("testLevel").buildMap();
        map.subscribe(timer);
        List<Train> trainList = new ArrayList<>();
        // Statistics stat = new Statistics(this);
        Train train = new Train( trainList);
        Station station = new Station(new Coordinate(0,0), new Color(""));
        TrainEngine te = new TrainEngine(train, new Speed(0));
        te.setNextNode(station);
        TrainCart tc = new TrainCart(train,  new Color(""));
        tc.setNextNode(station);
        train.addPart(tc);
        Prompt.supressMessages(false);
        // End of init magic

        while (run) {
            System.out.println("---------------------------------------");
            System.out.println("Válasszon az alábbi lehetőségek közül: ");
            System.out.println("1: Start");
            System.out.println("2: Stop");
            System.out.println("3: New -> Subscribe");
            System.out.println("4: Load -> Subscribe");
            System.out.println("5: Save -> Resume");
            System.out.println("6: Simulate -> Move, Collision Check, Explode, Visit Node");
            System.out.println("7: Move/Visit node");
            System.out.println("8: Collision Check");
            System.out.println("9: Explode");
            System.out.println("10: Activate");
            System.out.println("11: Exit");
            System.out.print("> ");
            int input = Prompt.readCommand();
            switch (input) {
                case 1:
                    startGame();
                    break;
                case 2:
                    stopGame();
                    break;
                case 3:
                    Prompt.addIndent("game.newGame(level)");
                    newGame("level1");
                    Prompt.removeIndent();
                    break;
                case 4:
                    mapManager.loadMap("level1");
                    break;
                case 5:
                    mapManager.saveMap("level1");
                    break;
                case 6:
                    timer.step();
                    break;
                case 7:
                    train.move();
                    break;
                case 8:
                    train.checkCollision();
                    break;
                case 9:
                    train.explode();
                    break;
                case 10:
                    map.activateNode(new Coordinate(0, 0));
                    break;
                case 11:
                    run = false;
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
        Prompt.printMessage("Game.startGame");
        Prompt.addIndent("timer.start()");
        timer.start();
        Prompt.removeIndent();
    }

    /**
     * Stop game.
     * <p>
     * Megállítja a szimulációt. A SimulationTimernek stop üzenetet küld, aminek a hatására az időzítés megáll.
     * </p>
     */
    public void stopGame() {
        Prompt.printMessage("Game.stopGame");
        Prompt.addIndent("timer.stop()");
        timer.stop();
        Prompt.removeIndent();
    }

    /**
     * Resume game.
     * <p>
     * A játék előző állapota alapján dönt. Ha futott a játék akkor újra elindítja azt.
     * </p>
     */
    public void resumeGame() {
        Prompt.printMessage("Game.resumeGame");

        System.out.println("[?] Futott a játék előzőleg?");
        System.out.print("[>] ");

        if (Prompt.readBool()) {
            Prompt.addIndent("timer.start()");
            timer.start();
            Prompt.removeIndent();
        }
    }

    /**
     * New game.
     * <br>
     * Megállítja a szimulációt, majd szól a MapManagernek, hogy hozzon létre egy új pályát, végül pedig elindítja azt.
     *
     * @param nextLevel the next level
     */
    public void newGame(String nextLevel) {
        Prompt.printMessage("Game.newGame");

        Prompt.addIndent("game.stopGame()");
        stopGame();
        Prompt.removeIndent();

        Prompt.addIndent("mapManager.newMap(nextLevel)");
        mapManager.newMap(nextLevel);
        Prompt.removeIndent();

        Prompt.addIndent("game.startGame()");
        startGame();
        Prompt.removeIndent();
    }
}
