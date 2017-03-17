import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Game {
    private SimulationTimer timer;
    private MapManager mapManager;
    private boolean prevRunning;


    public static void main(String[] args) {
        Game game = new Game();
        game.loop();

    }

    private void loop() {
        boolean run = true;
        Prompt.supressMessages(true);
        MapManager mapManager = new MapManager(timer, this);
        mapManager.newMap("level1");
        Map map = new MapBuilder("testLevel").buildMap(this);
        map.subscribe(timer);

        List<Train> trainList = map.getTrainList();
        Train train = trainList.get(0);
        Train train2 = trainList.get(0);
        train2.startTrain();

        Prompt.supressMessages(false);

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
                    Prompt.supressMessages(true);
                    MapBuilder mb = new MapBuilder("level1");
                    Map map2 = mb.buildMap(this);
                    SimulationTimer timer2 = new SimulationTimer();
                    map2.subscribe(timer2);
                    List<Train> trains = map2.getTrainList();
                    for (Train t : trains) {
                        t.startTrain();
                    }
                    Prompt.supressMessages(false);
                    timer2.step();
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
                    map.activateNode(new Coordinate());
                    break;
                case 11:
                    run = false;
                    break;
            }
        }
    }

    public Game() {
        Prompt.printMessage("Game.Game");

        Prompt.addIndent("<<create>>");
        timer = new SimulationTimer();
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        mapManager = new MapManager(timer, this);
        Prompt.removeIndent();
    }

    public void startGame() {
        Prompt.printMessage("Game.startGame");
        Prompt.addIndent("timer.start()");
        timer.start();
        Prompt.removeIndent();
    }

    public void stopGame() {
        Prompt.printMessage("Game.stopGame");
        Prompt.addIndent("timer.stop()");
        timer.stop();
        Prompt.removeIndent();
    }

    public void resumeGame() {
        Prompt.printMessage("Game.resumeGame");

        System.out.println("[?] Futott a játék előzőleg?");
        System.out.print("[>] ");
        prevRunning = Prompt.readBool();

        if (prevRunning) {
            Prompt.addIndent("timer.start()");
            timer.start();
            Prompt.removeIndent();
        }
    }

    public void newGame(String nextLevel) {
        Prompt.printMessage("Game.newGame");

        Prompt.addIndent("game.stopGame()");
        stopGame();
        Prompt.removeIndent();

        Prompt.addIndent("mapManager.newMap(nextLevel)");
        mapManager.newMap(nextLevel);
        Prompt.removeIndent();
        startGame();
    }
}
