import util.Coordinate;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * Created by Boti on 2017. 03. 16..
 */
public class Prompt {
    private Game game;
    
    public Prompt(Game g) {
        game = g;
    }
    
    public void commandRead() {
        String input ="";
        InputStreamReader ir = new InputStreamReader(System.in);
        BufferedReader br = new BufferedReader(ir);
        try { input = br.readLine(); input = input.toLowerCase(); } catch (IOException e) {}
        while (!input.equals("exit")) {
            parseInput(input);
            try { input = br.readLine(); input = input.toLowerCase(); } catch (IOException e) {}
        }
    }
    
    private void parseInput(String input) {

        String[] inputParts = input.split("\\s+");  //szetvagja whitespace-enkent a bemenetet
        switch (inputParts[0])  {
            case "start":
                game.startGame();
                break;
            case "stop":
                game.stopGame();                
                break;
            case "new":
                game.newGame("level1");
                break;
            case "load":
                if (inputParts.length != 2 )
                    break;
                game.newGame(inputParts[1]);
                break;
            case "save":
                if (inputParts.length != 2 )
                    break;
                MapManager mapm = new MapManager();
                mapm.saveMap(inputParts[1]);
                break;
            case "simulate":

                break;
            case "collcheck":

                break;
            case "explode":

                break;
            case "activate":
                Map map = new Map();
                map.activateNode(new Coordinate());
                break;
        }
    }
    
}
