package sample.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.animation.FadeTransition;
import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.animation.TranslateTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseButton;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import javafx.util.Duration;
import sample.gui.controller.Student.RootLayoutStudentController;
import sample.gui.controller.Teacher.RootLayoutTeacherController;
import sample.gui.model.LoggingModel;
import sample.gui.util.Animations;
import sample.gui.util.RegexValidator;
import sample.gui.util.ShowMessage;

import java.awt.event.MouseEvent;
import java.io.IOException;
import java.net.URL;
import java.util.EventObject;
import java.util.ResourceBundle;

/**
 * @author kuba
 */
public class LogInController implements Initializable, ILogIn{
    private LoggingModel loggingModel = new LoggingModel();

    @FXML
    private BorderPane borderPane;
    private JFXButton logInAsAStudent;
    private JFXButton logInAsATeacher;
    private JFXTextField emailField;
    private  JFXTextField passwordField;
    boolean xyState= true;
    ShowMessage showMessage1;
    ShowMessage justMakeRed;
    Button minimize = new Button();
    Button close = new Button();
    private double xOffset=0;
    private double yOffset=0;
    private Scene oldScene;
    int counter= 0;

    //add that items programmatically
    private Label addLabel() {
        Label logIn = new Label("Log in");
        logIn.setId("logInLabel");
        return logIn;
    }


    private void addInputFields() {
         emailField = new JFXTextField();
        emailField.setLabelFloat(true);
        emailField.setPromptText("insert email");

        passwordField = new JFXTextField();
        passwordField.setLabelFloat(true);
        passwordField.setPromptText("insert password");

        VBox vBox = new VBox();
        vBox.setSpacing(40);
        //vBox.setPadding(new Insets(0, 150, 0, 150));
        vBox.getChildren().addAll(emailField, passwordField, getLoginButtons());
        //vBox.getChildren().add(passwordField);
        borderPane.setAlignment(vBox, Pos.CENTER);
        borderPane.setMargin(vBox, new Insets(50, 150, 0, 150));
        borderPane.setCenter(vBox);
    }

    /**
     * this method should be in bll
     * @param emailField
     */
    private void checkEmail(JFXTextField emailField) {
        RegexValidator emailVal = new RegexValidator("Not correct email",
                "^[\\w!#$%&'+/=?`{|}~^-]+(?:\\.[\\w!#$%&'+/=?`{|}~^-]+)*@(?:[a-zA-Z0-9-]+\\.)+[a-zA-Z]{2,6}$");
        emailField.getValidators().add(emailVal);
        emailField.focusedProperty().addListener((o,oldVal,newVal)->{
            if(!newVal) emailField.validate();
        });
    }

