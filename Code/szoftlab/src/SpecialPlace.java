import util.Coordinate;

import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 *     Ezeken a speciális csomópontokon tud a felhasználó alagútszájakat építeni. A pályán maximum 2 alagútszáj lehet aktív, amiket alagút köt össze. Megépített alagútszájat le is lehet rombolni, ha nem tartózkodik az alagútban vonat.
 * </p>
 */
public class SpecialPlace extends Node{

    /**
     * Maximálisan felépíthető alagútszájak száma
     */
    private static final int MAX_NUM_SPECIALPLACE = 2;

    /**
     * Az összes SpecialPlace-et tartalmazó lista.
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

    /**
     * Elvégzi az alagútszáj építéséhez köthető logikát, ha az lehetséges. Egy privát statikus változóban tárolódik, hogy mely szájak vannak felépítve a pályán (maximum 2 lehetséges). Ez alapján eldönti, hogy lehetséges-e. Alagútszáj lerombolásánál gondoskodik róla, hogy csak akkor lehessen lerombolni, ha az alagútban nem tartózkodik vonat.
     */
    @Override
    public void activate() {
        if (isConstructed){
            isConstructed = false;
        } else {
            boolean trainOnMe = checkForTrain();
            boolean canConstruct = canConstruct();
            if (!trainOnMe && canConstruct) {
                for (int i = 0 ; i < spList.size(); i++) {
                    if (this == spList.get(i)){
                        System.out.println("activate SpecialPlace" + i);
                    }
                }
                isConstructed = true;
            }
        }

    }

    /**
     * Ha van másik felépített alagútszáj oda irányítja a vonatot, különben vakvágány
     * @return A következő csomópont
     */
    @Override
    protected Node route() {
        for(SpecialPlace sp: spList){
            if (sp.isConstructed && sp != this)
                return sp;
        }
        return null;
    }

    /**
     * Megadja, hogy felépíthető-e a alagútszáj a pályán
     * @return Igaz, ha felépíthető
     */
    private boolean canConstruct(){
        int numOfConstructed = 0;
        for(SpecialPlace sp: spList)
            if (sp.isConstructed) numOfConstructed++;

        // Ha fel van építve az összes lehetséges
        if (numOfConstructed == SpecialPlace.MAX_NUM_SPECIALPLACE)
            return false;

        return true;
    }

    @Override
    public String toString() {
        return "type=specialPlace " + "pos=" + pos + " isConstructed=" + isConstructed;
    }
}
