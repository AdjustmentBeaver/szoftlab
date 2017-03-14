import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainCart extends TrainPart {
    private Color color;
    private Statistics stat;

    public TrainCart(Train t, Statistics st, Color color) {
        super(t);
        this.color = color;
        stat = st;
    }

    public Color getColor() {
        return color;
    }

    public void unload() {

    }
}
