import java.io.Serializable;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Vonatok indításának ütemezéséért felelős osztály. A SimulationTimer triggereli, ekkor ha szükséges indítja a következő vonatot.
 * </p>
 */
public class TrainScheduler implements Notifiable, Serializable {

    private List<Train> trainList;

    private int time;

    /**
     * Konstruktor, megkapja a vonatlistát.
     *
     * @param trainList Vonatok listája, innen keres vonatot, amit tud indítani.
     */
    public TrainScheduler(List<Train> trainList) {
        this.trainList = trainList;
        time = 0;
    }

    /**
     * Notifiable interfész megvalósítása, itt történik a vonatindítási logika.
     */
    @Override
    public void update() {
        for (Train t: trainList) {
            if (!t.isRunning() && t.getStartTime() <= time) {
                t.startTrain();
            }
        }
        time++;
    }
}
