import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainScheduler implements Notifiable {
    private List<Train> trainList;

    public TrainScheduler(List<Train> trainList) {
        System.out.println("TrainScheduler.TrainScheduler");
        this.trainList = trainList;
    }

    @Override
    public void update() {
        System.out.println("TrainScheduler.update");
        System.out.println("[?] Can we start a train?");
        try {
            if (new BufferedReader(new InputStreamReader(System.in)).readLine().equals("Y")) {
                // find one that is not already running
                for (Train t : trainList) {
                    if (!t.isRunning()) {
                        t.startTrain();
                        return;
                    }
                }
                System.out.println("Every train is already running");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
