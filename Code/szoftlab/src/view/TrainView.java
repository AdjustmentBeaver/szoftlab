package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.Map;
import model.Train;
import model.TrainPart;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by szilard95 on 5/5/17.
 */
public class TrainView extends View{
    private GraphicsContext graphicsContext;
    private BufferedImage spriteTrainEngine;
    private BufferedImage spriteTrainCoalWagon;
    private BufferedImage spriteTrainCart;

    public TrainView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        try {
            spriteTrainCart = ImageIO.read(new java.io.File("sprites/TrainCart.png"));
            spriteTrainCoalWagon = ImageIO.read(new java.io.File("sprites/TrainCoalWagon.png"));
            spriteTrainEngine = ImageIO.read(new java.io.File("sprites/TrainEngine.png"));
        } catch (IOException e) {
            System.err.println("ERROR LOADING TRAIN SPRITES. RESISTANCE IS FUTILE.");
        }
    }

    public void draw(Map map) {
        graphicsContext.setFill(Color.BLUE);
        for (Train t : map.getTrainList()) {
            for (TrainPart tp : t.getPartList()) {
                graphicsContext.fillOval(tp.getPos().getX() - 5, tp.getPos().getY() - 5, 10, 10);
            }
        }
    }
}
