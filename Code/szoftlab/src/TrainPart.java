import util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public abstract class TrainPart {
    private Coordinate pos;
    protected Node nextNode;
    protected Node prevNode;
    protected Train train;

    public TrainPart(Train t) {
        Prompt.printMessage("TrainPart.TrainPart");
        train = t;
    }

    public abstract void move();

    public void setNextNode(Node n) {
        Prompt.printMessage("TrainPart.setNextNode");
        prevNode = nextNode;
        nextNode = n;
    }

    public Train getTrain() {
        Prompt.printMessage("TrainPart.getTrain");
        return train;
    }

    public boolean checkCollision(TrainPart tp) {
        Prompt.printMessage("TrainPart.checkCollision");
        System.out.println("[?] Történt ütközés? [Y/N]");
        System.out.print("[>] ");
        return Prompt.readBool();
    }

    public Node getPrevNode() {
        Prompt.printMessage("TrainPart.getPrevNode");
        return prevNode;
    }

    public Node getNextNode() {
        Prompt.printMessage("TrainPart.getNextNode");
        return nextNode;
    }
}
