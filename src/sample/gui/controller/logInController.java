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
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;
import sample.gui.model.LoggingModel;

import java.net.URL;
import java.util.ResourceBundle;

public class logInController implements Initializable {
    private LoggingModel loggingModel = new LoggingModel();

    @FXML
    private BorderPane borderPane;

    //add that items programatically
    private void addLabel(){
     Label label = new Label();
     label.setText("Log in");
     label.setFont(new Font("Arial", 55));
     label.setPadding(new Insets(15, 0, 0, 0));
     borderPane.setAlignment(label, Pos.TOP_CENTER);
     borderPane.setTop(label);
    }


    private void addInputFields(){
     JFXTextField emailField = new JFXTextField();
     emailField.setLabelFloat(true);
     emailField.setPromptText("insert email");
     //validate if its empty
     RequiredFieldValidator validator1 = new RequiredFieldValidator();
     validator1.setMessage("Input Required");
     //emailField.getValidators().add(validator1);
     //when we will change to another field
     //messege will be displayed
    // emailField.focusedProperty().addListener((o,oldVal,newVal)->{
    //  if(!newVal) emailField.validate();
    // });
     //validate if such email can even exist (we are not going to look it up
     // in the database cause for bigger amounts of records it will be too time consuming)
     RequiredFieldValidator emailValidator = new RequiredFieldValidator();
     emailValidator.setMessage("Not correct form of email");
     emailField.focusedProperty().addListener((observableValue, aBoolean, newVal) -> {
      if (!newVal) {
       emailField.getValidators().add(validator1);
       emailField.validate();
      }  if (!loggingModel.validEmail(emailField.getText())) {
       emailField.getValidators().add(emailValidator);
       emailField.validate();
      }
     });


     JFXTextField passwordField = new JFXTextField();
     passwordField.setLabelFloat(true);
     passwordField.setPromptText("insert password");


     VBox vBox = new VBox();
     vBox.setSpacing(15);
     //vBox.setPadding(new Insets(0, 150, 0, 150));
     vBox.getChildren().addAll(emailField, passwordField);
     //vBox.getChildren().add(passwordField);
     borderPane.setAlignment(vBox, Pos.CENTER);
     borderPane.setMargin(vBox, new Insets(50, 150, 0, 150) );
     borderPane.setCenter(vBox);
    }

    private void addButton(){
     JFXButton logInButton = new JFXButton("Log in");
     logInButton.getStyleClass().add("button-raised");
     logInButton.setFont(new Font("Arial", 35));
     borderPane.setMargin(logInButton, new Insets(0, 0, 40, 0));
     borderPane.setAlignment(logInButton, Pos.BOTTOM_CENTER);
     borderPane.setBottom(logInButton);
    }


    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        addLabel();
        addInputFields();
        addButton();
    }
}
