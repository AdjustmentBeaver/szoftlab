package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;

import java.awt.*;
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
        drawSprite(station, spriteStations.get(station.getColor().toString()));
    }

    @Override
    public void draw(Switch sw) {
        double x1 = sw.getRootNode().getPos().getX() - sw.getPos().getX();
        double y1 = sw.getRootNode().getPos().getY() - sw.getPos().getY();
        double len1 = Math.sqrt(x1*x1 + y1*y1);
        double x2 = sw.getActiveNode().getPos().getX() - sw.getPos().getX();
        double y2 = sw.getActiveNode().getPos().getY() - sw.getPos().getY();
        double len2 = Math.sqrt(x2*x2 + y2*y2);
        
        graphicsContext.setStroke(Color.RED);
        graphicsContext.setLineWidth(6);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), sw.getPos().getX() + 20*x1/len1, sw.getPos().getY() + 20*y1/len1);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), sw.getPos().getX() + 20*x2/len2, sw.getPos().getY() + 20*y2/len2);
        graphicsContext.setFill(Color.RED);
        graphicsContext.fillRect(sw.getPos().getX() + 20*x2/len2-6, sw.getPos().getY() + 20*y2/len2-6, 12, 12);
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
