import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */

/**
 * A szimuláció időzítéséért felelős. Meghatározott időközönként értesíti a szimulációban részt vevő objektumokat.
 */
public class SimulationTimer {
    /**
     * The Subscribers.
     */
    List<Notifiable> subscribers;

    /**
     * Instantiates a new Simulation timer.
     */
    public SimulationTimer() {
        Prompt.printMessage("SimulationTimer.SimulationTimer");
        subscribers = new ArrayList<>();
    }

    /**
     * Start.
     */
    public void start() {
        Prompt.printMessage("SimulationTimer.start");
    }

    /**
     * Stop.
     */
    public void stop() {
        Prompt.printMessage("SimulationTimer.stop");
    }

    /**
     * Lefuttat egy szimulációs lépést.
     */
    public void step() {
        Prompt.printMessage("SimulationTimer.step");
        for (Notifiable sub : subscribers) {
            Prompt.addIndent("sub.update()");
            sub.update();
            Prompt.removeIndent();
        }
    }

    /**
     * Add subscriber.
     * <p>
     * Az ütemezni kívánt objektumokhoz adja a paraméterül kapott Notifable interfészt megvalósító interfészt.
     * </p>
     *
     * @param sub the subscriber
     */
    public void addSubscriber(Notifiable sub) {
        Prompt.printMessage("SimulationTimer.addSubscriber");
        subscribers.add(sub);
    }

    /**
     * Delete subscriptions.
     */
    public void deleteSubscriptions() {
        Prompt.printMessage("SimulationTimer.deleteSubscriptions");
        subscribers.clear();
    }
}
