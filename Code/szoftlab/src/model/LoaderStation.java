package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.Color;
import model.util.Coordinate;
import view.View;

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
        if (hasPassengers && color.equals(cartColor) && tc.isEmpty()){
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
    public void draw(View view) {
        view.draw(this);
    }

    /**
     * Lekérhető milyen színnel rendelkezik az állomás
     * @return állomás színe
     */
    public Color getColor() {
        return color;
    }
}
