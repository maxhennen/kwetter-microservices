package service;

import dao.kweet.KweetDAOTest;
import dao.user.UserDAOTest;
import domain.Kweet;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;
import static org.mockito.Mockito.*;

@RunWith(MockitoJUnitRunner.class)
public class KweetServiceTest {

    @Mock
    KweetService kweetService;

    @Mock
    KweetDAOTest kweetDAOTest;

    @Mock
    UserDAOTest userDAOTest;

    @Mock
    User testUser1 = mock(User.class);

    @Mock
    Kweet testKweet1;

    @Mock
    Kweet testKweet2;


    @Before
    public void setUp() throws Exception {
        kweetService.setKweetDAO(kweetDAOTest);
        kweetService.setUserDAO(userDAOTest);
    }


    @Test
    public void createKweet() {
        kweetService.createKweet(testKweet1);
        verify(kweetService, Mockito.times(1)).createKweet(testKweet1);
    }

    @Test
    public void editKweet() {
        testKweet1.setContent("hallo");
        kweetService.editKweet(testKweet1);
        verify(kweetService, Mockito.times(1)).editKweet(testKweet1);
    }

    @Test
    public void removeKweet() {
        kweetService.removeKweet(testKweet1);
        verify(kweetService, Mockito.times(1)).removeKweet(testKweet1);
    }

    @Test
    public void getAllKweets() {
        kweetService.getAllKweets();
        verify(kweetService, Mockito.times(1)).getAllKweets();
    }

    @Test
    public void getKweetById() {
        kweetService.getKweetById(1);
        verify(kweetService, Mockito.times(1)).getKweetById(1);
    }

    @Test
    public void getKweetsByEmail() {
        kweetService.getKweetsByEmail("test12");
        verify(kweetService, Mockito.times(1)).getKweetsByEmail("test12");
    }
}