package org.myeducation.databaseapi.entities;

import javax.persistence.*;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 14:14
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "attachdata")
public class AttachData {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "attachdata_id")
    private long id;

    @Column(name = "attachdata_content", columnDefinition = "LONGTEXT")
    private String content;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "attachdata_type")
    private AttachDataType type;

    @ManyToOne(fetch = FetchType.EAGER , cascade = CascadeType.ALL)
    @JoinColumn(name = "tasksend_id")
    private TaskSend taskSend;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public AttachDataType getType() {
        return type;
    }

    public void setType(AttachDataType type) {
        this.type = type;
    }

    public TaskSend getTaskSend() {
        return taskSend;
    }

    public void setTaskSend(TaskSend taskSend) {
        this.taskSend = taskSend;
    }
}
