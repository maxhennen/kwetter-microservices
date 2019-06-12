package domain;

import java.io.Serializable;
import javax.persistence.*;

@Entity
@Table(name="user_groups")
@NamedQueries({
        @NamedQuery(name = "Group.getByUserEmail", query = "SELECT G FROM Group G WHERE G.email = :email"),
        @NamedQuery(name = "Group.findAll", query = "SELECT G FROM Group G")
})

public class Group implements Serializable {

    private static final long serialVersionUID = 1528447384986169065L;

    @Id
    @Column(name="email", nullable=false, length=255)
    private String email;

    @Column(name="groupname", nullable=false, length=32)
    private String groupname;

    public Group() {}
    public Group(String email, String groupname) {
        this.email = email;
        this.groupname = groupname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getGroupname() {
        return groupname;
    }
    public void setGroupname(String groupname) {
        this.groupname = groupname;
    }

    @Override
    public String toString() {
        return "Group{" +
                "email='" + email + '\'' +
                ", groupname='" + groupname + '\'' +
                '}';
    }
}