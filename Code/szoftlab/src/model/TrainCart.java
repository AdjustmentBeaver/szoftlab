package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.Color;
import model.util.Coordinate;
import view.View;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat része a kocsi, utasokat szállít, meghatározott színe van.
 * </p>
 */
public class TrainCart extends TrainPart {

    /**
     * Kocsi színe
     */
    private Color color;

    /**
     * Konstruktor, a Statistics osztályt ismeri, itt kapja meg. Beállításra kerül még, hogy melyik vonathoz tartozik.
     *
     * @param t     A vonat amihez tartozik.
     * @param color A model.TrainCart színe.
     */
    public TrainCart(Train t, Color color) {
        super(t);
        this.color = color;
        isEmpty = false;
    }

    /**
     *  Visszaadja a model.TrainCart színét.
     *
     * @return A model.TrainCart színe.
     */
    public Color getColor() {
        return color;
    }

    /**
     * Kiüríti a kocsit. Beállítja semleges színűre és triggereli a Statistics osztály ürítéseket számláló függvényét.
     */
    public void unload() {
        isEmpty = true;
    }

    /**
     * A model.TrainCart mozgása.
     */
    @Override
    public void move() {
        super.move();

        // Ha közel ér a csomóponthoz
        if (nextNode.getPos().substract(pos).length() <= activateRadius){
            nextNode.accept(this);
        }
    }

    /**
     * Utasok felszállása a kocsira
     */
    public void load() {
        isEmpty = false;
    }

    @Override
    public void draw(View view) {
        view.draw(this);
    }
}
