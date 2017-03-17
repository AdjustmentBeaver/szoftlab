/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */
public class MapManager {
    private Game game;
    private Map map;
    private SimulationTimer timer;

    public MapManager(SimulationTimer timer, Game game) {
        Prompt.printMessage("MapManager.MapManager");
        this.game = game;
        this.timer = timer;
    }

    public void newMap(String mapName) {
        Prompt.printMessage("MapManager.newMap");

        Prompt.addIndent("<<create>>");
        MapBuilder mapBuilder = new MapBuilder("newLevel");
        Prompt.removeIndent();

        Prompt.addIndent("mapBuilder.buildMap(game)");
        map = mapBuilder.buildMap(game);
        Prompt.removeIndent();

        Prompt.addIndent("map.subscribe(timer)");
        map.subscribe(timer);
        Prompt.removeIndent();
    }

    public void saveMap(String mapName) {
        Prompt.printMessage("MapManager.saveMap");

        Prompt.addIndent("game.stopGame()");
        game.stopGame();
        Prompt.removeIndent();

        // Serialization
        Prompt.addIndent("game.resumeGame()");
        game.resumeGame();
        Prompt.removeIndent();
    }

    public void loadMap(String mapName) {
        Prompt.printMessage("MapManager.loadMap");
        // Deserialization
        Prompt.addIndent("<<create>>");
        Map newMap = new Map();
        // map = newMap
        Prompt.removeIndent();

        Prompt.addIndent("map.subscribe(timer)");
        map.subscribe(timer);
        Prompt.removeIndent();
    }
}
