import java.io.BufferedReader;
import java.io.InputStreamReader;

/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */
public class Map {

    private List<Node> nodeList;
    private List<Train> trainList;
    private List<Notifiable> notifiables;
    private Statistics stat;


    public Map() {
        System.out.println("Map.map");
    }

    public void subscribe(SimulationTimer timer) {
        System.out.println("Map.subscibe");
        timer.deleteSubscriptions();
        for (Train train : trainList) {
            timer.addSubscriber(train);
        }
        TrainScheduler scheduler = notifiables[0];
        timer.addSubscriber(scheduler);
    }

    public void activateNode(Coordinate c) {
        System.out.println("Map.activateNode");
        System.out.println("[?] What do you want to activate? [Sw]itch [T]unnel");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = in.readLine();
        if (input.equals("T")) {
            new Tunnel().activate();
        } else {
            new Switch().activate();
        }
    }

    public void addNode(Node n) {
        System.out.println("Map.addNode");
        nodeList.add(n);
    }

    public void addTrain(Train t) {
        System.out.println("Map.addTrain");
        trainList.add(t);
    }

    public void addStatistics(Statistics st) {
        System.out.println("Map.addStatistics");
        stat = st;
    }

    public void addNotifiable(Notifiable n) {
        System.out.println("Map.addNotifiable");
        notifiables.add(n);
    }

    public List<Node> getNodeList() {
        System.out.println("Map.getNodeList");
        return nodeList;
    }

    public List<Train> getTrainList() {
        System.out.println("Map.getTrainList");
        return trainList;
    }

}