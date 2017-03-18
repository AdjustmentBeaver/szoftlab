import util.Speed;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat része a mozdony, meghatározza a vonat sebességét. Ha több mozdony lenne, tetszőleges logika szerint lehetne vonat eredő sebességét számolni.
 * </p>
 */
public class TrainEngine extends TrainPart {
    /**
     * A TrainEngine sebességét adja meg.
     */
    Speed speed;

    /**
     * Konstruktor, a mozdonyhoz.
     *
     * @param t     A Train, amihez tartozik.
     * @param speed A TrainEngine sebessége.
     */
    public TrainEngine(Train t, Speed speed) {
        super(t);
        Prompt.printMessage("TrainEngine.TrainEngine");
        this.speed = speed;
    }

    /**
     *  Visszaadja a TrainEngine sebességét.
     *
     * @return A TrainEngine sebessége
     */
    public Speed getSpeed() {
        Prompt.printMessage("TrainEngine.getSpeed");
        return speed;
    }

    /**
     * A TrainEngine mozgatása.
     */
    @Override
    public void move() {
        Prompt.printMessage("TrainEngine.move");
        if (getNextNode() != null) {
            Prompt.addIndent("getNextNode().accept(this)");
            nextNode.accept(this);
            Prompt.removeIndent();
        }
    }
}
