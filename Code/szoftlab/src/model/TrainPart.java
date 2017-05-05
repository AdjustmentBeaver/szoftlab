package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.BoundingBox;
import model.util.Coordinate;
import model.util.IDrawable;
import view.View;

import java.io.Serializable;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Absztrakt bázisosztály vonatelemekhez. A vonatok TrainPartokból állnak, a vonatok mozgatása a TrainPartok mozgatására vezethetö vissza, ezért minden model.TrainPart mozgatja saját magát. Mozgatáskor érheti el a célpontjául szolgáló adott állomást, és visitor minta szerint meglátogatja, azaz triggereli a node-okat ha elért hozzájuk.
 * </p>
 */
public abstract class TrainPart implements Serializable, IDrawable {
    /**
     * A model.TrainPart pozíciója
     */
    protected Coordinate pos;

    /**
     *  BoundingBox az ütközésvizsgálathoz
     */
    private BoundingBox boundingBox;

    /**
     * Irány, amiben a kocsi halad
     */
    private Coordinate direction;

    /**
     * A következő csomópont ami felé halad.
     */
    protected Node nextNode;

    /**
     * Az a csomópont amit előzőleg elhagyott.
     */
    protected Node prevNode;

    /**
     * A vonat amihez tartozik.
     */
    protected Train train;

    /**
     * Üres-e a model.TrainPart
     */
    protected boolean isEmpty;

    /**
     * Megadja milyen sugarú körben triggelelődik a csomópont, aminek a közelébe ért a model.TrainPart
     */
    protected double activateRadius;

    /**
     * Konstruktor. Paraméterül kapja, melyik vonatkoz tartozik.
     *
     * @param t A vonat, amihez tartozik.
     */
    public TrainPart(Train t) {
        train = t;
        isEmpty = true;
    }

    /**
     * Beállíja a csomópontokhoz érés sugarát (Ilyen sugarú körben aktiválódik a csomópont)
     * @param activateRadius A sugár nagysága
     */
    public void setActivateRadius(double activateRadius) {
        this.activateRadius = activateRadius;
    }

    /**
     * pos értéke kérdezhető le vele
     * @return a pos aktuális éréke
     */
    public Coordinate getPos() {
        return pos;
    }

    /**
     * Beállítja a kocsi pozícióját
     * @param pos   beállítani kívánt érték, Coordinate típusú
     */
    public void setPos(Coordinate pos) {
        this.pos = pos;

        // Irány beállítása
        Coordinate nextNodePosition = nextNode.getPos();
        direction = new Coordinate( nextNodePosition.getX() - pos.getX(),
                nextNodePosition.getY() - pos.getY());
        direction.normalize();

        // BoundingBox frissítés
        boundingBox = new BoundingBox(this.pos, direction);
    }

    /**
     * Mozgatja a TrainPartot. Absztrakt, a leszármazottak valósítják meg..
     */
    public void move(){
        double speed = train.getSpeed().getSpeedAsDouble();
        // Új pozíció beállítása
        setPos(new Coordinate((pos.getX() + direction.getX() * speed), (pos.getY() + direction.getY() * speed)));
    }

    /**
     * Beállítja a kapott model.Node-ot az adott kocsi következő céljának.
     *
     * @param n A következő csomópont
     */
    public void setNextNode(Node n) {
        prevNode = nextNode;
        nextNode = n;
    }

    /**
     * Visszaadja, hogy a model.TrainPart melyik Trainhez tartozik.
     *
     * @return A vonat, amihez a model.TrainPart tartozik.
     */
    public Train getTrain() {
        return train;
    }

    /**
     * Megvizsgálja, hogy a paraméterül kapott másik trainParttal összeütközött-e.
     *
     * @param tp A másik model.TrainPart, amihez hasonlítunk.
     * @return Igaz, ha történt ütközés.
     */
    public boolean checkCollision(TrainPart tp) {
        return boundingBox.isCollided(tp.getBoundingBox());
    }

    /**
     * Visszaadja az előző csomópontot, amin tartózkodott.
     *
     * @return Az előző csomópont.
     */
    public Node getPrevNode() {
        return prevNode;
    }

    /**
     * Visszaadja a következő csomópontot, ami felé halad.
     *
     * @return A következő csomópont.
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * Visszaadja, hogy a kocsi üres-e.
     * @return Üres-e a kocsi.
     */
    public boolean isEmpty(){
        return isEmpty;
    }

    /**
     * Visszaadja a kocsi BoundigBoxát
     * @return A kocsi BoundingBoxa
     */
    public BoundingBox getBoundingBox() {
        return boundingBox;
    }

    @Override
    public String toString() {
        return "pos = "+pos+" next = ["+nextNode+"] empty = "+isEmpty;
    }

    @Override
    public void draw(View view) {
        view.draw(this);
    }
}
