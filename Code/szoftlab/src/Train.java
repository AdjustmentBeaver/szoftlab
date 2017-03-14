import util.Color;

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
        System.out.println("Train.Train");
        stat = st;
        this.trainList = trainList;
        isRunning = false;
    }

    public void move() {
        System.out.println("Train.move");
        for (TrainPart tp : trainPartList) {
            tp.move();
        }
    }

    public void explode() {
        System.out.println("Train.explode");
        stat.trainExploded();
    }

    public boolean isRunning() {
        System.out.println("Train.isRunning");
        return isRunning;
    }

    public void addPart(TrainPart p) {
        System.out.println("Train.addPart");
        trainPartList.add(p);
    }

    public Color getColor() {
        System.out.println("Train.getColor");
        return new Color("");
    }

    public void startTrain() {
        System.out.println("Train.startTrain");
        isRunning = true;
    }

    public void checkCollision() {
        System.out.println("Train.checkCollision");
        for (Train other : trainList) {
            List<TrainPart> othParts = other.getPartList();
            if (other != this) {
                for (TrainPart otherPart : othParts) {
                    for (TrainPart ownPart : trainPartList) {
                        boolean collided = otherPart.checkCollision(ownPart);
                        if (collided)
                            explode();
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
        move();
        checkCollision();
    }
}
