package com.andrsam.groupextractor.reader.impl;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

public class FileProcessorTest {

    private static final String[] STR_DUPLICATES = new String[]{
            "\"83178111069\";\"100000094623698\";\"200000064948582\"",
            "\"83822459695\";\"\";\"200000087201031\"",
            "\"83178111069\";\"100000815068665\";\"200000605588834\"",
            "\"\";\"100000661125097\";\"200000171650768\"",
            "\"83273885690\";\"100000372967409\";\"200000269854191\"",
            "\"83408667148\";\"100000815068665\";\"200000827683220\"",
            "\"83178111069\";\"100000094623697\";\"200000064948582" };

    private static final String[] STR_NO_DUPLICATES = new String[]{"\"83171375084\";\"100000003421956\";",
            "\"83620410120\";\"100000227778156\";\"\"",
            "\"83918105990\";\"100000043186771\";\"200000590456885\"",
            "\"83120897035\";\"100000785378490\";\"200000999920329\"",
            "\"83710725507\";\"100000901641382\";\"200000461415093\"",
            "\"83552471951\";\"100000395408945\";\"200000858070199\"",
            "\"83384681155\";\"100000854416515\";\"200000670938412\"" };

    private static final String[] SIMPLE_CASE = new String[]{"1;2;3", "3;4;5", "5;6;7"};



    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void readDuplicates() throws IOException {
        Map<String, List<String>> map = fileProcessor.read(new StringReader(arrayToString(STR_DUPLICATES)));
        assertFalse(map.isEmpty());
        assertEquals(3, map.get("\"83178111069\"").size());
    }

    @Test
    public void readNoDuplicates() throws IOException {
        Map<String, List<String>> map = fileProcessor.read(new StringReader(arrayToString(STR_DUPLICATES)));
        assertTrue(map.isEmpty());
    }

    @Test
    public void readSimpleTest() throws IOException {
        Map<String, List<String>> map = fileProcessor.read(new StringReader(arrayToString(SIMPLE_CASE)));
        System.out.println(map.toString());
    }

    private String arrayToString(String[] arr) {
        return Arrays.stream(arr).collect(Collectors.joining(System.lineSeparator()));
    }
}