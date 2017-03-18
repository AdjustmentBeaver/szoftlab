import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Boti on 2017. 03. 16..
 */

/**
 * Szkeletonhoz a lefutásokat, üzeneteket kiíró segédosztály.
 */
public class Prompt {

    private static boolean doSupress = false;
    private static int indentLevel = 0;

    /**
     * Supress messages.
     *
     * @param doSupress true ha le legyenek tiltva
     */
    public static void supressMessages(boolean doSupress) {
        Prompt.doSupress = doSupress;
    }

    /**
     * Add indent.
     *
     * @param msg the msg
     */
    public static void addIndent(String msg) {
        if (doSupress) {
            return;
        }
        printMessage("|");
        printMessage("|" + msg);
        printMessage("|-->");
        indentLevel++;
    }

    /**
     * Remove indent.
     */
    public static void removeIndent() {
        if (doSupress) {
            return;
        }
        indentLevel--;
        printMessage("|<--");
    }

    /**
     * Standard inputról érkezö parancsokat várja, értelmezi
     *
     * @return int-ként adja vissza a beolvasott értéket, hiba esetén -1
     */
    public static int readCommand() {
        int input = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        try {
            inputStr = br.readLine().toLowerCase();
            input = Integer.parseInt(inputStr);
        } catch (IOException e) {
            return input;
        } catch (NumberFormatException e) {
            switch (inputStr) {
                case "yes":
                case "y":
                    input = 1;
                    break;
                case "no":
                case "n":
                    input = 0;
                    break;
                default:
                    input = -1;
                    break;
            }
        }
        return input;
    }

    /**
     * Eldötendö kérdésekkor használt osztály.
     *
     * @return true - [Y]es
     */
    public static boolean readBool() {
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        try {
            String inputStr = br.readLine().toLowerCase();
            switch (inputStr) {
                case "yes":
                case "y":
                    return true;
                default:
                    return false;
            }
        } catch (IOException e) {
            return false;
        }
    }

    /**
     * Print message.
     *
     * @param s the message
     */
    public static void printMessage(String s) {
        if (doSupress) {
            return;
        }
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("|\t");
        }
        System.out.println(s);
    }

}
