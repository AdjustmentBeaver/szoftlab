package model;

import javafx.scene.canvas.GraphicsContext;
import model.util.Color;
import model.util.Coordinate;
import model.util.IDrawable;
import model.util.Speed;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * A vonat felrobbantásáért felelős, ekkor triggereli Statistics osztály figyelő függvényét. Tárolja a kocsikat, mozdonyt. Mozgatja saját magát.
 * </p>
 */
public class Train implements Notifiable, Serializable, IDrawable {
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
     * A vonat inditasi helye
     */
    private Node startNode;

    /**
     * A vonat inditasi ideje
     */
    private int startTime;

    private boolean exploded;

    /**
     *  Konstruktor. Beállítja a trainList attribútumot.
     *
     * @param trainList A vonatok listája.
     */
    public Train(List<Train> trainList, Node startNode, int startTime) {
        this.trainList = trainList;
        isRunning = false;
        trainPartList = new ArrayList<>();
        this.startNode = startNode;
        this.startTime = startTime;
        exploded = false;
    }

    /**
     * Mozgatja a Traint és minden elemét.
     */
    public void move() {
        for (int i = 0; i < trainList.size(); i++){
            if ( this == trainList.get(i)) {
                System.out.println("model.Train" + i + " | " + trainPartList.get(0).getNextNode());
            }
        }

        for (TrainPart tp : trainPartList){
            tp.move();
        }
    }

    /**
     * Felrobbantja a Traint.
     */
    public void explode() {
        exploded = true;
    }

    /**
     * Visszaadja, hogy a model.Train mozgásban van-e.
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
     * Visszaadja az utolsó nem üres model.TrainPart színét.
     *
     * @return Az utolsó nem üres model.TrainPart színe.
     */
    public Color getColor() {
        int tpIndex = 1;
        while (tpIndex < trainPartList.size() && trainPartList.get(tpIndex).isEmpty())
            tpIndex++;

        // Ha volt nem üres kocsi - az Engine és a Coalwagon "üresek" ezért nem választódhatnak ki. csak model.TrainCart
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
        Coordinate startcord= new  Coordinate(startNode.getPos().getX()-1,startNode.getPos().getY()-1);
        for (TrainPart T:trainPartList) {
            T.setNextNode(startNode);
            T.setPos(startcord);
            T.setActivateRadius(((TrainEngine)trainPartList.get(0)).getSpeed().getSpeedAsDouble() / 2.0 + 0.01);
            startcord = new Coordinate(startcord.getX()-20,startcord.getY()-20);
        }
    }

    /**
     * Ütközés vizsgálata a vonatok között. A vonat minden model.TrainPart-ját összehasonlítja a az összes többi vonat model.TrainPart-jával. Minden szimulációs lépésben végrehajtódik.
     */
    public void checkCollision() {
        // Az összes vonatra nézzük
        for (Train otherTrain: trainList) {
            // Ha nem mi vagyunk
            if (otherTrain != this && otherTrain.isRunning()) {
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
     * Visszaadja a model.Train-hez tartozó TrainPartok listáját.
     *
     * @return A vonathoz tartozó TrainPartok listája.
     */
    public List<TrainPart> getPartList() {
        return trainPartList;
    }

    /**
     * A model.Notifiable interfész megvalósításából származik. Ezen belül történik a mozgatás (move()) és az ütközésvizsgálat (checkCollision()) műveletek hívása.
     */
    @Override
    public void update(String event) {
        if (event == null) {
            // Ha fut a vonat
            if (isRunning) {
                // Mozgatás
                move();
                // Ütközésvizsgálat
                checkCollision();
            }
        }
    }

    /**
     * A vonat inditasi idejet lekerdezo fuggveny
     * @return a vonat inditasi ideje
     */
    public int getStartTime() {
        return startTime;
    }

    /**
     * Visszaadja, hogy a vonat felrobbant-e
     * @return Igaz, ha a vonat felrobbant
     */
    public boolean isExploded() {
        return exploded;
    }

    public Speed getSpeed(){
        return ((TrainEngine) getPartList().get(0)).getSpeed();
    }

    @Override
    public String toString() {
        StringBuilder s = new StringBuilder("isRunning = " + isRunning + " startTime = " + startTime + " exploded = " + exploded + " pos = " + trainPartList.get(0).getPos() + "\n");
        for (int i = 0; i < trainPartList.size(); i++) {
            TrainPart part = trainPartList.get(i);
            s.append("\t [").append(i).append("] ").append(part).append("\n");
        }
        return s.toString();
    }

    @Override
    public void Draw(GraphicsContext gc) {
        for (TrainPart tp: trainPartList) {
            tp.Draw(gc);
        }
    }
}
