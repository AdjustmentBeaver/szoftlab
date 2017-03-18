/**
 * Created by Istvan Telek on 3/14/2017.
 * <p>
 *     Ezeken a speciális csomópontokon tud a felhasználó alagútszájakat építeni. A pályán maximum 2 alagútszáj lehet aktív, amiket alagút köt össze. Megépített alagútszájat le is lehet rombolni, ha nem tartózkodik az alagútban vonat.
 * </p>
 */
public class SpecialPlace extends Node {

    /**
     * Konstruktor. Létrehozza a SpecialPlacet.
     */
    public SpecialPlace() {
        Prompt.printMessage("SpecialPlace.SpecialPlace");
    }

    /**
     * Elvégzi az alagútszáj építéséhez köthető logikát, ha az lehetséges. Egy privát statikus változóban tárolódik, hogy mely szájak vannak felépítve a pályán (maximum 2 lehetséges). Ez alapján eldönti, hogy lehetséges-e. Alagútszáj lerombolásánál gondoskodik róla, hogy csak akkor lehessen lerombolni, ha az alagútban nem tartózkodik vonat.
     */
    @Override
    public void activate() {
        Prompt.addIndent("tunnel.checkForTrain()");
        boolean trainOnMe = checkForTrain();
        Prompt.removeIndent();
    }
}
