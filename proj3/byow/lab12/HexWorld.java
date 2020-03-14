package byow.lab12;
import org.junit.Test;
import static org.junit.Assert.*;

import byow.TileEngine.TERenderer;
import byow.TileEngine.TETile;
import byow.TileEngine.Tileset;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

/**
 * Draws a world consisting of hexagonal regions.
 */
public class HexWorld {

    // compute everyline's xoff
    public Map<Integer, Integer> xOff(int length) {
        Map<Integer, Integer> xOffMap = new HashMap<>();
        for (int i=0; i<2*length; i++) {
            if (i < length) {
                xOffMap.put(i, -i);
            } else {
                xOffMap.put(i, i-2*length+1);
            }
        }
        return xOffMap;
    }

    public Map<Integer, Integer> xWidth(int length) {
        Map<Integer, Integer> xWidthMap = new HashMap<>();
        for (int i=0; i<2*length; i++) {
            if (i < length) {
                xWidthMap.put(i, length+2*i);
            } else {
                xWidthMap.put(i, 5*length-2-2*i);
            }
        }
        return xWidthMap;
    }

    public void addHexagon(int length, Point p, TETile t, TETile[][] world) {
        Map<Integer, Integer> xOffMap = xOff(length);
        Map<Integer, Integer> xWidthMap = xWidth(length);
        int xCoord, yCoord;
        for (int i=0; i <2*length; i++) {
            int xOff = xOffMap.get(i);
            int xWidth = xWidthMap.get(i);
            for (int j=0; j<xWidth; j++) {
                xCoord = p.x+xOff+j;
                yCoord = p.y+i;
                world[xCoord][yCoord] = t;
            }
        }
    }
}
