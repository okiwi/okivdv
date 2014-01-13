package vote.domaine;

public class Vote {
    public Vote(Bulletin bulletin, String candidat) {
        this.bulletin = bulletin;
        this.candidat = candidat;
    }

    public Bulletin avecNote(int note) {
        this.note = note;
        return bulletin;
    }

    private final Bulletin bulletin;
    final String candidat;
    int note;
}
