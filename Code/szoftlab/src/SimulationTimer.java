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
     * Az esemenysor
     */
    List<String> events;

    /**
     * Instantiates a new Simulation timer.
     */
    public SimulationTimer() {
        subscribers = new ArrayList<>();
        events = new ArrayList<>();
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
    public void step(int stepNum) {
        for (String event: events) {
            for (Notifiable sub: subscribers) {
                sub.update(event);
            }
        }
        events.clear();
        for (int i = 0; i < stepNum; i++) {
            for (Notifiable sub : subscribers) {
                sub.update(null);
            }
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

    /**
     *
     * @param event
     */
    public void addEvent(String event) {
        events.add(event);
    }
}
