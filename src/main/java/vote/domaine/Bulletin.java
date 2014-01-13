package vote.domaine;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;
import com.google.common.collect.Sets;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class Bulletin {

    public Vote pour(String candidat) {
        Vote vote = new Vote(this, candidat);
        votes.add(vote);
        return vote;
    }

    public Optional<Integer> noteDe(final String candidat) {
        for (Vote vote : votes) {
            if(vote.candidat.equals(candidat))
                return Optional.of(vote.note);
        }
        return Optional.absent();
    }

    public Set<String> candidats() {
        HashSet<String> candidats = Sets.newHashSet();
        for (Vote vote : votes) {
            candidats.add(vote.candidat);
        }
        return candidats;
    }

    private final List<Vote> votes = Lists.newArrayList();
}
