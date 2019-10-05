package com.andrsam.groupextractor.reader.impl;

import com.andrsam.groupextractor.parser.IParser;
import com.andrsam.groupextractor.parser.impl.Parser;
import com.andrsam.groupextractor.reader.IFileProcessor;

import java.io.*;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class FileProcessor implements IFileProcessor {
    @Override
    public Map<String, List<String>> read(Reader reader) throws IOException {
        Map<String, List<String>> map = new HashMap<>();
        IParser parser = new Parser();

        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                parser.parse(line, map);
            }
        }
        map.entrySet().removeIf(entry -> entry.getValue().size() < 2);
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
