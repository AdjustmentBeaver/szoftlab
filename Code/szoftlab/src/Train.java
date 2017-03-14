import util.Color;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Train implements Notifiable {
    private Statistics stat;
    private List<TrainPart> trainPartList;
    private List<Train> trainList;

    public Train(Statistics st, List<Train> trainList) {
        stat = st;
        this.trainList = trainList;
    }

    public void move() {

    }

    public void explode() {

    }

    public boolean isRunning() {

    }

    public void addPart(TrainPart p) {

    }

    public Color getColor() {

    }

    public void startTrain() {

    }

    public void checkCollision() {

    }

    public List<TrainPart> getPartList() {

    }

    @Override
    public void update() {

    }
}
