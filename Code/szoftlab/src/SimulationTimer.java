import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class SimulationTimer {
    List<Notifiable> subscribers;

    public SimulationTimer() {
        Prompt.printMessage("SimulationTimer.SimulationTimer");
        subscribers = new ArrayList<>();
    }

    public void start() {
        Prompt.printMessage("SimulationTimer.start");
    }

    public void stop() {
        Prompt.printMessage("SimulationTimer.stop");
    }

    public void step() {
        Prompt.printMessage("SimulationTimer.step");
        for (Notifiable sub : subscribers) {
            Prompt.addIndent("sub.update()");
            sub.update();
            Prompt.removeIndent();
        }
    }

    public void addSubscriber(Notifiable sub) {
        Prompt.printMessage("SimulationTimer.addSubscriber");
        subscribers.add(sub);
    }

    public void deleteSubscriptions() {
        Prompt.printMessage("SimulationTimer.deleteSubscriptions");
        subscribers.clear();
    }
}
