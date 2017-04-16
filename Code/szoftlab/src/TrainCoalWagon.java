import util.Coordinate;

/**
 * <p>
 * A vonat része a szeneskocsi, nem csinál semmit.
 * </p>
 */
public class TrainCoalWagon extends TrainPart {

    /**
     * Konstruktor, a szeneskocsihoz.
     *
     * @param t     A Train, amihez tartozik.
     */
    public TrainCoalWagon(Train t) {
        super(t);
    }

    /**
     * A szeneskocsi mozgatása.
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
