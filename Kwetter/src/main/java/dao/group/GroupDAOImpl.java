package dao.group;



import domain.Group;

import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class GroupDAOImpl implements GroupDAO {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public Group create(Group g) {
        em.persist(g);
        return g;
    }

    @Override
    public Group getByUserEmail(String email) {
        try {
            Object o  = em.createNamedQuery("Group.getByUserEmail")
                    .setParameter("email", email).getSingleResult();
            return (Group) o;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }

    }

    @Override
    public Group update(Group g) {
        em.merge(g);
        return g;
    }

    @Override
    public List<Group> getAllGroups() {
        return (List<Group>) em.createNamedQuery("Group.findAll").getResultList();
    }
}
