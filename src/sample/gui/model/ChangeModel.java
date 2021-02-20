package sample.gui.model;

import sample.be.Change;
import sample.dal.ChangeDAO;

import java.util.List;

public class ChangeModel {

    private ChangeDAO changeDAO;

    public ChangeModel() {
        changeDAO = new ChangeDAO();
    }

    public List<Change> getAllChanges() {
        return changeDAO.getAllChanges();
    }
}
