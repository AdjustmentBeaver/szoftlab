package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;

/**
 * Created by szilard95 on 5/5/17.
 */
public class NodeView extends View {
    private GraphicsContext graphicsContext;
    private Image spriteNode;
    private Image spriteSpecialPlace;
    private Image spriteSwitch;
    private Image spriteStation;

    private ArrayList<SpecialPlace> tunnels = new ArrayList<>();

    public NodeView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        graphicsContext.setLineWidth(12);
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
        drawSprite(node,spriteNode);
    }

    private void drawSprite(Node node, Image sprite) {
        graphicsContext.drawImage(sprite, node.getPos().getX() - spriteNode.getWidth() / 2, node.getPos().getY() - spriteNode.getHeight() / 2);
    }

    @Override
    public void draw(Station station) {
        drawSprite(station,spriteStation);
    }

    @Override
    public void draw(Switch sw) {
        double x1 = (sw.getPos().getX() + sw.getRootNode().getPos().getX())/2.0;
        double y1 = (sw.getPos().getY() + sw.getRootNode().getPos().getY())/2.0;
        double x2 = (sw.getPos().getX() + sw.getActiveNode().getPos().getX())/2.0;
        double y2 = (sw.getPos().getY() + sw.getActiveNode().getPos().getY())/2.0;

        graphicsContext.setStroke(Color.RED);
        graphicsContext.setLineWidth(12);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), x1, y1);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), x2, y2);
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(x2-8,y2-8,16,16);
        //drawSprite(sw,spriteSwitch);
    }

    @Override
    public void draw(SpecialPlace tunnel) {
        drawSprite(tunnel,spriteSpecialPlace);
        if (tunnel.isConstructed) tunnels.add(tunnel);
    }

    public void drawTunnel() {
        graphicsContext.setStroke(Color.RED);
        if(tunnels.size()==2)
            graphicsContext.strokeLine(tunnels.get(0).getPos().getX(), tunnels.get(0).getPos().getY(), tunnels.get(1).getPos().getX(), tunnels.get(1).getPos().getY());
        tunnels.clear();
    }
}
