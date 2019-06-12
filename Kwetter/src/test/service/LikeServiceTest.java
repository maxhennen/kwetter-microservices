package service;

import dao.like.LikeDAOTest;
import domain.Kweet;
import domain.Like;
import domain.Details;
import domain.Location;
import domain.User;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.junit.MockitoJUnitRunner;

import static org.mockito.Mockito.verify;
import java.time.LocalDateTime;
import java.util.ArrayList;


@RunWith(MockitoJUnitRunner.class)
public class LikeServiceTest {

    @Mock
    LikeService likeService;

    @Mock
    LikeDAOTest likeDAOTest;

    @Mock
    Like testLike1;

    @Before
    public void setUp() throws Exception {

        likeService.setLikeDAO(likeDAOTest);
    }


    @Test
    public void createLike() {
        likeService.createLike(testLike1);
        verify(likeService, Mockito.times(1)).createLike(testLike1);
    }

    @Test
    public void removeLike() {
        likeService.removeLike(testLike1);
        verify(likeService, Mockito.times(1)).removeLike(testLike1);
    }

    @Test
    public void findALlLikes() {
        likeService.findALlLikes();
        verify(likeService).findALlLikes();
    }

    @Test
    public void getById() {
        likeService.getById(1);
        verify(likeService, Mockito.times(1)).getById(1);
    }
}