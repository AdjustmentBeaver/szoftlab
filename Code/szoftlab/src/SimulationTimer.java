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
        Prompt.addIndent("");
        for (Notifiable sub : subscribers) {
            sub.update();
        }
        Prompt.removeIndent();
    }

    public void addSubscriber(Notifiable sub) {
        Prompt.printMessage("SimulationTimer.addSubscriber");
        Prompt.addIndent("");
        subscribers.add(sub);
        Prompt.removeIndent();
    }

    public void deleteSubscriptions() {
        Prompt.printMessage("SimulationTimer.deleteSubscriptions");
        Prompt.addIndent("");
        subscribers.clear();
        Prompt.removeIndent();
    }
}
