package service;

import dao.user.UserDAOTest;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceTest {

    @Mock
    UserService userService;

    @Mock
    UserDAOTest userDAOTest;

    @Mock
    User testUser1;

    @Mock
    User testUser2;


    @Before
    public void setUp() throws Exception {

        userService.setUserDAO(userDAOTest);
    }


    @Test
    public void createUser() {
        userService.createUser(testUser1);
        verify(userService).createUser(testUser1);
    }

    @Test
    public void editUser() {
        testUser1.setName("max");
        userService.editUser(testUser1);
        verify(userService).editUser(testUser1);
    }

    @Test
    public void getFollowing() {
        userService.getFollowing(testUser1);
        verify(userService).getFollowing(testUser1);
    }

    @Test
    public void getFollowers() {
        userService.getFollowers(testUser1);
        verify(userService).getFollowers(testUser1);
    }

    @Test
    public void removeUser() {
        userService.removeUser(testUser1);
        verify(userService).removeUser(testUser1);
    }

    @Test
    public void getAllUsers() {
        userService.getAllUsers();
        verify(userService).getAllUsers();
    }

    @Test
    public void findByEmail() {
        userService.findByEmail(testUser1.getEmail());
        verify(userService).findByEmail(testUser1.getEmail());
    }


//    @Test
//    public void followUser() {
//        userService.followUser(testUser1, testUser2);
//    }

}