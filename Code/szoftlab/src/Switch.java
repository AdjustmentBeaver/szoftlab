/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Switch extends Node {
    private boolean trainOnMe;

    @Override
    protected Node route() {

    }

    @Override
    public void activate() {
        trainOnMe = checkForTrain();
    }
}
