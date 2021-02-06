package sample.bll;

public class BllManager implements BllFacade{
    Validations validations = new Validations();

    @Override
    public boolean validEmail(String insertedEmail) {
        String regexEmail = "^[_A-Za-z0-9-+]+(\\.[_A-Za-z0-9-]+)*@"
                + "[A-Za-z0-9-]+(\\.[A-Za-z0-9]+)*(\\.[A-Za-z]{2,})$";
        return validations.validateInput(regexEmail, insertedEmail);
    }
}
