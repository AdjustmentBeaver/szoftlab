import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

import javax.management.modelmbean.XMLParseException;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import java.io.IOException;

/**
 * Teszt app, amivel az XML feldolgozast lehet tesztelni
 * A program elso parametere a betoltendo xml fajl eleresi utvonala
 */
public class App {
    public static void main(String[] args) {
        try {
            // Betoltjuk az XML dokumentumot az elso parameterkent megadott eleresi utvonalrol
            DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            factory.setValidating(true);
            factory.setNamespaceAware(true);
            DocumentBuilder builder = factory.newDocumentBuilder();
            Document document = builder.parse(args[0]);

            // Normalizaljuk a dokumentumot
            // http://stackoverflow.com/questions/13786607/normalization-in-dom-parsing-with-java-how-does-it-work
            document.getDocumentElement().normalize();

            // Root node, a mi esetunkben level
            Node level = document.getDocumentElement();
            if (!level.getNodeName().equals("level")) {
                throw new XMLParseException("Wrong root node name: " + level.getNodeName());
            }
            // Entitas csoportok (nodes, trains)
            NodeList groups = level.getChildNodes();
            for (int i=0; i<groups.getLength(); ++i) {
                Node group = groups.item(i);
                // Csak a node tipusu elementek erdekelnek minket
                if (group.getNodeType() == Node.ELEMENT_NODE) {
                    // TODO: csoport parseolas
                    System.out.println("Group " + i + ": " + group.getNodeName());
                    // Node tipusu entitasok
                    if (group.getNodeName().equals("nodes")) {
                        // Megkapjuk a listat ami a nodeokat tartalmazza
                        NodeList nodes = group.getChildNodes();
                        for (int j=0; j<nodes.getLength(); ++j) {
                            Node node = nodes.item(j);
                            // Csak a node tipusu elementek kellenek
                            if (node.getNodeType() == Node.ELEMENT_NODE) {
                                // Nezzuk meg, hogy jo-e a tipusa
                                if (!node.getNodeName().equals("node")) {
                                    throw new XMLParseException("Invalid element: " + node.getNodeName());
                                }
                                // Node attributumok lekerese, eloszor azokat amik child nodekent vannak tarolva
                                NodeList nodeAttrs = node.getChildNodes();
                                for (int k = 0; k < nodeAttrs.getLength(); k++) {
                                    Node nodeAttr = nodeAttrs.item(k);
                                    if (nodeAttr.getNodeType() == Node.ELEMENT_NODE) {
                                        String attrName = nodeAttr.getNodeName();
                                        switch (attrName) {
                                            case "position":
                                                // TODO: save position
                                                String xPos = ((Element) nodeAttr).getAttribute("x");
                                                String yPos = ((Element) nodeAttr).getAttribute("y");
                                                System.out.println("Position: (" + xPos + ";" + yPos + ")");
                                                break;
                                            case "neighbours":
                                                // TODO: parse and save neighbour list
                                                System.out.println("Neighbours");
                                                break;
                                            default:
                                                throw new XMLParseException("Invalid node attribute: " + attrName);
                                        }
                                    }
                                }
                                // Node nevenek lekerese (kotelezo attributum)
                                String nodeName = ((Element)node).getAttribute("name");
                                System.out.println("Node name: " + nodeName);
                                // Node tipusanak lekerese (kotelezo attributum)
                                String nodeType = ((Element)node).getAttribute("type");
                                switch (nodeType) {
                                    case "node":
                                        System.out.println("Node is a node");
                                        break;
                                    case "switch":
                                        System.out.println("Node is a switch");
                                        break;
                                    case "station":
                                        System.out.println("Node is a station");
                                        break;
                                    case "loader_station":
                                        System.out.println("Node is a loader station");
                                        break;
                                    case "special_place":
                                        System.out.println("Node is a special place");
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
                            Node train = trains.item(j);
                            if (train.getNodeType() == Node.ELEMENT_NODE) {
                                System.out.println("Train " + j + ": " + train.getNodeName());
                            }
                        }
                    } else {
                        throw new XMLParseException("Invalid group type:" + group.getNodeName());
                    }
                }
            }
        } catch (ParserConfigurationException | SAXException | IOException | XMLParseException e) {
            e.printStackTrace();
        }
    }
}
