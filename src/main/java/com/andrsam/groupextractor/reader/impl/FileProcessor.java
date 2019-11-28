package com.andrsam.groupextractor.reader.impl;

import com.andrsam.groupextractor.parser.IParser;
import com.andrsam.groupextractor.parser.impl.Parser;
import com.andrsam.groupextractor.reader.IFileProcessor;

import java.io.*;
import java.util.*;
import java.util.stream.Collectors;

public class FileProcessor implements IFileProcessor {

    @Override
    public Map<String, Set<String>> read(Reader reader) throws IOException {
        ;
        IParser parser = new Parser();
        Map<String, Set<String>> map = parseFile(reader, parser);
        map.entrySet().removeIf(entry -> entry.getValue().size() == 1);
        processIntersections(map);
        return map;
    }

    private Map<String, Set<String>> parseFile(Reader reader, IParser parser) throws IOException {
        Map<String, Set<String>> map = new TreeMap<>();
        try (BufferedReader br = new BufferedReader(reader)) {
            String line;
            while ((line = br.readLine()) != null) {
                parser.parse(line, map);
            }
        }
        return map;
    }

    private void processIntersections(Map<String, Set<String>> map) {
        Deque<String> stack = new ArrayDeque<>(map.keySet());

        List<String> keysToMerge = new ArrayList<>();
        while (!stack.isEmpty()) {
            String key = stack.pop();
            keysToMerge.add(key);
            stack.stream()
                    .filter(dstKey -> isKeyContainsInValue(map, key, dstKey))
                    .collect(Collectors.toCollection(() -> keysToMerge));
            keysToMerge.forEach(stack::remove);
            mergeKeys(map, keysToMerge);
        }
    }

    private boolean isKeyContainsInValue(Map<String, Set<String>> map, String srcKey, String dstKey) {
        Set<String> intersection = new HashSet<>(map.get(dstKey));
        boolean mapContainsKey = map.containsKey(srcKey);
        if (mapContainsKey) {
            intersection.retainAll(map.get(srcKey));
        }

        return mapContainsKey && !intersection.isEmpty();
    }

    private void mergeKeys(Map<String, Set<String>> map, List<String> keysToRemove) {

        String newKey = String.join(",", keysToRemove);
        Set<String> newValue = keysToRemove
                .stream()
                .map(map::get)
                .flatMap(Collection::stream)
                .collect(Collectors.toCollection(HashSet::new));

        map.entrySet().removeIf(e -> keysToRemove.contains(e.getKey()));
        map.put(newKey, newValue);
        keysToRemove.clear();
    }

    @Override
    public void writeMap(Map<String, Set<String>> map, String path) throws IOException {
        File file = new File(path);
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(file))) {
            writer.write("Groups total: " + map.size());
            writer.newLine();
            writer.write("===========");
            writer.newLine();

            for (Map.Entry<String, Set<String>> entry : map.entrySet()) {
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
