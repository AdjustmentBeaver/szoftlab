package model.util;

import java.io.Serializable;
import java.util.ArrayList;

/**
 * Created by Márton on 2017. 04. 16..
 * <p>
 * TrainPartok fizikai kiterjesztése, alkalmas az elemek kirajzolására,
 * ütközések vizsgálatára
 * </p>
 */
public class BoundingBox implements Serializable {
    /**
     * Az alakzat közepe
     */
    private Coordinate middle;
    /**
     * Az alakzat iránya, vektor
     */
    private Coordinate direction;
    /**
     * Az alakzat csúcsai
     */
    private ArrayList<Coordinate> points;
    private double radius;

    /**
     * Konstruktor
     *
     * @param middle    középpont
     * @param direction irányvektor
     */
    public BoundingBox(Coordinate middle, Coordinate direction, int w, int h) {
        points = new ArrayList<>();
        setPos(middle, direction, w, h);
        radius = middle.getDistanceTo(points.get(0));
    }

    public double getRadius() {
        return radius;
    }

    /**
     * Beállítja az aktuális középpontot és irányt, majd ezek segítségével kiszámolja az alakzat csúcspontjait
     *
     * @param mid középpont
     * @param dir irány
     */
    private void setPos(Coordinate mid, Coordinate dir, double w, double h) {
        this.middle = mid;
        this.direction = dir;
        w *= 0.5;
        h *= 0.5;
        Coordinate n = new Coordinate(-direction.getY(), direction.getX()).scale(h);
        dir = direction.scale(w);
        points.add(0, middle.add(dir).add(n));
        points.add(1, middle.substract(dir).add(n));
        points.add(2, middle.substract(dir).substract(n));
        points.add(3, middle.add(dir).substract(n));
    }

    /**
     * Visszaadja a BoundingBox pontjait
     *
     * @return
     */
    public ArrayList<Coordinate> getPoints() {
        return points;
    }

    /**
     * Összehasonlítja, hogy a paraméterben kapott BoundigBox-al ütközött-e
     *
     * @param otherBoundingBox A másik BoundingBox amivel az ütközést vizsgáljuk
     * @return Történt-e ütközés
     */
    public boolean isCollided(BoundingBox otherBoundingBox) {
        if (radius + otherBoundingBox.radius < middle.getDistanceTo(otherBoundingBox.getMiddle()))
            return false;

        Coordinate axises[] = {
                points.get(0).substract(points.get(1)),
                points.get(0).substract(points.get(3)),
                otherBoundingBox.getPoints().get(1).substract(otherBoundingBox.getPoints().get(2)),
                otherBoundingBox.getPoints().get(1).substract(otherBoundingBox.getPoints().get(0))
        };
        for (Coordinate axis : axises) {
            if (!checkAxisCollision(otherBoundingBox, axis))
                return false;
        }
        return true;
    }

    /**
     * Megnézi egy adott irányra van-e átfedés
     *
     * @param otherBoundingBox A másik BoundingBox amivel az ütközést vizsgáljuk.
     * @param axis             Az irány, amiben vizsgáljuk az ütközést
     * @return Történt-e ütközés
     */
    private boolean checkAxisCollision(BoundingBox otherBoundingBox, Coordinate axis) {
        double Amax, Amin, Bmax, Bmin;

        // A MAX, MIN meghatározása
        Amax = Amin = getScalarForCollision(points.get(0), axis);

        for (int i = 1; i < points.size(); i++) {
            double scalar = getScalarForCollision(points.get(i), axis);
            if (scalar < Amin) Amin = scalar;
            if (scalar > Amax) Amax = scalar;
        }

        // B MAX, MIN
        Bmax = Bmin = getScalarForCollision(otherBoundingBox.getPoints().get(0), axis);

        for (int i = 1; i < points.size(); i++) {
            double scalar = getScalarForCollision(otherBoundingBox.getPoints().get(i), axis);
            if (scalar < Bmin) Bmin = scalar;
            if (scalar > Bmax) Bmax = scalar;
        }

        return Bmin <= Amax && Bmax >= Amin;
    }

    /**
     * Mehatározza egy pont vetületét egy tengelyre (irányra)
     *
     * @param point A pont amit vetítünk
     * @param axis  A tengely (irnány) amire vetítünk
     * @return A tengelyen elfoglalt hely - Skalár szám
     */
    private double getScalarForCollision(Coordinate point, Coordinate axis) {
        return Coordinate.dot(axis.scale(Coordinate.dot(point, axis) / Coordinate.dot(axis, axis)), axis);
    }

    public Coordinate getMiddle() {
        return middle;
    }
}
