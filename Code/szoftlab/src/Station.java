import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Station extends Node {
    private Color color;

    public Station(Color color) {
        System.out.println("Station.Station");
        this.color = color;
    }

    @Override
    public void accept(TrainCart tc) {
        super.accept(tc);
        System.out.println("Station.accept(TrainCart)");
        Train t = tc.getTrain();
        Color colorToUnload = t.getColor();
        Color cartColor = tc.getColor();

        if ((this.color == cartColor) && (this.color == colorToUnload)) {
            tc.unload();
        }
    }
}
