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

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "user_id")
    private UserLogin user;

    @OneToMany(fetch = FetchType.EAGER, mappedBy = "taskSend")
    private List<AttachData> attachDatas;

    @Column(name = "tasksend_timesend")
    private long timeSend;

}
