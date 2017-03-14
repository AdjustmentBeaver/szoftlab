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
        //      Nodes
        Node node = new Node();
        map.addNode(node);
        Switch sw = new Switch();
        map.addNode(sw);
        SpecialPlace tunnel1 = new SpecialPlace();
        map.addNode(tunnel1);
        SpecialPlace tunnel2 = new SpecialPlace();
        map.addNode(tunnel2);
        Station station = new Station(new Color("red"));
        map.addNode(station);

        node.addNeighbourNode(sw);
        sw.addNeighbourNode(station);
        sw.addNeighbourNode(tunnel1);
        tunnel1.addNeighbourNode(tunnel2);
        tunnel2.addNeighbourNode(tunnel1);
        station.addNeighbourNode(tunnel1);

        //      Trains
        Train train1 = new Train(stat, trainList);
        Train train2 = new Train(stat, trainList);
        map.addTrain(train1);
        map.addTrain(train2);
        //      TrainParts
        TrainEngine engine1 = new TrainEngine(train1, new Speed(20));
        TrainEngine engine2 = new TrainEngine(train2, new Speed(20));
        Speed speed = engine1.getSpeed();
        Speed speed2 = engine2.getSpeed();
        engine1.setNextNode(node);
        engine2.setNextNode(station);
        train1.addPart(engine1);
        train2.addPart(engine2);

        TrainCart cart = new TrainCart(train1, stat, new Color("green"));
        cart.setNextNode(node);
        train1.addPart(cart);

        TrainScheduler scheduler = new TrainScheduler(trainList);
        map.addNotifiable(scheduler);

        System.out.println("### END sd_mapBuilding ###");
        return map;
    }
}
