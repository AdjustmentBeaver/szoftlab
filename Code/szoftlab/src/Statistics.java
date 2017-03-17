/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Statistics {
    private Game game;

    public Statistics(Game game) {
        Prompt.printMessage("Statistics.Statistics");
        this.game = game;
    }

    public void trainExploded() {
        Prompt.printMessage("Statistics.trainExploded");
        Prompt.addIndent("game.newGame(nextLevel)");
        // game.newGame("next level");
        // Azert van hogy ne adja fel a szolgalatot a szkeleton :)
        Prompt.printMessage("Game.newGame");
        Prompt.removeIndent();
    }

    public void cartUnloaded() {
        Prompt.printMessage("Statistics.cartUnloaded");
        System.out.println("[?] Ez volt az utolsó kocsi ami kiürült?");
        System.out.println("[>] ");
        if (Prompt.readBool()) {
            Prompt.addIndent("game.newGame(nextLevel)");
            // game.newGame("next level");
            // Azert van hogy ne adja fel a szolgalatot a szkeleton :) (Déjà vu)
            Prompt.printMessage("Game.newGame");
            Prompt.removeIndent();
        }
    }
}
