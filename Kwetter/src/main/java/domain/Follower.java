package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "user_follower")
public class Follower implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email_user", nullable = false)
    private String emailUser;

    @Column(name = "email_follower", nullable = false)
    private String emailFollower;

    public Follower() {
    }

    public Follower(String emailUser, String emailFollower) {
        this.emailUser = emailUser;
        this.emailFollower = emailFollower;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getEmailUser() {
        return emailUser;
    }

    public void setEmailUser(String emailUser) {
        this.emailUser = emailUser;
    }

    public String getEmailFollower() {
        return emailFollower;
    }

    public void setEmailFollower(String emailFollower) {
        this.emailFollower = emailFollower;
    }
}
