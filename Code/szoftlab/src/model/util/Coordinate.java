package model.util;

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
    public Coordinate normalize(){
        if (length() > 0.0001 || length() < -0.0001) {
            return new Coordinate(x / length(), y / length());
        } else {
            return new Coordinate(0, 0);
        }
    }

    /**
     * Visszaadja a vektor hosszát
     * @return Vektor hossza
     */
    public double length(){
        return Math.sqrt(Math.pow(x, 2) + Math.pow(y, 2));
    }

    /**
     * Megadja egy pont távolságát egy másik ponthoz viszonyítva
     * @param p A pont amihez viszonyítunk
     * @return Távolság a ponttól
     */
    public double getDistanceTo(Coordinate p) {
        return Math.sqrt(Math.pow(x-p.getX(),2) + Math.pow(y-p.getY(),2));
    }

    public Coordinate add(Coordinate c) {
        return new Coordinate(x + c.x, y + c.y);
    }

    public Coordinate substract(Coordinate c) {
        return new Coordinate(x - c.x, y - c.y);
    }

    public Coordinate scale(double d) {
        return new Coordinate(x * d, y * d);
    }

    public static double dot(Coordinate c1, Coordinate c2){
        return c1.getX()*c2.getX()+c1.getY()*c2.getY();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
