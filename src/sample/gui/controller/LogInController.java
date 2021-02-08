package sample.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.gui.model.LoggingModel;

import java.net.URL;
import java.util.ResourceBundle;

public class LogInController implements Initializable, ILogIn{
    private LoggingModel loggingModel = new LoggingModel();

    @FXML
    private BorderPane borderPane;
    private JFXButton logInAsAStudent;
    private JFXButton logInAsATeacher;

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
        JFXTextField emailField = new JFXTextField();
        emailField.setLabelFloat(true);
        emailField.setPromptText("insert email");

        JFXTextField passwordField = new JFXTextField();
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
    }

    /**
     * method is used to handle log in buttons
     */
    private void addButtonsOnAction(){
        logInAsAStudent.setOnAction(actionEvent -> {

        });
    }

    /**
     * Log in student or teacher
     */
    @Override
    public void logIn() {

    }

    @Override
    public void saveUserInPreferences() {

    }

    @Override
    public void unsaveUserInPreferences() {

    }

    @Override
    public boolean checkIfStudentExists() {
        
    }

    @Override
    public boolean checkIfTeacherExists() {

    }


    public enum LoggingState{
        STUDENTLOGGED,
        STUDENTDENIED,
        TEACHERLOGGED,
        TEACHERDENIED
    }
}
