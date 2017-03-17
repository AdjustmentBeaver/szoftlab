import util.Coordinate;
import util.Speed;

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

    public void addNeighbourNode(Node n) {
        Prompt.printMessage("Node.addNeighbourNode");
        neighbourNodeList.add(n);
    }
}
