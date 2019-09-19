package com.andrsam.groupextractor.parser;

import java.util.List;
import java.util.Map;

public interface IParser {
    Map<String, List<String>> parse(String source, Map<String, List<String>> sourceMap);
}
