package org.myeducation.taskexecuter.core.processor.java;

import com.google.common.io.Closeables;
import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.databaseapi.entities.TestDatas;
import org.myeducation.properties.PropertiesFactory;
import org.myeducation.taskexecuter.core.processor.AbstractProcessor;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;


/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 14:43
 * To change this template use File | Settings | File Templates.
 */
public class JavaProcessor extends AbstractProcessor<Boolean> {

    public JavaProcessor(){
        super(Integer.parseInt(PropertiesFactory.getProperties("processors").getProperty("processor.java.cores")));
    }

    @Override
    protected Boolean validateResult(AttachData data, TestData testData) throws Exception{
        Properties properties = PropertiesFactory.getProperties("filesystem");

        String fullFilePathName = properties.getProperty("java.filepath") + File.separator + data.getContent();

        File javaFile = new File(fullFilePathName);
        File jarFile = new File(getJarName(javaFile));

        if (!jarFile.exists()){
            jarFile = createJar(javaFile);
        }

        if (jarFile.exists()){
            return validateJarFile(jarFile, testData);
        }else{
            throw new FileNotFoundException("Can't find jar file="+jarFile.getAbsolutePath());
        }
    }

    private boolean validateJarFile(File jarFile, TestData testData) throws Exception{
        List<String> commands = new ArrayList<String>();
        commands.add("java");
        commands.add("-jar");
        commands.add(jarFile.getAbsolutePath());

        ProcessBuilder builder = new ProcessBuilder(commands);
        final Process program = builder.start();

        BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                program.getOutputStream()));

        bw.write(testData.getInputData());

        Closeables.close(bw, false);

        program.waitFor();

        BufferedReader br = new BufferedReader(new InputStreamReader(
                program.getInputStream()));

        StringBuilder output = new StringBuilder();
        String line;
        while ((line = br.readLine()) != null) {
            output.append(line);
        }
        Closeables.close(br, false);

        System.out.println("Result = " + output);
        System.out.println("Test data = "+testData.getOutputData());
        if (output.toString().equals(testData.getOutputData())){
            System.out.println("Match!!");
            System.out.println();
            return true;
        }else{
            System.out.println("Unmatch!!");
            System.out.println();
            return false;
        }
    }

    private File createJar(File javaFile) throws Exception {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, getClassName(javaFile));
        String jarFileName = getJarName(javaFile);
        JarOutputStream target = new JarOutputStream(new FileOutputStream(jarFileName), manifest);
        addFileToJar(createClassFile(javaFile), target);
        target.close();
        return new File(jarFileName);
    }

    private String getClassName(File javaFile){
        String fileName = javaFile.getName();
        return fileName.substring(0, fileName.length()-5);
    }

    private String getClassFileName(File javaFile){
        String absolutePath = javaFile.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.length()-5)+".class";
    }

    private String getJarName(File javaFile){
        String absolutePath = javaFile.getAbsolutePath();
        return absolutePath.substring(0, absolutePath.length()-5)+".jar";
    }

    private File createClassFile(File javaFile) throws Exception{
        List<String> commands = new ArrayList<String>();
        commands.add("javac");
        commands.add(javaFile.getAbsolutePath());

        ProcessBuilder builder = new ProcessBuilder(commands);
        //java compile errors
        Process process = builder.start();
        process.waitFor();
        return new File(getClassFileName(javaFile));
    }

    private void addFileToJar(File source, JarOutputStream target) throws IOException {
        BufferedInputStream in = null;
        try {
            JarEntry entry = new JarEntry(source.getName());
            entry.setTime(source.lastModified());
            target.putNextEntry(entry);
            in = new BufferedInputStream(new FileInputStream(source));
            BufferedReader r = new BufferedReader(new InputStreamReader(System.in));
            byte[] buffer = new byte[2048];  //TODO what about size of buffer?
            while (true) {
                int count = in.read(buffer);
                if (count == -1)
                    break;
                target.write(buffer, 0, count);
            }
            target.closeEntry();
        } finally {
            if (in != null)
                in.close();
        }
    }

    @Override
    protected Boolean processException(Exception ex, AttachData data, TestData testData) {
        ex.printStackTrace();
        return false;
    }

    @Override
    protected void storeResult(Boolean result, AttachData attachData, TestData testData) {

    }

    @Override
    protected void storeAggregatedResult(List<Boolean> result, AttachData attachData, TestDatas testData) {

    }

    @Override
    protected boolean needBreakPointResult(Boolean result){
        if (result){
            return false;
        }
        return true;
    }

    @Override
    protected boolean needBreakPointException(Exception ex){
        return false;
    }

    @Override
    public String getProcessorName() {
        return "java";
    }
}
