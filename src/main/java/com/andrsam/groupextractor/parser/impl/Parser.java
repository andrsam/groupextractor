package com.andrsam.groupextractor.parser.impl;

import com.andrsam.groupextractor.parser.IParser;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class Parser implements IParser {
    @Override
    public Map<String, List<String>> parse(String source, Map<String, List<String>> sourceMap) {
        String[] values = source.split(";");
        for (String value : values) {
            if (!value.equals("\"\"")) {
                List<String> list = sourceMap.containsKey(value) ? sourceMap.get(value) : new ArrayList<>();
                list.add(source);
                sourceMap.put(value, list);
            }
        }
        return sourceMap;
    }
}
