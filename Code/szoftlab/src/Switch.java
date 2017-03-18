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
        Prompt.printMessage("Switch.Switch");
        activeNode = null;
    }

    /**
     * Meghatározza a vonat továbbhaladási irányát. Ha a vonat a root felől jön, akkor az aktív node felé irányíja, ha pedig a másik irányok valamelyikéből, akkor a root felé fog vezetni az irány.
     * @return  A Switch aktív csomópontja.
     */
    @Override
    protected Node route() {
        Prompt.printMessage("Switch.route");
        return activeNode;
    }

    /**
     * Elvégzi a váltó állításhoz szükséges logikát. Kiválasztja a haladási irányt, ha a nem tartozkódik rajta vonat.
     */
    @Override
    public void activate() {
        Prompt.printMessage("Switch.activate");
        Prompt.addIndent("switch.checkForTrain()");
        boolean trainOnMe = checkForTrain();
        Prompt.removeIndent();
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
