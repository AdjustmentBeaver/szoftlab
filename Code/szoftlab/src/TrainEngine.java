import util.Speed;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainEngine extends TrainPart {
    Speed speed;

    public TrainEngine(Train t, Speed speed) {
        super(t);
        Prompt.printMessage("TrainEngine.TrainEngine");
        this.speed = speed;
    }

    public Speed getSpeed() {
        Prompt.printMessage("TrainEngine.getSpeed");
        return speed;
    }

    @Override
    public void move() {
        Prompt.printMessage("TrainEngine.move");
        if (getNextNode() != null) {
            Prompt.addIndent("getNextNode().accept(this)");
            nextNode.accept(this);
            Prompt.removeIndent();
        }
    }
}
