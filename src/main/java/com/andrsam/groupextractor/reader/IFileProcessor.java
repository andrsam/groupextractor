package com.andrsam.groupextractor.reader;

import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.Map;

public interface IFileProcessor {
    Map<String, List<String>> read(File file) throws IOException;

    void writeMap(Map<String, List<String>> map, String path) throws IOException;
}
