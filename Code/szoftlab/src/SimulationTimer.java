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
        subscribers = new ArrayList<>();
    }

    /**
     * Start.
     */
    public void start() {

    }

    /**
     * Stop.
     */
    public void stop() {
    }

    /**
     * Lefuttat egy szimulációs lépést.
     */
    public void step() {
        for (Notifiable sub : subscribers) {
            sub.update();
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
        subscribers.add(sub);
    }

    /**
     * Delete subscriptions.
     */
    public void deleteSubscriptions() {
        subscribers.clear();
    }
}
