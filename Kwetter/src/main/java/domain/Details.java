package domain;

import javax.persistence.*;
import java.io.Serializable;

@Entity
@Table(name = "details")
public class Details implements Serializable {

    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    private long id;
    @Column(name = "bio")
    private String bio;
    @Column(name = "website")
    private String website;
    @OneToOne(mappedBy = "details", cascade = CascadeType.PERSIST)
    private Location location;
    @OneToOne
    private User user;

    public Details(String bio, String website, Location location) {
        this.bio = bio;
        this.website = website;
        this.location = location;
    }

    public Details(String bio, String website) {
        this.bio = bio;
        this.website = website;
    }

    public Details() {
    }

    public String getBio() {
        return bio;
    }

    public String getWebsite() {
        return website;
    }

    public Location getLocation() {
        return location;
    }

    public void setBio(String bio) {
        this.bio = bio;
    }

    public void setWebsite(String website) {
        this.website = website;
    }

    public void setLocation(Location location) {
        this.location = location;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    @Override
    public String toString() {
        return "Details{" +
                "bio='" + bio + '\'' +
                ", website='" + website + '\'' +
                ", location=" + location +
                '}';
    }
}
