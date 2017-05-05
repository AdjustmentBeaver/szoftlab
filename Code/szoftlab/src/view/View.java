package view;

import controller.Controller;
import javafx.scene.Parent;
import javafx.scene.Scene;
import model.MapManager;

public class View extends Scene {
    private Controller ctrl;
    private MapManager mapManager;

    public void Update() {

    }

    public View(Parent root, MapManager mapManager, Controller ctrl) {
        super(root, 640, 640);
        this.mapManager = mapManager;
        this.ctrl = ctrl;
    }
}
