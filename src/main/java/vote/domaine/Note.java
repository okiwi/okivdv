package vote.domaine;

public class Note {
    public Note(Vote vote, String candidat) {
        this.vote = vote;
        this.candidat = candidat;
    }

    public Vote avecNote(int note) {
        this.note = note;
        return vote;
    }

    private final Vote vote;
    final String candidat;
    int note;
}
