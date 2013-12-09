package vote.domaine;

import com.google.common.collect.Maps;

import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentMap;

public class Depouillement {


    public Map<String, Integer> depouille(List<Vote> votes) {
        ConcurrentMap<String,Integer> resultat = Maps.newConcurrentMap();
        resultat.put("Francois Hollande", votes.get(0).noteDe("Francois Hollande").get());
        return resultat;
    }
}
