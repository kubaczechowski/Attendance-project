package sample.gui.controller;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.chart.PieChart;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.util.Callback;
import sample.be.Change;
import sample.be.Student;

import java.net.URL;
import java.util.ResourceBundle;

public class TeacherViewController implements Initializable {

    @FXML private TableView changeTable;
    @FXML private TableColumn nameColumnn;
    @FXML private TableColumn typeColumnn;
    @FXML private TableColumn acceptColumnn;
    @FXML private TableColumn declineColumnn;
    @FXML private TableColumn nameColumn;
    @FXML private TableColumn monthColumn;
    @FXML private TableColumn semesterColumn;
    @FXML private TableColumn dayColumn;
    @FXML private ListView absentList;
    @FXML private TableView studentsTable;
    @FXML private PieChart absenceChart;


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        setChart();
        setStudentsTable();
        setAbsentList();
        setChangeTable();
    }

    private void setChangeTable() {
        nameColumnn.setCellValueFactory(new PropertyValueFactory<Change, String>("name"));
        typeColumnn.setCellValueFactory(new PropertyValueFactory<Change, String>("type"));
        acceptColumnn.setCellValueFactory(new PropertyValueFactory<Change, Void>(""));
        declineColumnn.setCellValueFactory(new PropertyValueFactory<Change, Void>(""));

        Callback<TableColumn<Change, Void>, TableCell<Change, Void>> cellFactory = new Callback<TableColumn<Change, Void>, TableCell<Change, Void>>() {
            @Override
            public TableCell<Change, Void> call(final TableColumn<Change, Void> param) {
                final TableCell<Change, Void> cell = new TableCell<Change, Void>() {

                    private final Button btn = new Button("Accept");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Change change = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + change);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        Callback<TableColumn<Change, Void>, TableCell<Change, Void>> cFactory = new Callback<TableColumn<Change, Void>, TableCell<Change, Void>>() {
            @Override
            public TableCell<Change, Void> call(final TableColumn<Change, Void> param) {
                final TableCell<Change, Void> cell = new TableCell<Change, Void>() {

                    private final Button btn = new Button("Decline");

                    {
                        btn.setOnAction((ActionEvent event) -> {
                            Change change = getTableView().getItems().get(getIndex());
                            System.out.println("selectedData: " + change);
                        });
                    }

                    @Override
                    public void updateItem(Void item, boolean empty) {
                        super.updateItem(item, empty);
                        if (empty) {
                            setGraphic(null);
                        } else {
                            setGraphic(btn);
                        }
                    }
                };
                return cell;
            }
        };

        ObservableList<Change> changes = FXCollections.observableArrayList(
                new Change("Jack Grey", "p -> a"),
                new Change("Electra Boomes", "a -> p")
        );
        changeTable.setItems(changes);

        acceptColumnn.setCellFactory(cellFactory);
        declineColumnn.setCellFactory(cFactory);
    }

    private void setAbsentList() {
        ObservableList<String> absentStudents = FXCollections.observableArrayList(
                "Jack Grey", "Anne Dumpling", "Jerry Window", "Tom Monte"
        );
        absentList.setItems(absentStudents);
    }

    private void setChart() {
        ObservableList<PieChart.Data> pieData = FXCollections.observableArrayList(
                new PieChart.Data("Absent", 12),
                new PieChart.Data("Present", 78)
        );
        absenceChart.setData(pieData);
    }

    private void setStudentsTable() {
        nameColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("name"));
        monthColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("month"));
        semesterColumn.setCellValueFactory(new PropertyValueFactory<Student, Double>("semester"));
        dayColumn.setCellValueFactory(new PropertyValueFactory<Student, String>("day"));

        ObservableList<Student> students = FXCollections.observableArrayList(
                new Student("Cicily Scarse",3,25, "monday"),
                new Student("Gus Audibert",10,4, "-"),
                new Student("Rowan Donohue",3,13, "monday"),
                new Student("Niven Canadine",4,12, "-"),
                new Student("Electra Boomes",17,11, "-"),
                new Student("Florenza Mardell",12,11, "-"),
                new Student("Verge Swanbourne",8,8, "wednesday"),
                new Student("Byrle Corgenvin",13,2, "friday"),
                new Student("Jeff Gude",8,7, "monday"),
                new Student("Scarlet De Vaar",1,4, "-"),
                new Student("Cati Templar",16,10, "-"),
                new Student("Tiebout McGurk",4,2, "-"),
                new Student("Shelby Robbie",7,10, "tuesday")
        );

        studentsTable.setItems(students);
    }
}
