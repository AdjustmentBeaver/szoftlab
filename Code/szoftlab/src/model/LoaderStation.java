package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.Color;
import model.util.Coordinate;

/**
 * Created by András on 14/04/2017.
 *<p>
 *     Az ilyen típusú állomásokról az utasok felszállnak a felszállás szabályának megfelelő kocsiba.
 *</p>
 */
public class LoaderStation extends Node {

    /**
     * Az állomás színe
     */
    private Color color;

    /**
     * Vannak-e utasok az állomson
     */
    boolean hasPassengers;

    /**
     * Konstruktor.
     * @param pos Az állomás helye
     * @param color Az állomás színe
     */

    public LoaderStation(Coordinate pos, Color color) {
        super(pos);
        this.color =  color;
        hasPassengers = true;
    }

    /**
     * Megivizsgálja, hogy az állomáshoz ért kocsi színe a megegyezik az állomáséval és, hogy
     * a felszállhatnak-e utasok az állomásról.
     * @param tc Az állomáshoz érő model.TrainCart
     */
    @Override
    public void accept(TrainCart tc) {
        Color cartColor = tc.getColor();
        if (hasPassengers && color.toString().equals(cartColor.toString()) && tc.isEmpty()){
            tc.load();
            hasPassengers = false;
        }

        super.accept(tc);
    }

    @Override
    public String toString() {
        return "type=loaderStation pos=" + pos + " color=" + color + " hasPassengers=" + hasPassengers;
    }

    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(javafx.scene.paint.Color.PURPLE);
        gc.setLineWidth(0);
        gc.fillRoundRect(this.pos.getX()-10, this.pos.getY()-10, 20, 20,20, 20);
    }
}
