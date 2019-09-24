package com.andrsam.groupextractor.parser.impl;

import com.andrsam.groupextractor.parser.IParser;
import org.junit.Test;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

public class ParserTest {
    public static final String TEST_STRING = "\"83822459695\";\"\";\"200000087201031\"";
    private IParser parser = new Parser();
    private final Map<String, List<String>> map = new HashMap<>();

    @Test
    public void parse() {
        Map<String, List<String>> result = parser.parse(TEST_STRING, map);
        assertNull(result.get(""));
        assertEquals(TEST_STRING, result.get("83822459695").get(0));
        assertEquals(TEST_STRING, result.get("200000087201031").get(0));
    }
}