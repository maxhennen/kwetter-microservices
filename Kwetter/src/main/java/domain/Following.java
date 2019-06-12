package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name="user_following")
public class Following implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(name = "email_user", nullable = false)
    private String emailUser;

    @Column(name = "email_following", nullable = false)
    private String emailFollowing;

    public Following() {
    }

    public Following(String emailUser, String emailFollowing) {
        this.emailUser = emailUser;
        this.emailFollowing = emailFollowing;
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

    public String getEmailFollowing() {
        return emailFollowing;
    }

    public void setEmailFollowing(String emailFollowing) {
        this.emailFollowing = emailFollowing;
    }
}
