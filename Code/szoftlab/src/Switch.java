/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Switch extends Node {
    private Node activeNode;

    public Switch() {
        super();
        Prompt.printMessage("Switch.Switch");
        activeNode = null;
    }

    @Override
    protected Node route() {
        Prompt.printMessage("Switch.route");
        return activeNode;
    }

    @Override
    public void activate() {
        Prompt.printMessage("Switch.activate");
        boolean trainOnMe = checkForTrain();
    }

    @Override
    public void addNeighbourNode(Node n) {
        super.addNeighbourNode(n);
        activeNode = n;
    }
}
