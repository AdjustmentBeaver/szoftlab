import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Boti on 2017. 03. 16..
 */
public class Prompt {

    private static boolean doSupress = true;
    private static int indentLevel = 0;

    public static void supressMessages(boolean doSupress) {
        Prompt.doSupress = doSupress;
    }

    public static void addIndent() {
        printMessage("->");
        indentLevel++;
    }

    public static void removeIndent() {
        if (indentLevel > 0) {
            indentLevel--;
            printMessage("<-");
        }
    }

    public static int readCommand() {
        int input = -1;
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        String inputStr = "";
        try {
            inputStr = br.readLine();
            input = Integer.parseInt(inputStr);
        } catch (IOException e) {
            return -1;
        } catch (NumberFormatException e) {
            inputStr = inputStr.toLowerCase();
            switch (inputStr) {
                case "yes":;
                case "y":
                    input = 1;
                    break;
                case "no":;
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

    public static void printMessage(String s) {
        for (int i = 0; i < indentLevel; i++) {
            System.out.print("\t");
        }
        System.out.println(s);
    }

}
