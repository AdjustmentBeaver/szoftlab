/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Switch extends Node {

    public Switch() {
    System.out.println("Switch.Switch");
}

    @Override
    protected Node route() {
        System.out.println("Switch.route");
        return new Node();
    }

    @Override
    public void activate() {
        System.out.println("Switch.activate");
        boolean trainOnMe = checkForTrain();
    }
}
