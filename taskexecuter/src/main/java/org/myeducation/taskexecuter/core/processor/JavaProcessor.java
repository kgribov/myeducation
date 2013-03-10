package org.myeducation.taskexecuter.core.processor;

import com.google.common.io.Closeables;
import org.myeducation.databaseapi.entities.AttachData;
import org.myeducation.databaseapi.entities.TestData;
import org.myeducation.properties.PropertiesFactory;

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
public class JavaProcessor extends AbstractProcessor {

    public JavaProcessor(){
        //need to change to read from props
        super(50);
    }

    @Override
    public void executeData(AttachData data, TestData testData) {
        Properties properties = PropertiesFactory.getProperties("filesystem");
        String name  = data.getContent();
        try {
            String absoluteFilePath= properties.getProperty("java.filepath") + File.separator + name;
            List<String> commands = new ArrayList<String>();

            if (!isExistJarFile(absoluteFilePath)){
                commands.add("javac");
                commands.add(absoluteFilePath);

                executeProcess(commands);

                createJar(absoluteFilePath);

                commands.clear();
            }

            commands.add("java");
            commands.add("-jar");
            commands.add(absoluteFilePath + ".jar");

            Process process = executeProcess(commands);

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(
                    process.getOutputStream()));
            BufferedReader br = new BufferedReader(new InputStreamReader(
                    process.getInputStream()));
            bw.write(testData.getInputData());

            StringBuilder result = new StringBuilder();
            String line;
            while ((line = br.readLine()) != null) {
                System.out.println(line);
                result.append(line);
                result.append("\n");
            }

            if (result.toString().equals(testData.getOutputData())){
                System.out.println("Match!!");
            }else{
                System.out.println("Unmatch!!");
            }

            Closeables.close(br, false);
            Closeables.close(bw, false);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    private void createJar(String name) throws IOException {
        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");
        manifest.getMainAttributes().put(Attributes.Name.MAIN_CLASS, name);
        JarOutputStream target = new JarOutputStream(new FileOutputStream(name + ".jar"), manifest);
        addFileToJar(new File(name + ".class"), target);
        target.close();
    }

    private void addFileToJar(File source, JarOutputStream target) throws IOException {
        BufferedInputStream in = null;
        try {
            JarEntry entry = new JarEntry(source.getName());
            entry.setTime(source.lastModified());
            target.putNextEntry(entry);
            in = new BufferedInputStream(new FileInputStream(source));

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

    private Process executeProcess(List<String> commands) {
        ProcessBuilder builder = new ProcessBuilder(commands);
        builder.redirectErrorStream(true);
        try{
            Process process = builder.start();

            BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(process.getOutputStream()));
            bw.write("\n"); // For imitate to pressed button "Enter"

            bw.flush();
            process.waitFor();
            bw.close();
            return process;
        }catch (Exception ex){
            ex.printStackTrace();
        }
        return null;
    }

    private boolean isExistJarFile(String name){
        File jarFile = new File(name+".jar");
        if (jarFile.exists()){
            return true;
        }else{
            return false;
        }
    }

    @Override
    public String getProcessorName() {
        return "JAVA_PROCESSOR";
    }
}
