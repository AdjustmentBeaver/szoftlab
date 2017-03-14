import util.Coordinate;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Node {
    private List<Node> neighbourNodeList;
    private Train lastTrain;
    private Coordinate pos;

    public Node() {
        System.out.println("Node.Node");
    }

    public void accept(TrainEngine te) {
        System.out.println("Node.accept(TrainEngine)");
    }

    public void accept(TrainCart tc) {
        System.out.println("Node.accept(TrainCart)");
    }

    protected Node route() {
        System.out.println("Node.route");
        return new Node();
    }

    public void activate() {
        System.out.println("Node.activate");
    }

    protected boolean checkForTrain() {
        System.out.println("Node.checkForTrain");
        // TODO: 3/14/2017 Activate szekvencia javitas (hiba felveve)
        return false;
    }

    public void addNeighbourNode(Node n) {
        System.out.println("Node.addNeighbourNode");
    }
}
