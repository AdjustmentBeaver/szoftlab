/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Game {
    private SimulationTimer timer;
    private MapManager mapManager;
    private boolean prevRunning;

    public static void main(String[] args) {
        System.out.println("Game.main");
        Game trainSimulator = new Game();
    }

    public Game() {
        System.out.println("Game.Game");
        timer = new SimulationTimer();
        mapManager = new MapManager(timer, this);
    }

    public void startGame() {
        System.out.println("Game.startGame");
        timer.start();
    }

    public void stopGame() {
        System.out.println("Game.stopGame");
        timer.stop();
    }

    public void resumeGame() {
        System.out.println("Game.resumeGame");
        if (prevRunning) {
            timer.start();
        }
    }

    public void newGame(String nextLevel) {
        System.out.println("Game.newGame");
        stopGame();
        mapManager.newMap(nextLevel);
        startGame();
    }
}
