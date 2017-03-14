import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainScheduler implements Notifiable {
    private List<Train> trainList;

    public TrainScheduler(List<Train> trainList) {
        this.trainList = trainList;
    }

    @Override
    public void update() {

    }
}
