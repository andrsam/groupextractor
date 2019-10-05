package com.andrsam.groupextractor.reader.impl;

import org.junit.Test;

import java.io.IOException;
import java.io.StringReader;
import java.util.List;
import java.util.Map;

import static org.junit.Assert.*;

public class FileProcessorTest {
    public static final String STR_DUPLICATES = "\"83178111069\";\"100000094623698\";\"200000064948582\"\n" +
            "\"83822459695\";\"\";\"200000087201031\"\n" +
            "\"83178111069\";\"100000815068665\";\"200000605588834\"\n" +
            "\"\";\"100000661125097\";\"200000171650768\"\n" +
            "\"83273885690\";\"100000372967409\";\"200000269854191\"\n" +
            "\"83408667148\";\"100000815068665\";\"200000827683220\"\n" +
            "\"83178111069\";\"100000094623697\";\"200000064948582";

    public static final String STR_NO_DUPLICATES = "\"83171375084\";\"100000003421956\";\"\"\n" +
            "\"83620410120\";\"100000227778156\";\"\"\n" +
            "\"83918105990\";\"100000043186771\";\"200000590456885\"\n" +
            "\"83120897035\";\"100000785378490\";\"200000999920329\"\n" +
            "\"83710725507\";\"100000901641382\";\"200000461415093\"\n" +
            "\"83552471951\";\"100000395408945\";\"200000858070199\"\n" +
            "\"83384681155\";\"100000854416515\";\"200000670938412\"";

    private FileProcessor fileProcessor = new FileProcessor();

    @Test
    public void readDuplicates() throws IOException {
        Map<String, List<String>> map = fileProcessor.read(new StringReader(STR_DUPLICATES));
        assertFalse(map.isEmpty());
        assertEquals(3, map.get("\"83178111069\"").size());
    }

    @Test
    public void readNoDuplicates() throws IOException {
        Map<String, List<String>> map = fileProcessor.read(new StringReader(STR_NO_DUPLICATES));
        assertTrue(map.isEmpty());
    }
}