package dao.group;

import domain.Group;

import java.util.List;

public interface GroupDAO {

    /**
     * Creates a new Like object
     */
    Group create(Group r);

    /**
     * Retrieves a group by name
     */
    Group getByUserEmail(String email);

    /**
     * Edit group
     * @param g
     */
    Group update(Group g);

    /**
     * Returns list of all groups
     * @return
     */
    List<Group> getAllGroups();
}
