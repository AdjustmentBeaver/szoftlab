import util.Color;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Train implements Notifiable {
    private Statistics stat;
    private List<TrainPart> trainPartList;
    private List<Train> trainList;
    private boolean isRunning;

    public Train(Statistics st, List<Train> trainList) {
        stat = st;
        this.trainList = trainList;
        isRunning = false;
    }

    public void move() {

    }

    public void explode() {

    }

    public boolean isRunning() {
        return isRunning;
    }

    public void addPart(TrainPart p) {

    }

    public Color getColor() {
        return new Color("");
    }

    public void startTrain() {
        isRunning = true;
    }

    public void checkCollision() {

    }

    public List<TrainPart> getPartList() {
        return trainPartList;
    }

    @Override
    public void update() {

    }
}
