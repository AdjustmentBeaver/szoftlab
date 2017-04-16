import util.Color;
import util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Ezeken a helyeken tudnak leszállni az emberek a kocsiból, de csak akkor, ha az állomás színével megegyező az adott kocsi, és nincs előtte nem üres. Ha adott színű kocsiból több van egymás után, az összes kiürül.
 * </p>
 */
public class Station extends Node {

    /**
     * Az állomás színe
     */
    private Color color;

    /**
     * Konstruktor, beállítható az Station színe.
     *
     * @param pos A Station pozíciója.
     * @param color Az Station színe.
     */
    public Station(Coordinate pos, Color color) {
        super(pos);
        this.color = color;
    }

    /**
     * Egy TrainCart-al való találkozáskor fut le. Elvégzi a vizsgálatot, hogy a TrainCart kiüríthető-e.
     *
     * @param tc A TrainCart, amivel iterakcióba lépett.
     */
    @Override
    public void accept(TrainCart tc) {
        Train t = tc.getTrain();
        Color colorToUnload = t.getColor();
        Color cartColor = tc.getColor();
        if (color == cartColor && color == colorToUnload){
            tc.unload();
        }
        super.accept(tc);
    }

    @Override
    public String toString() {
        return "type=station " + "pos=" + pos + " color=" + color;
    }
}
