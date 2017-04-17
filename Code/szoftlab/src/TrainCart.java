import util.Color;
import util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat része a kocsi, utasokat szállít, meghatározott színe van.
 * </p>
 */
public class TrainCart extends TrainPart {

    /**
     * Kocsi színe
     */
    private Color color;

    /**
     * Konstruktor, a Statistics osztályt ismeri, itt kapja meg. Beállításra kerül még, hogy melyik vonathoz tartozik.
     *
     * @param t     A vonat amihez tartozik.
     * @param color A TrainCart színe.
     */
    public TrainCart(Train t, Color color) {
        super(t);
        this.color = color;
        isEmpty = false;
    }

    /**
     *  Visszaadja a TrainCart színét.
     *
     * @return A TrainCart színe.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Kiüríti a kocsit. Beállítja semleges színűre és triggereli a Statistics osztály ürítéseket számláló függvényét.
     */
    public void unload() {
        System.out.println("Color: " + color + " UNLOADED");
        isEmpty = true;
        //st.cartUnloaded();
    }

    /**
     * A TrainCart mozgása.
     */
    @Override
    public void move() {
        super.move();

        // Ha közel ér a csomóponthoz
        double length = new Coordinate(nextNode.getPos().getX() - getPos().getX(),
                                        nextNode.getPos().getY() - getPos().getY())
                                        .getLength();
        if (length < activateRadius){
            nextNode.accept(this);
        }
    }

    /**
     * Utasok felszállása a kocsira
     */
    public void load() {
        System.out.println("Color: " + color + " LOADED");
        isEmpty = false;
    }
}
