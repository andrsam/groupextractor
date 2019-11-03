package com.andrsam.groupextractor.reader;

import java.io.IOException;
import java.io.Reader;
import java.util.Map;
import java.util.Set;

public interface IFileProcessor {
    Map<String, Set<String>> read(Reader reader) throws IOException;

    void writeMap(Map<String, Set<String>> map, String path) throws IOException;
}
