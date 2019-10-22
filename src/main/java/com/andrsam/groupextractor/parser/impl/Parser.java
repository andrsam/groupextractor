package com.andrsam.groupextractor.parser.impl;

import com.andrsam.groupextractor.parser.IParser;

import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Parser implements IParser {
    @Override
    public Map<String, Set<String>> parse(String source, Map<String, List<String>> sourceMap) {
        String[] values = source.split(";");
        for (String value : values) {
            if (!value.equals("\"\"")) {
                Set<String> set = sourceMap.containsKey(value) ? sourceMap.get(value) : new HashSet<>();
                set.add(source);
                sourceMap.put(value, set);
            }
        }
        return sourceMap;
    }
}
