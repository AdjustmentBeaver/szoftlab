package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;
import model.util.Coordinate;

import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by szilard95 on 5/5/17.
 */
public class NodeView extends View {
    private GraphicsContext graphicsContext;
    private Image spriteNode;
    private Image spriteSpecialPlace;
    private Image spriteSpecialPlaceBuilt;
    private Image spriteSwitch;
    private HashMap<String, Image> spriteStations;
    private HashMap<String, Image> spriteLoaderStations;
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
            spriteSwitch = new Image(fi = new FileInputStream("sprites/Node.png"));
            fi.close();
            spriteStations=new HashMap<>();
            spriteStations.put("blue", new Image(fi = new FileInputStream("sprites/station_blue.png")));
            fi.close();
            spriteStations.put("red", new Image(fi = new FileInputStream("sprites/station_red.png")));
            fi.close();
            spriteStations.put("green", new Image(fi = new FileInputStream("sprites/station_green.png")));
            fi.close();
            spriteStations.put("orange", new Image(fi = new FileInputStream("sprites/station_orange.png")));
            fi.close();
            spriteLoaderStations=new HashMap<>();
            spriteLoaderStations.put("blue", new Image(fi = new FileInputStream("sprites/loader_blue.png")));
            fi.close();
            spriteLoaderStations.put("red", new Image(fi = new FileInputStream("sprites/loader_red.png")));
            fi.close();
            spriteLoaderStations.put("green", new Image(fi = new FileInputStream("sprites/loader_green.png")));
            fi.close();
            spriteLoaderStations.put("orange", new Image(fi = new FileInputStream("sprites/loader_orange.png")));
            fi.close();
        } catch (IOException e) {
            System.err.println("ERROR LOADING NODE SPRITES. RESISTANCE IS FUTILE.");
        }
    }

    public void draw(Map map) {
        graphicsContext.setLineWidth(6);
        graphicsContext.setStroke(Color.color(0.15, 0.15, 0.15));
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
        drawSprite(station, spriteStations.get(station.getColor().toString()));
    }

    @Override
    public void draw(Switch sw) {
        Coordinate root = sw.getPos().add(sw.getPos().substract(sw.getRootNode().getPos()).normalize().scale(-16));
        Coordinate active = sw.getPos().add(sw.getPos().substract(sw.getActiveNode().getPos()).normalize().scale(-16));

        graphicsContext.setLineWidth(4);
        graphicsContext.setStroke(Color.RED);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), root.getX(), root.getY());
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), active.getX(), active.getY());
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillOval(active.getX()-3, active.getY()-3, 6, 6);
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
        drawSprite(loaderStation, spriteLoaderStations.get(loaderStation.getColor().toString()));
    }


    public void drawTunnel() {
        if (tunnels.size() == 2) {
            SpecialPlace t1 = tunnels.get(0);
            SpecialPlace t2 = tunnels.get(1);

            graphicsContext.setLineWidth(26);
            graphicsContext.setStroke(Color.DARKGREEN);
            graphicsContext.strokeLine(t1.getPos().getX(), t1.getPos().getY(), t2.getPos().getX(), t2.getPos().getY());

            drawSprite(t1, spriteSpecialPlaceBuilt);
            drawSprite(t2, spriteSpecialPlaceBuilt);
        }
        tunnels.clear();
    }

}
