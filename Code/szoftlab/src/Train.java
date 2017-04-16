import util.Color;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat felrobbantásáért felelős, ekkor triggereli Statistics osztály figyelő függvényét. Tárolja a kocsikat, mozdonyt. Mozgatja saját magát.
 * </p>
 */
public class Train implements Notifiable, Serializable {
    /**
     *  Tartalmazza, hogy milyen egységekből áll a vonat.
     */
    private List<TrainPart> trainPartList;

    /**
     *  Vonatok listája, ütközésvizsgálathoz.
     */
    private List<Train> trainList;

    /**
     * A vonat a pályán van-e.
     */
    private boolean isRunning;

    /**
     * A vonat inditasi ideje
     */
    private int startTime;

    /**
     * A vonat inditasi helye
     */
    private Node startNode;

    /**
     *  Konstruktor. Beállítja a statistics és trainList attribútumokat.
     *
     * @param trainList A vonatok listája.
     */
    public Train(List<Train> trainList, Node startNode, int startTime) {
        this.trainList = trainList;
        isRunning = false;
        trainPartList = new ArrayList<>();
        this.startNode = startNode;
        this.startTime = startTime;
    }

    /**
     * Mozgatja a Traint és minden elemét.
     */
    public void move() {
        for (TrainPart tp : trainPartList){
            tp.move();
        }
    }

    /**
     * Felrobbantja a Traint.
     */
    public void explode() {
        //stat.trainExploded();
    }

    /**
     * Visszaadja, hogy a Train mozgásban van-e.
     *
     * @return Igaz, ha a vonat mozgásban van.
     */
    public boolean isRunning() {
        return isRunning;
    }

    /**
     * Hozzáad egy kocsit a vonathoz.
     *
     * @param p A hozzáadandó kocsi.
     */
    public void addPart(TrainPart p) {
        trainPartList.add(p);
    }

    /**
     * Visszaadja az utolsó nem üres TrainPart színét.
     *
     * @return Az utolsó nem üres TrainPart színe.
     */
    public Color getColor() {
        int tpIndex = 0;
        while (tpIndex < trainPartList.size() && !trainPartList.get(tpIndex).isEmpty())
            tpIndex++;

        // Ha volt nem üres kocsi - az Engine és a Coalwagon "üresek" ezért nem választódhatnak ki. csak TrainCart
        if (tpIndex < trainPartList.size())
            return ((TrainCart) trainPartList.get(tpIndex)).getColor();

        // nullt ad vissza "ures" szin helyett
        return null;
    }

    /**
     * Elindítja a vonatot.
     */
    public void startTrain() {
        isRunning = true;
        // TODO: helyezzuk el a megfelelo pozicioba, a trainpartokat
    }

    /**
     * Ütközés vizsgálata a vonatok között. A vonat minden TrainPart-ját összehasonlítja a az összes többi vonat TrainPart-jával. Minden szimulációs lépésben végrehajtódik.
     */
    public void checkCollision() {
        // Az összes vonatra nézzük
        for (Train otherTrain: trainList) {
            // Ha nem mi vagyunk
            if (otherTrain != this) {
                // Másik vonat TrainPartjainak lekérdezése
                ArrayList<TrainPart> otherTrainPartList = (ArrayList<TrainPart>) otherTrain.getPartList();

                // Saját TrainPartok összehasonlítása a másik vonat TrainPartjaival
                for (TrainPart otherTp: otherTrainPartList) {
                    for (TrainPart myPart: trainPartList) {
                        if (myPart.checkCollision(otherTp))
                            explode();
                    }
                }
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
        // Ha fut a vonat
        if (isRunning) {
            // Mozgatás
            move();
            // Ütközésvizsgálat
            checkCollision();
        }
    }

    /**
     * A vonat inditasi idejet lekerdezo fuggveny
     * @return a vonat inditasi ideje
     */
    public int getStartTime() {
        return startTime;
    }
}
