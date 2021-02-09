package sample.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import javafx.stage.Stage;
import sample.gui.model.LoggingModel;

import java.io.IOException;
import java.net.URL;
import java.util.ResourceBundle;


public class LogInController implements Initializable, ILogIn{
    private LoggingModel loggingModel = new LoggingModel();

    @FXML
    private BorderPane borderPane;
    private JFXButton logInAsAStudent;
    private JFXButton logInAsATeacher;
    private JFXTextField emailField;
    private  JFXTextField passwordField;

    //add that items programatically
    private void addLabel() {
        Label label = new Label();
        label.setText("Log in");
        label.setFont(new Font("Arial", 55));
        label.setPadding(new Insets(15, 0, 0, 0));
        borderPane.setAlignment(label, Pos.TOP_CENTER);
        borderPane.setTop(label);
    }


    private void addInputFields() {
         emailField = new JFXTextField();
        emailField.setLabelFloat(true);
        emailField.setPromptText("insert email");

       passwordField = new JFXTextField();
        passwordField.setLabelFloat(true);
        passwordField.setPromptText("insert password");

        //validate if its empty
        checkIfEmpty(emailField, passwordField);

        VBox vBox = new VBox();
        vBox.setSpacing(40);
        //vBox.setPadding(new Insets(0, 150, 0, 150));
        vBox.getChildren().addAll(emailField, passwordField, getLoginButtons());
        //vBox.getChildren().add(passwordField);
        borderPane.setAlignment(vBox, Pos.CENTER);
        borderPane.setMargin(vBox, new Insets(50, 150, 0, 150));
        borderPane.setCenter(vBox);
    }

    private void checkIfEmpty(JFXTextField emailField, JFXTextField passwordField) {
        RequiredFieldValidator validator1 = new RequiredFieldValidator();
        validator1.setMessage("Input Required");
        RequiredFieldValidator emailValidator = new RequiredFieldValidator();
        emailValidator.setMessage("Not correct form of email");
        emailField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            if (!newVal) {
                if (!emailField.getText().isEmpty()
                        && !loggingModel.validEmail(emailField.getText())) {
                    emailField.getValidators().add(emailValidator);
                    emailField.validate();
                } else {
                    emailField.getValidators().add(validator1);
                    emailField.validate();
                }

            }
        });
        passwordField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
            if (!newVal) {
                passwordField.getValidators().add(validator1);
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
        addLabel();
        addInputFields();
        addButtonsOnAction();
    }

    /**
     * method is used to handle log in buttons
     */
    private void addButtonsOnAction(){
        logInAsAStudent.setOnAction(actionEvent -> {
                //check if user exists in the system
                if(checkIfStudentExists()){
                    doAnimation(LoggingState.STUDENTLOGGED);
                    logIn(LoggingState.STUDENTLOGGED);
                }
               else
                   doAnimation(LoggingState.STUDENTDENIED);
        });
        //for the teacher its the same story
        logInAsATeacher.setOnAction(actionEvent -> {
            if(checkIfTeacherExists()){
                doAnimation(LoggingState.TEACHERLOGGED);
                logIn(LoggingState.TEACHERLOGGED);
            }
            else
                doAnimation(LoggingState.TEACHERDENIED);
        });

    }

    /**
     * Method is responsible for doing animation whenever
     * user fails to log in or logs in successfuly
     * @param studentlogged
     */
    private void doAnimation(LoggingState studentlogged) {

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
        RootLayoutController rootLayoutController = new RootLayoutController();
        rootLayoutController.initRootLayout();
        rootLayoutController.goToTeacherDashboard();
    }

    private void openStudentDashboard(){
        RootLayoutController rootLayoutController = new RootLayoutController();
        rootLayoutController.initRootLayout();
        //rootLayoutController.goToStudentDashboard();
    }

    @Override
    public void saveUserInPreferences() {

    }

    @Override
    public void unsaveUserInPreferences() {

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


    public enum LoggingState{
        STUDENTLOGGED,
        STUDENTDENIED,
        TEACHERLOGGED,
        TEACHERDENIED,
        STUDENT,
        TEACHER
    }
    //you can try pressing alt insert in your application
}
