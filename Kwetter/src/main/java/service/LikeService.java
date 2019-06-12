package service;

import dao.kweet.KweetDAOImpl;
import dao.like.LikeDAO;
import dao.like.LikeDAOImpl;
import dao.like.LikeDAOTest;
import domain.Like;

import javax.ejb.Stateless;
import javax.inject.Inject;
import java.util.List;

@Stateless
public class LikeService {

    @Inject
    private LikeDAO likeDAO;


    /**
     * Used for testing only!
     *
     * @param dao
     */
    public void setLikeDAO(LikeDAOTest dao) {
        likeDAO = dao;
    }

    /**
     * Create a like
     * @param like
     */
    public Like createLike(Like like){
        return likeDAO.create(like);
    }

    /**
     * Removes a like
     * @param like
     */
    public Like removeLike(Like like){
        return likeDAO.removeLike(like);
    }

    /**
     * Retrieves all likes
     * @return
     */
    public List<Like> findALlLikes(){
        return likeDAO.findAll();
    }

    /**
     * Retrieve the like by id
     * @param id
     * @return
     */
    public Like getById(long id){
        return likeDAO.get(id);
    }
}
