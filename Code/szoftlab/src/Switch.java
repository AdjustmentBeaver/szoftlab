/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Switch extends Node {
    @Override
    protected Node route() {

    }

    @Override
    public void activate() {
        boolean trainOnMe = checkForTrain();
    }
}
