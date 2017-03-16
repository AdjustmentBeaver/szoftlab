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
    }

    public void cartUnloaded() {
        Prompt.printMessage("Statistics.cartUnloaded");
    }
}
