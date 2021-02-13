package sample.dll;

import sample.be.Student;
import sample.gui.controller.LogInController;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

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
                    if(line.equals(email+", "+ password))
                        return true;
                }
            }return false;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public List<Student> getAllStudents(){
        List<Student> allStudents = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(STUDENTS_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines && !line.isBlank())
                {


                    try{  allStudents.add(makeObjectFromString(line));} catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("Number format exception: "+ line);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allStudents;

    }
    private Student makeObjectFromString(String line)
    {
        String[] splittedLine = new String[8];
       splittedLine = line.split(",");
        String email = splittedLine[0];
        String password = splittedLine[1];
       String firstName = splittedLine[2];
       String secondName = splittedLine[3];
       String filePath = splittedLine[4];
       String course = splittedLine[5];
       int sem = Integer.parseInt(splittedLine[6]);

       Student student = new Student(firstName, secondName, filePath, course, sem);
        return student;
    }

    @Override
    public boolean emailExists(String email, LogInController.LoggingState user) {
       /* String source;

        switch (user){
            case STUDENT:{
                source= STUDENTS_SOURCE;
                break;
            }
            case  TEACHER:{
                source = TEACHERS_SOURCE;
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + user);
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
                    if(line.matches(email+", " +  ".+"))
                        return true;
                }
            }return false;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;

        */
        return false;
    }
}
