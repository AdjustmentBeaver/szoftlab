package util;

import java.io.Serializable;

/**
 * Koordinata tipus, egy pozicio tarolasara.
 */
public class Coordinate implements Serializable{
    private double x;
    private double y;

    /**
     * Konstruktor, ket dimenzios koordinata.
     *
     * @param x az x tengelyen levo pozicio
     * @param y az y tengelyen levo pozicio
     */
    public Coordinate(double x, double y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Visszaadja az x tengelyen levo poziciot.
     *
     * @return az x tengelyen levo pozicio
     */
    public double getX() {
        return x;
    }

    /**
     * Visszaadja az y tengelyen levo poziciot.
     *
     * @return az y tengelyen levo pozicio
     */
    public double getY() {
        return y;
    }

    /**
     * Normalizálja a vektort
     */
    public void normalize(){
        x *= (1 /  getLength());
        y *= (1 / getLength());
    }

    /**
     * Visszaadja a vektor hosszát
     * @return Vektor hossza
     */
    private double getLength(){
        return Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }

    public double getDistanceTo(Coordinate p) {
        return Math.sqrt(Math.pow(x-p.getX(),2) + Math.pow(y-p.getY(),2));
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
