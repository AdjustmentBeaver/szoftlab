import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

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
        Prompt.printMessage("Map.map");
        trainList = new ArrayList<>();
        nodeList = new ArrayList<>();
        notifiables = new ArrayList<>();
    }

    public void subscribe(SimulationTimer timer) {
        Prompt.printMessage("Map.subscibe");

        Prompt.addIndent("timer.deleteSubscriptions()");
        timer.deleteSubscriptions();
        Prompt.removeIndent();

        Prompt.addIndent("timer.addSubscriber(train)");
        timer.addSubscriber(trainList.get(0));
        Prompt.removeIndent();

        Notifiable scheduler = null;
        if (notifiables.size() > 0) scheduler = notifiables.get(0);

        Prompt.addIndent("timer.addSubscriver(scheduler)");
        timer.addSubscriber(scheduler);
        Prompt.removeIndent();
    }

    public void activateNode(Coordinate c) {
        Prompt.printMessage("Map.activateNode");
        System.out.println("[?] Mit szeretne aktiválni? [S]witch [T]unnel");
        System.out.print("[>] ");
        BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
        String input = "";
        try {
            input = in.readLine().toLowerCase();
        } catch (IOException e) {
            e.printStackTrace();
        }
        if (input.equals("t")) {
            Prompt.addIndent("tunnel.activate()");
            new SpecialPlace().activate();
            Prompt.removeIndent();
        } else if (input.equals("s")) {
            Prompt.addIndent("switch.activate()");
            new Switch().activate();
            Prompt.removeIndent();
        } else {
            System.out.println("Hibás bemenet!");
        }
    }

    public void addNode(Node n) {
        Prompt.printMessage("Map.addNode");
        nodeList.add(n);
    }

    public void addTrain(Train t) {
        Prompt.printMessage("Map.addTrain");
        trainList.add(t);
    }

    public void addStatistics(Statistics st) {
        Prompt.printMessage("Map.addStatistics");
        stat = st;
    }

    public void addNotifiable(Notifiable n) {
        Prompt.printMessage("Map.addNotifiable");
        notifiables.add(n);
    }

    public List<Node> getNodeList() {
        Prompt.printMessage("Map.getNodeList");
        return nodeList;
    }

    public List<Train> getTrainList() {
        Prompt.printMessage("Map.getTrainList");
        return trainList;
    }

}