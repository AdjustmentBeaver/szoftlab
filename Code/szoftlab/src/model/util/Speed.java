package model.util;

import java.io.Serializable;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Speed implements Serializable {
    private double speed;

    public Speed(double speed) {
        this.speed = speed;
    }

    public double getSpeedAsDouble(){
        return speed;
    }
}
