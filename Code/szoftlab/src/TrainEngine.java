import util.Speed;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainEngine extends TrainPart {
    Speed speed;

    public TrainEngine(Train t, Speed speed) {
        super(t);
        this.speed = speed;
    }

    public Speed getSpeed() {
        return speed;
    }
}
