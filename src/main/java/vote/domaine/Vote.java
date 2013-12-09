package vote.domaine;

import com.google.common.base.Optional;
import com.google.common.collect.Lists;

import java.util.List;

public class Vote {
    public Note pour(String candidat) {
        Note note = new Note(this, candidat);
        notes.add(note);
        return note;
    }

    public Optional<Integer> noteDe(final String candidat) {
        for (Note note : notes) {
            if(note.candidat.equals(candidat))
                return Optional.of(note.note);
        }
        Optional<Integer> optional = Optional.absent();
        return optional;
    }

    private final List<Note> notes = Lists.newArrayList();
}
