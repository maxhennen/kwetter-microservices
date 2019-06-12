package dao.kweet;


import domain.Kweet;

import javax.ejb.Stateless;
import javax.enterprise.context.RequestScoped;
import javax.enterprise.inject.Alternative;
import javax.enterprise.inject.Default;
import javax.inject.Named;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;


@Stateless
@Default
public class KweetDAOImpl implements KweetDAO {


    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public Kweet create(Kweet k) {
        em.persist(k);
        return k;
    }

    @Override
    public Kweet edit(Kweet k) {
        em.merge(k);
        return k;
    }

    @Override
    public boolean removeKweet(Kweet k) {
        if(!em.contains(k)){
            k = em.merge(k);
        }
        em.remove(k);
        return true;
    }

    @Override
    public List<Kweet> findAll() {
        try {
            return em.createNamedQuery("Kweet.findAll").getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Kweet get(long id) {
        try {
            return (Kweet) em.createNamedQuery("Kweet.getByID").getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<Kweet> getKweetsByEmail(String email) {
        try {
            return em.createNamedQuery("Kweet.getKweetsByEmail")
                    .setParameter("email", email).getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
