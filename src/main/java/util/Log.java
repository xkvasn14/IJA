package util;

import java.util.ArrayList;
import java.util.List;

public class Log {
    double frameDelay;
    int[] pacmanPos;
    ArrayList<List<Integer>> ghostPos;
    ArrayList<List<Integer>> keyPos;

    Log(double frameDelay, int[] pacmanPos, ArrayList<List<Integer>> ghostPos, ArrayList<List<Integer>> keyPos) {
        this.frameDelay = frameDelay;
        this.pacmanPos = pacmanPos;
        this.ghostPos = ghostPos;
        this.keyPos = keyPos;
    }

    public double getFrameDelay() {
        return this.frameDelay;
    }

    public int[] getPacmanPos() {
        return this.pacmanPos;
    }

    public ArrayList<List<Integer>> getGhostPos() {
        return this.ghostPos;
    }

    public ArrayList<List<Integer>> getKeyPos() {
        return this.keyPos;
    }
}
