package view;

import javafx.scene.SnapshotParameters;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.paint.Color;
import model.*;
import model.util.Coordinate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.HashMap;

/**
 * The type Train view.
 */
public class TrainView extends View {
    private GraphicsContext graphicsContext;

    private HashMap<String, Image> trainCarts;
    private HashMap<String, Image> trainParts;
    private static final String trainPartTypes[] = {"cart", "coal_wagon", "engine"};

    /**
     * Instantiates a new Train view.
     *
     * @param graphicsContext the graphics context
     */
    public TrainView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        try {
            trainCarts = new HashMap<>();
            for (String color: model.util.Color.getValidColors()) {
                try (FileInputStream fi = new FileInputStream("sprites/cart_" + color + ".png")) {
                    trainCarts.put(color, new Image(fi));
                }
            }
            trainParts = new HashMap<>();
            for (String type: trainPartTypes) {
                try (FileInputStream fi = new FileInputStream("sprites/" + type + ".png")) {
                    trainParts.put(type, new Image(fi));
                }
            }
        } catch (IOException e) {
            System.err.println("TrainView.TrainView(): File error: " + e.getMessage());
        }
    }

    /**
     * Draw.
     *
     * @param map the map
     */
    public void draw(Map map) {
        graphicsContext.setFill(Color.BLUE);
        for (Train t : map.getTrainList()) {
            Coordinate dir = t.getPartList().get(0).getDirection();
            if (t.isRunning() && dir != null && dir.length() != 0) {
                for (TrainPart tp : t.getPartList()) {
                    tp.draw(this);
                }
            }
        }
    }

    @Override
    public void draw(TrainCart trainCart) {
        Image sprite = null;
        if (trainCart.isEmpty()) {
            sprite = trainParts.get("cart");
        } else {
            sprite = trainCarts.get(trainCart.getColor().toString());
        }
        if (sprite != null) {
            drawSprite(trainCart, sprite);
        } else {
            System.err.println("TrainView.Draw(TrainCart): Invalid cart color: " + trainCart.getColor().toString());
        }
    }

    /**
     * Kirajzol egy TrainPartot a GraphicsContextre, elforgatva a sebessege iranyaba.
     */
    private void drawSprite(TrainPart trainPart, Image sprite) {
        ImageView iv = new ImageView(sprite);
        Coordinate dir = trainPart.getDirection();
        // Az elforgatas szoget az (1,0) vektorhoz viszonyitjuk, mert vizszintesen es jobbra allva taroljuk a spriteokat
        Coordinate ref = new Coordinate(1, 0);
        double deg = Math.toDegrees(Math.acos(Coordinate.dot(dir, ref)));
        // Ha az elforgatas szoge > 180 fok, akkor is jo iranyba forgassuk
        if (Coordinate.cross(dir, ref) > 0) {
            deg = 360 - deg;
        }
        iv.setRotate(deg);
        SnapshotParameters param = new SnapshotParameters();
        param.setFill(Color.TRANSPARENT);
        Image rotatedImage = iv.snapshot(param, null);
        graphicsContext.drawImage(rotatedImage, trainPart.getPos().getX() - rotatedImage.getWidth() / 2, trainPart.getPos().getY() - rotatedImage.getHeight() / 2);
    }

    @Override
    public void draw(TrainEngine trainEngine) {
        drawSprite(trainEngine, trainParts.get("engine"));
    }

    @Override
    public void draw(TrainCoalWagon trainCoalWagon) {
        drawSprite(trainCoalWagon, trainParts.get("coal_wagon"));
    }
}
