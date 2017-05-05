package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;

/**
 * Created by szilard95 on 5/5/17.
 */
public class NodeView extends View {
    private GraphicsContext graphicsContext;
    private Image spriteNode;
    private Image spriteSpecialPlace;
    private Image spriteSwitch;
    private Image spriteStation;

    private SpecialPlace tunnels[];

    public NodeView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        try {
            spriteNode = new Image(new FileInputStream("sprites/Node.png"));
            spriteSpecialPlace = new Image(new FileInputStream("sprites/Node.png"));
            spriteStation = new Image(new FileInputStream("sprites/Node.png"));
            spriteSwitch = new Image(new FileInputStream("sprites/Node.png"));
        } catch (IOException e) {
            System.err.println("ERROR LOADING NODE SPRITES. RESISTANCE IS FUTILE.");
        }
    }

    public void draw(Map map) {
        graphicsContext.setStroke(Color.PINK);
        for (Node n : map.getNodeList()) {
            for (Node nb : map.getNeighborList().get(n)) {
                graphicsContext.strokeLine(n.getPos().getX(), n.getPos().getY(), nb.getPos().getX(), nb.getPos().getY());
            }
        }
        for (Node n : map.getNodeList()) {
            n.draw(this);
        }
    }

    @Override
    public void draw(Node node) {
        drawSprite(node);
    }

    private void drawSprite(Node node) {
        graphicsContext.drawImage(spriteNode, node.getPos().getX() - spriteNode.getWidth() / 2, node.getPos().getY() - spriteNode.getHeight() / 2);
    }

    @Override
    public void draw(Station station) {
        drawSprite(station);
    }

    @Override
    public void draw(Switch sw) {
        drawSprite(sw);
    }

    @Override
    public void draw(SpecialPlace tunnel) {
        drawSprite(tunnel);
    }

    public void drawTunnel() {

    }
}
