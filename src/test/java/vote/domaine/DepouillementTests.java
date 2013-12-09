package vote.domaine;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.entry;

public class DepouillementTests {

    @Before
    public void setUp() throws Exception {
        depouillement = new Depouillement();
    }

    @Test
    public void peutDonnerLesResultats() {
        Vote vote = unVotePour("Francois Hollande");

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(vote));

        assertThat(resultats).hasSize(1);
        assertThat(resultats.containsKey("Francois Hollande")).isTrue();
    }

    private Vote unVotePour(String candidat) {
        return unVotePour(candidat, 2);
    }

    @Test
    public void peutDonnerLaNoteDUnCandidat() {
        Vote vote = unVotePour("Francois Hollande", 2);

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(vote));

        assertThat(resultats).includes(entry("Francois Hollande", 2));
    }

    @Test
    public void peutDonnerUneAutreNoteAuCandidat() {
        Vote vote = unVotePour("Francois Hollande", -1);

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(vote));

        assertThat(resultats).includes(entry("Francois Hollande", -1));
    }

    @Test
    @Ignore("la prochaine fois")
    public void peutVoterPourUnAutreCandidat() {
        Vote vote = unVotePour("Francois Bayrou");

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(vote));

        assertThat(resultats.containsKey("Francois Bayrou")).isTrue();

    }

    private Vote unVotePour(String candidat, int note) {
        return new Vote().pour(candidat).avecNote(note);

    }

    private Depouillement depouillement;
}
