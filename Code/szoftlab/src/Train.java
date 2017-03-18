import util.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat felrobbantásáért felelős, ekkor triggereli Statistics osztály figyelő függvényét. Tárolja a kocsikat, mozdonyt. Mozgatja saját magát.
 * </p>
 */
public class Train implements Notifiable {
    private Statistics stat;
    private List<TrainPart> trainPartList;
    private List<Train> trainList;
    private boolean isRunning;

    /**
     *  Konstruktor. Beállítja a statistics és trainList attribútumokat.
     *
     * @param st        A játék statisztikája.
     * @param trainList A vonatok listája.
     */
    public Train(Statistics st, List<Train> trainList) {
        Prompt.printMessage("Train.Train");
        stat = st;
        this.trainList = trainList;
        isRunning = false;
        trainPartList = new ArrayList<>();
    }

    /**
     * Mozgatja a Traint és minden elemét.
     */
    public void move() {
        Prompt.printMessage("Train.move");
        // for tp in trainPartList
        TrainPart tp = trainPartList.get(0);
        Prompt.addIndent("trainPart.move()");
        tp.move();
        Prompt.removeIndent();
    }

    /**
     * Felrobbantja a Traint.
     */
    public void explode() {
        Prompt.printMessage("Train.explode");
        Prompt.addIndent("stat.trainExploded()");
        stat.trainExploded();
        Prompt.removeIndent();
    }

    /**
     * Visszaadja, hogy a Train mozgásban van-e.
     *
     * @return Igaz, ha a vonat mozgásban van.
     */
    public boolean isRunning() {
        Prompt.printMessage("Train.isRunning");
        System.out.println("[?] Fut már a vonat? [Y/N]");
        System.out.print("[>] ");
        return Prompt.readBool();
    }

    /**
     * Hozzáad egy kocsit a vonathoz.
     *
     * @param p A hozzáadandó kocsi.
     */
    public void addPart(TrainPart p) {
        Prompt.printMessage("Train.addPart");
        trainPartList.add(p);
    }

    /**
     * Visszaadja az utolsó nem üres TrainPart színét.
     *
     * @return Az utolsó nem üres TrainPart színe.
     */
    public Color getColor() {
        Prompt.printMessage("Train.getColor");
        return new Color("");
    }

    /**
     * Elindítja a vonatot.
     */
    public void startTrain() {
        Prompt.printMessage("Train.startTrain");
        isRunning = true;
    }

    /**
     * Ütközés vizsgálata a vonatok között. A vonat minden TrainPart-ját összehasonlítja a az összes többi vonat TrainPart-jával. Minden szimulációs lépésben végrehajtódik.
     */
    public void checkCollision() {
        Prompt.printMessage("Train.checkCollision");

        // for other in trainList
        Train other = this;
        System.out.println("[?] A vizsgált vonatok különbözőek? [Y/N]");
        System.out.print("[>] ");
        if (Prompt.readBool()) {
            // for otherPart in other.getPartList()
            Prompt.addIndent("other.getPartList()");
            other.getPartList();
            Prompt.removeIndent();
            // for ownPart in trainPartList
            TrainPart otherPart = trainPartList.get(0);
            TrainPart ownPart = trainPartList.get(0);
            Prompt.addIndent("otherPart.checkCollision(ownPart)");
            boolean collided = otherPart.checkCollision(ownPart);
            Prompt.removeIndent();
            if (collided) {
                Prompt.addIndent("train.explode()");
                explode();
                Prompt.removeIndent();
            }
        }
    }

    /**
     * Visszaadja a Train-hez tartozó TrainPartok listáját.
     *
     * @return A vonathoz tartozó TrainPartok listája.
     */
    public List<TrainPart> getPartList() {
        return trainPartList;
    }

    /**
     * A Notifiable interfész megvalósításából származik. Ezen belül történik a mozgatás (move()) és az ütközésvizsgálat (checkCollision()) műveletek hívása.
     */
    @Override
    public void update() {
        System.out.println("[?] Fut már a vonat?");
        System.out.print("[>] ");
        if (Prompt.readBool()) {
            Prompt.addIndent("train.move()");
            move();
            Prompt.removeIndent();
            Prompt.addIndent("train.checkCollision()");
            checkCollision();
            Prompt.removeIndent();
        }
    }
}
