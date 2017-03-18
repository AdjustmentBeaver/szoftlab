import util.Color;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Ezeken a helyeken tudnak leszállni az emberek a kocsiból, de csak akkor, ha az állomás színével megegyező az adott kocsi, és nincs előtte nem üres. Ha adott színű kocsiból több van egymás után, az összes kiürül.
 * </p>
 */
public class Station extends Node {
    private Color color;

    /**
     * Konstruktor, beállítható az Station színe.
     *
     * @param color Az Station színe.
     */
    public Station(Color color) {
        Prompt.printMessage("Station.Station");
        this.color = color;
    }

    /**
     * Egy TrainCart-al való találkozáskor fut le. Elvégzi a vizsgálatot, hogy a TrainCart kiüríthető-e.
     *
     * @param tc A TrainCart, amivel iterakcióba lépett.
     */
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
