package util;

import common.CommonField;
import common.CommonMaze;
import common.CommonMazeObject;
import game.*;

import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.security.Key;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Logging {
    CommonMaze maze;
    PacmanObject pacman;
    List<CommonMazeObject> ghosts;
    List<CommonMazeObject> keys;
    String path;

    long prevTime = new Date().getTime();
    long currentTime = new Date().getTime();

    MazeImplementation mazeImplementation;

    public Logging(CommonMaze maze,String path) {
        this.maze = maze;
        this.ghosts = maze.ghosts();
        this.keys = maze.keys();
        this.path = path;
    }

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


    public void saveLog(Log log,String path) throws IOException {
        // saves the log to a file
        File f = new File(path);
        String file = f.getName();
        File file1 = new File("data\\records\\" + file);
        FileWriter fr = new FileWriter(file1, true);
        fr.write("DELAY: " + log.getFrameDelay() + ";" +
                "PACMAN: " + log.getPacmanPos()[0] + "," + log.getPacmanPos()[1] + ";" +
                "GHOSTS: " + log.getGhostPos().get(0) + "," + log.getGhostPos().get(1) + ";" +
                "KEYS: " + log.getKeyPos().get(0).get(0) + "," + log.getKeyPos().get(0).get(1) + "\n");
        fr.close();
    }

    public void createLog(String path) throws IOException {
        // creates a new log file
        File f = new File(path);
        String file = f.getName();
        File file1 = new File("data\\records\\" + file);
        String mapContent = Files.readAllLines(Paths.get(path)).toString().replaceAll(",","\n");
        FileWriter fw = new FileWriter(file1);
        fw.write(mapContent);
        fw.write("\n");
        fw.close();
    }
}
