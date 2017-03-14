/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Game {
    private SimulationTimer timer;
    private MapManager mapManager;
    private boolean prevRunning;

    public static void main(String[] args) {
        Game game = new Game();

        game.newGame("level1");
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
