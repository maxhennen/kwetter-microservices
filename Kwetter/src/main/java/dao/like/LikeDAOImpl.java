package dao.like;

import domain.Like;

import javax.ejb.Stateless;
import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.Query;
import java.util.List;

@Stateless
@Default
public class LikeDAOImpl implements LikeDAO {

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public Like create(Like l) {
        em.persist(l);
        return l;
    }

    @Override
    public Like removeLike(Like l) {
        em.remove(l);
        return l;
    }

    @Override
    public List<Like> findAll() {
        return em.createNamedQuery("Like.findAll").getResultList();
    }

    @Override
    public Like get(long id) {
        return (Like) em.createNamedQuery("Like.getByID")
                .setParameter("id", id).getSingleResult();
    }
}
