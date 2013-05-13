package org.myeducation.taskexecuter.core;

import org.myeducation.databaseapi.model.ExecutorDataDto;
import org.myeducation.properties.PropertiesFactory;
import org.myeducation.taskexecuter.core.model.ClusterPack;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.net.Socket;
import java.util.Properties;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 13.02.13
 * Time: 1:04
 * To change this template use File | Settings | File Templates.
 */
public class SlaveLauncher {
    public static void main(String[] args) throws IOException, InterruptedException, ClassNotFoundException {

        Properties properties = PropertiesFactory.getProperties("executer");
        final Thread currentThread = Thread.currentThread();
        final Executor executor = new Executor();

        final Socket socket = new Socket(properties.getProperty("master.address"), Integer.parseInt(properties.getProperty("master.port")));
        ObjectInputStream dataReader = new ObjectInputStream(socket.getInputStream());

        Runtime.getRuntime().addShutdownHook(new Thread(new Runnable() {
            @Override
            public void run() {
                executor.shutDown();

                try {
                    currentThread.join();
                    socket.close();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }));

        while (true){
            System.out.println("Waiting for new data....");
            ClusterPack data = (ClusterPack)dataReader.readObject();
            executor.processTestDatas(data.getData());
            currentThread.sleep(1000);
        }

    }
}
