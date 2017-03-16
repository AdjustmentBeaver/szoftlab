/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class SpecialPlace extends Node {

    public SpecialPlace() {
        Prompt.printMessage("SpecialPlace.SpecialPlace");
    }

    @Override
    public void activate() {
        boolean trainOnMe = checkForTrain();
    }
}
