package vote.domaine;

import org.junit.Test;

import static org.fest.assertions.Assertions.assertThat;

public class VoteTests {

    @Test
    public void onPeutDonnerUneNoteAUnCandidat() {
        Vote vote = new Vote();
        String candidat = "François Hollande";

        Vote voteRetourne = vote.pour(candidat).avecNote(0);

        assertThat(voteRetourne).isEqualTo(vote);
        assertThat(vote.noteDe(candidat)).isEqualTo(0);
    }
}
