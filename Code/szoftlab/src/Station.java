import util.Color;

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
     * @param color Az Station színe.
     */
    public Station(Color color) {
        super();
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
}
