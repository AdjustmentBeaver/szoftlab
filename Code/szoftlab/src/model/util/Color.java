package model.util;

import java.io.Serializable;

/**
 * Created by Istvan Telek on 3/14/2017.
 */
public class Color implements Serializable {
    private String color;

    private static String validColors[] = {"blue", "red", "green", "orange"};

    public Color(String color) {
        this.color = color;
    }

    @Override
    public String toString() {
        return color;
    }

    @Override
    public boolean equals(Object obj) {
        return this == obj || obj instanceof Color && color.equals(obj.toString());
    }

    public static String[] getValidColors() {
        return validColors;
    }
}
