package sample.dll;

import sample.gui.controller.LogInController;

import java.io.*;

public class UserDAO implements IUserDAO {
    private static final String TEACHERS_SOURCE =
            "resources/teachers.txt";
    private static final String STUDENTS_SOURCE =
            "resources/students.txt";

    @Override
    public boolean checkIfExistsInSystem(String email, String password,
                                         LogInController.LoggingState state) {
        String source;

        switch (state){
            case STUDENT:{
                source= STUDENTS_SOURCE;
                break;
            }
            case  TEACHER:{
                source = TEACHERS_SOURCE;
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }

        try(BufferedReader br = new BufferedReader(
                new FileReader(new File(source))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines && !line.isBlank())
                {
                    if(line.contains(email+","+password)){
                        return true;
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        return false;
    }
}
