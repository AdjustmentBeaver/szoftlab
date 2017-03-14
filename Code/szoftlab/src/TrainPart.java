import util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public abstract class TrainPart {
    private Coordinate pos;
    private Node nextNode;
    private Node prevNode;
    private Train train;

    public TrainPart(Train t) {
        train = t;
    }

    public void move() {

    }

    public void setNextNode(Node n) {
        prevNode = nextNode;
        nextNode = n;
    }

    public Train getTrain() {
        return train;
    }

    public boolean checkCollision(TrainPart tp) {
        return false;
    }

    public Node getPrevNode() {
        return prevNode;
    }

    public Node getNextNode() {
        return  nextNode;
    }
}
