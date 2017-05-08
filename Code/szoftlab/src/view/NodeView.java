package view;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.image.Image;
import javafx.scene.paint.Color;
import model.*;
import model.util.Coordinate;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * Created by szilard95 on 5/5/17.
 */
public class NodeView extends View {
    private GraphicsContext graphicsContext;
    private HashMap<String, Image> spriteStations;
    private HashMap<String, Image> spriteLoaderStations;
    private HashMap<String, Image> spriteNodes;
    private ArrayList<SpecialPlace> tunnels = new ArrayList<>();
    private static final String nodeTypes[] = {"node", "tunnel_built", "tunnel_closed"};

    public NodeView(GraphicsContext graphicsContext) {
        this.graphicsContext = graphicsContext;
        graphicsContext.setLineWidth(12);
        try {
            spriteNodes = new HashMap<>();
            for (String type: nodeTypes) {
                try (FileInputStream fi = new FileInputStream("sprites/" + type + ".png")) {
                    spriteNodes.put(type, new Image(fi));
                }
            }
            spriteStations=new HashMap<>();
            spriteLoaderStations=new HashMap<>();
            for (String color: model.util.Color.getValidColors()) {
                try (FileInputStream fi = new FileInputStream("sprites/station_" + color + ".png")) {
                    spriteStations.put(color, new Image(fi));
                }
                try (FileInputStream fi = new FileInputStream("sprites/loader_" + color + ".png")) {
                    spriteLoaderStations.put(color, new Image(fi));
                }
            }
        } catch (IOException e) {
            System.err.println("NodeView.NodeView: File error: " + e.getMessage());
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
        drawSprite(node, spriteNodes.get("node"));
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
        graphicsContext.setStroke(Color.DARKRED);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), root.getX(), root.getY());
        graphicsContext.setStroke(Color.RED);
        graphicsContext.strokeLine(sw.getPos().getX(), sw.getPos().getY(), active.getX(), active.getY());
    }

    @Override
    public void draw(SpecialPlace tunnel) {
        if (tunnel.isConstructed) {
            drawSprite(tunnel, spriteNodes.get("tunnel_built"));
            tunnels.add(tunnel);
        } else {
            drawSprite(tunnel, spriteNodes.get("tunnel_closed"));
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

            drawSprite(t1, spriteNodes.get("tunnel_built"));
            drawSprite(t2, spriteNodes.get("tunnel_built"));
        }
        tunnels.clear();
    }

}
