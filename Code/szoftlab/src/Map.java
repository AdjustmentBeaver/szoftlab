import util.Coordinate;

import java.io.Serializable;
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
public class Map implements Serializable, Notifiable {

    private List<Node> nodeList;
    private List<Train> trainList;
    private List<Notifiable> notifiables;
    private Game game;
//    private Statistics stat;

    /**
     * Instantiates a new Map.
     * @param game A Game osztály, ami a játékot vezérli
     */
    public Map(Game game) {
        trainList = new ArrayList<>();
        nodeList = new ArrayList<>();
        notifiables = new ArrayList<>();
        this.game = game;
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
        timer.deleteSubscriptions();
        // Vonatok feliratkoztatása
        for (Train t : trainList) {
            timer.addSubscriber(t);
        }
        // Sceduler feliratkoztatása
        Notifiable scheduler = null;
        if (notifiables.size() > 0) scheduler = notifiables.get(0);
        timer.addSubscriber(scheduler);
        timer.addSubscriber(this);
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
        for (Node n : nodeList) {
            if (n.getPos() == c) {
                n.activate();
                break;
            }
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
        trainList.add(t);
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
        notifiables.add(n);
    }

    /**
     * Gets train list.
     *
     * @return the train list
     */
    public List<Train> getTrainList() {
        return trainList;
    }

    @Override
    public void update(String event) {
        // Check collision and empty
        if (event == null) {
            boolean trainExploded = false;
            boolean cartsEmpty = true;
            for (Train t: trainList) {
                if (t.isExploded()) {
                    trainExploded = true;
                }
                if (t.getColor() != null) {
                    cartsEmpty = false;
                }

                // Ne nyerje meg a jatekot
                if (t.getPartList().size() == 1){
                    cartsEmpty = false;
                }
            }
            if (trainExploded) {
                game.lost();
            } else if (cartsEmpty) {
                game.won();
            }
        } else {
            String evt[] = event.split(" ");
            if (evt.length == 0) {
                return;
            }
            int i;
            switch (evt[0]) {
                case "activate":
                    Coordinate pos = new Coordinate(Integer.parseInt(evt[1]), Integer.parseInt(evt[2]));
                    for (Node n: nodeList) {
                        if (n.getPos().getDistanceTo(pos) < 0.5) {
                            n.activate();
                        }
                    }
                    break;
                case "listNodes":
                    i = 0;
                    for (Node n: nodeList) {
                        System.out.println("Node (" + i++ + ") " + n.toString());
                    }
                    break;
                case "listTrains":
                    i = 0;
                    for (Train t: trainList) {
                        System.out.println("Train (" + i++ + ") " + t.toString());
                    }
                    break;
            }
        }
    }
}