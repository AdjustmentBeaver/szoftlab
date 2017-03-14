/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */
public class MapManager {
    private Game game;
    private Map map;
    private SimulationTimer timer;

    public MapManager(SimulationTimer timer, Game game) {
        System.out.println("MapManager.MapManager");
        this.game = game;
        this.timer = timer;
    }

    public void newMap(String mapName) {
        System.out.println("MapManager.newMap");
        MapBuilder mapBuilder = new MapBuilder("newLevel");
        map = mapBuilder.buildMap(game);
        map.subscribe(timer);
    }

    public void saveMap(String mapName) {
        System.out.println("MapManager.saveMap");
        game.stopGame();
        // Serialization
        game.resumeGame();
    }

    public void loadMap(String mapName) {
        System.out.println("MapManager.loadMap");
        // Deserialization
        map = new Map();
        map.subscribe(timer);
    }
}
