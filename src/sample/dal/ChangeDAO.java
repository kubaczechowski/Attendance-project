package sample.dal;

import sample.be.Change;

import java.util.ArrayList;
import java.util.List;

public class ChangeDAO {

    public List<Change> getAllChanges() {
        List<Change> changes = new ArrayList<>();
        changes.add(new Change("Jack Grey", "p -> a", "27.01.21"));
        changes.add(new Change("Electra Boomes", "a -> p", "05.02.21"));

        return changes;
    }
}
