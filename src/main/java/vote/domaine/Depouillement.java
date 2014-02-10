package vote.domaine;

import com.google.common.collect.Maps;
import com.google.common.collect.Sets;

import java.util.List;
import java.util.Map;
import java.util.Set;

public class Depouillement {


    public Map<String, Integer> depouille(List<Bulletin> bulletins) {
        Map<String,Integer> resultat = Maps.newConcurrentMap();
        for (String candidat : tousLesCandidats(bulletins)) {
            resultat.put(candidat, scoreDe(candidat, bulletins));
        }
        return resultat;
    }

    private int scoreDe(String candidat, List<Bulletin> bulletins) {
        int score = 0;
        for (Bulletin bulletin : bulletins) {
            score+= bulletin.noteDe(candidat).or(0);
        }
        return score;
    }

    private Set<String> tousLesCandidats(List<Bulletin> bulletins) {
        Set<String> candidats = Sets.newHashSet();
        for (Bulletin bulletin : bulletins) {
            candidats.addAll(bulletin.candidats());
        }
        return candidats;
    }

    public String gagnant(Map<String, Integer> dépouillement) {
        Map.Entry < String, Integer > meilleurCandidat = dépouillement.entrySet().iterator().next();
        for(Map.Entry < String, Integer > candidat:dépouillement.entrySet()){
            if( meilleurCandidat.getValue()< candidat.getValue()) {
                meilleurCandidat = candidat;
            }
        }
        return meilleurCandidat.getKey();
    }
}
