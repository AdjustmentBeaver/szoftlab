package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.Coordinate;

/**
 * <p>
 * A vonat része a szeneskocsi, nem csinál semmit.
 * </p>
 */
public class TrainCoalWagon extends TrainPart {

    /**
     * Konstruktor, a szeneskocsihoz.
     *
     * @param t     A model.Train, amihez tartozik.
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
        if (nextNode.getPos().substract(pos).length() <= activateRadius){
            nextNode.accept(this);
        }
    }

}
