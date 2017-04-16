import util.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * Ő végzi az IO műveleteket.
 * <br>
 * Létrehozza magát a pályát és feltölti a rajta lévő és a hozzá kapcsolódó elemekkel.
 */
public class MapBuilder {
    private String mapName;
    /**
     * Létrehoz egy új Map buildert.
     *
     * @param mapName a pálya elérési útvonala
     */
    public MapBuilder(String mapName) {
        this.mapName = mapName;
    }

    /**
     * Létrehozza a pálya működéséhez szükséges összetevőket, és elvégzi a file olvasás műveletet az adatok feldolgozásával.
     *
     * @param game a Game példány amihez csatoljuk
     * @return a betöltött pálya
     */
    public Map buildMap(Game game) {
        try {
            // Betoltjuk az XML dokumentumot az elso parameterkent megadott eleresi utvonalrol
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(mapName);

            // Normalizaljuk a dokumentumot
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();

            // Root node, a mi esetunkben level
            org.w3c.dom.Node level = document.getDocumentElement();
            if (!level.getNodeName().equals("level")) {
                throw new XMLParseException("Wrong root node name: " + level.getNodeName());
            }

            Map map = new Map();

            Statistics stat = new Statistics(game);

            map.addStatistics(stat);

            List<Train> trainList = map.getTrainList();

            List<SpecialPlace> spNeighbours = new ArrayList<>();

            // Entitas csoportok (nodes, trains)
            NodeList groups = level.getChildNodes();
            for (int i=0; i<groups.getLength(); ++i) {
                org.w3c.dom.Node group = groups.item(i);
                // Csak a node tipusu elementek erdekelnek minket
                if (group.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                    // TODO: csoport parseolas
                    // Node tipusu entitasok
                    if (group.getNodeName().equals("nodes")) {
                        // Megkapjuk a listat ami a nodeokat tartalmazza
                        NodeList nodes = group.getChildNodes();
                        for (int j=0; j<nodes.getLength(); ++j) {
                            org.w3c.dom.Node node = nodes.item(j);
                            // Csak a node tipusu elementek kellenek
                            if (node.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                // Nezzuk meg, hogy jo-e a tipusa
                                if (!node.getNodeName().equals("node")) {
                                    throw new XMLParseException("Invalid element: " + node.getNodeName());
                                }
                                // Node nevenek lekerese (kotelezo attributum)
                                String nodeName = getNodeAttribute(node, "name");
                                // Node tipusanak lekerese (kotelezo attributum)
                                String nodeType = getNodeAttribute(node, "type");
                                switch (nodeType) {
                                    case "node":
                                        Node nd = new Node();
                                        map.addNode(nd);
                                        break;
                                    case "switch":
                                        Switch sw = new Switch();
                                        map.addNode(sw);
                                        break;
                                    case "station":
                                        String color = getNodeAttribute(node, "color");
                                        Station station = new Station(new Color(color));
                                        map.addNode(station);
                                        break;
                                    case "loaderStation":
                                        String loaderColor = getNodeAttribute(node, "color");
                                        LoaderStation loaderStation = new LoaderStation(new Color(loaderColor));
                                        map.addNode(loaderStation);
                                        break;
                                    case "specialPlace":
                                        SpecialPlace tunnel = new SpecialPlace(spNeighbours);
                                        map.addNode(tunnel);
                                        break;
                                    default:
                                        throw new XMLParseException("Invalid node type: " + nodeType);
                                }
                                // Node attributumok lekerese, eloszor azokat amik child nodekent vannak tarolva
                                NodeList nodeAttrs = node.getChildNodes();
                                for (int k = 0; k < nodeAttrs.getLength(); k++) {
                                    org.w3c.dom.Node nodeAttr = nodeAttrs.item(k);
                                    if (nodeAttr.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                        String attrName = nodeAttr.getNodeName();
                                        switch (attrName) {
                                            case "position":
                                                // TODO: save position
                                                String xPos = getNodeAttribute(nodeAttr, "x");
                                                String yPos = getNodeAttribute(nodeAttr, "y");
                                                break;
                                            case "neighbours":
                                                NodeList neighbourList = nodeAttr.getChildNodes();
                                                for (int l = 0; l < neighbourList.getLength(); l++) {
                                                    org.w3c.dom.Node neighbour = neighbourList.item(l);
                                                    if (neighbour.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                                        // TODO: parse and save neighbour list
                                                    }
                                                }
                                                break;
                                            default:
                                                throw new XMLParseException("Invalid node attribute: " + attrName);
                                        }
                                    }
                                }
                            }
                        }
                        // Train tipusu entitasok
                    } else if (group.getNodeName().equals("trains")) {
                        NodeList trains = group.getChildNodes();
                        for (int j=0; j<trains.getLength(); ++j) {
                            org.w3c.dom.Node train = trains.item(j);
                            if (train.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                if (!train.getNodeName().equals("train")) {
                                    throw new XMLParseException("Invalid element: " + train.getNodeName());
                                }

                                Train tr = new Train(stat, trainList);
                                map.addTrain(tr);

                                String startNode = getNodeAttribute(train, "start_node");
                                String startTime = getNodeAttribute(train, "start_time");
                                NodeList trainParts = train.getChildNodes();
                                for (int k = 0; k < trainParts.getLength(); k++) {
                                    org.w3c.dom.Node trainPart = trainParts.item(k);
                                    if (trainPart.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                        String partType = getNodeAttribute(trainPart, "type");
                                        switch (partType) {
                                            case "engine":
                                                String speed = getNodeAttribute(trainPart, "speed");
                                                TrainEngine engine = new TrainEngine(tr, new Speed(Integer.parseInt(speed)));
                                                tr.addPart(engine);
                                                break;
                                            case "cart":
                                                String color = getNodeAttribute(trainPart, "color");
                                                TrainCart cart = new TrainCart(tr, stat, new Color(color));
                                                tr.addPart(cart);
                                                break;
                                            case "coalWagon":
                                                TrainCoalWagon wagon = new TrainCoalWagon(tr);
                                                tr.addPart(wagon);
                                                break;
                                            default:
                                                throw new XMLParseException("Invalid train part type: " + partType);
                                        }
                                    }
                                }
                            }
                        }
                    } else {
                        throw new XMLParseException("Invalid group type:" + group.getNodeName());
                    }
                }
            }

            TrainScheduler scheduler = new TrainScheduler(trainList);

            map.addNotifiable(scheduler);

            return map;
        } catch (ParserConfigurationException | SAXException | IOException | XMLParseException e) {
            e.printStackTrace();
        }
        return null;
    }

    /**
     * Gets node attribute.
     *
     * @param n    the node
     * @param attr the attribute to get
     * @return the node attribute as a String
     * @throws NullPointerException   occurs when the node is null
     * @throws NullAttributeException occurs when the specified attribute is not found or empty
     */
    private static String getNodeAttribute(org.w3c.dom.Node n, String attr) throws NullPointerException, NullAttributeException {
        if (n == null) {
            throw new NullPointerException();
        }
        String s = ((Element)n).getAttribute(attr);
        if (s == null || s.equals("")) {
            throw new NullAttributeException("Attribute is null");
        }
        return s;
    }
}
