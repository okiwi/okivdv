package vote.domaine;

import com.google.common.base.Optional;
import com.google.common.collect.Sets;
import org.junit.Test;

import java.util.Set;

import static org.fest.assertions.Assertions.*;

public class BulletinTests {

    @Test
    public void onPeutDonnerUneNoteAUnCandidat() {
        Bulletin bulletin = new Bulletin();
        String candidat = "François Hollande";

        Bulletin bulletinRetourne = bulletin.pour(candidat).avecNote(0);

        assertThat(bulletinRetourne).isEqualTo(bulletin);
        assertThat(bulletin.noteDe(candidat)).isEqualTo(Optional.of(0));
    }

    @Test
    public void peutDonnerLaListeDuCandidat() {
        Bulletin bulletin = bulletinAvec("François Bayrou");
        Set<String> candidats = Sets.newHashSet("François Bayrou");
        assertThat(bulletin.candidats()).isEqualTo(candidats);
    }

    @Test
    public void peutDonnerLaListeDesCandidats() {
        Bulletin bulletin = bulletinAvec("François Hollande", "François Bayrou");
        Set<String> candidatsDuBulletin = bulletin.candidats();
        assertThat(candidatsDuBulletin).isEqualTo(Sets.newHashSet("François Hollande", "François Bayrou"));
    }

    private Bulletin bulletinAvec(String... candidats) {
        Bulletin bulletin = new Bulletin();
        for (String candidat : candidats){
            bulletin.pour(candidat);
        }
        return bulletin;
    }


}
