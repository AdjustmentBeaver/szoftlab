import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Boti on 2017. 03. 16..
 */
public class Prompt {

    private static boolean supressMessages = false;

    public static int readCommand() {
        int input = -1;
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        try {
            input = Integer.parseInt(br.readLine());
        } catch (IOException e) {
        }
        return input;
    }

    public static void printMessage(String s) {


    }

}
