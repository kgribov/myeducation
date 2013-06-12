package org.myeducation.taskexecuter.core.util;

import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.properties.PropertiesFactory;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.UUID;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 12.05.13
 * Time: 17:06
 * To change this template use File | Settings | File Templates.
 */
public class DataSourceUtil {
    public static Object getSource(String sourceString){
        if (sourceString.matches("file:.+")){
            return new File(PropertiesFactory.getProperties("filesystem").getProperty("filepath")+File.separator+sourceString.substring(5, sourceString.length()));
        }
        return sourceString;
    }

    public static File createFile(AttachData data){
        File file = new File(PropertiesFactory.getProperties("filesystem").getProperty("filepath")+File.separator+data.getId());
        try {
            if (!file.exists()){
                file.createNewFile();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(data.getContent());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }
}
