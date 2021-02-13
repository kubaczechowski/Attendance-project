package sample.gui.controller.Teacher;

import com.jfoenix.controls.JFXScrollPane;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Pos;
import javafx.scene.chart.PieChart;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.TilePane;
import javafx.scene.layout.VBox;
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
 */
public class StudentsViewController implements Initializable {
    @FXML
    private BorderPane borderPane;
   //private JFXScrollPane scrollPane = new JFXScrollPane();
   private ScrollPane scrollPane = new ScrollPane();
    private TilePane tilePane = new TilePane();
    private List<Student> allStudents = new ArrayList<>();
    private StudentsModel studentsModel = new StudentsModel();

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        initCore();
    }

    //add scroll pane to the center of the BorderPane at the very end
    private void initCore(){
        addSAllStudentsBoxes();
        setCenter();
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
       scrollPane.setContent(tilePane);
       scrollPane.setFitToWidth(true);
       scrollPane.setFitToHeight(true);
        borderPane.setCenter(scrollPane);
        borderPane.getCenter().setId("borderCenter");
    }

    //for all students create in the loop
    //the boxes and add them to the gridPane inside
    //JFXScrollPane
    private VBox singleUserBox(Student student, int absDays, int presentDays){
        VBox vboxContainer = new VBox();
        HBox bottomContainer = new HBox();
        
        setStudentsName(student);
        setStudentsProgramInfo(student);
       // bottomContainer.getChildren().addAll(setStudentsAttendanceInfo(absDays, presentDays), getChart(absDays, presentDays));
        vboxContainer.getChildren().addAll( addStudentsPhoto(student), setStudentsName(student), 
                setStudentsProgramInfo(student),
                setStudentsAttendanceInfo(absDays, presentDays), getChart(absDays, presentDays));
        //vboxContainer.setPrefSize(300, 350);
        vboxContainer.setAlignment(Pos.BASELINE_CENTER);
        return vboxContainer;
    }

    private Label setStudentsAttendanceInfo(int absDays, int presDays){
        int averageAttendace = presDays / (presDays + absDays);
        Label attendance = new Label("Attendance: " + averageAttendace);
        attendance.setId("attendanceLabel");
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
      //  String filePath = student.getFilePathImg().replaceAll(" ","");
       // String filePath = "/src/sample/gui/css/student1.png";
       // System.out.println(filePath);
       // Image image = new Image(filePath);
        ImageView imageView = new ImageView("/sample/Images/student1.png");
        imageView.setFitWidth(75);
        imageView.setFitHeight(75);
       // imageView.setImage(image);
        return imageView;
    }
    
    private PieChart getChart(int absDays, int presentDays){
        PieChart chart = new PieChart();
        PieChart.Data absent = new PieChart.Data("Absent", absDays);
        PieChart.Data present = new PieChart.Data("Present", presentDays);
        chart.getData().addAll(present, absent);
        chart.setLabelsVisible(false);
        chart.setMinSize(100, 100);
        chart.setPrefSize(20, 20);
        return chart;
    }

}
