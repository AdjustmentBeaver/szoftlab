/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */

import java.io.*;

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
        this.game = game;
        this.map = null;
        this.timer = timer;
    }

    /**
     * Betölti a paraméterül kapott nevű pályát egy MapBuilder segítségével.
     *
     * @param mapName the map name
     */
    public void newMap(String mapName) {
        MapBuilder mapBuilder = new MapBuilder(mapName);
        map = mapBuilder.buildMap();
        if (map != null) {
            map.subscribe(timer);
        }
    }

    /**
     * Lementi az aktuális játékállást a paraméterben kapott néven.
     *
     * @param mapName the map name
     */
    public void saveMap(String mapName) {
        game.stopGame();
        // Serialization
        try {
            ObjectOutputStream ser = new ObjectOutputStream(new FileOutputStream(mapName));
            ser.writeObject(map);
            ser.close();
        } catch (IOException e) {
            System.err.println("Unable to save level: " + e.getMessage());
        }
        game.resumeGame();
    }

    /**
     * Betölt egy korábban lementett játékállást.
     *
     * @param mapName the map name
     */
    public void loadMap(String mapName) {
        // Deserialization
        try {
            ObjectInputStream ser = new ObjectInputStream(new FileInputStream(mapName));
            map = (Map) ser.readObject();
            if (map == null) {
                System.err.println("Unable to load level: Map is null.");
                return;
            }
            // map = newMap
            map.subscribe(timer);
        } catch (StreamCorruptedException e) {
            System.err.println("Unable to load level " + mapName + ": data file is corrupted.");
            System.err.println(e.getMessage());
            return;
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load level " + mapName + ": class not found.");
            System.err.println(e.getMessage());
            return;
        }  catch (InvalidClassException e) {
            System.err.println("Unable to load level " + mapName + ": class is invalid.");
            System.err.println(e.getMessage());
            return;
        } catch (IOException e) {
            System.err.println("Unable to load level " + mapName + ": I/O error.");
            System.err.println(e.getMessage());
            return;
        }
    }
}
