import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Station extends Node {
    private Color color;

    public Station(Color color) {
        Prompt.printMessage("Station.Station");
        this.color = color;
    }

    @Override
    public void accept(TrainCart tc) {
        Prompt.printMessage("Station.accept(TrainCart)");
        Train t = tc.getTrain();
        Color colorToUnload = t.getColor();
        Color cartColor = tc.getColor();
        System.out.println("[?] Megegyezik a vonat színe az állomáséval? [Y/N]");
        System.out.print("[>] ");
        if (Prompt.readBool()) {
            tc.unload();
        }
        super.accept(tc);
    }
}
