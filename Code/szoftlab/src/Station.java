/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Station extends Node {
    private Color color;

    @Override
    public void accept(TrainCart tc) {
        Train t = tc.getTrain();
        Color colorToUnload = t.getColor();
        Color cartColor = tc.getColor();

        if ((this.color == cartColor) && (this.color == colorToUnload)) {
            tc.unload();
        }
    }
}
