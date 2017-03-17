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
        Prompt.addIndent("tc.getTrain()");
        Train t = tc.getTrain();
        Prompt.removeIndent();
        Prompt.addIndent("t.getColor()");
        Color colorToUnload = t.getColor();
        Prompt.removeIndent();
        Prompt.addIndent("tc.getColor()");
        Color cartColor = tc.getColor();
        Prompt.removeIndent();
        System.out.println("[?] Megegyezik a vonat színe az állomáséval? [Y/N]");
        System.out.print("[>] ");
        if (Prompt.readBool()) {
            Prompt.addIndent("tc.unload()");
            tc.unload();
            Prompt.removeIndent();
        }
        super.accept(tc);
    }
}
