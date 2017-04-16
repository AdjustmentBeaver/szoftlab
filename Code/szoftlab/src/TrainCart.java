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
     *
     */
    private Coordinate direction;

    /**
     * Konstruktor, a Statistics osztályt ismeri, itt kapja meg. Beállításra kerül még, hogy melyik vonathoz tartozik.
     *
     * @param t     A vonat amihez tartozik.
     * @param color A TrainCart színe.
     */
    public TrainCart(Train t, Color color) {
        super(t);
        this.color = color;
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
        isEmpty = true;
        //st.cartUnloaded();
    }

    /**
     * A TrainCart mozgása.
     */
    @Override
    public void move() {
        // Irány beállítása - akkor is működjön ha az első csomópontnál van
        Coordinate nextNodePosition = nextNode.getPos();
        direction = new Coordinate( nextNodePosition.getX() - getPos().getX(),
                                    nextNodePosition.getY() - getPos().getY());
        direction.normalize();

        // Új pozíció beállítása
        setPos(new Coordinate(getPos().getX() + direction.getX(), getPos().getY() + direction.getY()));

        // Jelenleg körös megvalósítás
        int R = 2;
        if (Math.sqrt(Math.pow(getPos().getX(),2) + Math.pow(getPos().getY(),2)) < R){
            nextNode.accept(this);
        }
    }

    /**
     * Utasok felszállása a kocsira
     */
    public void load() {
        isEmpty = false;
    }
}
