import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Vonatok indításának ütemezéséért felelős osztály. A SimulationTimer triggereli, ekkor ha szükséges indítja a következő vonatot.
 * </p>
 */
public class TrainScheduler implements Notifiable {

    private List<Train> trainList;

    /**
     * Konstruktor, megkapja a vonatlistát.
     *
     * @param trainList Vonatok listája, innen keres vonatot, amit tud indítani.
     */
    public TrainScheduler(List<Train> trainList) {
        Prompt.printMessage("TrainScheduler.TrainScheduler");
        this.trainList = trainList;
    }

    /**
     * Notifiable interfész megvalósítása, itt történik a vonatindítási logika.
     */
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
