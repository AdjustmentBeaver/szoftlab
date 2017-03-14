/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Statistics {
    private Game game;

    public Statistics(Game game) {
        System.out.println("Statistics.Statistics");
        this.game = game;
    }

    public void trainExploded() {
        System.out.println("Statistics.trainExploded");
    }

    public void cartUnloaded() {
        System.out.println("Statistics.cartUnloaded");
    }
}
