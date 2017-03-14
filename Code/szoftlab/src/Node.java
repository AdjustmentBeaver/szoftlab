import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
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
        System.out.println("Node.Node");
        neighbourNodeList = new ArrayList<>();
    }

    private void accept(TrainPart tp) {
        lastTrain = tp.getTrain();
        visitorComingFrom = tp.getPrevNode();
        Node destination = route();
        if (destination == null) {
            lastTrain.explode();
        }
    }

    public void accept(TrainEngine te) {
        System.out.println("Node.accept(TrainEngine)");
        accept((TrainPart) te);
    }

    public void accept(TrainCart tc) {
        System.out.println("Node.accept(TrainCart)");
        accept((TrainPart) tc);
    }

    protected Node route() {
        System.out.println("Node.route");
        Node destination = null;
        for (Node n : neighbourNodeList) {
            if (n != visitorComingFrom) destination = n;
        }
        return destination;
    }

    public void activate() {
        System.out.println("Node.activate");
    }

    protected boolean checkForTrain() {
        System.out.println("Node.checkForTrain");
        // TODO: 3/14/2017 Activate szekvencia javitas (hiba felveve)
        System.out.println("[?] Force place a train on me?");
        try {
            if (new BufferedReader(new InputStreamReader(System.in)).readLine().equals("Y")) return true;
        } catch (IOException e) {
            e.printStackTrace();
        }
        List<TrainPart> parts = lastTrain.getPartList();
        for (TrainPart tp : parts) {
            if (tp.getNextNode() == this)
                return true;
        }
        return false;
    }

    public void addNeighbourNode(Node n) {
        System.out.println("Node.addNeighbourNode");
        neighbourNodeList.add(n);
    }
}
