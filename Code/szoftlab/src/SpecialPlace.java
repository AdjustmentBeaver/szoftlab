import java.util.List;

/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 *     Ezeken a speciális csomópontokon tud a felhasználó alagútszájakat építeni. A pályán maximum 2 alagútszáj lehet aktív, amiket alagút köt össze. Megépített alagútszájat le is lehet rombolni, ha nem tartózkodik az alagútban vonat.
 * </p>
 */
public class SpecialPlace extends Node{

    private static final int MAX_NUM_SPECIALPLACE = 2;

    List<SpecialPlace> spList;
    boolean isConstructed;

    /**
     * Konstruktor. Létrehozza a SpecialPlacet.
     */
    public SpecialPlace(List<SpecialPlace> spList) {
        super();
        this.spList = spList;
        isConstructed = false;
    }

    /**
     * Elvégzi az alagútszáj építéséhez köthető logikát, ha az lehetséges. Egy privát statikus változóban tárolódik, hogy mely szájak vannak felépítve a pályán (maximum 2 lehetséges). Ez alapján eldönti, hogy lehetséges-e. Alagútszáj lerombolásánál gondoskodik róla, hogy csak akkor lehessen lerombolni, ha az alagútban nem tartózkodik vonat.
     */
    @Override
    public void activate() {
        boolean trainOnMe = checkForTrain();
        boolean canConstruct = canConstruct();
        if (!trainOnMe && canConstruct) {
            isConstructed = true;
        }
    }

    @Override
    protected Node route() {
        // Ha van másik felépített alagútszáj
        for(SpecialPlace sp: spList){
            if (sp.isConstructed && sp != this)
                return sp;
        }
        return null;
    }

    private boolean canConstruct(){
        int numOfConstructed = 0;
        for(SpecialPlace sp: spList)
            if (sp.isConstructed) numOfConstructed++;

        // Ha fel van építve az összes lehetséges
        if (numOfConstructed == SpecialPlace.MAX_NUM_SPECIALPLACE)
            return false;

        return true;
    }
}
