import util.Color;
import util.Coordinate;

/**
 * Created by András on 14/04/2017.
 *<p>
 *     Az ilyen típusú állomásokról az utasok felszállnak a felszállás szabályának megfelelő kocsiba.
 *</p>
 */
public class LoaderStation extends Node{

    /**
     * Az állomás színe
     */
    private Color color;

    public LoaderStation(Coordinate pos, Color color) {
        super(pos);
        this.color =  color;
    }

    /**
     * Megivizsgálja, hogy az állomáshoz ért kocsi színe a megegyezik az állomáséval és, hogy
     * a vonaton ez az első üres kocsi.
     * @param tc Az állomáshoz érő TrainCart
     */
    @Override
    public void accept(TrainCart tc) {
        Color cartColor = tc.getColor();
        if (color == cartColor){
            tc.load();
        }

        super.accept(tc);
    }
}
