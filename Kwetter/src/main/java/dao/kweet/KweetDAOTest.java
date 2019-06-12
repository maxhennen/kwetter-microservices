package dao.kweet;

import domain.Kweet;

import javax.enterprise.inject.Alternative;
import java.util.ArrayList;
import java.util.List;

@Alternative
public class KweetDAOTest implements KweetDAO {

    List<Kweet> kweets = new ArrayList<>();

    @Override
    public Kweet create(Kweet k) {
        kweets.add(k);
        return k;
    }

    @Override
    public Kweet edit(Kweet k) {

        for (Kweet kw: kweets) {
            if(kw.getId() == k.getId()){
                kweets.remove(kw);
                kweets.add(k);
                break;
            }
        }
        return k;
    }

    @Override
    public boolean removeKweet(Kweet k) {
        kweets.remove(k);
        return true;
    }

    @Override
    public List<Kweet> findAll() {
        return kweets;
    }

    @Override
    public Kweet get(long id) {
        for (Kweet k: kweets) {
            if(k.getId() == id){
                return k;
            }
        }
        return null;
    }

    @Override
    public List<Kweet> getKweetsByEmail(String email) {
        List<Kweet> kweetsByEmail = new ArrayList<>();

        for (Kweet k: kweets) {
            if(k.getUser().equals(email)){
                kweetsByEmail.add(k);
            }
        }
        return kweetsByEmail;
    }
}
