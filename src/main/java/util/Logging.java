/**
 * Class for logging the game
 * @authors: xjalak00, xkvasn14
 */
package util;

import common.CommonField;
import common.CommonMaze;
import common.CommonMazeObject;
import game.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Class for logging the game
 */
public class Logging {
    CommonMaze maze;
    CommonMazeObject pacman;
    List<CommonMazeObject> ghosts;
    List<CommonMazeObject> keys;
    String path;

    long prevTime = new Date().getTime();
    long currentTime = new Date().getTime();

    MazeImplementation mazeImplementation;

    /**
     * Constructor
     * @param maze
     * @param path
     */
    public Logging(CommonMaze maze,String path) {
        this.maze = maze;
        this.ghosts = maze.ghosts();
        this.keys = maze.keys();
        this.pacman = maze.pacman();
        this.path = path;
    }

    /**
     * Constructor
     */
    public void log() throws IOException {
        // takes data from the maze and logs it to a file
        int pacmanPos[] = new int[2];
        ArrayList<List<Integer>> ghostPos = new ArrayList<List<Integer>>();
        ArrayList<List<Integer>> keyPos = new ArrayList<List<Integer>>();
        double frameDelay = 0.0;

        for (int i = 0; i < this.maze.numRows(); i++) {
            for (int j = 0; j < this.maze.numCols(); j++) {
                CommonField field = this.maze.getField(i, j);
                if (field.contains(this.pacman)) {
                    // log pacman position
                    pacmanPos[0] = i;
                    pacmanPos[1] = j;
                }
                for (CommonMazeObject ghost : this.ghosts) {
                    if (field.contains(ghost)) {
                        // log ghost position
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(j);
                        ghostPos.add(list);
                    }
                }
                for (CommonMazeObject key : this.keys) {
                    if (field.contains(key)) {
                        // log key position
                        List<Integer> list = new ArrayList<Integer>();
                        list.add(i);
                        list.add(j);
                        keyPos.add(list);
                    }
                }
            }
        }
        frameDelay = this.currentTime - this.prevTime;
        this.prevTime = this.currentTime;
        this.currentTime = new Date().getTime();
        Log log = new Log(frameDelay, pacmanPos, ghostPos, keyPos);
        saveLog(log, path);
    }

    /**
     * Saves the log to a file
     * @param log
     * @param path
     * @throws IOException
     */
    public void saveLog(Log log,String path) throws IOException {
        // saves the log to a file
        File f = new File(path);
        String file = f.getName();
        File file1 = new File("data\\records\\" + "record_" + file);
        FileWriter fr = new FileWriter(file1, true);
        fr.write("DELAY: " + log.getFrameDelay() + ";" +
                "PACMAN: " + log.getPacmanPos()[0] + "," + log.getPacmanPos()[1] + ";" +
                "GHOSTS: " + log.getGhostPos()  + ";" +
                "KEYS: " + log.getKeyPos() + "\n");
        fr.close();
    }

    /**
     * Creates a new log file
     * @param path
     * @throws IOException
     */
    public void createLog(String path) throws IOException {
        // creates a new log file
        File f = new File(path);
        String file = f.getName();
        File file1 = new File("data\\records\\" + "record_" + file);
        String mapContent = Files.readAllLines(Paths.get(path)).toString().replaceAll(",","\n");
        FileWriter fw = new FileWriter(file1);
        fw.write(mapContent);
        fw.write("\n");
        fw.close();
    }
}
