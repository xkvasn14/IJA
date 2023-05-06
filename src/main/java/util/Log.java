/**
 * @authors: xjalak00, xkvasn14
 * @file Log.java
 * @description: Class for storing log data
 */

package util;

import java.util.ArrayList;
import java.util.List;

/**
 * Class for storing log data
 */
public class Log {
    double frameDelay;
    int[] pacmanPos;
    ArrayList<List<Integer>> ghostPos;
    ArrayList<List<Integer>> keyPos;

    /**
     * Constructor
     * @param frameDelay frame delay
     * @param pacmanPos pacman position
     * @param ghostPos ghost positions
     * @param keyPos key positions
     */
    Log(double frameDelay, int[] pacmanPos, ArrayList<List<Integer>> ghostPos, ArrayList<List<Integer>> keyPos) {
        this.frameDelay = frameDelay;
        this.pacmanPos = pacmanPos;
        this.ghostPos = ghostPos;
        this.keyPos = keyPos;
    }

    /**
     * Getter for frameDelay
     * @return frameDelay
     */
    public double getFrameDelay() {
        return this.frameDelay;
    }

    /**
     * Getter for pacmanPos
     * @return pacmanPos
     */
    public int[] getPacmanPos() {
        return this.pacmanPos;
    }

    /**
     * Getter for ghostPos
     * @return ghostPos
     */
    public ArrayList<List<Integer>> getGhostPos() {
        return this.ghostPos;
    }

    /**
     * Getter for keyPos
     * @return keyPos
     */
    public ArrayList<List<Integer>> getKeyPos() {
        return this.keyPos;
    }
}
