import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class SimulationTimer {
    List<Notifiable> subscribers;

    public SimulationTimer() {
        System.out.println("SimulationTimer.SimulationTimer");
        subscribers = new ArrayList<>();
    }

    public void start() {
        System.out.println("SimulationTimer.start");
    }

    public void stop() {
        System.out.println("SimulationTimer.stop");
    }

    public void step() {
        System.out.println("SimulationTimer.step");
        for (Notifiable sub : subscribers) {
            sub.update();
        }
    }

    public void addSubscriber(Notifiable sub) {
        System.out.println("SimulationTimer.addSubscriber");
        subscribers.add(sub);
    }

    public void deleteSubscriptions() {
        System.out.println("SimulationTimer.deleteSubscriptions");
        subscribers.clear();
    }
}
