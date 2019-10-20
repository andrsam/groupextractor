package com.andrsam.groupextractor.reader.impl;

import com.andrsam.groupextractor.parser.IParser;
import com.andrsam.groupextractor.parser.impl.Parser;
import com.andrsam.groupextractor.reader.IFileProcessor;

import java.io.*;
import java.util.*;

public class FileProcessor implements IFileProcessor {

    @Override
    public Map<String, Set<String>> read(Reader reader) throws IOException {
        Map<String, Set<String>> map = new HashMap<>();
        IParser parser = new Parser();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                parser.parse(line, map);
            }
        }

        //TODO:remove it
        map.entrySet().removeIf(entry -> entry.getValue().size() < 2);

        Map<String, Set<String>> resultingMap = new HashMap<>();

        ArrayList<String> keys = new ArrayList<>(map.keySet());

        StringJoiner stringJoiner = new StringJoiner(",");

        for (int i = 0; i < keys.size(); i++) {
            for (int j = i + 1; j < keys.size(); j++) {
                String key = keys.get(j);
                boolean isKeyExistsInString = map.get(key).stream().filter(v -> v.indexOf(key) > 0).count() > 0;
                if (isKeyExistsInString) {
                    String newKey = stringJoiner.add(keys.get(i)).add(keys.get(j)).toString();
                    Set<String> newSet = new HashSet<>();
                    map.put(newKey, newSet);
                    map.remove(key);
                    keys.remove(key);
                }


            }
        }


        System.out.println("resultingMap = " + resultingMap.toString());

        return map;
    }

    @Override
    public void writeMap(Map<String, List<String>> map, String path) throws IOException {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            for (Map.Entry<String, List<String>> entry : map.entrySet()) {
                writer.write("key = " + entry.getKey());
                writer.newLine();
                writer.write("----------");
                writer.newLine();
                for (String line : entry.getValue()) {
                    writer.write(line);
                    writer.newLine();
                }
                writer.newLine();
            }
        }

    }
}
