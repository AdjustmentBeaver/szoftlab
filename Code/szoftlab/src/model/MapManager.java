package model; /**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */

import java.io.*;
import java.util.ArrayList;

/**
 * A pályák kezeléséért felelős. <br>
 * Képes menteni egy játékállást, betölteni egy korábban lementettet, vagy újat, és kezeli az ezzel járó
 * feliratkoztatásokat (ld. subscription szekvencia). <br>
 * Tárolja a jelenlegi model.Map-ot.
 */
public class MapManager {
    private Game game;
    private Map map;
    private SimulationTimer timer;
    private ArrayList<String> mapRotation=new ArrayList<>();

    /**
     * Instantiates a new model.Map manager.
     *
     * @param timer the timer
     * @param game  the game
     */
    public MapManager(SimulationTimer timer, Game game) {
        this.game = game;
        this.map = null;
        this.timer = timer;
        mapRotation.add("jatek_megnyeres");
        mapRotation.add("cvtest");
        mapRotation.add("hosszu");
    }

    /**
     * Betölti a paraméterül kapott nevű pályát egy model.MapBuilder segítségével.
     *
     * @param mapName the map name
     */
    public void newMap(String mapName) {
        MapBuilder mapBuilder = new MapBuilder(mapName);
        mapBuilder.setGame(game);
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
            mapName = mapName + ".save";
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
            mapName = mapName + ".save";
            ObjectInputStream ser = new ObjectInputStream(new FileInputStream(mapName));
            map = (Map) ser.readObject();
            if (map == null) {
                System.err.println("Unable to load level: model.Map is null.");
                return;
            }
            map.setGame(game);
            map.subscribe(timer);
        } catch (StreamCorruptedException e) {
            System.err.println("Unable to load level " + mapName + ": data file is corrupted.");
            System.err.println(e.getMessage());
        } catch (ClassNotFoundException e) {
            System.err.println("Unable to load level " + mapName + ": class not found.");
            System.err.println(e.getMessage());
        } catch (InvalidClassException e) {
            System.err.println("Unable to load level " + mapName + ": class is invalid.");
            System.err.println(e.getMessage());
        } catch (IOException e) {
            System.err.println("Unable to load level " + mapName + ": I/O error.");
            System.err.println(e.getMessage());
        }
    }

    public Map getMap() {
        return map;
    }

    public void nextMap() {
        String mapName = mapRotation.get(0);
        mapRotation.remove(mapName);
        mapRotation.add(mapName);
        newMap(mapName);
    }
}
