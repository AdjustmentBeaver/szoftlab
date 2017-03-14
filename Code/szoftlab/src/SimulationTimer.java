/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class SimulationTimer {
    public SimulationTimer() {
        System.out.println("SimulationTimer.SimulationTimer");
    }

    public void start() {
        System.out.println("SimulationTimer.start");
    }

    public void stop() {
        System.out.println("SimulationTimer.stop");
    }

    public void addSubscriber(Notifiable sub) {
        System.out.println("SimulationTimer.addSubscriber");
    }

    public void deleteSubscriptions() {
        System.out.println("SimulationTimer.deleteSubscriptions");
    }
}
