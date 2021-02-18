package sample.gui.controller.Teacher;

import com.jfoenix.controls.JFXTextField;
import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.geometry.Side;
import javafx.scene.chart.PieChart;
import javafx.scene.control.ComboBox;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import sample.be.Student;
import sample.gui.model.StudentsModel;

import java.net.URL;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import java.util.ResourceBundle;

/**
 * on the right there will be scroll bar,
 * on the top there will be a searching funcitonality,
 * in the centre the tilePane with the students views
 *
 * @author kuba
 */
public class StudentsViewController implements Initializable {
    @FXML
    private BorderPane borderPane;
   //private JFXScrollPane scrollPane = new JFXScrollPane();
   private ScrollPane scrollPane = new ScrollPane();
    private TilePane tilePane = new TilePane();
    private List<Student> allStudents = new ArrayList<>();
    private StudentsModel studentsModel = new StudentsModel();
    private  JFXTextField field = new JFXTextField();
    private  final ComboBox comboBox = new ComboBox();


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCore();
    }

    //add scroll pane to the center of the BorderPane at the very end
    private void initCore(){
        addSAllStudentsBoxes();
        setCenter();
        setTop();
        setSearchListener();
    }

    /**
     * move the core of this functionality to the BLL
     */
    private void setSearchListener() {
        field.textProperty().addListener((observableValue, s, t1) -> {
            System.out.println(t1.toString());
            allStudents.clear();
            allStudents.addAll(studentsModel.search(t1));
                tilePane.getChildren().clear();
                addSAllStudentsBoxes();

        });

    }

    private void addSAllStudentsBoxes(){
        allStudents = studentsModel.getAllStudents();
        for(Student student: allStudents){
            //for now use random generator
            Random random = new Random();
            int present = random.nextInt((100 - 40) + 1) + 40;
            int absent =  random.nextInt((20 - 4) + 1) + 4;
            tilePane.getChildren().add(singleUserBox(student, absent, present));
        }
    }

    private void setCenter(){
        tilePane.setId("tilePane");
        tilePane.setHgap(25);
        tilePane.setVgap(25);
        tilePane.setPadding(new Insets(10));
       scrollPane.setContent(tilePane);
       scrollPane.setVbarPolicy(ScrollPane.ScrollBarPolicy.ALWAYS);
       scrollPane.setFitToWidth(true);
       scrollPane.setFitToHeight(true);
        borderPane.setCenter(scrollPane);
        borderPane.getCenter().setId("borderCenter");
    }

    //for all students create boxes in the loop
    // and add them to the gridPane inside
    //JFXScrollPane
    private VBox singleUserBox(Student student, int absDays, int presentDays){
        VBox vboxContainer = new VBox();
        HBox bottomContainer = new HBox();
        bottomContainer.setAlignment(Pos.TOP_CENTER);
        vboxContainer.setAlignment(Pos.BASELINE_CENTER);
        bottomContainer.getChildren().add( getChart(absDays, presentDays));
        vboxContainer.getChildren().addAll( addStudentsPhoto(student), setStudentsName(student),
                setStudentsProgramInfo(student),
                setStudentsAttendanceInfo(absDays, presentDays), bottomContainer
               );
        VBox.setVgrow(getChart(absDays, presentDays), Priority.ALWAYS);
        vboxContainer.setSpacing(5);
        vboxContainer.setId("vBox");
        vboxContainer.setMinWidth(225);
        vboxContainer.setPadding(new Insets(5));
        return vboxContainer;
    }

    private Label setStudentsAttendanceInfo(int absDays, int presDays){
        int averageAttendace = presDays / (presDays + absDays);
        Label attendance = new Label("Attendance: " + averageAttendace);
        attendance.setId("attendanceLabel");
        attendance.setWrapText(false);
        return attendance;
    }
    
    private Label setStudentsName(Student student){
        String name = student.getFirstName() + " " + student.getSecondName();
        Label nameLabel = new Label(name);
        nameLabel.setId("NameLabel");
        nameLabel.setWrapText(true);
        return nameLabel;
    }
    
    private Label setStudentsProgramInfo(Student student){
        String program = student.getStudyProgram() + " sem: " + student.getSemester();
        Label programLabel = new Label(program);
        programLabel.setId("ProgramLabel");
        programLabel.setWrapText(true);
        return  programLabel;
    }
    
    private ImageView addStudentsPhoto(Student student){
        ImageView imageView = new ImageView("/sample/Images/student1.png");
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);
        return imageView;
    }
    
    private PieChart getChart(int absDays, int presentDays){
        PieChart chart = new PieChart();
        PieChart.Data absent = new PieChart.Data("Absent", absDays);
        PieChart.Data present = new PieChart.Data("Present", presentDays);
        chart.getData().addAll(present, absent);
        chart.setLabelsVisible(true);
        chart.setMinSize(110, 110);
        chart.setPrefSize(110, 110);
        chart.setLabelLineLength(10);
        chart.setLegendSide(Side.TOP);
        chart.setId("Chart");
        System.out.println(chart.getPrefHeight());
        return chart;
    }

    /**
     * method adds the searchField and the comboBox for choosing a class
     * Class will be chosen based on the classes that are taught
     * by that one logged in teacher
     */
    private void setTop(){
      // field.setLabelFloat(false);
       field.setPromptText("Search: ");
       field.setId("searchfield");
        //later add here all courses taugh by the logged teacher
        ObservableList<String> options = FXCollections.observableArrayList(
                "SCO1", "SCO2", "SCO3", "SCO4", "SCO5");
        comboBox.setItems(options);
        comboBox.setPromptText("Course");
        comboBox.setEditable(true);
        comboBox.valueProperty().addListener(new ChangeListener() {
            @Override
            public void changed(ObservableValue observableValue, Object o, Object t1) {
                comboBox.setPromptText((String)t1);
                //look for the students enrolled in that course
            }
        });

        //add it to the screen
        HBox hBox = new HBox();
        hBox.setSpacing(50);
        hBox.setPadding(new Insets(10));
        hBox.getChildren().addAll(field, comboBox);
        borderPane.setTop(hBox);
        borderPane.getTop().setId("topInStudentsV");
    }

    // we don't need access to the fields or parameters??


}
