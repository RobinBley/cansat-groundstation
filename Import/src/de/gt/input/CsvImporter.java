/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package de.gt.input;

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
    public Map<String, List> importData(File input) {
        HashMap data = new HashMap();

        try {
            BufferedReader reader = new BufferedReader(new FileReader(input));
            String line;
            ArrayList list = new ArrayList<String>();
            try {
                line = reader.readLine();
                while ((line = reader.readLine()) != null) {
                    String[] dataSet = line.split(";");
                    if (dataSet.length >= 2) {

                        for (int i = 1; i < dataSet.length; i++) {
                            list.add(dataSet[i]);
                        }
                        data.put(dataSet[0], list);

                    }

                }
            } catch (IOException ex) {
                Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
            }

        } catch (FileNotFoundException ex) {
            Logger.getLogger(CsvImporter.class.getName()).log(Level.SEVERE, null, ex);
        }

        return data;
    }

}
