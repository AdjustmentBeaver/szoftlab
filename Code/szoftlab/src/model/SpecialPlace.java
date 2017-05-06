package model;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import model.util.Coordinate;
import view.View;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 *     Ezeken a speciális csomópontokon tud a felhasználó alagútszájakat építeni. A pályán maximum 2 alagútszáj lehet aktív, amiket alagút köt össze. Megépített alagútszájat le is lehet rombolni, ha nem tartózkodik az alagútban vonat.
 * </p>
 */
public class SpecialPlace extends Node {

    /**
     * Maximálisan felépíthető alagútszájak száma
     */
    private static final int MAX_NUM_SPECIALPLACE = 2;

    /**
     * Az összes model.SpecialPlace-et tartalmazó lista.
     */
    public List<SpecialPlace> spList;

    /**
     * Fel van-e építve az alagútszáj
     */
    public boolean isConstructed;

    /**
     * Konstruktor. Létrehozza a SpecialPlacet.
     */
    public SpecialPlace(Coordinate pos, List<SpecialPlace> spList) {
        super(pos);
        this.spList = spList;
        isConstructed = false;
    }

    protected boolean checkWasLast() {
        if (lastTrain != null){
            for(TrainPart tp: lastTrain.getPartList()){
                if (tp.getPrevNode() == this) {
                    return true;
                }
            }
        }
        return false;
    }

    /**
     * Elvégzi az alagútszáj építéséhez köthető logikát, ha az lehetséges. Egy privát statikus változóban tárolódik, hogy mely szájak vannak felépítve a pályán (maximum 2 lehetséges). Ez alapján eldönti, hogy lehetséges-e. Alagútszáj lerombolásánál gondoskodik róla, hogy csak akkor lehessen lerombolni, ha az alagútban nem tartózkodik vonat.
     */
    @Override
    public void activate() {
        boolean trainWasOnMe = checkWasLast();

        SpecialPlace nb = null;
        for(SpecialPlace sp: spList){
            if (sp.isConstructed && sp != this)
                nb = sp;
        }

        boolean trainWasOnNb = false;
        Train lastNbTrain = null;
        if (nb != null) {
            trainWasOnNb = nb.checkWasLast();
            lastNbTrain = nb.lastTrain;
        }

        boolean trainInTunnel = false;
        if (lastTrain != null) {
            Node pnd = lastTrain.getPartList().get(0).getPrevNode();
            Node nnd = lastTrain.getPartList().get(0).getNextNode();
            if ((trainWasOnMe && (nnd == nb)) || (trainWasOnMe && (pnd == nb)) || (trainWasOnNb && (pnd == this))) {
                trainInTunnel = true;
            }
        }
        if (lastNbTrain != null) {
            Node pnd = lastNbTrain.getPartList().get(0).getPrevNode();
            Node nnd = lastNbTrain.getPartList().get(0).getNextNode();
            if ((trainWasOnMe && (pnd == nb)) || (trainWasOnNb && (nnd == this))) {
                trainInTunnel = true;
            }
        }

        // Nem modosithatjuk az allapotat, hogyha az alagutban van a vonat, vagy nincs felepitve, de a szomszedjaval akarjuk osszekotni.
        if (trainInTunnel || !isConstructed && !canConstruct()) {
            return;
        }

        isConstructed = !isConstructed;

    }

    @Override
    protected void accept(TrainPart tp) {
        super.accept(tp);
        if (!isConstructed) {
            return;
        }
        for(SpecialPlace sp: spList) {
            if (sp.isConstructed) {
                if (sp.equals(tp.getNextNode())) {
                    tp.setInTunnel();
                }
            }
        }
    }

    /**
     * Ha van másik felépített alagútszáj oda irányítja a vonatot, különben vakvágány
     * @return A következő csomópont
     */
    @Override
    protected Node route() {
        if (!isConstructed)
             return null;
        Node nb = super.route();
        if (nb != null)
            return nb;
        for(SpecialPlace sp: spList){
            if (sp.isConstructed && sp != this) {
                return sp;
            }
        }
        return null;
    }

    /**
     * Megadja, hogy felépíthető-e a alagútszáj a pályán
     * @return Igaz, ha felépíthető
     */
    private boolean canConstruct(){
        ArrayList<SpecialPlace> spListConstructed = new ArrayList<>();
        for(SpecialPlace sp: spList) {
            if (sp.isConstructed) {
                spListConstructed.add(sp);
            }
        }

        // Ha fel van építve az összes lehetséges
        if (spListConstructed.size() == SpecialPlace.MAX_NUM_SPECIALPLACE) {
            return false;
        }

        // Ha a szomszed alaguttal akarjuk magunkat osszekotni
        for (SpecialPlace sp: spListConstructed) {
            if (sp.equals(neighbourNodeList.get(0))) {
                return false;
            }
        }

        return true;
    }

    @Override
    public String toString() {
        return "type=specialPlace " + "pos=" + pos + " isConstructed=" + isConstructed;
    }

    @Override
    public void draw(View view) {
        view.draw(this);
    }
}
