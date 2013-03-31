package org.myeducation.databaseapi.entities;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 03.03.13
 * Time: 13:33
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "tasksend")
public class TaskSend {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "tasksend_id")
    private long id;

    @ManyToOne(fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private UserLogin user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taskSend", cascade = CascadeType.ALL)
    private List<AttachData> attachDatas;

    @Column(name = "tasksend_timesend")
    private long timeSend;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public UserLogin getUser() {
        return user;
    }

    public void setUser(UserLogin user) {
        this.user = user;
    }

    public List<AttachData> getAttachDatas() {
        return attachDatas;
    }

    public void setAttachDatas(List<AttachData> attachDatas) {
        this.attachDatas = attachDatas;
    }

    public long getTimeSend() {
        return timeSend;
    }

    public void setTimeSend(long timeSend) {
        this.timeSend = timeSend;
    }
}
