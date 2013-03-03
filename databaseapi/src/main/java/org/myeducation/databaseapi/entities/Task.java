package org.myeducation.databaseapi.entities;

import javax.persistence.*;
import java.util.Set;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 26.02.13
 * Time: 22:21
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "task")
public class Task {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column
    private long id;

    @Column(name = "task_name")
    private String name;

    @Column(name = "task_description", columnDefinition = "LONGTEXT")
    private String description;

    @OneToMany(mappedBy = "task", fetch = FetchType.EAGER, cascade = CascadeType.ALL)
    private Set<AttachDataType> attachDataTypes;

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Set<AttachDataType> getAttachDataTypes() {
        return attachDataTypes;
    }

    public void setAttachDataTypes(Set<AttachDataType> attachDataTypes) {
        this.attachDataTypes = attachDataTypes;
    }
}

