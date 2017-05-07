package model;

import javafx.scene.media.Media;
import javafx.scene.media.MediaPlayer;
import javafx.util.Duration;

import java.io.File;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 * Vonatok indításának ütemezéséért felelős osztály. A model.SimulationTimer triggereli, ekkor ha szükséges indítja a következő vonatot.
 * </p>
 */
public class TrainScheduler implements Notifiable, Serializable {

    private List<Train> trainList;

    private int time;

    private transient MediaPlayer hornMedia = null;

    private static final String hornSound =  "sound/horn.mp3";

    /**
     * Konstruktor, megkapja a vonatlistát.
     *
     * @param trainList Vonatok listája, innen keres vonatot, amit tud indítani.
     */
    public TrainScheduler(List<Train> trainList) {
        this.trainList = trainList;
        time = 0;
        hornMedia = new MediaPlayer(new Media(new File(hornSound).toURI().toString()));
    }

    private void readObject(ObjectInputStream in) throws IOException, ClassNotFoundException {
        in.defaultReadObject();
        hornMedia = new MediaPlayer(new Media(new File(hornSound).toURI().toString()));
    }

    /**
     * model.Notifiable interfész megvalósítása, itt történik a vonatindítási logika.
     */
    @Override
    public void update(String event) {
        if (event == null) {
            for (Train t : trainList) {
                if (!t.isRunning() && t.getStartTime() <= time) {
                    t.startTrain();
                    // Play horn sound
                    if (hornMedia != null) {
                        hornMedia.seek(Duration.ZERO);
                        hornMedia.play();
                    }
                }
            }
            time++;
        }
    }
}
