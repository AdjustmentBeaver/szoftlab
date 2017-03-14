/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class SpecialPlace extends Node {

    public SpecialPlace() {
        System.out.println("SpecialPlace.SpecialPlace");
    }

    @Override
    public void activate() {
        System.out.println("SpecialPlace.activate");
        boolean trainOnMe = checkForTrain();
    }
}
