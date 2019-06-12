package dao.user;

import domain.Follower;
import domain.Following;
import domain.Token;
import domain.User;
import sun.reflect.generics.reflectiveObjects.NotImplementedException;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class UserDAOTest implements UserDAO {

    List<User> users = new ArrayList<>();

    @Override
    public User createUser(User u) {
        users.add(u);
        return u;
    }

    @Override
    public User editUser(User u) {
        for(User user : users){
            if(user.getId() == u.getId()){
                users.remove(user);
                users.add(u);
            }
        }
        return u;
    }

    @Override
    public boolean removeUser(User u) {
        users.remove(u);
        return true;
    }

    @Override
    public List<User> getAllUsers() {
        return users;
    }

    @Override
    public User findUserByEmail(String email) {
        for(User u : users){
            if(u.getEmail().equals(email)){
                return u;
            }
        }
        return null;
    }

    @Override
    public List<User> getAllFollowing(User u) {
        throw  new NotImplementedException();
    }

    @Override
    public List<User> getAllFollowers(User u) {
        throw  new NotImplementedException();
    }

    @Override
    public Follower addFollower(String emailFollower, String emailFollowing) {
        return new Follower();
    }

    @Override
    public boolean removeFollower(Follower follower, Following following) {
        return true;
    }

    @Override
    public boolean removeFollowing(Following following, Follower follower) {
        return true;
    }

    @Override
    public Follower getFollowerByEmail(String email) {
        return null;
    }

    @Override
    public Following getFollowingByEmail(String email) {
        return null;
    }

    @Override
    public User login(String email, String password) {
        return null;
    }

    @Override
    public Token addToken(Token token) {
        return null;
    }

    @Override
    public Token getToken(String token) {
        return null;
    }

}
