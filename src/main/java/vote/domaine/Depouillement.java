package vote.domaine;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Depouillement {


    public Map<String, Integer> depouille(List<Bulletin> bulletins) {
        Map<String,Integer> resultat = Maps.newConcurrentMap();
        Set<String> candidats = Sets.newHashSet();
        candidats.addAll(bulletins.get(0).candidats());
        for (String candidat : candidats) {
            resultat.put(candidat, bulletins.get(0).noteDe(candidat).get());
        }

        return resultat;
    }
}
