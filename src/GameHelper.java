import java.io.*;
import java.util.*;

public class GameHelper {
    private static final String alphabet = "abcdefg";
    private int gridLength = 7;
    private  int gridSize = 49;
    private int [] grid = new int[gridSize];
    private int comCount = 0;

    public String getUserInput (String promt) {
        String inputLine = null;
        System.out.print((promt + " "));
        try {
            BufferedReader is = new BufferedReader(
                    new InputStreamReader(System.in));
            inputLine = is.readLine();
            if (inputLine.length() == 0) return  null;
        } catch (IOException e) {
                System.out.println("IOException: " + e);
        }
        return inputLine.toLowerCase();
    }
    public ArrayList<String> placeDotCom (int comSize) {
        ArrayList<String> alphaCells = new ArrayList<String>();
        //String [] alphacoords = new String[comSize];
        String temp = null;
        int [] coords = new int[comSize];
        int attempts = 0;
        boolean success = false;
        int location;

        comCount = (int) (Math.random() * 2 + 1);
        int incr = 1;
        if ((comCount / 2) == 1) {
            incr = gridLength;
        }

        while ( !success & attempts++ < 200) {
            location = (int) (Math.random() * gridSize);
            System.out.print(" пробуем " + location);
            int x = 0;
            success = true;
            while (success && x < comSize) {
                if (grid[location] == 0) {
                    coords[x++] = location;
                    location += incr;
                    if (location >= gridSize) {
                        System.out.println("выходит за границы по высоте");
                        success = false;
                    }
                    System.out.println("\nlocation % gridLength: " + location % gridLength);
                    if (x>0 && (location % gridLength == 0)) {
                        System.out.println("выходит за границы по ширине");
                        success = false;
                    }
                }
                else {
                    System.out.print(" используется " + location);
                    success = false;
                }
            }
        }

        int x1 = 0;
        int row = 0;
        int column = 0;

        while (x1 < comSize) {
            grid[coords[x1]] = 1;
            row = (int) (coords[x1] / gridLength);
            column = coords[x1] % gridLength;
            temp = String.valueOf(alphabet.charAt(column));

            alphaCells.add(temp.concat(Integer.toString(row)));
            x1++;
            System.out.print(" coord "+x1+" = " + alphaCells.get(x1-1));
        }
        System.out.print("\n");
        return alphaCells;
    }
}
