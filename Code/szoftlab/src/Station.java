import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Station extends Node {
    private Color color;

    public Station() {
        System.out.println("Station.Station");
    }

    @Override
    public void accept(TrainCart tc) {
        System.out.println("Station.accept(TrainCart)");
        Train t = tc.getTrain();
        Color colorToUnload = t.getColor();
        Color cartColor = tc.getColor();

        if ((this.color == cartColor) && (this.color == colorToUnload)) {
            tc.unload();
        }
    }
}
