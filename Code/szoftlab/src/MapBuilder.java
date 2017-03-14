/**
 * Created by szilard95 on 3/14/17.
 * Project: szoftlab
 */
public class MapBuilder {
    public MapBuilder(String mapName) {
        System.out.println("MapBuilder.MapBuilder");
    }

    public Map buildMap(Game game) {
        System.out.println("MapBuilder.buildMap");
        Map map = new Map();
        Statistics stat = new Statistics();
        map.addStatistics(stat);
        List<Train> trainList = map.getTrainList();
        //  File I/O
        //      Trains
        Train train = new Train(stat, trainList);
        map.addTrain(train);
        //      TrainParts
        TrainEngine engine = new TrainEngine(train, stat, new Speed(20));
        Speed speed = engine.getSpeed();
        train.addPart(engine);

        TrainCart cart = new TrainCart(train, stat, new Color("green"));
        train.addPart(cart);
        //      Nodes
        Node node = new Node();
        Switch sw = new Switch();
        SpecialPlace tunnel1 = new SpecialPlace();
        SpecialPlace tunnel2 = new SpecialPlace();
        Station station = new Station();
        map.addNode(node);
        map.addNode(sw);
        map.addNode(tunnel1);
        map.addNode(tunnel2);
        map.addNode(station);

        node.addNeighbourNode(sw);
        sw.addNeighbourNode(station);
        sw.addNeighbourNode(tunnel1);
        tunnel1.addNeighbourNode(tunnel2);
        tunnel2.addNeighbourNode(tunnel1);
        station.addNeighbourNode(tunnel1);

        TrainScheduler scheduler = new TrainScheduler(trainList);
        map.addNotifiable(scheduler);
    }
}
