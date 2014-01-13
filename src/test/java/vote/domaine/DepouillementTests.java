package vote.domaine;

import com.google.common.collect.Lists;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;

import static org.fest.assertions.Assertions.*;
import static org.fest.assertions.MapAssert.*;

public class DepouillementTests {

    @Before
    public void setUp() throws Exception {
        depouillement = new Depouillement();
    }

    @Test
    public void peutDonnerLesResultats() {
        Bulletin bulletin = unVotePour("Francois Hollande");

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(bulletin));

        assertThat(resultats).hasSize(1);
        assertThat(resultats.containsKey("Francois Hollande")).isTrue();
    }

    private Bulletin unVotePour(String candidat) {
        return unVotePour(candidat, 2);
    }

    @Test
    public void peutDonnerLaNoteDUnCandidat() {
        Bulletin bulletin = unVotePour("Francois Hollande", 2);

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(bulletin));

        assertThat(resultats).includes(entry("Francois Hollande", 2));
    }

    @Test
    public void peutDonnerUneAutreNoteAuCandidat() {
        Bulletin bulletin = unVotePour("Francois Hollande", -1);

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(bulletin));

        assertThat(resultats).includes(entry("Francois Hollande", -1));
    }

    @Test
    public void peutVoterPourUnAutreCandidat() {
        Bulletin bulletin = unVotePour("Francois Bayrou");

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(bulletin));

        assertThat(resultats.containsKey("Francois Bayrou")).isTrue();
    }

    @Test
    @Ignore
    public void peutCumulerLesNotes() {
        Bulletin premierBulletin = unVotePour("Francois Bayrou", 1);
        Bulletin deuxiemeBulletin = unVotePour("Francois Bayrou", 2);

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(premierBulletin, deuxiemeBulletin));

        assertThat(resultats).includes(entry("Francois Bayrou", 3));
    }

    private Bulletin unVotePour(String candidat, int note) {
        return new Bulletin().pour(candidat).avecNote(note);

    }

    private Depouillement depouillement;
}
