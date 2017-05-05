package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Egy kereszteződésben ezek segítségével állítható, merre szeretnénk, hogy a vonat továbbhaladjon. Ha a két irány, amelyek irányába állítható a váltó, egyikéből jön a vonat, mindig a harmadik irányba halad tovább. A váltó nem állítható, ha vonat tartózkodik rajta.
 * </p>
 */
public class Switch extends Node {

    /**
     * A váltó aktív iránya
     */
    private Node activeNode;

    /**
     * A váltó gyökércsomópontja, ahonnan az elágazás indul.
     */
    private Node rootNode;

    /**
     * Konstruktor, létrehozza a Switchet.
     */
    public Switch(Coordinate pos) {
        super(pos);
        activeNode = null;
    }

    /**
     * Meghatározza a vonat továbbhaladási irányát. Ha a vonat a root felől jön, akkor az aktív node felé irányíja, ha pedig a másik irányok valamelyikéből, akkor a root felé fog vezetni az irány.
     * @return  A model.Switch aktív csomópontja.
     */
    @Override
    protected Node route() {
        // Ha Root felől jött
        if (visitorComingFrom == rootNode)
            return activeNode;
        else
            return rootNode;

    }

    /**
     * Elvégzi a váltó állításhoz szükséges logikát. Kiválasztja a haladási irányt, ha a nem tartozkódik rajta vonat.
     */
    @Override
    public void activate() {
        boolean trainOnMe = checkForTrain();

        if (!trainOnMe){
            // Ha a kettes az aktív
            if (activeNode == neighbourNodeList.get(2))
                // Aktiváljuk az egyest
                activeNode = neighbourNodeList.get(1);
            else if (neighbourNodeList.size() >= 3)
                // Aktiváljuk a kettest
                activeNode = neighbourNodeList.get(2);
            else
                // Ha nincs kettes, akkor vakvágány
                activeNode = null;

            System.out.println("activate " + name + " activeNode:" + activeNode);
        }

    }

    /**
     * A Switchel szomszédos csomópontok hozzáadása.
     * @param n Szomszédos csomópont.
     */
    @Override
    public void addNeighbourNode(Node n) {
        // Az első csomópont a gyökércsomópont
        if (neighbourNodeList.size() == 0) rootNode = n;
        super.addNeighbourNode(n);
        activeNode = n;
    }

    @Override
    public String toString() {
        return "type=switch " + "pos=" + pos+" active=["+activeNode+"]";
    }

    @Override
    public void Draw(GraphicsContext gc) {
        gc.setFill(Color.BROWN);
        gc.setLineWidth(0);
        gc.fillRoundRect(this.pos.getX()-10, this.pos.getY()-10, 20, 20,20, 20);
    }
}
