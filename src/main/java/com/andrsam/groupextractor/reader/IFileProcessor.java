package com.andrsam.groupextractor.reader;

import java.io.IOException;
import java.io.Reader;
import java.util.List;
import java.util.Map;

public interface IFileProcessor {
    Map<String, List<String>> read(Reader reader) throws IOException;

    void writeMap(Map<String, List<String>> map, String path) throws IOException;
}
