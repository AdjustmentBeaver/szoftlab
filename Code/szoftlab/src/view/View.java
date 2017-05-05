package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.paint.Color;
import model.MapManager;
import model.Node;
import model.Train;
import model.TrainPart;

public class View extends Scene {
    private Controller ctrl;
    private MapManager mapManager;

    public View(Parent root, MapManager mapManager, Controller ctrl) {
        super(root, 640, 640);
        this.mapManager = mapManager;
        this.ctrl = ctrl;
    }

    public void Update() {
        ctrl.getCanvasGC().clearRect(0, 0, ctrl.getCanvasGC().getCanvas().getWidth(), ctrl.getCanvasGC().getCanvas().getHeight());
        ctrl.getCanvasGC().setFill(Color.BLACK);

        for (Node n : mapManager.getMap().getNodeList()) {
            ctrl.getCanvasGC().fillRect(n.getPos().getX() - 5, n.getPos().getY() - 5, 10, 10);
            for (Node nb : mapManager.getMap().getNeighborList().get(n)) {
                ctrl.getCanvasGC().strokeLine(n.getPos().getX(), n.getPos().getY(), nb.getPos().getX(), nb.getPos().getY());
            }
        }
        ctrl.getCanvasGC().setFill(Color.BLUE);
        for (Train t : mapManager.getMap().getTrainList()) {
            for (TrainPart tp : t.getPartList()) {
                ctrl.getCanvasGC().fillOval(tp.getPos().getX() - 5, tp.getPos().getY() - 5, 10, 10);
            }
        }
    }
}
