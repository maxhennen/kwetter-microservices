package views;

import domain.Group;
import domain.Kweet;
import domain.User;
import service.GroupService;
import service.KweetService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class ModeratorView implements Serializable {

    private static final long serialVersionUID = 1685823449195612778L;

    @EJB
    private UserService userService;
    @EJB
    private KweetService kweetService;
    @EJB
    private GroupService groupService;

    private List<User> users;
    private List<Kweet> kweets;

    public List<Kweet> getKweets(){
        return kweets;
    }

    public List<User> getUsers(){
        return users;
    }

    public Group getGroupByEmail(String email){
        Group g = groupService.getGroupByName(email);
        return g;
    }

    public void deleteKweet(Kweet kweet){
        kweetService.removeKweet(kweet);
        kweets = new ArrayList<>();
        kweets = kweetService.getAllKweets();
    }

    @PostConstruct
    public void init(){
        users = new ArrayList<>();
        kweets = new ArrayList<>();
        users = userService.getAllUsers();
        kweets = kweetService.getAllKweets();
    }
}
