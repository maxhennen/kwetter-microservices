package dao.like;

import domain.Like;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class LikeDAOTest implements LikeDAO{

    List<Like> likes = new ArrayList<>();

    @Override
    public Like create(Like l) {
        likes.add(l);
        return l;
    }

    @Override
    public Like removeLike(Like l) {
        likes.remove(l);
        return l;
    }

    @Override
    public List<Like> findAll() {
        return likes;
    }

    @Override
    public Like get(long id) {
        for (Like l: likes) {
            if(l.getId() == id){
                return l;
            }
        }
        return null;
    }
}
