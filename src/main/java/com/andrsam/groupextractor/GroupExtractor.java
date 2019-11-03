package com.andrsam.groupextractor;

import com.andrsam.groupextractor.reader.impl.FileProcessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.time.Duration;
import java.time.Instant;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import java.util.Set;

public class GroupExtractor {
    private static final String SRC_FILENAME = "lng.csv";
    public static final String DST_FILENAME = "result.txt";
    private static final Logger LOG = LoggerFactory.getLogger(GroupExtractor.class);

    public static void extractGroups() {
        URL srcResource = GroupExtractor.class.getClassLoader().getResource(SRC_FILENAME);

        File file = new File(Objects.requireNonNull(srcResource).getFile());
        FileProcessor fileProcessor = new FileProcessor();
        LOG.info("start processing file...");
        Instant start = Instant.now();
        Map<String, Set<String>> map = new HashMap<>();
        try {
            map = fileProcessor.read(new FileReader(file));
            fileProcessor.writeMap(map, DST_FILENAME);
        } catch (IOException e) {
            e.printStackTrace();
        }

        Instant finish = Instant.now();
        LOG.info("file processing finished. duration: {} seconds", Duration.between(start, finish).getSeconds());
        LOG.info("groups total: {}", map.size());
    }
}
