import util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Absztrakt bázisosztály vonatelemekhez. A vonatok TrainPartokból állnak, a vonatok mozgatása a TrainPartok mozgatására vezethetö vissza, ezért minden TrainPart mozgatja saját magát. Mozgatáskor érheti el a célpontjául szolgáló adott állomást, és visitor minta szerint meglátogatja, azaz triggereli a node-okat ha elért hozzájuk.
 * </p>
 */
public abstract class TrainPart {

    /**
     * A TrainPart pozíciója
     */
    private Coordinate pos;

    /**
     * A következő csomópont ami felé halad.
     */
    protected Node nextNode;

    /**
     * Az a csomópont amit előzőleg elhagyott.
     */
    protected Node prevNode;

    /**
     * A vonat amihez tartozik.
     */
    protected Train train;

    /**
     * Üres-e a TrainPart
     */
    protected boolean isEmpty;

    /**
     * Konstruktor. Paraméterül kapja, melyik vonatkoz tartozik.
     *
     * @param t A vonat, amihez tartozik.
     */
    public TrainPart(Train t) {
        train = t;
        isEmpty = true;
    }

    /**
     * Mozgatja a TrainPartot. Absztrakt, a leszármazottak valósítják meg..
     */
    public abstract void move();

    /**
     * Beállítja a kapott Node-ot az adott kocsi következő céljának.
     *
     * @param n A következő csomópont
     */
    public void setNextNode(Node n) {
        prevNode = nextNode;
        nextNode = n;
    }

    /**
     * Visszaadja, hogy a TrainPart melyik Trainhez tartozik.
     *
     * @return A vonat, amihez a TrainPart tartozik.
     */
    public Train getTrain() {
        return train;
    }

    /**
     * Megvizsgálja, hogy a paraméterül kapott másik trainParttal összeütközött-e.
     *
     * @param tp A másik TrainPart, amihez hasonlítunk.
     * @return Igaz, ha történt ütközés.
     */
    public boolean checkCollision(TrainPart tp) {
        Prompt.printMessage("TrainPart.checkCollision");
        System.out.println("[?] Történt ütközés? [Y/N]");
        System.out.print("[>] ");
        return Prompt.readBool();
    }

    /**
     * Visszaadja az előző csomópontot, amin tartózkodott.
     *
     * @return Az előző csomópont.
     */
    public Node getPrevNode() {
        return prevNode;
    }

    /**
     * Visszaadja a következő csomópontot, ami felé halad.
     *
     * @return A következő csomópont.
     */
    public Node getNextNode() {
        return nextNode;
    }

    /**
     * Visszaadja, hogy a kocsi üres-e.
     * @return Üres-e a kocsi.
     */
    public boolean isEmpty(){
        return isEmpty;
    }
}
