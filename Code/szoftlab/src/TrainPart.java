import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public abstract class TrainPart {
    private Coordinate pos;
    private Node nextNode;
    private Node prevNode;
    private Train train;

    public TrainPart(Train t) {
        System.out.println("TrainPart.TrainPart");
        train = t;
    }

    public abstract void move();

    public void setNextNode(Node n) {
        System.out.println("TrainPart.setNextNode");
        prevNode = nextNode;
        nextNode = n;
    }

    public Train getTrain() {
        System.out.println("TrainPart.getTrain");
        return train;
    }

    public boolean checkCollision(TrainPart tp) {
        System.out.println("TrainPart.checkCollision");
        System.out.print("[?] Collision happened?\n[>] ");
        try {
            return new BufferedReader(new InputStreamReader(System.in)).readLine().equals("Y");
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public Node getPrevNode() {
        System.out.println("TrainPart.getPrevNode");
        return prevNode;
    }

    public Node getNextNode() {
        System.out.println("TrainPart.getNextNode");
        return nextNode;
    }
}