    private void checkIfEmpty(JFXTextField emailField, JFXTextField passwordField) {
        RequiredFieldValidator noInputVal = new RequiredFieldValidator();
        noInputVal.setMessage("Input Required");
       emailField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            if (!newVal) {
                    emailField.getValidators().add(noInputVal);
                    emailField.validate();
            }
        });

        passwordField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            if (!newVal) {
                passwordField.getValidators().add(noInputVal);
                passwordField.validate();
            }
        });
    }

    private HBox getLoginButtons() {
        logInAsAStudent = new JFXButton("Log in as a student");
        logInAsAStudent.getStyleClass().add("login-asStudent");
        logInAsAStudent.setFont(new Font("Arial", 15));
        logInAsAStudent.setWrapText(true);

        logInAsATeacher = new JFXButton("Log in as a teacher");
        logInAsATeacher.getStyleClass().add("login-asTeacher");
        logInAsATeacher.setFont(new Font("Arial", 15));
        logInAsATeacher.setWrapText(true);

        HBox hBox = new HBox();

        hBox.getChildren().addAll(logInAsAStudent, logInAsATeacher);
        hBox.setSpacing(50);

        return hBox;
    }

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        borderPane.setPadding(new Insets(20));
       // addLabel();
        addInputFields();
        setTop();
        addTopButtonsOnAction();
        //validate if its empty
        checkIfEmpty(emailField, passwordField);
        checkEmail(emailField);
        addButtonsOnAction();
        hoverLogStudentButton();
        hoverLogTeacherButton();
        clearValidators();
        //delete it!!
        openTeacherDashboard();
    }


    private void addTopButtonsOnAction() {
        minimize.setOnMouseClicked(mouseEvent -> {
            Stage stage = (Stage)((Node)((EventObject) mouseEvent).
                    getSource()).getScene().getWindow();
            stage.setIconified(true);
        });
        close.setOnMouseClicked(mouseEvent -> Platform.exit());

    }

    private void setTop() {

        HBox hBox = new HBox();
        ImageView img1 = new ImageView("/sample/gui/images/minimize.png");
        img1.setFitHeight(15);
        img1.setFitWidth(15);
        minimize.setGraphic(img1);
        minimize.setId("minimizeButton");
        close.setId("closeButton");
        ImageView img2 = new ImageView("/sample/gui/images/close3.png");
        img2.setFitHeight(15);
        img2.setFitWidth(15);
        close.setGraphic(img2);
        hBox.setSpacing(15);
        hBox.setAlignment(Pos.TOP_RIGHT);
        hBox.getChildren().addAll( minimize, close);
        borderPane.setTop(hBox);
    }

    private void clearValidators() {
     /*   if(showMessage1 !=null && justMakeRed!=null) {
            emailField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
                if (t1)
                    emailField.getValidators().remove(justMakeRed);
                passwordField.getValidators().remove(showMessage1);
            });
            passwordField.focusedProperty().addListener((observableValue, aBoolean, t1) -> {
                if (t1)
                    emailField.getValidators().remove(justMakeRed);
                passwordField.getValidators().remove(showMessage1);
                //  justMakeRed.setMessage("aaaaa");
                //  showMessage1.setMessage("bbbbb");

            });
        }

      */
    }

    /**
     * method is used to handle log in buttons
     */
    private void addButtonsOnAction(){
        logInAsAStudent.setOnAction(actionEvent -> {
                //check if user exists in the system
                if(checkIfStudentExists()){
                    doAnimationAndShowInfo(LoggingState.STUDENTLOGGED);
                    logIn(LoggingState.STUDENTLOGGED);
                    closeLogIn(actionEvent);
                }
               else {
                    doAnimationAndShowInfo(LoggingState.STUDENTDENIED);
                }
        });
        //for the teacher its the same story
        logInAsATeacher.setOnAction(actionEvent -> {
            if(checkIfTeacherExists()){
                doAnimationAndShowInfo(LoggingState.TEACHERLOGGED);
                logIn(LoggingState.TEACHERLOGGED);
            }
            else
                doAnimationAndShowInfo(LoggingState.TEACHERDENIED);
        });

    }

    private void hoverLogStudentButton(){
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(100) , logInAsAStudent);
        fadeTransition.setFromValue(0.75);
        fadeTransition.setToValue(1);
        logInAsAStudent.setOnMouseEntered(mouseEvent -> {
            //fade in??
            fadeTransition.setRate(1.0);
            fadeTransition.play();
        });
        logInAsAStudent.setOnMouseExited(mouseEvent -> {
            fadeTransition.setRate(-2.0);
            fadeTransition.play();
        });
    }

    private void hoverLogTeacherButton() {
        FadeTransition fadeTransition = new FadeTransition(Duration.millis(100) , logInAsATeacher);
        fadeTransition.setFromValue(0.75);
        fadeTransition.setToValue(1);
        logInAsATeacher.setOnMouseEntered(mouseEvent -> {
            //fade in??
            fadeTransition.setRate(1.0);
            fadeTransition.play();
        });
        logInAsATeacher.setOnMouseExited(mouseEvent -> {
            fadeTransition.setRate(-2.0);
            fadeTransition.play();
        });
    }

    /**
     * Method is responsible for doing animation whenever
     * user fails to log in or logs in successfuly
     * @param loggingState
     */
    private void doAnimationAndShowInfo(LoggingState loggingState) {
       switch(loggingState){
           case STUDENTLOGGED:
           case TEACHERLOGGED: {
               // show logging animation
               break;
           }
           case STUDENTDENIED, TEACHERDENIED:{
               if(counter>3)
                   Animations.shakeStageAnimation(borderPane);
               Animations.shakeNodeAnimation(emailField);
               Animations.shakeNodeAnimation(passwordField);
               if(loggingState==LoggingState.STUDENTDENIED)
                   showInfo(LoggingState.STUDENT);
               else
                   showInfo(LoggingState.TEACHER);
               counter++;
               break;
           }
       }
    }

    /**
     * 1. Email exists in the db but incorrect password: incorrect email massege
     *
     * 2. Email doesn't exists in the db: show information that user doesn't exist
     * show the regarding messege for student/teacher
     * @param user
     */
    private void showInfo(LoggingState user) {
        switch (user) {
            case STUDENT: {
                //check if email exists
                if (loggingModel.emailExists(emailField.getText(), LoggingState.STUDENT)) {
                    //show the information that password is incorrect
                    showMessage("incorrect password", false);
                }
                else{
                        //show information that there is no such user
                        showMessage("such student doesn't exist", true);
                    }

                }break;
            case TEACHER: {
                if (loggingModel.emailExists(emailField.getText(), LoggingState.TEACHER)) {
                    //show the information that password is incorrect
                    showMessage("incorrect password", false);
                }
                else{
                    //show information that there is no such user
                    showMessage("such teacher doesn't exist", true);
                }

            }
            }
        }


    private void showMessage(String message, boolean userNotFound){
        showMessage1 = new ShowMessage(message);
        passwordField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            if (!newVal) {
                passwordField.getValidators().add(showMessage1);
                passwordField.validate();
            }
        });
        if(userNotFound){
            justMakeRed = new ShowMessage("");
           emailField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
                if (!newVal) {
                    emailField.getValidators().add(justMakeRed);
                   emailField.validate();
                }
            });
        }
    }
    
    /**
     * Log in student or teacher
     */
    @Override
    public void logIn(LoggingState state) {
        //open the dashboard view for the teacher or student
        switch(state){
            case STUDENTLOGGED:{
                //open the dashboard for the student
               openStudentDashboard();
                break;
            }
            case TEACHERLOGGED:{
                //open the dashboard for the teacher
              openTeacherDashboard();
                break;
            }
        }
    }

    private void openTeacherDashboard() {
        RootLayoutTeacherController rootLayoutTeacherController = new RootLayoutTeacherController();
        rootLayoutTeacherController.initRootLayout();
        rootLayoutTeacherController.goToTeacherDashboard();
    }

    private void openStudentDashboard(){
        RootLayoutStudentController rootLayoutStudentController =new RootLayoutStudentController();
        rootLayoutStudentController.initRootLayout();
        rootLayoutStudentController.goToStudentDashboard();
    }

    private void closeLogIn(ActionEvent actionEvent) {
        Node n = (Node) actionEvent.getSource();
        Stage stage = (Stage) n.getScene().getWindow();
        stage.close();
    }


    @Override
    public void saveUserInPreferences() {
        //To do
    }

    @Override
    public void unsaveUserInPreferences() {
        // to do
    }

    @Override
    public boolean checkIfStudentExists() {
        //look up in the static mock data
       return loggingModel.checkIfExist(LoggingState.STUDENT, emailField.getText(),
                passwordField.getText());
    }

    @Override
    public boolean checkIfTeacherExists() {
        //look up in the static mock data
        return loggingModel.checkIfExist(LoggingState.TEACHER, emailField.getText(),
                passwordField.getText());
    }

    public void setScene(Scene scene) {
        this.oldScene = scene;
    }


    public enum LoggingState{
        STUDENTLOGGED,
        STUDENTDENIED,
        TEACHERLOGGED,
        TEACHERDENIED,
        STUDENT,
        TEACHER
    }

}
