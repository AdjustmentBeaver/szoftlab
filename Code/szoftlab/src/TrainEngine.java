import util.Speed;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainEngine extends TrainPart {
    Speed speed;

    public TrainEngine(Train t, Speed speed) {
        super(t);
        System.out.println("TrainEngine.TrainEngine");
        this.speed = speed;
    }

    public Speed getSpeed() {
        System.out.println("TrainEngine.getSpeed");
        return speed;
    }

    @Override
    public void move() {
        System.out.println("TrainEngine.move");
        getNextNode().accept(this);
    }
}
