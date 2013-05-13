package org.myeducation.taskexecuter.core;

import org.myeducation.databaseapi.model.ExecutorDataDto;
import org.myeducation.databaseapi.service.ExecutorSaveService;
import org.myeducation.databaseapi.service.Service;
import org.myeducation.properties.PropertiesFactory;
import org.myeducation.taskexecuter.core.model.ClusterPack;

import java.io.IOException;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.03.13
 * Time: 22:05
 * To change this template use File | Settings | File Templates.
 */
public class MasterLauncher {
    public static void main(String[] args) throws IOException, InterruptedException {

        final List<Socket> avaliableSlaves = new ArrayList<Socket>();
        final ExecutorSaveService service = Service.getFactory().executorSaveService();

        Properties properties = PropertiesFactory.getProperties("executer");
        ServerSocket serverSocket = new ServerSocket(Integer.parseInt(properties.getProperty("master.port")));

        int slavecount = Integer.parseInt(properties.getProperty("slave.mincount"));
        if (slavecount < 1) return;

        System.out.println("Waiting for "+slavecount+" slaves");
        for (int i=0; i<slavecount; i++){
            Socket socket = serverSocket.accept();
            System.out.println("Connect slave : "+socket.getInetAddress().getCanonicalHostName());
            avaliableSlaves.add(socket);
        }
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                List<ExecutorDataDto> dataList = service.getNewExecutorData();
                Iterator<ClusterPack> packs = getPackages(dataList).iterator();
                Iterator<Socket> slavesIterator = avaliableSlaves.iterator();
                while (packs.hasNext()){
                    ClusterPack current = packs.next();
                    Socket socket = null;
                    if (slavesIterator.hasNext()){
                        socket = slavesIterator.next();
                    }else{
                        slavesIterator = avaliableSlaves.iterator();
                        socket = slavesIterator.next();
                    }

                    try {
                        ObjectOutputStream stream = new ObjectOutputStream(socket.getOutputStream());
                        stream.writeObject(current);
                    } catch (IOException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, new Date(), 2000);
    }

    public static List<ClusterPack> getPackages(Iterable<ExecutorDataDto> data){
        List<ClusterPack> list = new ArrayList<ClusterPack>();
        for (ExecutorDataDto executorDataDto : data){
            ClusterPack clusterPack = new ClusterPack();
            clusterPack.setTime(System.currentTimeMillis());
            ArrayList dataList = new ArrayList(1);
            dataList.add(executorDataDto);
            clusterPack.setData(dataList);
        }
        return list;
    }
}
