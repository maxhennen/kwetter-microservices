package utils;

import dao.group.GroupDAO;
import dao.kweet.KweetDAO;
import dao.user.UserDAO;
import domain.*;

import javax.annotation.PostConstruct;
import javax.ejb.Startup;
import javax.inject.Inject;
import javax.ejb.Singleton;
import java.time.LocalDateTime;


@Singleton
@Startup
public class JPALoader {

    @Inject
    private UserDAO userDAO;
    @Inject
    private GroupDAO groupDAO;
    @Inject
    private KweetDAO kweetDAO;

    @PostConstruct
    public void init(){
        try {
            User user1 = new User("test1@test.nl", "Test123", "Test1");
            User user2 = new User("test2@test.nl", "Test123", "Test2");
            User user3 = new User("test3@test.nl", "Test123", "Test3");

            Location location = new Location("NL","EHV", "Schoolstraat", "1");
            Location location1 = new Location("NL","EHV", "Schoolstraat", "1");
            Location location2= new Location("NL","EHV", "Schoolstraat", "1");

            Details details = new Details("hoi", "hoi.nl", location);
            Details details1 = new Details("hoi", "hoi.nl", location1);
            Details details2 = new Details("hoi", "hoi.nl", location2);

            userDAO.addFollower(user1.getEmail(), user2.getEmail());
            userDAO.addFollower(user1.getEmail(), user3.getEmail());
            userDAO.addFollower(user2.getEmail(), user1.getEmail());

            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hoi", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi1", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi2", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi3", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi4", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi5", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi6", user1.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hoi7", user1.getEmail()));
            kweetDAO.create( new Kweet(LocalDateTime.now(), "Hoi8", user1.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hoi9", user1.getEmail()));

            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hallo", user2.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hallo1", user2.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hallo2", user2.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Hallo3", user2.getEmail()));

            kweetDAO.create(new Kweet(LocalDateTime.now(), "Heey", user3.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Heey1", user3.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Heey2", user3.getEmail()));
            kweetDAO.create(new Kweet(LocalDateTime.now(), "Heey3", user3.getEmail()));

            user1.setDetails(details);
            user2.setDetails(details1);
            user3.setDetails(details2);

            userDAO.createUser(user1);
            userDAO.createUser(user2);
            userDAO.createUser(user3);

            groupDAO.create(new Group(user1.getEmail(), "ROLE_USER"));
            groupDAO.create(new Group(user2.getEmail(), "ROLE_ADMIN"));
            groupDAO.create(new Group(user3.getEmail(), "ROLE_MOD"));

        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
