import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Boti on 2017. 03. 16..
 */
public class Prompt {

    private static boolean doSupress = false;
    private static int indentLevel = 0;

    public static void supressMessages(boolean doSupress) {
        Prompt.doSupress = doSupress;
    }

    public static void addIndent(String msg) {
        if (doSupress) {
            return;
        }
        printMessage("|");
        printMessage("|" + msg);
        printMessage("|-->");
        indentLevel++;
    }

    public static void removeIndent() {
        if (doSupress) {
            return;
        }
        indentLevel--;
        printMessage("|<--");
    }

    public static int readCommand() {
        int input = -1;
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        String inputStr = "";
        try {
            inputStr = br.readLine().toLowerCase();
            input = Integer.parseInt(inputStr);
        } catch (IOException e) {
            return -1;
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
