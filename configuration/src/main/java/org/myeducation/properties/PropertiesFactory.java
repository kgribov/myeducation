package org.myeducation.properties;

import java.io.File;
import java.io.FileReader;
import java.util.HashMap;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 10.03.13
 * Time: 13:24
 * To change this template use File | Settings | File Templates.
 */
public class PropertiesFactory {

    private static HashMap<String, Properties> propertiesMap = new HashMap<String, Properties>();

    public static Properties getProperties(String propName){
        if (!propertiesMap.containsKey(propName)){
            Properties result = null;
            try{
                Properties properties = new Properties();
                String fileName = propName+".properties";
                String folder = "properties";
                String module = "configuration";
                String fullPath = ".."+File.separator+module + File.separator + folder + File.separator + fileName;
                properties.load(new FileReader(fullPath));
                result = properties;
            }catch (Exception ex){
                ex.printStackTrace();
            }
            propertiesMap.put(propName, result);
            return result;
        }else{
            return propertiesMap.get(propName);
        }
    }
}
