import java.util.ArrayList;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Node {
    private ArrayList<Node> neighbourNodeList;
    private Train lastTrain;
    private Coordinate pos;

    public Node() {

    }

    public void accept(TrainEngine te) {

    }

    public void accept(TrainCart tc) {
    }

    protected Node route() {

    }

    public void activate() {

    }

    protected boolean checkForTrain() {
        // TODO: 3/14/2017 Activate szekvencia javitas (hiba felveve) 
    }

    public void addNeighbourNode(Node n) {

    }
}
