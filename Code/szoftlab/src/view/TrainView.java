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

/**
 * Created by szilard95 on 5/5/17.
 */
public class TrainView extends View {
    private GraphicsContext graphicsContext;
    private Image spriteTrainEngine;
    private Image spriteTrainCoalWagon;
    private Image spriteTrainCartBlue;
    private Image spriteTrainCartGreen;
    private Image spriteTrainCartOrange;
    private Image spriteTrainCartRed;
    private Image spriteTrainCartEmpty;

    public TrainView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        try {
            FileInputStream fi;
            spriteTrainCartBlue = new Image(fi = new FileInputStream("sprites/cart_blue.png"));
            fi.close();
            spriteTrainCartGreen = new Image(fi = new FileInputStream("sprites/cart_green.png"));
            fi.close();
            spriteTrainCartOrange = new Image(fi = new FileInputStream("sprites/cart_orange.png"));
            fi.close();
            spriteTrainCartRed = new Image(fi = new FileInputStream("sprites/cart_red.png"));
            fi.close();
            spriteTrainCoalWagon = new Image(fi = new FileInputStream("sprites/coal_wagon.png"));
            fi.close();
            spriteTrainCartEmpty= new Image(fi = new FileInputStream("sprites/cart.png"));
            fi.close();
            spriteTrainEngine = new Image(fi = new FileInputStream("sprites/dank_engine.png"));
            fi.close();
        } catch (IOException e) {
            System.err.println("ERROR LOADING TRAIN SPRITES. RESISTANCE IS FUTILE.");
        }
    }

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
        switch (trainCart.getColor().toString()) {
            case "blue":
                sprite = spriteTrainCartBlue;
                break;
            case "green":
                sprite = spriteTrainCartGreen;
                break;
            case "orange":
                sprite = spriteTrainCartOrange;
                break;
            case "red":
                sprite = spriteTrainCartRed;
                break;
        }
        if (trainCart.isEmpty())
            sprite=spriteTrainCartEmpty;
        drawSprite(trainCart, sprite);
    }

    private void drawSprite(TrainPart trainPart, Image sprite) {
        ImageView iv = new ImageView(sprite);
        Coordinate dir = trainPart.getDirection();
        double deg = Math.toDegrees(Math.acos(Coordinate.dot(dir, new Coordinate(1, 0))));
        if (Coordinate.cross(dir, new Coordinate(1, 0)) > 0) {
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
        drawSprite(trainEngine, spriteTrainEngine);
    }

    @Override
    public void draw(TrainCoalWagon trainCoalWagon) {
        drawSprite(trainCoalWagon, spriteTrainCoalWagon);
    }
}
