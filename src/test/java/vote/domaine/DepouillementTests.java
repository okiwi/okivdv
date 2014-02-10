package vote.domaine;

import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

import java.util.Map;
import java.util.concurrent.ConcurrentMap;

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
    public void peutCumulerLesNotes() {
        Bulletin premierBulletin = unVotePour("Francois Bayrou", 1);
        Bulletin deuxiemeBulletin = unVotePour("Francois Bayrou", 2);

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(premierBulletin, deuxiemeBulletin));

        assertThat(resultats).includes(entry("Francois Bayrou", 3));
    }

    @Test
    public void peutVoterPourPlusieursCandidats() {
        Bulletin premierBulletin = unVotePour("Francois Bayrou");
        Bulletin deuxiemeBulletin = unVotePour("Francois Hollande");

        Map<String, Integer> resultats = depouillement.depouille(Lists.newArrayList(premierBulletin, deuxiemeBulletin));

        assertThat(resultats.containsKey("Francois Bayrou")).isTrue();
        assertThat(resultats.containsKey("Francois Hollande")).isTrue();
    }


    @Test
    public void peutDonnerUnGagnant() {
        ConcurrentMap<String, Integer> résultat = Maps.newConcurrentMap();
        résultat.putIfAbsent("François", 2);

        String gagnant = depouillement.gagnant(résultat);

        assertThat(gagnant).isEqualTo("François");
    }

    @Test
    public void peutDonnerUnGagnantParmiPlusieursCandidats() {
        ConcurrentMap<String, Integer> résultat = Maps.newConcurrentMap();
        résultat.putIfAbsent("François", 1);
        résultat.putIfAbsent("Jacques", 2);
        résultat.putIfAbsent("Pierre", 0);

        String gagnant = depouillement.gagnant(résultat);

        assertThat(gagnant).isEqualTo("Jacques");
    }

    @Test
    @Ignore
    public void peutDéterminerLAbsenceDeGagnant() {
        depouillement.gagnant(Maps.<String, Integer>newConcurrentMap());

    }

    @Test
    @Ignore("à implémenter par un mec qui sait ce qu'il fait")
    public void peutDéciderDUneStrategieDePriseEnCompteDesVotesBlanc() {


    }

    @Test
    public void unBulletinPeutConcernerDeuxCandidats() {
        Bulletin bulletin = new Bulletin().pour("François Bayrou").avecNote(1)
                .pour("François Hollande").avecNote(2);

        Map<String, Integer> résultat = depouillement.depouille(Lists.newArrayList(bulletin));

        assertThat(résultat).includes(entry("François Bayrou", 1), entry("François Hollande", 2));
    }

    private Bulletin unVotePour(String candidat, int note) {
        return new Bulletin().pour(candidat).avecNote(note);
    }

    private Depouillement depouillement;
}
