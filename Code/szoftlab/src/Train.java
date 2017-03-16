import util.Color;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Train implements Notifiable {
    private Statistics stat;
    private List<TrainPart> trainPartList;
    private List<Train> trainList;
    private boolean isRunning;

    public Train(Statistics st, List<Train> trainList) {
        Prompt.printMessage("Train.Train");
        stat = st;
        this.trainList = trainList;
        isRunning = false;
        trainPartList = new ArrayList<>();
    }

    public void move() {
        Prompt.printMessage("Train.move");
        for (TrainPart tp : trainPartList) {
            Prompt.addIndent("trainPart.move()");
            tp.move();
            Prompt.removeIndent();
        }
    }

    public void explode() {
        Prompt.printMessage("Train.explode");
        Prompt.addIndent("stat.trainExploded()");
        stat.trainExploded();
        Prompt.removeIndent();
    }

    public boolean isRunning() {
        Prompt.printMessage("Train.isRunning");
        System.out.println("[?] Fut már a vonat? [Y/N]");
        System.out.print("[>] ");
        return Prompt.readBool();
    }

    public void addPart(TrainPart p) {
        Prompt.printMessage("Train.addPart");
        trainPartList.add(p);
    }

    public Color getColor() {
        Prompt.printMessage("Train.getColor");
        return new Color("");
    }

    public void startTrain() {
        Prompt.printMessage("Train.startTrain");
        isRunning = true;
    }

    public void checkCollision() {
        Prompt.printMessage("Train.checkCollision");
        for (Train other : trainList) {
            List<TrainPart> othParts = other.getPartList();
            if (other != this && other.isRunning()) {
                for (TrainPart otherPart : othParts) {
                    for (TrainPart ownPart : trainPartList) {
                        boolean collided = otherPart.checkCollision(ownPart);
                        if (collided) {
                            explode();
                            return;
                        }
                    }
                }
            }
        }
    }

    public List<TrainPart> getPartList() {
        return trainPartList;
    }

    @Override
    public void update() {
        if (!isRunning) return;
        move();
        checkCollision();
    }
}
