import util.Coordinate;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Absztrakt bázisosztály vonatelemekhez. A vonatok TrainPartokból állnak, a vonatok mozgatása a TrainPartok mozgatására vezethetö vissza, ezért minden TrainPart mozgatja saját magát. Mozgatáskor érheti el a célpontjául szolgáló adott állomást, és visitor minta szerint meglátogatja, azaz triggereli a node-okat ha elért hozzájuk.
 * </p>
 */
public abstract class TrainPart {
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
     * Konstruktor. Paraméterül kapja, melyik vonatkoz tartozik.
     *
     * @param t A vonat, amihez tartozik.
     */
    public TrainPart(Train t) {
        Prompt.printMessage("TrainPart.TrainPart");
        train = t;
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
        Prompt.printMessage("TrainPart.setNextNode");
        prevNode = nextNode;
        nextNode = n;
    }

    /**
     * Visszaadja, hogy a TrainPart melyik Trainhez tartozik.
     *
     * @return A vonat, amihez a TrainPart tartozik.
     */
    public Train getTrain() {
        Prompt.printMessage("TrainPart.getTrain");
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
        Prompt.printMessage("TrainPart.getPrevNode");
        return prevNode;
    }

    /**
     * Visszaadja a következő csomópontot, ami felé halad.
     *
     * @return A következő csomópont.
     */
    public Node getNextNode() {
        Prompt.printMessage("TrainPart.getNextNode");
        return nextNode;
    }
}
