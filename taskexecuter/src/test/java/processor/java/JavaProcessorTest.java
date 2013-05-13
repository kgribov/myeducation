package processor.java;

import org.junit.Test;
import org.myeducation.databaseapi.dao.Dao;
import org.myeducation.databaseapi.dao.TaskDAO;
import org.myeducation.databaseapi.entities.*;
import org.myeducation.taskexecuter.core.Executor;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 04.03.13
 * Time: 23:05
 * To change this template use File | Settings | File Templates.
 */
public class JavaProcessorTest {

    private Executor executor = new Executor();

    private TaskDAO dao = Dao.getFactory().createTaskDao();

    @Test
    public void testHelloWorld(){
        TaskSend send =  dao.getTaskSend(1);
        AttachData attachData = send.getAttachDatas().iterator().next();
        executor.processData(attachData, attachData.getType().getTestDatas().iterator().next());
        executor.shutDown();
    }
}
