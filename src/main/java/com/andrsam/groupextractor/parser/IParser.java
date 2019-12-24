package com.andrsam.groupextractor.parser;

import java.util.Map;
import java.util.Set;

public interface IParser {
    void parse(String source, Map<String, Set<String>> sourceMap);
}
