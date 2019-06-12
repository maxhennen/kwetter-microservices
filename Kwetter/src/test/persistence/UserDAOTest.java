package persistence;

import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import javax.persistence.EntityManager;
import javax.swing.text.html.parser.Entity;

import static org.junit.Assert.*;
import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserDAOTest {

    @InjectMocks
    User user1;

    @Mock
    EntityManager em;

    @Before
    public void setUp() throws Exception {
    }

    @Test
    public void createUser(){
        em.persist(user1);
        verify(em).persist(user1);
    }

    @Test
    public void editUser(){
        user1.setName("qewrgt");
        em.persist(user1);
        verify(em).persist(user1);
    }

    @Test
    public void removeUser(){
        em.remove(user1);
        verify(em).remove(user1);
    }

    @Test
    public void getAllUsers(){
        em.createNamedQuery("User.findAll");
        verify(em).createNamedQuery("User.findAll");
    }
}