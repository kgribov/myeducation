package org.myeducation.portal.server;

import com.google.gwt.user.server.rpc.RemoteServiceServlet;
import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.entities.*;
import org.myeducation.databaseapi.service.Service;
import org.myeducation.portal.client.TaskLoad;
import org.myeducation.portal.client.dto.TaskInfo;
import org.myeducation.portal.client.dto.TaskSendDto;
import org.myeducation.portal.client.dto.TaskSendResult;

import java.util.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 31.05.13
 * Time: 1:11
 * To change this template use File | Settings | File Templates.
 */
public class TaskLoadImpl extends RemoteServiceServlet implements TaskLoad {
    @Override
    public TaskInfo getTaskInfo(long id) {
        TaskInfo info = new TaskInfo();
        Task task = Service.getFactory().taskService().getTask(id);

        info.setId(task.getId());
        info.setDescription(task.getDescription());
        info.setName(task.getName());

        return info;
    }

    @Override
    public List<TaskSendResult> getResults(long taskId) {
        List<TaskSendResult> resultList = new ArrayList<TaskSendResult>();

        List<TestDataResult> results = Service.getFactory().taskService().getResults(taskId);

        Map<String, List<TestDataResult>> map = new HashMap<String, List<TestDataResult>>();
        for (TestDataResult testDataResult : results){
            String userLogin = testDataResult.getAttachData().getTaskSend().getUser().getLogin();
            if (map.containsKey(userLogin)){
                map.get(userLogin).add(testDataResult);
            }else{
                ArrayList<TestDataResult> list = new ArrayList<TestDataResult>();
                list.add(testDataResult);
                map.put(userLogin, list);
            }
        }

        for (String userLogin : map.keySet()){

            List<TestDataResult> userResultsList = map.get(userLogin);
            HashMap<Long, List<TestDataResult>> groupBySend = new HashMap<Long, List<TestDataResult>>();
            for (TestDataResult userResult : userResultsList){
                Long sendId = userResult.getAttachData().getTaskSend().getId();
                if (!groupBySend.containsKey(sendId)){
                    ArrayList<TestDataResult> list = new ArrayList<TestDataResult>();
                    list.add(userResult);
                    groupBySend.put(sendId, list);
                }else{
                    groupBySend.get(sendId).add(userResult);
                }

            }

            for (Long sendId : groupBySend.keySet()){
                List<TestDataResult> current = groupBySend.get(sendId);
                long sum = 0;
                long minPoints = 0;

                TaskSendResult result = new TaskSendResult();
                result.setUser(userLogin);
                result.setStatus("Execution complete");

                TaskSend taskSend = null;
                for (TestDataResult testDataResult : current){
                    if (taskSend == null){
                        taskSend = testDataResult.getAttachData().getTaskSend();
                    }
                    if (testDataResult.getSuccess()){
                        sum += testDataResult.getTestData().getPoints();
                    }
                }

                for (AttachData attachData : taskSend.getAttachDatas()){
                    for (TestDatas testDatas : attachData.getType().getTestDatas()){
                        minPoints += testDatas.getMinPoints();
                    }
                }

                result.setPoints(sum);
                result.setTime(taskSend.getTimeSend());
                result.setMinPoints(minPoints);

                resultList.add(result);
            }
        }
        return resultList;
    }

    @Override
    public void sendTaskSend(TaskSendDto taskSendDto) {
        TaskSend send = new TaskSend();
        send.setTimeSend(System.currentTimeMillis());

        UserLogin user = Dao.getFactory().createUserDao().getLogin(taskSendDto.getUserId());
        send.setUser(user);

        List<AttachData> datas = new ArrayList<AttachData>();
        for (Map.Entry<Long, Object> entry : taskSendDto.getAttachDatas().entrySet()){
            AttachDataType attachDataType = Dao.getFactory().createTaskDao().getAttachDataType(entry.getKey());

            AttachData attachData = new AttachData();
            attachData.setType(attachDataType);
            attachData.setContent(entry.getValue().toString());
            attachData.setTaskSend(send);

            datas.add(attachData);
        }

        send.setAttachDatas(datas);

        Dao.getFactory().createTaskDao().addTaskSend(send);
    }
}