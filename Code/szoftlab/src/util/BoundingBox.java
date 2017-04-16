package util;

import java.util.ArrayList;

/**
 * Created by Márton on 2017. 04. 16..
 * <p>
 *     TrainPartok fizikai kiterjesztése, alkalmas az elemek kirajzolására,
 *     ütközések vizsgálatára
 *</p>
 */
public class BoundingBox {
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

    /**
     * Konstruktor
     * @param middle középpont
     * @param direction irányvektor
     */
    public BoundingBox(Coordinate middle,Coordinate direction){
        setPos(middle,direction);
    }

    /**
     * Beállítja az aktuális középpontot és irányt, majd ezek segítségével kiszámolja az alakzat csúcspontjait
     * @param mid középpont
     * @param dir irány
     */
    public void setPos(Coordinate mid,Coordinate dir) {
        this.middle = middle;
        this.direction = direction;
        this.direction.normalize();
        points.add(0,new Coordinate(middle.getX()+direction.getX()-direction.getY()*0.5,middle.getY()+direction.getY()+direction.getX()*0.5));
        points.add(1,new Coordinate(middle.getX()+direction.getX()+direction.getY()*0.5,middle.getY()+direction.getY()-direction.getX()*0.5));
        points.add(2,new Coordinate(middle.getX()-direction.getX()-direction.getY()*0.5,middle.getY()-direction.getY()+direction.getX()*0.5));
        points.add(3,new Coordinate(middle.getX()-direction.getX()+direction.getY()*0.5,middle.getY()-direction.getY()-direction.getX()*0.5));

    }

    /**
     * Visszaadja a BoundingBox pontjait
     * @return
     */
    public ArrayList<Coordinate> getPoints() {
        return points;
    }

    /**
     * Összehasonlítja, hogy a paraméterben kapott BoundigBox-al ütközött-e
     * @param otherBoundingBox A másik BoundingBox amivel az ütközést vizsgáljuk
     * @return  Történt-e ütközés
     */
    public boolean isCollided(BoundingBox otherBoundingBox){
        int numOfOverLaps = 0;

        // For A axises
        for (int i = 0; i<2; i++){
            Coordinate axis1 = new Coordinate(points.get(i+1).getX() - points.get(i).getX(),
                                                points.get(i+1).getY() - points.get(i).getY());

            if (checkAxisCollision(otherBoundingBox, axis1))
                numOfOverLaps++;
            else
                return false;
        }

        // For B axises
        for (int i = 0; i<2; i++){
            Coordinate axis1 = new Coordinate(otherBoundingBox.getPoints().get(i+1).getX() - otherBoundingBox.getPoints().get(i).getX(),
                                                otherBoundingBox.getPoints().get(i+1).getY() - otherBoundingBox.getPoints().get(i).getY());

            if (checkAxisCollision(otherBoundingBox, axis1))
                numOfOverLaps++;
            else
                return false;
        }

        if (numOfOverLaps == 4)
            return true;
        else
            return false;
    }

    /**
     * Megnézi egy adott irányra van-e átfedés
     * @param otherBoundingBox A másik BoundingBox amivel az ütközést vizsgáljuk.
     * @param axis Az irány, amiben vizsgáljuk az ütközést
     * @return Történt-e ütközés
     */
    private boolean checkAxisCollision(BoundingBox otherBoundingBox, Coordinate axis){
        double Amax, Amin, Bmax, Bmin;

        // A MAX, MIN meghatározása
        Amax = Amin = getScalarForCollision(points.get(0), axis);

        for(int i = 1; i<points.size(); i++){
            double scalar = getScalarForCollision(points.get(i), axis);
            if (scalar < Amin) Amin = scalar;
            if (scalar > Amax) Amax = scalar;
        }

        // B MAX, MIN
        Bmax = Bmin = getScalarForCollision(otherBoundingBox.getPoints().get(0), axis);

        for(int i = 1; i<points.size(); i++){
            double scalar = getScalarForCollision(otherBoundingBox.getPoints().get(i), axis);
            if (scalar < Bmin) Bmin = scalar;
            if (scalar > Bmax) Bmax = scalar;
        }

        if (Bmin <= Amin || Bmax >= Amax)
            return true;

        return false;
    }

    private double getScalarForCollision(Coordinate point, Coordinate axis){
        double projConst = (point.getX() * axis.getX() + point.getY() * axis.getY())/(Math.pow(axis.getX(),2) + Math.pow(axis.getY(),2));
        double pointScalar = projConst * Math.pow(point.getX(),2) + projConst * Math.pow(point.getY(),2);
        return pointScalar;
    }
}
