package org.myeducation.databaseapi.entities;

import javax.persistence.*;
import java.util.Set;

import static javax.persistence.GenerationType.IDENTITY;

/**
 * Created with IntelliJ IDEA.
 * User: kirilkadurilka
 * Date: 10.02.13
 * Time: 17:19
 * To change this template use File | Settings | File Templates.
 */
@Entity
@Table(name = "userlogin")
@NamedQueries({
        @NamedQuery(name = "select_UserLogin_by_login", query = "SELECT u from UserLogin u where u.login=:login"),
})
public class UserLogin {

    @Id
    @GeneratedValue(strategy = IDENTITY)
    @Column(name = "userlogin_id")
    private int id;

    @Column(name = "userlogin_login")
    private String login;

    @Column(name = "password")
    private String password;

    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "user_id")
    private User user;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user", cascade = CascadeType.ALL)
    private Set<TaskSend> taskSends;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public Set<TaskSend> getTaskSends() {
        return taskSends;
    }

    public void setTaskSends(Set<TaskSend> taskSends) {
        this.taskSends = taskSends;
    }
}
