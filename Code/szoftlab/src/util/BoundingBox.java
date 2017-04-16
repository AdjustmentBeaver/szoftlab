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
     *
     * @param middle középpont
     * @param direction irányvektor
     */
    BoundingBox(Coordinate middle,Coordinate direction){
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
}
