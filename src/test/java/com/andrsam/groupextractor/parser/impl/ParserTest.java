package com.andrsam.groupextractor.parser.impl;

import com.andrsam.groupextractor.parser.IParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertNull;

public class ParserTest {
    private IParser parser = new Parser();
    private final Map<String, List<String>> map = new HashMap<>();

    @Test
    public void parse() {
        Map<String, List<String>> result = parser.parse("\"83822459695\";\"\";\"200000087201031\"", map);
        assertNull(result.get(""));
    }
}