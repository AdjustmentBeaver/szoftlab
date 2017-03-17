import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainCart extends TrainPart {
    private Color color;
    private Statistics st;

    public TrainCart(Train t, Statistics st, Color color) {
        super(t);
        Prompt.printMessage("TrainCart.TrainCart");
        this.color = color;
        this.st = st;
    }

    public Color getColor() {
        Prompt.printMessage("TrainCart.getColor");
        return color;
    }

    public void unload() {
        Prompt.printMessage("TrainCart.unload");
        color = new Color("empty");
        Prompt.addIndent("st.cartUnloaded()");
        st.cartUnloaded();
        Prompt.removeIndent();
    }

    @Override
    public void move() {
        Prompt.printMessage("TrainCart.move");

        Prompt.addIndent("nextNode.accept(this)");
        nextNode.accept(this);
        Prompt.removeIndent();
    }
}
