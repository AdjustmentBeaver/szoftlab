import util.*;
import org.w3c.dom.*;
import org.xml.sax.SAXException;
import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Ő végzi az IO műveleteket.
 * <br>
 * Létrehozza magát a pályát és feltölti a rajta lévő és a hozzá kapcsolódó elemekkel.
 */
public class MapBuilder {
    private String mapName;
    private Game game;
    /**
     * Létrehoz egy új Map buildert.
     *
     * @param mapName a pálya elérési útvonala
     */
    public MapBuilder(String mapName) {
        this.mapName = mapName;
        this.game = null;
    }

    /**
     * Létrehozza a pálya működéséhez szükséges összetevőket, és elvégzi a file olvasás műveletet az adatok feldolgozásával.
     *
     * @return a betöltött pálya
     */
    public Map buildMap() {
        try {
            if (game == null) {
                throw new NullPointerException("Game is null");
            }

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

            Map map = new Map(game);

            List<Train> trainList = map.getTrainList();

            List<SpecialPlace> spNeighbours = new ArrayList<>();
            // A nodeok listaja, neveikkel azonositva
            HashMap<String, Node> nodeList = new HashMap<>();
            // A nodeok szomszedainak listai, minden nodeot a neveivel azonositva
            // Azert kell ez a modszer, mert beolvasas kozben nem tudjuk egybol a szomszedokat hozzaadni, ha meg nem
            // olvastunk be minden szomszedot, a feldolgozast a vegen fogjuk elvegezni
            HashMap<String, ArrayList<String>> nodeNeighbours = new HashMap<>();

            // Entitas csoportok (nodes, trains)
            NodeList groups = level.getChildNodes();
            for (int i=0; i<groups.getLength(); ++i) {
                org.w3c.dom.Node group = groups.item(i);
                // Csak a node tipusu elementek erdekelnek minket
                if (group.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
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
                                // Node pozicioja
                                Coordinate pos = null;
                                // Node attributumok lekerese, eloszor azokat amik child nodekent vannak tarolva
                                NodeList nodeAttrs = node.getChildNodes();
                                for (int k = 0; k < nodeAttrs.getLength(); k++) {
                                    org.w3c.dom.Node nodeAttr = nodeAttrs.item(k);
                                    if (nodeAttr.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                        String attrName = nodeAttr.getNodeName();
                                        switch (attrName) {
                                            case "position":
                                                String xPos = getNodeAttribute(nodeAttr, "x");
                                                String yPos = getNodeAttribute(nodeAttr, "y");
                                                pos = new Coordinate(Integer.parseInt(xPos), Integer.parseInt(yPos));
                                                break;
                                            case "neighbours":
                                                NodeList neighbourList = nodeAttr.getChildNodes();
                                                // Csinalunk egy uj listat a szomszedok neveivel
                                                ArrayList<String> nbList = new ArrayList<>();
                                                nodeNeighbours.put(nodeName, nbList);
                                                // Minden egyes szomszedot hozzaadunk a listahoz
                                                for (int l = 0; l < neighbourList.getLength(); l++) {
                                                    org.w3c.dom.Node neighbour = neighbourList.item(l);
                                                    // Csak a Node tipusuak erdekelnek
                                                    if (neighbour.getNodeType() == org.w3c.dom.Node.ELEMENT_NODE) {
                                                        // Hozzaadjuk a listahoz, hogy mi a szomszed neve
                                                        nbList.add(getNodeAttribute(neighbour, "name"));
                                                    }
                                                }
                                                break;
                                            default:
                                                throw new XMLParseException("Invalid node attribute: " + attrName);
                                        }
                                    }
                                }
                                switch (nodeType) {
                                    case "node":
                                        Node nd = new Node(pos);
                                        map.addNode(nd);
                                        nodeList.put(nodeName, nd);
                                        break;
                                    case "switch":
                                        Switch sw = new Switch(pos);
                                        map.addNode(sw);
                                        nodeList.put(nodeName, sw);
                                        break;
                                    case "station":
                                        String color = getNodeAttribute(node, "color");
                                        Station station = new Station(pos, new Color(color));
                                        map.addNode(station);
                                        nodeList.put(nodeName, station);
                                        break;
                                    case "loaderStation":
                                        String loaderColor = getNodeAttribute(node, "color");
                                        LoaderStation loaderStation = new LoaderStation(pos, new Color(loaderColor));
                                        map.addNode(loaderStation);
                                        nodeList.put(nodeName, loaderStation);
                                        break;
                                    case "specialPlace":
                                        SpecialPlace tunnel = new SpecialPlace(pos, spNeighbours);
                                        map.addNode(tunnel);
                                        nodeList.put(nodeName, tunnel);
                                        break;
                                    default:
                                        throw new XMLParseException("Invalid node type: " + nodeType);
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
                                String startNode = getNodeAttribute(train, "start_node");
                                if (!nodeList.containsKey(startNode)) {
                                    throw new NullPointerException("Invalid start node: " + startNode);
                                }
                                String startTime = getNodeAttribute(train, "start_time");
                                Train tr = new Train(trainList, nodeList.get(startNode), Integer.parseInt(startTime));
                                map.addTrain(tr);
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
                                                TrainCart cart = new TrainCart(tr, new Color(color));
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

            // A train schedulert letrehozzuk, es atadjuk neki a vonatok listajat
            TrainScheduler scheduler = new TrainScheduler(trainList);
            map.addNotifiable(scheduler);

            // Feldolgozzuk a szomszedsagi listat
            for (java.util.Map.Entry<String, ArrayList<String>> ndList: nodeNeighbours.entrySet()) {
                // Az aktualis node kell, amit a node listabol tudunk kivenni
                Node actNode = nodeList.get(ndList.getKey());
                // Feldolgozzuk a szomszedokat
                for (String nbName: ndList.getValue()) {
                    // Ha a szomszed nincs a listaban, akkor NullPointerException
                    if (!nodeList.containsKey(nbName)) {
                        throw new NullPointerException("Invalid neighbour: " + nbName);
                    }
                    // Ha van, akkor odaadjuk neki a szomszedot
                    actNode.addNeighbourNode(nodeList.get(nbName));
                }
            }

            return map;
        } catch (ParserConfigurationException | SAXException | IOException | XMLParseException | NullPointerException | NumberFormatException | NullAttributeException e) {
            System.err.println("Error while parsing level: " + mapName);
            System.err.println(e.getMessage());
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
            throw new NullAttributeException("Attribute is null: " + attr);
        }
        return s;
    }

    public void setGame(Game game) {
        this.game = game;
    }
}
