package de.gt.core.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

/**
 * Loads configs
 * @author Kevin
 */
public class ConfigLoader {

    /**
     * Loads a config
     * @param fileName
     * @return loaded config data
     * @throws IOException thrown when the config cannot be loaded
     */
    public static String load(String fileName) throws IOException {
        return readFile(fileName, StandardCharsets.UTF_8);
    }

    private static String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }
}
