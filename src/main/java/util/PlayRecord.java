package util;

import window.SystemWindow;
import java.io.*;
import java.util.ArrayList;
import java.util.List;


public class PlayRecord {
    public static String[] readRecord(String path) throws IOException {
        File file = new File(path);
        if (!file.exists()) {
            SystemWindow.error("Error", "File not found");
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

        return paths;
    }

    public static ArrayList<Log> parseLogFromFile(String path) throws IOException {
        double frameDelay;
        ArrayList<List<Integer>> ghostPos = new ArrayList<>();
        ArrayList<List<Integer>> keyPos = new ArrayList<>();
        ArrayList<Log> logs = new ArrayList<>();

        File file = new File(path);
        if (!file.exists()) {
            SystemWindow.error("Error", "File not found");
        }
        BufferedReader br = new BufferedReader(new FileReader(file));

        String line, tmp;
        String[] split;
        while ((line = br.readLine()) != null) {
            if (line.equals("")) {
                return logs;
            }

            //delay
            String[] logData = line.split(";");
            frameDelay = Double.parseDouble(logData[0].replace("DELAY: ", ""));

            //pacman
            tmp = logData[1].replace("PACMAN: ", "");
            split = tmp.split(",");
            int[] pacmanPos = new int[2];
            pacmanPos[0] = Integer.parseInt(split[0]);
            pacmanPos[1] = Integer.parseInt(split[1]);

            // ghosts
            tmp = logData[2].replace("GHOSTS: [", "");
            tmp = tmp.substring(0, tmp.length()-1);
            ghostPos = strToD2Arr(tmp);

            // keys
            tmp = logData[3].replace("KEYS: [", "");
            tmp = tmp.substring(0, tmp.length()-1);
            keyPos = strToD2Arr(tmp);

            Log log = new Log(frameDelay, pacmanPos, ghostPos, keyPos);
            logs.add(log);
        }
        return logs;
    }

    public static ArrayList<List<Integer>> strToD2Arr(String str) {
        ArrayList<List<Integer>> result = new ArrayList<List<Integer>>();
        boolean closed = false, afterComma = false;
        boolean started = false;
        StringBuilder sb = new StringBuilder();
        String tmp;
        List<Integer> list = new ArrayList<>();
        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '[' && !closed) {
                started = true;
            }
            else if (Character.isDigit(str.charAt(i)) && !afterComma) {
                sb.append(str.charAt(i));
            }
            else if (str.charAt(i) == ',' && !afterComma && started) {
                afterComma = true;
                tmp = sb.toString();
                list.add(Integer.parseInt(tmp));
                sb.setLength(0);
            }
            else if (Character.isDigit(str.charAt(i)) && afterComma) {
                sb.append(str.charAt(i));
            }
            else if (str.charAt(i) == ']') {
                tmp = sb.toString();
                list.add(Integer.parseInt(tmp));
                sb.setLength(0);
                afterComma = false;
                started = false;
                closed = true;
            }
            else if (closed) {
                List<Integer> copy = new ArrayList<>(list);
                result.add(copy);
                list.clear();
                closed = false;
            }
        }
        result.add(list);
        return result;
    }

}

