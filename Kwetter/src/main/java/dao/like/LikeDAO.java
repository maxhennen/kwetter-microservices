package dao.like;

import domain.Like;

import java.util.List;

public interface LikeDAO {

    /**
     * Creates a new Like object
     */
    Like create(Like l);



    /**
     * Removes a Like object
     *
     * @param l
     */
    Like removeLike(Like l);


    /**
     * Returns all likes in this application
     * @return list of likes
     */
    List<Like> findAll() ;

    /**
     * Returns a like based on id
     * @return like
     */
    Like get(long id);
}
