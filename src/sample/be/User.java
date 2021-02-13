package sample.be;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.scene.image.ImageView;

public class User {
    private StringProperty firstName;
    private StringProperty secondName;
    private StringProperty filePathImg;
    private ImageView photo;

    public User(String firstName, String secondName, String filePathImg) {
        this.firstName = new SimpleStringProperty(this, "firstName", firstName);
        this.secondName = new SimpleStringProperty(this, "secondName", secondName);
        this.filePathImg = new SimpleStringProperty(this, "filePathImg", filePathImg);

        //if(filePathImg==null)
            //photo = new ImageView("/Images/default.png");
        //else
          //  String imgp = filePathImg.replace("src","").
             //       replace("\\", "/");
           // this.photo = new ImageView(imgp);
        }
        //this.photo.setFitHeight(20);
       // this.photo.setFitWidth(20);


    public String getFirstName() {
        return firstName.get();
    }

    public StringProperty firstNameProperty() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }

    public String getSecondName() {
        return secondName.get();
    }

    public StringProperty secondNameProperty() {
        return secondName;
    }

    public void setSecondName(String secondName) {
        this.secondName.set(secondName);
    }

    public String getFilePathImg() {
        return filePathImg.get();
    }

    public StringProperty filePathImgProperty() {
        return filePathImg;
    }

    public void setFilePathImg(String filePathImg) {
        this.filePathImg.set(filePathImg);
    }



}
