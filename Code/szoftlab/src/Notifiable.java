/**
 * Created by Istvan Telek on 3/14/2017.
 */

/**
 * Felületet nyújt az időzítőre feliratkozni kívánó osztályok számára.
 */
public interface Notifiable {
    /**
     * Az időzítéskor meghívott metódus. A SimualtionTimer hívja meg a Notifiablet megvalósító osztályoknak.
     */
    void update();
}
