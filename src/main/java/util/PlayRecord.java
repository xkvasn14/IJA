package util;

import window.SystemWindow;

import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PlayRecord {
    public static void readRecord(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            SystemWindow.error("Error", "File not found");
            throw new IOException("File not found"); //todo error window
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        boolean mapRead = false;
        StringBuilder sb = new StringBuilder();
        String line = br.readLine().substring(1); // ignore first [
        while (line != null) {

            if (!mapRead && line.endsWith("]")) {
                sb.append(line, 0, line.length()-1);
                sb.append("BREAK");
                line = br.readLine();
                mapRead = true;
            }

            sb.append(line);
            sb.append('\n');
            if (!mapRead) {
                line = br.readLine().substring(1);
            }
            else {
                line = br.readLine();
            }
        }

        String str = sb.toString();
        String[] arrOfStr = str.split("BREAK");
        String[] paths = new String[2];
        for (int i = 0; i < arrOfStr.length; i++) {
            String fileName = file.getName();
            File file1 = new File("data\\records\\" + "play" + i + "_" + fileName);
            PrintWriter writer = new PrintWriter(file1);
            writer.println(arrOfStr[i]);
            writer.close();
            paths[i] = file1.getAbsolutePath();
        }
        System.out.println("Done");
        br.close();

        //todo return paths to new files
        parseLogFromFile(paths[1]);
    }

    public static void parseLogFromFile(String path) throws IOException {
        //todo load path from readrecord
        //File file = new File("file:data/records/play1_record_map1.txt");
        int[] pacmanPos = new int[2];
        ArrayList<List<Integer>> ghostPos = new ArrayList<List<Integer>>();
        ArrayList<List<Integer>> keyPos = new ArrayList<List<Integer>>();

        File file = new File(path);
        if (!file.exists()) {
            SystemWindow.error("Error", "File not found");
            throw new IOException("File not found"); //todo error window
        }
        BufferedReader br = new BufferedReader(new FileReader(file));
        String line, tmp, tmp2;
        String[] split, split2;
        while ((line = br.readLine()) != null) {
            //delay
            String[] logData = line.split(";");
            double frameDelay = Double.parseDouble(logData[0].replace("DELAY: ", ""));

            //pacman
            tmp = logData[1].replace("PACMAN: ", "");
            split = tmp.split(",");
            pacmanPos[0] = Integer.parseInt(split[0]);
            pacmanPos[1] = Integer.parseInt(split[1]);

            //todo ghosts
            tmp = logData[2].replace("GHOSTS: [", "");
            tmp = tmp.substring(0, tmp.length()-1);
            split = tmp.split(", ");

            for (String s : split) {
                tmp2 = s.substring(1, split[1].length() - 1);
                split2 = tmp2.split(", ");
                List<Integer> list = new ArrayList<Integer>();
                for (int j = 0; j < split2.length; j++) {
                    list.add(j);
                }
                ghostPos.add(list);
            }
            //ghostPos.set(0, Integer.parseInt(split[0]));
            System.out.println(Arrays.toString(split));
        }
    }

}
