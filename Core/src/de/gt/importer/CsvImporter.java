/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.importer;

import de.gt.api.importer.Importer;
import java.io.BufferedReader;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author Robin
 */
public class CsvImporter implements Importer {

    @Override
    public Map<String, List<Double>> importData(File input) {
        Map<String, List<Double>> data = new HashMap<String, List<Double>>();

        try (BufferedReader reader = new BufferedReader(new FileReader(input));) {

            String line;
            line = reader.readLine();
            String[] keys = line.split(";");
            String[] dataSet;
            for (String key : keys) {
                data.put(key, new ArrayList<Double>());
            }

            while ((line = reader.readLine()) != null) {
                dataSet = line.split(";");

                for (int i = 0; i < dataSet.length; i++) {
                    data.get(String.valueOf(keys[0])).add(Double.valueOf(dataSet[i]));

                }

            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
        } catch (IOException ex) {
            Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
        }
        return data;
    }

}
