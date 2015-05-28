package de.gt.core.config;

import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;
import org.openide.util.lookup.ServiceProvider;

/**
 * Lädt Configs
 *
 * @author Kevin
 */
@ServiceProvider(service = de.gt.api.config.ConfigLoader.class)
public class ConfigLoader implements de.gt.api.config.ConfigLoader {

    /**
     * Lädt eine Config
     *
     * @param fileName
     * @return geladene Configdaten
     * @throws IOException geworfen wenn Config nicht geladen werden kann
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
