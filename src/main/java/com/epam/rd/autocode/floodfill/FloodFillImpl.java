package com.epam.rd.autocode.floodfill;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;


public class FloodFillImpl implements FloodFill {

    static private List<String> toList(String[][] oldMap) {
        List<String> list = new ArrayList<>();
        for (String[] strings : oldMap) {
            list.addAll(Arrays.asList(strings).subList(0, oldMap[0].length));
        }
        return list;
    }

    public void flood(String maps, FloodLogger logger) {
        String[] lines = maps.split("\n");
        String[][] map = new String[lines.length][lines[0].length()];
        for (int i = 0; i < lines.length; i++) {
            char[] chars = lines[i].toCharArray();
            for (int j = 0; j < chars.length; j++) {
                map[i][j] = String.valueOf(chars[j]);
            }
        }
        print(map, logger);
        String[][] mapForCheck = clone(map);
        for (int i = 0; i < map.length; i++) {
            for (int j = 0; j < map[0].length; j++) {
                if (map[i][j].equals(String.valueOf('░'))) {
                    if (i != 0) {
                        mapForCheck[i - 1][j] = String.valueOf('░');
                    }
                    if (j != 0) {
                        mapForCheck[i][j - 1] = String.valueOf('░');
                    }
                    if (i != map.length - 1) {
                        mapForCheck[i + 1][j] = String.valueOf('░');
                    }
                    if (j != map[0].length - 1) {
                        mapForCheck[i][j + 1] = String.valueOf('░');
                    }
                }
            }
        }

        if (toList(map).equals(toList(mapForCheck))) {
            return;
        }

        flood(String.join("\n", mapToList(mapForCheck)), logger);
    }

    void print(String[][] map, FloodLogger logger) {
        logger.log(String.join("\n", mapToList(map)));
    }

    private String[][] clone(String[][] oldMap) {
        String[][] newMap = new String[oldMap.length][oldMap[0].length];
        for (int i = 0; i < newMap.length; i++) {
            System.arraycopy(oldMap[i], 0, newMap[i], 0, newMap[0].length);
        }
        return newMap;
    }

    List<String> mapToList(String[][] someMap) {
        List<String> lines = new ArrayList<>();
        for (String[] strings : someMap) {
            StringBuilder line = new StringBuilder();
            for (String string : strings) {
                line.append(string);
            }
            lines.add(line.toString());
        }
        return lines;
    }
}
