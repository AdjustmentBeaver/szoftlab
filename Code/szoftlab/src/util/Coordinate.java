package util;

import java.io.Serializable;

/**
 * Koordinata tipus, egy pozicio tarolasara.
 */
public class Coordinate implements Serializable{
    private int x;
    private int y;

    /**
     * Konstruktor, ket dimenzios koordinata.
     *
     * @param x az x tengelyen levo pozicio
     * @param y az y tengelyen levo pozicio
     */
    public Coordinate(int x, int y) {
        this.x = x;
        this.y = y;
    }

    /**
     * Visszaadja az x tengelyen levo poziciot.
     *
     * @return az x tengelyen levo pozicio
     */
    public int getX() {
        return x;
    }

    /**
     * Visszaadja az y tengelyen levo poziciot.
     *
     * @return az y tengelyen levo pozicio
     */
    public int getY() {
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
    private int getLength(){
        return (int) Math.sqrt(Math.pow(x,2) + Math.pow(y,2));
    }
}
