import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.canvas.Canvas;
import javafx.scene.canvas.GraphicsContext;
import javafx.scene.input.MouseEvent;

/**
 * Created by moriss on 4/23/2017.
 */
public class Controller {
    @FXML
    Canvas cvGame;

    Game game;

    public void setModel(Game game) {
        this.game = game;
    };

    public GraphicsContext getCanvasGC() {
        return cvGame.getGraphicsContext2D();
    }

    public void stepEventHandler(ActionEvent actionEvent) {
        game.update("step 1");
    }

    public void newEventHandler(ActionEvent actionEvent) {
        game.update("new cvtest");
        game.update("start");
    }

    public void step10EventHandler(ActionEvent actionEvent) {
        game.update("step 10");
    }

    public void canvasClickHandler(MouseEvent mouseEvent) {
        game.update("activate " + ((Double)mouseEvent.getX()).intValue() + " " + ((Double)mouseEvent.getY()).intValue());
    }
}
