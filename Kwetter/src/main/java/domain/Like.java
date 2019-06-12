package domain;

import javax.persistence.*;
import java.io.Serializable;
import java.time.LocalDateTime;

@Entity
@Table(name = "kweetLike")
@NamedQueries({
        @NamedQuery(name = "Like.findAll", query = "SELECT L FROM Like L"),
        @NamedQuery(name = "Like.getByID", query = "SELECT L FROM Like L WHERE L.id = :id")
})
public class Like implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "date_time")
    private LocalDateTime dateTime;
    @ManyToOne
    private User user;
    private long kweetId;
    public Like(LocalDateTime dateTime, User user, long kweetId) {
        this.dateTime = dateTime;
        this.user = user;
        this.kweetId = kweetId;
    }

    public Like() {
    }

    public long getId() {
        return id;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public User getUser() {
        return user;
    }

    public void setId(long id) {
        this.id = id;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Like{" +
                "id=" + id +
                ", dateTime=" + dateTime +
                ", user=" + user +
                '}';
    }
}
