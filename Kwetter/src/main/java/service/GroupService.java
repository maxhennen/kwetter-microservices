package service;

import dao.group.GroupDAO;
import domain.Group;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class GroupService {

    @Inject
    private GroupDAO groupDAO;

    public Group createGroup(Group group){
        return groupDAO.create(group);
    }

    public Group getGroupByName(String email){
        return groupDAO.getByUserEmail(email);
    }

    public Group edit(Group group){
        return groupDAO.update(group);
    }

    public List<Group> getAllGroups(){
        return groupDAO.getAllGroups();
    }
}
