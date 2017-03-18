/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A játékról vezet statisztikát. A TrainPartok őt értesítik, ha felrobbantak vagy kiürültek.
 * </p>
 */
public class Statistics {
    private Game game;

    /**
     * Konstruktor. Beállítja a game attribútumot.
     *
     * @param game Maga a játék. Game objektum.
     */
    public Statistics(Game game) {
        Prompt.printMessage("Statistics.Statistics");
        this.game = game;
    }

    /**
     * Train hívja amikor felrobban. Kiadja a pálya újraindítása parancsot.
     */
    public void trainExploded() {
        Prompt.printMessage("Statistics.trainExploded");
        Prompt.addIndent("game.newGame(nextLevel)");
        // game.newGame("next level");
        // Azert van hogy ne adja fel a szolgalatot a szkeleton :)
        Prompt.printMessage("Game.newGame");
        Prompt.removeIndent();
    }

    /**
     * Egy TrainPart hívja, amikor kiürült. Vizsgálja, hogy sikerült-e teljesíteni a pályát.
     */
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
