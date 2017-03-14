/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Game {
    private SimulationTimer timer;
    private MapManager mapManager;
    private boolean prevRunning;

    public static void main(String[] args) {
        Game trainSimulator = new Game();
    }

    public Game() {
        timer = new SimulationTimer();
        mapManager = new MapManager(timer, this);
    }

    public void startGame() {
        timer.start();
    }

    public void stopGame() {
        timer.stop();
    }

    public void resumeGame() {
        if (prevRunning) {
            timer.start();
        }
    }

    public void newGame(String nextLevel) {
        stopGame();
        mapManager.newMap(nextLevel);
        startGame();
    }
}
