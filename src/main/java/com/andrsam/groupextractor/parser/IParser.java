package com.andrsam.groupextractor.parser;

import java.util.List;
import java.util.Map;
import java.util.Set;

public interface IParser {
    Map<String, Set<String>> parse(String source, Map<String, List<String>> sourceMap);
}
