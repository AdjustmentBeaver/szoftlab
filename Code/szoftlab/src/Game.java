import util.Coordinate;

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
        MapManager mapManager = new MapManager(timer, this);
        Train train = new Train(new Statistics(this), new ArrayList<Train>());
        while (run) {
            System.out.println("Válasszon az alábbi lehetőségek közűl");
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
            int input = Prompt.readCommand();
            switch (input) {
                case 1:
                    startGame();
                    break;
                case 2:
                    stopGame();
                    break;
                case 3:
                    newGame("level1");
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
                    Node node = new Node();
                    node.activate();
                    break;
                case 11:
                    run = false;
                    break;
            }
        }
    }

    public Game() {
        System.out.println("Game.Game");
        System.out.println("### sd_init ###");
        timer = new SimulationTimer();
        mapManager = new MapManager(timer, this);
        System.out.println("### END sd_init ###");
    }

    public void startGame() {
        System.out.println("### sd_startGame ###");
        System.out.println("Game.startGame");
        timer.start();
        System.out.println("### END sd_startGame ###");
    }

    public void stopGame() {
        System.out.println("### sd_stopGame ###");
        System.out.println("Game.stopGame");
        timer.stop();
        System.out.println("### END sd_stopGame ###");
    }

    public void resumeGame() {
        System.out.println("### sd_resumeGame ###");
        System.out.println("Game.resumeGame");
        if (prevRunning) {
            timer.start();
        }
        System.out.println("### END sd_resumeGame ###");
    }

    public void newGame(String nextLevel) {
        System.out.println("### sd_newGame ###");
        System.out.println("Game.newGame");
        stopGame();
        mapManager.newMap(nextLevel);
        startGame();
        System.out.println("### END sd_newGame ###");
    }
}
