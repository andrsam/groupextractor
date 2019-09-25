package com.andrsam.groupextractor.reader.impl;

import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.net.URL;
import java.util.List;
import java.util.Map;
import java.util.Objects;

import static org.junit.Assert.*;

public class FileProcessorTest {
    private final String SRC_FILENAME_DUPLICATES = "test_duplicates.txt";
    private final String SRC_FILENAME_DISTINCT = "test_no_duplicates.txt";

    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void readDuplicates() throws IOException {
        URL srcResource = getClass().getClassLoader().getResource(SRC_FILENAME_DUPLICATES);
        File file = new File(Objects.requireNonNull(srcResource).getFile());
        Map<String, List<String>> map = fileProcessor.read(file);
        assertFalse(map.isEmpty());
        assertEquals(3, map.get("\"83178111069\"").size());
    }

    @Test
    public void readNoDuplicates() throws IOException {
        URL srcResource = getClass().getClassLoader().getResource(SRC_FILENAME_DISTINCT);
        File file = new File(Objects.requireNonNull(srcResource).getFile());
        Map<String, List<String>> map = fileProcessor.read(file);
        assertTrue(map.isEmpty());
    }
}