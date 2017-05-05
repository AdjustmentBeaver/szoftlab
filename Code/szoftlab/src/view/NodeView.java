package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

import javax.imageio.ImageIO;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.util.List;

/**
 * Created by szilard95 on 5/5/17.
 */
public class NodeView extends View{
    private GraphicsContext graphicsContext;
    private BufferedImage spriteNode;
    private BufferedImage spriteSpecialPlace;
    private BufferedImage spriteSwitch;
    private BufferedImage spriteStation;

    private SpecialPlace tunnels[];

    public NodeView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        try {
            spriteNode = ImageIO.read(new java.io.File("sprites/Node.png"));
            spriteSpecialPlace = ImageIO.read(new java.io.File("sprites/SpecialPlace.png"));
            spriteStation = ImageIO.read(new java.io.File("sprites/Station.png"));
            spriteSwitch = ImageIO.read(new java.io.File("sprites/Switch.png"));
        } catch (IOException e) {
            System.err.println("ERROR LOADING NODE SPRITES. RESISTANCE IS FUTILE.");
        }
    }

    public void draw(Map map) {
        graphicsContext.setFill(Color.BLACK);
        for (Node n : map.getNodeList()) {
            graphicsContext.fillRect(n.getPos().getX() - 5, n.getPos().getY() - 5, 10, 10);
            for (Node nb : map.getNeighborList().get(n)) {
                graphicsContext.strokeLine(n.getPos().getX(), n.getPos().getY(), nb.getPos().getX(), nb.getPos().getY());
            }
        }
    }

    public void drawTunnel() {

    }
}
