package dao.kweet;

import domain.Kweet;

import javax.ejb.Stateless;
import java.util.List;

public interface KweetDAO {

    /**
     * Creates a new Kweet object
     */
    Kweet create(Kweet k);


    /**
     * Edits a Kweet
     * @param k
     */
    Kweet edit( Kweet k);


    /**
     * Removes a Kweet object
     *
     * @param k
     */
    boolean removeKweet(Kweet k);


    /**
     * Returns all kweets in this application
     * @return list of kweets
     */
    List<Kweet> findAll() ;

    /**
     * Returns a kweet based on id
     * @return kweet
     */
    Kweet get(long id);

    /**
     * Retrieves kweets by email
     * @param email
     * @return
     */
    List<Kweet> getKweetsByEmail(String email);

}
