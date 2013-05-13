package org.myeducation.taskexecuter.core.util;

import org.myeducation.properties.PropertiesFactory;

import java.io.File;

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
            return new File(PropertiesFactory.getProperties("filesystem")+File.separator+sourceString.substring(5, sourceString.length()));
        }
        return sourceString;
    }
}
