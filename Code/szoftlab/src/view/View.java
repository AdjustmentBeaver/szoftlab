package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.*;

public class View {
    protected Controller ctrl;
    protected MapManager mapManager;
    private NodeView nodeView;
    private TrainView trainView;
    private Scene scene;

    protected View() {
    }

    public View(MapManager mapManager, Controller ctrl) {
        this.mapManager = mapManager;
        this.ctrl = ctrl;
        GraphicsContext gc = ctrl.getCanvasGC();
        nodeView = new NodeView(gc);
        trainView = new TrainView(gc);
    }

    public void Update() {
        ctrl.getCanvasGC().clearRect(0, 0, ctrl.getCanvasGC().getCanvas().getWidth(), ctrl.getCanvasGC().getCanvas().getHeight());
        Map m = mapManager.getMap();
        nodeView.draw(m);
        trainView.draw(m);
        nodeView.drawTunnel();
    }

    public void draw(TrainPart trainPart) {}
    public void draw(TrainCart trainCart) {}
    public void draw(TrainEngine trainEngine) {}
    public void draw(TrainCoalWagon trainCoalWagon) {}
    public void draw(Node node) {}
    public void draw(Station station) {}
    public void draw(Switch sw) {}
    public void draw(SpecialPlace tunnel) {}

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public double getHeight() {
        return 640;
    }

    public double getWidth() {
        return 640;
    }
}
