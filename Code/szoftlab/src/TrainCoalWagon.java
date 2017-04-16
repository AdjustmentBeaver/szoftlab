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
        if (getNextNode() != null) {
            nextNode.accept(this);
        }
    }
}
