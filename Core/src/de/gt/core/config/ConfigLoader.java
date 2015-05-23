package de.gt.core.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openide.util.lookup.ServiceProvider;

/**
 * Loads configs
 *
 * @author Kevin
 */
@ServiceProvider(service = de.gt.api.config.ConfigLoader.class)
public class ConfigLoader implements de.gt.api.config.ConfigLoader {

    /**
     * Loads a config
     *
     * @param fileName
     * @return loaded config data
     * @throws IOException thrown when the config cannot be loaded
     */
    @Override
    public String load(String fileName) throws IOException {
        return readFile(fileName, StandardCharsets.UTF_8);
    }

    private String readFile(String path, Charset encoding) throws IOException {
        byte[] encoded = Files.readAllBytes(Paths.get(path));
        return new String(encoded, encoding);
    }

}
