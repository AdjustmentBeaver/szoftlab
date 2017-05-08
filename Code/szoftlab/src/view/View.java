package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import javafx.scene.paint.Paint;
import model.*;

public class View {
    protected Controller ctrl;
    protected MapManager mapManager;
    private NodeView nodeView;
    private TrainView trainView;
    private Scene scene;
    private GraphicsContext gc;

    protected View() {
    }

    public View(MapManager mapManager, Controller ctrl) {
        this.mapManager = mapManager;
        this.ctrl = ctrl;
        gc = ctrl.getCanvasGC();
        nodeView = new NodeView(gc);
        trainView = new TrainView(gc);
    }

    public void Update() {
        ctrl.getCanvasGC().setFill(Color.GREEN);
        ctrl.getCanvasGC().fillRect(0, 0, ctrl.getCanvasGC().getCanvas().getWidth(), ctrl.getCanvasGC().getCanvas().getHeight());
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
    public void draw(LoaderStation loaderStation) {}

    public void setScene(Scene scene) {
        this.scene = scene;
    }

    public Scene getScene() {
        return scene;
    }

    public void reset() {
        gc.setFill(Color.rgb(0x11, 0x22, 0x33));
        gc.fillRect(0, 0, gc.getCanvas().getWidth(), gc.getCanvas().getHeight());
    }
}
