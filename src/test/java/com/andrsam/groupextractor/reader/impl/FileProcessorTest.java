package com.andrsam.groupextractor.reader.impl;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import static org.junit.Assert.assertEquals;

public class FileProcessorTest {

    private static final String[] SINGLE_GROUP = new String[]{"1;2;3", "3;4;5", "5;6;7"};
    private static final String[] TWO_GROUPS = new String[]{"1;2;3", "3;4;5", "5;6;7", "9;10;11", "10;11;12", "12;13;14", "99;99;99", "5;3;\"\""};


    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void readSingleGroup() throws IOException {
        Map<String, Set<String>> map = fileProcessor.read(new StringReader(arrayToString(SINGLE_GROUP)));
        assertEquals(1, map.size());
    }

    @Test
    public void readTwoGroups() throws IOException {
        Map<String, Set<String>> map = fileProcessor.read(new StringReader(arrayToString(TWO_GROUPS)));
        assertEquals(2, map.size());
    }


    private String arrayToString(String[] arr) {
        return Arrays.stream(arr).collect(Collectors.joining(System.lineSeparator()));
    }
}