package sample.bll;


import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class Validations {

    public boolean validateInput(String regexPattern, String input){
        Pattern pattern = Pattern.compile(regexPattern);
        Matcher matcher = pattern.matcher(input);
      //  return matcher.matches();
        return false;
    }
/*
    public boolean validateEmail(String emailToValidate){
        return  validateInput(regexEmail, emailToValidate);
    }


 */


}
