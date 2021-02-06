package sample.gui.controller;

import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXTextField;
import com.jfoenix.validation.RequiredFieldValidator;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.scene.text.Font;

import java.net.URL;
import java.util.ResourceBundle;

public class logInController implements Initializable {

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
     JFXButton logInButton = new JFXButton("JFoenix Button");
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
