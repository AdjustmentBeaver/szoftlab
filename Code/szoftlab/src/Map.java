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

/**
 * A pálya tartalmazza a mindenkori játékállást. <br>
 * A pályán találhatóak meg a vonatok és a csomópontok, illetve a pálya adminisztrációjához szükséges objektumok (Statistics, TrainScheduler).
 */
public class Map {

    private List<Node> nodeList;
    private List<Train> trainList;
    private List<Notifiable> notifiables;
    private Statistics stat;


    /**
     * Instantiates a new Map.
     */
    public Map() {
        Prompt.printMessage("Map.map");
        trainList = new ArrayList<>();
        nodeList = new ArrayList<>();
        notifiables = new ArrayList<>();
    }

    /**
     * Subscribe.
     * <p>
     * A maphoz tartozó vonatok, TrainScheduler feliratkoztatása az időzítőre.
     * </p>
     *
     * @param timer the timer
     */
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

    /**
     * Activate node.
     * <p>
     * Megkeresi ahhoz a koordinátához legközelebb eső Node-ot (egy környezeten belül), amit kapott. <br>
     * Ezen meghívja az activate() függvényt, amire az adott node logikája végrehajtódik.
     * </p>
     *
     * @param c the Coordinate
     */
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
        switch (input) {
            case "t":
                Prompt.addIndent("tunnel.activate()");
                // A little bit of magic here...
                SpecialPlace tunnel = (SpecialPlace) nodeList.get(1);
                // End of magic
                tunnel.activate();
                Prompt.removeIndent();
                break;
            case "s":
                Prompt.addIndent("switch.activate()");
                // A little bit of magic here again...
                Switch sw = (Switch) nodeList.get(0);
                // End of magic
                sw.activate();
                Prompt.removeIndent();
                break;
            default:
                System.out.println("Hibás bemenet!");
                break;
        }
    }

    /**
     * Add node.
     * <p>
     * Csomópont hozzáadása a nodeListhez.
     * </p>
     *
     * @param n the Node
     */
    public void addNode(Node n) {
        Prompt.printMessage("Map.addNode");
        nodeList.add(n);
    }

    /**
     * Add train.
     * <p>
     * Vonat hozzáadása a pályához.
     * </p>
     *
     * @param t the Train
     */
    public void addTrain(Train t) {
        Prompt.printMessage("Map.addTrain");
        trainList.add(t);
    }

    /**
     * Add statistics.
     * <p>
     * Statisztika hozzáadása a pályához.
     * </p>
     *
     * @param st the Statistics
     */
    public void addStatistics(Statistics st) {
        Prompt.printMessage("Map.addStatistics");
        stat = st;
    }

    /**
     * Add notifiable.
     * <p>
     * Statisztika hozzáadása a pályához. Ez jelenleg egy trainScheduler-t jelenthet.
     * </p>
     *
     * @param n the Notifiable
     */
    public void addNotifiable(Notifiable n) {
        Prompt.printMessage("Map.addNotifiable");
        notifiables.add(n);
    }

    /**
     * Gets train list.
     *
     * @return the train list
     */
    public List<Train> getTrainList() {
        Prompt.printMessage("Map.getTrainList");
        return trainList;
    }

}