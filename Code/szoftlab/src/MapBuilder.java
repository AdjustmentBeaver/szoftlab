import util.Color;
import util.Speed;

import java.util.List;

/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */
public class MapBuilder {
    public MapBuilder(String mapName) {
        Prompt.printMessage("MapBuilder.MapBuilder");
    }

    public Map buildMap(Game game) {
        Prompt.printMessage("MapBuilder.buildMap");

        Prompt.addIndent("<<create>>");
        Map map = new Map();
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        Statistics stat = new Statistics(game);
        Prompt.removeIndent();

        Prompt.addIndent("map.addStatistics(stat)");
        map.addStatistics(stat);
        Prompt.removeIndent();

        Prompt.addIndent("map.getTrainList()");
        List<Train> trainList = map.getTrainList();
        Prompt.removeIndent();

        // Először a node-okat olvassuk be, mert a trainPart-oknak oda kell adni a kezdő node-okat
        // Ez a szekvencián nem szerepel (lemaradt a setNextNode a trainPart-oknál)
        Prompt.addIndent("<<create>>");
        Switch sw = new Switch();
        Prompt.removeIndent();

        Prompt.addIndent("map.addNode(sw)");
        map.addNode(sw);
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        SpecialPlace tunnel = new SpecialPlace();
        Prompt.removeIndent();

        Prompt.addIndent("map.addNode(tunnel)");
        map.addNode(tunnel);
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        Station station = new Station(new Color("red"));
        Prompt.removeIndent();

        Prompt.addIndent("map.addNode(station)");
        map.addNode(station);
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        Node node = new Node();
        Prompt.removeIndent();

        Prompt.addIndent("map.addNode(node)");
        map.addNode(node);
        Prompt.removeIndent();


        Prompt.addIndent("node.addNeighbourNode(n)");
        node.addNeighbourNode(node);
        Prompt.removeIndent();

        // Trainek létrehozása
        Prompt.addIndent("<<create>>");
        Train train = new Train(stat, trainList);
        Prompt.removeIndent();

        Prompt.addIndent("map.addTrain(train)");
        map.addTrain(train);
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        TrainCart cart = new TrainCart(train, stat, new Color("green"));
        Prompt.removeIndent();

        Prompt.addIndent("cart.setNextNode(node)");
        cart.setNextNode(node);
        Prompt.removeIndent();

        Prompt.addIndent("train.addPart(cart)");
        train.addPart(cart);
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        TrainEngine engine = new TrainEngine(train, new Speed(20));
        Prompt.removeIndent();

        Prompt.addIndent("engine.getSpeed()");
        Speed speed = engine.getSpeed();
        Prompt.removeIndent();

        Prompt.addIndent("engine.setNextNode(node)");
        engine.setNextNode(node);
        Prompt.removeIndent();

        Prompt.addIndent("train.addPart(engine)");
        train.addPart(engine);
        Prompt.removeIndent();

        Prompt.addIndent("<<create>>");
        TrainScheduler scheduler = new TrainScheduler(trainList);
        Prompt.removeIndent();

        Prompt.addIndent("map.addNotifiable(scheduler)");
        map.addNotifiable(scheduler);
        Prompt.removeIndent();

        return map;
    }
}
