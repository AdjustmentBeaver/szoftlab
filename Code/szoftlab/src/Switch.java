/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Switch extends Node {
    private Node activeNode;

    public Switch() {
        super();
        System.out.println("Switch.Switch");
        activeNode = null;
    }

    @Override
    protected Node route() {
        System.out.println("Switch.route");
        return activeNode;
    }

    @Override
    public void activate() {
        System.out.println("Switch.activate");
        boolean trainOnMe = checkForTrain();
    }

    @Override
    public void addNeighbourNode(Node n) {
        super.addNeighbourNode(n);
        activeNode = n;
    }
}
