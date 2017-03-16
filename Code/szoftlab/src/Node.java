import util.Coordinate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Node {
    protected List<Node> neighbourNodeList;
    private Train lastTrain;
    private Coordinate pos;
    private Node visitorComingFrom;


    public Node() {
        Prompt.printMessage("Node.Node");
        neighbourNodeList = new ArrayList<>();
    }

    private void accept(TrainPart tp) {
        Prompt.printMessage("Node.accept");

        Prompt.addIndent("tp.getPrevNode()");
        visitorComingFrom = tp.getPrevNode();
        Prompt.removeIndent();

        System.out.println("[?] Tovább tudjuk irányítani a vonatot? [Y/N]");
        System.out.print("[>] ");
        if (Prompt.readBool()) {
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

    public void accept(TrainEngine te) {
        Prompt.printMessage("Node.accept(TrainEngine)");
        accept((TrainPart) te);
    }

    public void accept(TrainCart tc) {
        Prompt.printMessage("Node.accept(TrainCart)");
        accept((TrainPart) tc);
    }

    protected Node route() {
        Prompt.printMessage("Node.route");
        return this;
    }

    public void activate() {
        Prompt.printMessage("Node.activate");
    }

    protected boolean checkForTrain() {
        Prompt.printMessage("Node.checkForTrain");
        // TODO: 3/14/2017 Activate szekvencia javitas (hiba felveve)
        System.out.println("[?] Van a csomóponton vonat?");
        System.out.print("[>] ");
        return Prompt.readBool();

        /* Algoritmus node ellenorzesre
        if (lastTrain == null) {
            return false;
        }
        List<TrainPart> parts = lastTrain.getPartList();
        for (TrainPart tp : parts) {
            if (tp.getNextNode() == this) {
                return true;
            }
        }
        return false;
        */
    }

    public void addNeighbourNode(Node n) {
        Prompt.printMessage("Node.addNeighbourNode");
        neighbourNodeList.add(n);
    }
}
