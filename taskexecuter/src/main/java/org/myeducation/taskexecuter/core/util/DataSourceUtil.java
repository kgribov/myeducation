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
        String content = data.getContent();
        String className = getClassName(content);
        File file = new File(PropertiesFactory.getProperties("filesystem").getProperty("filepath")+File.separator+data.getId()+File.separator+className+".java");
        try {
            if (!file.exists()){
                file.getParentFile().mkdirs();
            }
            BufferedWriter writer = new BufferedWriter(new FileWriter(file));
            writer.write(data.getContent());
            writer.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return file;
    }

    private static String getClassName(String content){
        StringBuilder result = new StringBuilder();
        String pattern = "public class ";
        int index = content.indexOf(pattern);
        for (int i=index+pattern.length(); i<content.length(); i++){
            char c = content.charAt(i);
            if (c!=' ' && c!='{'){
                result.append(c);
            } else{
                break;
            }
        }
        return result.toString();
    }
}
