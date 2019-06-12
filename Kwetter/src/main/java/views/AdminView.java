package views;

import domain.Group;
import domain.User;
import service.GroupService;
import service.UserService;

import javax.annotation.PostConstruct;
import javax.ejb.EJB;
import javax.enterprise.context.SessionScoped;
import javax.faces.bean.ManagedBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

@ManagedBean
@SessionScoped
public class AdminView implements Serializable {

    private static final long serialVersionUID = 1685823449195612778L;

    @EJB
    private UserService userService;
    @EJB
    private GroupService groupService;

    private List<User> users;
    private List<Group> groups;

    private Group selectedGroup;

    @PostConstruct
    public void init(){
        users = new ArrayList<>();
        groups = new ArrayList<>();
        selectedGroup = new Group();

        users = userService.getAllUsers();
        groups.add(new Group("ROLE_ADMIN", ""));
        groups.add(new Group("ROLE_USER", ""));
        groups.add(new Group("ROLE_MOD", ""));
    }

    public List<User> getUsers() {
        return users;
    }

    public List<Group> getGroups() {
        return groups;
    }

    public Group getSelectedGroup() {
        return selectedGroup;
    }

    public void edit(String email, Group group){
        group.setEmail(email);
        groupService.edit(group);
    }
}
