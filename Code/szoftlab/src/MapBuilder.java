import util.Color;
import util.Speed;

import java.util.List;

/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */
public class MapBuilder {
    public MapBuilder(String mapName) {
        System.out.println("MapBuilder.MapBuilder");
    }

    public Map buildMap(Game game) {
        System.out.println("### sd_mapBuilding ###");
        System.out.println("MapBuilder.buildMap");
        Map map = new Map();
        Statistics stat = new Statistics(game);
        map.addStatistics(stat);
        List<Train> trainList = map.getTrainList();
        //  File I/O
        //      Trains
        Train train = new Train(stat, trainList);
        map.addTrain(train);
        //      TrainParts
        TrainEngine engine = new TrainEngine(train, new Speed(20));
        Speed speed = engine.getSpeed();
        train.addPart(engine);

        TrainCart cart = new TrainCart(train, stat, new Color("green"));
        train.addPart(cart);
        //      Nodes
        Node node = new Node();
        map.addNode(node);
        Switch sw = new Switch();
        map.addNode(sw);
        SpecialPlace tunnel1 = new SpecialPlace();
        map.addNode(tunnel1);
        SpecialPlace tunnel2 = new SpecialPlace();
        map.addNode(tunnel2);
        Station station = new Station();
        map.addNode(station);

        node.addNeighbourNode(sw);
        sw.addNeighbourNode(station);
        sw.addNeighbourNode(tunnel1);
        tunnel1.addNeighbourNode(tunnel2);
        tunnel2.addNeighbourNode(tunnel1);
        station.addNeighbourNode(tunnel1);

        TrainScheduler scheduler = new TrainScheduler(trainList);
        map.addNotifiable(scheduler);

        System.out.println("### END sd_mapBuilding ###");
        return map;
    }
}
