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
        double length = new Coordinate(nextNode.getPos().getX() - getPos().getX(),
                                        nextNode.getPos().getY() - getPos().getY())
                                        .getLength();
        if (length < activateRadius){
            nextNode.accept(this);
        }
    }

    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.BLACK);
        gc.setLineWidth(0);
        gc.fillRoundRect(this.pos.getX()-10, this.pos.getY()-10, 20, 20,20, 20);
    }
}
