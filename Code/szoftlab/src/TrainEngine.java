import util.Coordinate;
import util.Speed;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat része a mozdony, meghatározza a vonat sebességét. Ha több mozdony lenne, tetszőleges logika szerint lehetne vonat eredő sebességét számolni.
 * </p>
 */
public class TrainEngine extends TrainPart {
    /**
     * A TrainEngine sebességét adja meg.
     */
    Speed speed;

    /**
     * Konstruktor, a mozdonyhoz.
     *
     * @param t     A Train, amihez tartozik.
     * @param speed A TrainEngine sebessége.
     */
    public TrainEngine(Train t, Speed speed) {
        super(t);
        this.speed = speed;
    }

    /**
     *  Visszaadja a TrainEngine sebességét.
     *
     * @return A TrainEngine sebessége
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * A TrainEngine mozgatása.
     */
    @Override
    public void move() {
        super.move();

        // Ha közel ér a csomóponthoz
        double length = new Coordinate(nextNode.getPos().getX() - getPos().getX(),nextNode.getPos().getY() - getPos().getY()).getLength();
        if (length < ACTIVATE_RADIUS){
            nextNode.accept(this);
        }
    }
}
