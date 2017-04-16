import util.Coordinate;
import util.Speed;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */

/**
 * A TrainPartok következő úticélját határozza meg. A csomópontok gráfot alkotnak.
 */
public class Node {
    /**
     * The Neighbour node list.
     */
    protected List<Node> neighbourNodeList;
    private Train lastTrain;
    private Coordinate pos;
    private Node visitorComingFrom;

    /**
     * Instantiates a new Node.
     */
    public Node() {
        Prompt.printMessage("Node.Node");
        neighbourNodeList = new ArrayList<>();
    }

    public Coordinate getPos() {
        return pos;
    }

    private void accept(TrainPart tp) {
        Prompt.printMessage("Node.accept");

        Prompt.addIndent("tp.getPrevNode()");
        visitorComingFrom = tp.getPrevNode();
        Prompt.removeIndent();

        System.out.println("[?] Tovább tudjuk irányítani a vonatot? [Y/N]");
        System.out.print("[>] ");
        if (!Prompt.readBool()) {
            Prompt.addIndent("tp.getTrain()");
            lastTrain = tp.getTrain();
            Prompt.removeIndent();

            Prompt.addIndent("t.explode()");
            lastTrain.explode();
            Prompt.removeIndent();
        }

        Prompt.addIndent("tp.setNextNode(this.route())");
        tp.setNextNode(route());
        Prompt.removeIndent();
    }

    /**
     * A csomópont irányítja a TrainEnginet a következő állomás felé, ha az létezik.
     *
     * @param te the TrainEngine
     */
    public void accept(TrainEngine te) {
        Prompt.printMessage("Node.accept(TrainEngine)");
        accept((TrainPart) te);
    }

    /**
     * A csomópont irányítja a TrainCartot a következő állomás felé, ha az létezik.
     *
     * @param tc the TrainCart
     */
    public void accept(TrainCart tc) {
        Prompt.printMessage("Node.accept(TrainCart)");
        accept((TrainPart) tc);
    }

    /**
     * Megnézi honnan jött az aktuális TrainPart, és a másik node felé irányítja, mint ahonnan jött.
     *
     * @return the node
     */
    protected Node route() {
        Prompt.printMessage("Node.route");
        return this;
    }

    /**
     * A felhaszáló ezzel tudja a node-hoz tartozó logikát aktiválni. Alapértelmezetten ugyan semmi nem történik, de a leszármazottak felüldefiniálják ezt.
     */
    public void activate() {
        Prompt.printMessage("Node.activate");
    }

    /**
     * Ellenörzi, hogy van-e a node-on vonat jelenleg.
     * <p>
     * A node minden parttal történt interakciónál megjegyzi, hogy mi volt a hozzá tartozó vonat. (lastTrain)
     * Az ellenőrzésnél lekéri a vonat partListáját, és megnézi mindre, kivétel a legelsöre, a getNextNode() függvénnyel,
     * hogy mi a következő célja. Ha nem a node saját maga, akkor már nincs rajta a vonat.
     * </p>s
     *
     * @return true ha van rajta vonat
     */
    protected boolean checkForTrain() {
        Prompt.printMessage("Node.checkForTrain");
        Prompt.addIndent("lastTrain.getpartList()");
        // for each part in lastTrain.getPartList
        Prompt.supressMessages(true);
        if (lastTrain == null) {
            // Some kind of magic if nothing visited the node yet, because the program would crash otherwise
            Train tr = new Train(new Statistics(new Game()), new ArrayList<>());
            tr.addPart(new TrainEngine(tr, new Speed(0)));
            lastTrain = tr;
            // End of magic
        }
        Prompt.supressMessages(false);
        TrainPart part = lastTrain.getPartList().get(0);
        Prompt.removeIndent();
        // if part.getNextNode() == this
        Prompt.addIndent("part.getNextNode()");
        part.getNextNode();
        Prompt.removeIndent();
        System.out.println("[?] Van a csomóponton vonat? [Y/N]");
        System.out.print("[>] ");
        return Prompt.readBool();
    }

    /**
     * Add neighbour node.
     *
     * @param n the Node
     */
    public void addNeighbourNode(Node n) {
        Prompt.printMessage("Node.addNeighbourNode");
        neighbourNodeList.add(n);
    }
}
