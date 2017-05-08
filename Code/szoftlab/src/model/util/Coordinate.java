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
        if (length() > 0.00001 || length() < -0.00001) {
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

    /**
     * Paramétereket ad össze, akkor használható, mikor vektorokat jelentenek a paraméterek
     * @param c a hozzáadandó koordináta
     * @return a két koordináta összege, egy új koordinátában
     */
    public Coordinate add(Coordinate c) {
        return new Coordinate(x + c.x, y + c.y);
    }

    /**
     * Két koordináta kivonása egymásból
     * vektorokhoz használható
     * @param c mit vonjunk ki
     * @return két koordináta különbsége, egy új koordinátában
     */
    public Coordinate substract(Coordinate c) {
        return new Coordinate(x - c.x, y - c.y);
    }

    /**
     * Nyújtani lehet vele
     * @param d nyújtás nagysága
     * @return egy megnyújtott koordináta
     */
    public Coordinate scale(double d) {
        return new Coordinate(x * d, y * d);
    }

    /**
     * skalár szorzás vektorokhoz
     * @param c1 vektor 1
     * @param c2 vektor 2
     * @return skalár szorzat
     */
    public static double dot(Coordinate c1, Coordinate c2){
        return c1.getX()*c2.getX()+c1.getY()*c2.getY();
    }

    /**
     * Vektoriális szorzás vektorokhoz, az értéke egy skalár lesz
     * @param c1 vektor 1
     * @param c2 vektor 2
     * @return vektor szorzat
     */
    public static double cross(Coordinate c1, Coordinate c2){
        return c1.getX()*c2.getY()-c1.getY()*c2.getX();
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}
