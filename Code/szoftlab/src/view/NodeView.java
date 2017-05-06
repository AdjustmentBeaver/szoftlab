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
    private Image spriteSpecialPlaceBuilt;
    private Image spriteSwitch;
    private Image spriteStation;
    private Image spriteLoaderStation;
    private ArrayList<SpecialPlace> tunnels = new ArrayList<>();

    public NodeView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        graphicsContext.setLineWidth(12);
        try {
            FileInputStream fi;
            spriteNode = new Image(fi = new FileInputStream("sprites/Node.png"));
            fi.close();
            spriteSpecialPlace = new Image(fi = new FileInputStream("sprites/tunnel_closed.png"));
            fi.close();
            spriteSpecialPlaceBuilt = new Image(fi = new FileInputStream("sprites/tunnel.png"));
            fi.close();
            spriteStation = new Image(fi = new FileInputStream("sprites/station_blue.png"));
            fi.close();
            spriteSwitch = new Image(fi = new FileInputStream("sprites/Node.png"));
            fi.close();
            spriteLoaderStation = new Image(fi = new FileInputStream("sprites/loader_blue.png"));
            fi.close();
        } catch (IOException e) {
            System.err.println("ERROR LOADING NODE SPRITES. RESISTANCE IS FUTILE.");
        }
    }

    public void draw(Map map) {
        graphicsContext.setStroke(Color.BLACK);
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
        drawSprite(node, spriteNode);
    }

    private void drawSprite(Node node, Image sprite) {
        graphicsContext.drawImage(sprite, node.getPos().getX() - sprite.getWidth() / 2, node.getPos().getY() - sprite.getHeight() / 2);
    }

    @Override
    public void draw(Station station) {
        drawSprite(station, spriteStation);
    }

    @Override
    public void draw(Switch sw) {
        double x1 = (sw.getPos().getX() + sw.getRootNode().getPos().getX()) / 2.0;
        double y1 = (sw.getPos().getY() + sw.getRootNode().getPos().getY()) / 2.0;
        double x2 = (sw.getPos().getX() + sw.getActiveNode().getPos().getX()) / 2.0;
        double y2 = (sw.getPos().getY() + sw.getActiveNode().getPos().getY()) / 2.0;

        graphicsContext.setStroke(Color.RED);
        graphicsContext.setLineWidth(12);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), x1, y1);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), x2, y2);
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(x2 - 8, y2 - 8, 16, 16);
        //drawSprite(sw,spriteSwitch);
    }

    @Override
    public void draw(SpecialPlace tunnel) {
        if (tunnel.isConstructed) {
            drawSprite(tunnel, spriteSpecialPlaceBuilt);
            tunnels.add(tunnel);
        } else {
            drawSprite(tunnel, spriteSpecialPlace);
        }
    }

    @Override
    public void draw(LoaderStation loaderStation) {
        drawSprite(loaderStation, spriteLoaderStation);
    }


    public void drawTunnel() {
        graphicsContext.setLineWidth(26);
        graphicsContext.setStroke(Color.DARKGREEN);
        if (tunnels.size() == 2) {
            SpecialPlace t1 = tunnels.get(0);
            SpecialPlace t2 = tunnels.get(1);
            graphicsContext.strokeLine(t1.getPos().getX(), t1.getPos().getY(), t2.getPos().getX(), t2.getPos().getY());
            drawSprite(t1, spriteSpecialPlaceBuilt);
            drawSprite(t2, spriteSpecialPlaceBuilt);
        }
        tunnels.clear();
        graphicsContext.setLineWidth(12);
    }

}
