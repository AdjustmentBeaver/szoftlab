import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainCart extends TrainPart {
    private Color color;
    private Statistics st;

    public TrainCart(Train t, Statistics st, Color color) {
        super(t);
        System.out.println("TrainCart.TrainCart");
        this.color = color;
        this.st = st;
    }

    public Color getColor() {
        System.out.println("TrainCart.getColor");
        return color;
    }

    public void unload() {
        System.out.println("TrainCart.unload");
        color = new Color("empty");
        st.cartUnloaded();
    }

    @Override
    public void move() {
        System.out.println("TrainCart.move");
        getNextNode().accept(this);
    }
}
