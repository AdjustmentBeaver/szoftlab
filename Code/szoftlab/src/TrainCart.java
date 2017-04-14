import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat része a kocsi, utasokat szállít, meghatározott színe van.
 * </p>
 */
public class TrainCart extends TrainPart {

    private Color color;
    private Statistics st;

    /**
     * Konstruktor, a Statistics osztályt ismeri, itt kapja meg. Beállításra kerül még, hogy melyik vonathoz tartozik.
     *
     * @param t     A vonat amihez tartozik.
     * @param st    Játék statisztikája.
     * @param color A TrainCart színe.
     */
    public TrainCart(Train t, Statistics st, Color color) {
        super(t);
        Prompt.printMessage("TrainCart.TrainCart");
        this.color = color;
        this.st = st;
    }

    /**
     *  Visszaadja a TrainCart színét.
     *
     * @return A TrainCart színe.
     */
    public Color getColor() {
        Prompt.printMessage("TrainCart.getColor");
        return color;
    }

    /**
     * Kiüríti a kocsit. Beállítja semleges színűre és triggereli a Statistics osztály ürítéseket számláló függvényét.
     */
    public void unload() {
        Prompt.printMessage("TrainCart.unload");
        color = new Color("empty");
        Prompt.addIndent("st.cartUnloaded()");
        st.cartUnloaded();
        Prompt.removeIndent();
    }

    /**
     * A TrainCart mozgása.
     */
    @Override
    public void move() {
        Prompt.printMessage("TrainCart.move");

        Prompt.addIndent("nextNode.accept(this)");
        nextNode.accept(this);
        Prompt.removeIndent();
    }

    /**
     * Utasok felszállása a kocsira
     */
    public void load() {
    }
}
