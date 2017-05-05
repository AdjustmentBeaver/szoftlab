package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.Coordinate;
import model.util.Speed;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat része a mozdony, meghatározza a vonat sebességét. Ha több mozdony lenne, tetszőleges logika szerint lehetne vonat eredő sebességét számolni.
 * </p>
 */
public class TrainEngine extends TrainPart {
    /**
     * A model.TrainEngine sebességét adja meg.
     */
    Speed speed;

    /**
     * Konstruktor, a mozdonyhoz.
     *
     * @param t     A model.Train, amihez tartozik.
     * @param speed A model.TrainEngine sebessége.
     */
    public TrainEngine(Train t, Speed speed) {
        super(t);
        this.speed = speed;
    }

    /**
     *  Visszaadja a model.TrainEngine sebességét.
     *
     * @return A model.TrainEngine sebessége
     */
    public Speed getSpeed() {
        return speed;
    }

    /**
     * A model.TrainEngine mozgatása.
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
