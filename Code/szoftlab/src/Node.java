import util.Coordinate;
import util.Speed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */

/**
 * A TrainPartok következő úticélját határozza meg. A csomópontok gráfot alkotnak.
 */
public class Node implements Serializable {
    /**
     * The Neighbour node list.
     */
    protected List<Node> neighbourNodeList;

    /**
     * A csomóponttal utoljára kapcsolatba lépett vonat.
     */
    private Train lastTrain;

    /**
     * A csomópont helyzetét adja meg.
     */
    protected Coordinate pos;
    public Coordinate getPos() {return pos;}

    protected String name;
    public void setName(String n){
        name = n;
    }

    /**
     * Melyik szomszédos csomópont felől érkezett a látogató
     */
    protected Node visitorComingFrom;

    /**
     * Konstruktor
     *
     * @param pos a node pozicioja
     */
    public Node(Coordinate pos) {
        neighbourNodeList = new ArrayList<>();
        this.pos = pos;
    }

    private void accept(TrainPart tp) {
        visitorComingFrom = tp.getPrevNode();
        lastTrain = tp.getTrain();

        Node nextNode = route();

        if (nextNode != null) {
            tp.setNextNode(nextNode);
        } else {
            lastTrain.explode();
        }
    }

    /**
     * A csomópont irányítja a TrainEnginet a következő állomás felé, ha az létezik.
     *
     * @param te the TrainEngine
     */
    public void accept(TrainEngine te) {
        accept((TrainPart) te);
    }

    /**
     * A csomópont irányítja a TrainCartot a következő állomás felé, ha az létezik.
     *
     * @param tc the TrainCart
     */
    public void accept(TrainCart tc) {
        accept((TrainPart) tc);
    }

    /**
     * A csomópont irányítja a TrainCoalWagont a következő állomás felé, ha az létezik.
     *
     * @param tw the TrainCoalWagon
     */
    public void accept(TrainCoalWagon tw) {
        accept((TrainPart) tw);
    }

    /**
     * Megnézi honnan jött az aktuális TrainPart, és a másik node felé irányítja, mint ahonnan jött.
     *
     * @return the node
     */
    protected Node route() {
        // Ha nincs következő csomopont (Vakvágány)
        if (neighbourNodeList.size() == 1)
            if(neighbourNodeList.get(0) == visitorComingFrom)
                return null;
            else return neighbourNodeList.get(0);
        
        // Ha nem az egyik, akkor a másik. 2 lehetőség van csak. SpecialPlacenek felül kell definiálnia
        if (visitorComingFrom == neighbourNodeList.get(0)){
            return neighbourNodeList.get(1);
        }
        return neighbourNodeList.get(0);
        
    }

    /**
     * A felhaszáló ezzel tudja a node-hoz tartozó logikát aktiválni. Alapértelmezetten ugyan semmi nem történik, de a leszármazottak felüldefiniálják ezt.
     */
    public void activate() {
    }

    /**
     * Ellenörzi, hogy van-e a node-on vonat jelenleg.
     * <p>
     * A node minden parttal történt interakciónál megjegyzi, hogy mi volt a hozzá tartozó vonat. (lastTrain)
     * Az ellenőrzésnél lekéri a vonat partListáját, és megnézi mindre, kivétel a legelsöre, a getNextNode() függvénnyel,
     * hogy mi a következő célja. Ha nem a node saját maga, akkor már nincs rajta a vonat.
     * </p>s
     *
     * @return true ha van rajta vonat
     */
    protected boolean checkForTrain() {
        // Ha volt már rajta vonat
        if (lastTrain != null){
            for(TrainPart tp: lastTrain.getPartList()){
                if (tp.getNextNode() == this) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Add neighbour node.
     *
     * @param n the Node
     */
    public void addNeighbourNode(Node n) {
        neighbourNodeList.add(n);
    }

    @Override
    public String toString() {
        return "type=node " + "pos=" + pos;
    }
}
