package dao.user;

import domain.Follower;
import domain.Following;
import domain.Token;
import domain.User;
import utils.AuthenticationUtils;

import javax.annotation.PreDestroy;
import javax.ejb.Stateless;
import javax.enterprise.inject.Default;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.PersistenceContext;
import java.util.List;

@Stateless
@Default
public class UserDAOImpl implements UserDAO{

    @PersistenceContext(unitName = "NewPersistenceUnit")
    private EntityManager em;

    @Override
    public User createUser(User u) {
        u.setPassword(AuthenticationUtils.encodeSHA256(u.getPassword()));
        try {
            em.persist(u);
        } catch (Exception e) {
            e.printStackTrace();
        }

        return u;
    }

    @Override
    public User editUser(User u) {
        em.merge(u);
        return u;
    }

    @Override
    public boolean removeUser(User u) {
        em.remove(u);
        return em.find(User.class, u) == null;
    }

    @Override
    public List<User> getAllUsers() {
        return  em.createNamedQuery("User.findAll").getResultList();
    }

    @Override
    public User findUserByEmail(String email) {
        try {
            return (User)em.createNamedQuery("User.findByEmail")
                    .setParameter("email", email).getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public List<User> getAllFollowing(User u) {
        return (List<User>) em.createNamedQuery("User.getFollowing")
                .setParameter("email", u.getEmail()).getResultList();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        return (List<User>) em.createNamedQuery("User.getFollowers")
                .setParameter("email", u.getEmail()).getResultList();
    }

    @Override
    public Follower addFollower(String emailFollower, String emailFollowing) {
        Follower follower = new Follower(emailFollowing, emailFollower);
        Following following = new Following(emailFollower, emailFollowing);

        em.persist(follower);
        em.persist(following);

        Follower follower1 = em.find(Follower.class, follower.getId());
        Following following1 = em.find(Following.class, following.getId());

        em.persist(follower1);
        em.persist(following1);

        return follower;
    }

    @Override
    public boolean removeFollower(Follower follower, Following following) {
        return removeFollowingFollower(following, follower);
    }

    @Override
    public boolean removeFollowing(Following following, Follower follower) {
        return removeFollowingFollower(following, follower);
    }

    private boolean removeFollowingFollower(Following following, Follower follower){

        em.remove(follower);
        em.remove(following);

        Following following1 = em.find(Following.class, following);
        Follower follower1 = em.find(Follower.class, follower);

        if(follower1 == null && following1 == null){
            return true;
        } else {
            return false;
        }
    }

    @Override
    public Follower getFollowerByEmail(String email) {
        return (Follower)em.createNamedQuery("User.getFollowerByEmail")
                .setParameter("email", email);
    }

    @Override
    public Following getFollowingByEmail(String email) {
        return (Following) em.createNamedQuery("User.getFollowingByEmail")
                .setParameter("email", email);
    }

    @Override
    public User login(String email, String password) {
        try {
            return (User) em.createNamedQuery("User.login")
                    .setParameter("email", email)
                    .setParameter("password", password)
                    .getSingleResult();
        } catch (NoResultException e) {
            return null;
        }
    }

    @Override
    public Token addToken(Token token) {
        try {
            em.persist(token);
            return token;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    @Override
    public Token getToken(String token) {
        try{
            return (Token) em.createNamedQuery("User.getToken")
                    .setParameter("token", token).getSingleResult();
        } catch (NoResultException e){
            return null;
        }
    }

    @PreDestroy
    public void destroy(){
        em.close();
    }

}
