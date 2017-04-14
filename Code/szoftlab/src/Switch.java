/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Egy kereszteződésben ezek segítségével állítható, merre szeretnénk, hogy a vonat továbbhaladjon. Ha a két irány, amelyek irányába állítható a váltó, egyikéből jön a vonat, mindig a harmadik irányba halad tovább. A váltó nem állítható, ha vonat tartózkodik rajta.
 * </p>
 */
public class Switch extends Node {
    private Node activeNode;

    /**
     * Konstruktor, létrehozza a Switchet.
     */
    public Switch() {
        super();
        activeNode = null;
    }

    /**
     * Meghatározza a vonat továbbhaladási irányát. Ha a vonat a root felől jön, akkor az aktív node felé irányíja, ha pedig a másik irányok valamelyikéből, akkor a root felé fog vezetni az irány.
     * @return  A Switch aktív csomópontja.
     */
    @Override
    protected Node route() {
        // Feltételezve, hogy az elemek sorban: 0:ROOT 1:KIMENET 2:KIMENET
        // Ha Root felől jött
        if (visitorComingFrom == neighbourNodeList.get(0))
            return activeNode;
        else
            return neighbourNodeList.get(0);

    }

    /**
     * Elvégzi a váltó állításhoz szükséges logikát. Kiválasztja a haladási irányt, ha a nem tartozkódik rajta vonat.
     */
    @Override
    public void activate() {
        boolean trainOnMe = checkForTrain();

        if (!trainOnMe){
            // Ha a kettes az aktív
            if (activeNode != neighbourNodeList.get(1))
                // Aktiváljuk az egyest
                activeNode = neighbourNodeList.get(1);
            else if (neighbourNodeList.size() >= 3)
                // Aktiváljuk a kettest
                activeNode = neighbourNodeList.get(2);
            else
                // Ha nincs kettes, akkor vakvágány
                activeNode = null;
        }

    }

    /**
     * A Switchel szomszédos csomópontok hozzáadása.
     * @param n Szomszédos csomópont.
     */
    @Override
    public void addNeighbourNode(Node n) {
        super.addNeighbourNode(n);
        activeNode = n;
    }
}
