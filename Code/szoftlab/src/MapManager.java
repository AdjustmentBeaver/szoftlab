/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */

/**
 * A pályák kezeléséért felelős. <br>
 * Képes menteni egy játékállást, betölteni egy korábban lementettet, vagy újat, és kezeli az ezzel járó
 * feliratkoztatásokat (ld. subscription szekvencia). <br>
 * Tárolja a jelenlegi Map-ot.
 */
public class MapManager {
    private Game game;
    private Map map;
    private SimulationTimer timer;

    /**
     * Instantiates a new Map manager.
     *
     * @param timer the timer
     * @param game  the game
     */
    public MapManager(SimulationTimer timer, Game game) {
        Prompt.printMessage("MapManager.MapManager");
        this.game = game;
        this.timer = timer;
    }

    /**
     * Betölti a paraméterül kapott nevű pályát egy MapBuilder segítségével.
     *
     * @param mapName the map name
     */
    public void newMap(String mapName) {
        Prompt.printMessage("MapManager.newMap");

        Prompt.addIndent("<<create>>");
        MapBuilder mapBuilder = new MapBuilder("newLevel");
        Prompt.removeIndent();

        Prompt.addIndent("mapBuilder.buildMap(game)");
        map = mapBuilder.buildMap();
        Prompt.removeIndent();

        Prompt.addIndent("map.subscribe(timer)");
        map.subscribe(timer);
        Prompt.removeIndent();
    }

    /**
     * Lementi az aktuális játékállást a paraméterben kapott néven.
     *
     * @param mapName the map name
     */
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

    /**
     * Betölt egy korábban lementett játékállást.
     *
     * @param mapName the map name
     */
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
