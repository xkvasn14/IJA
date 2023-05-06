/**
 * @authors: xjalak00, xkvasn14
 */
package util;

import common.CommonMaze;
import game.MazeConfigure;
import window.SystemWindow;

import java.io.*;

/**
 * Class for reading the map from a file
 */
public class MapReader {

    /**
     * Reads the map from a file
     * @param path
     * @return CommonMaze
     * @throws IOException
     */
    public CommonMaze readMap(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            throw new IOException("File not found");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line = br.readLine();
        // parse line into two Integers
        String[] split = line.split(" ");
        if (split.length != 2) {
            throw new IOException("Invalid map: invalid row and col definition");
        }
        int width = Integer.parseInt(split[0]);
        int height = Integer.parseInt(split[1]);

        MazeConfigure mazeConfigure = new MazeConfigure();
        mazeConfigure.startReading(width, height);
        // begins reading the maze on second line = [1]
        int heightCounter = 0;
        while ((line = br.readLine()) != null) {
            // check if line includes only valid characters
            for (int i = 0; i < line.length(); i++) {
                char c = line.charAt(i);
                if (c != 'X' && c != '.' && c != 'S' && c != 'G' && c != 'K' && c != 'T') {
                    throw new IOException("Invalid map: invalid character");
                }
            }
            if (line.length() != width) {
                throw new IOException("Invalid map: a row has a different length than the others");
            }
            if (heightCounter > height) {
                throw new IOException("Invalid map: too many rows");
            }

            mazeConfigure.processLine(line);
            heightCounter++;
        }
        if (heightCounter != height) {
            throw new IOException("Invalid map: too few rows");
        }
        mazeConfigure.stopReading();
        CommonMaze maze = mazeConfigure.createMaze();
        return maze;
    }
}
