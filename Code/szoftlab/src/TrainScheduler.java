import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class TrainScheduler implements Notifiable {
    private List<Train> trainList;

    public TrainScheduler(List<Train> trainList) {
        Prompt.printMessage("TrainScheduler.TrainScheduler");
        this.trainList = trainList;
    }

    @Override
    public void update() {
        Prompt.printMessage("TrainScheduler.update");

        System.out.println("[?] Elindulhat a vonat? [Y/N]");
        System.out.print("[>] ");
        if (Prompt.readBool()) {
            Prompt.addIndent("train.isRunning()");
            if (trainList.get(0).isRunning()) {
                return;
            }
            Prompt.removeIndent();

            Prompt.addIndent("train.startTrain()");
            trainList.get(0).startTrain();
            Prompt.removeIndent();
        }
    }
}
