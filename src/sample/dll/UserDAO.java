package sample.dll;

import sample.be.Student;
import sample.gui.controller.LogInController;

import java.io.*;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
/**
 * @author kuba
 */
public class UserDAO implements IUserDAO {
    private static final String TEACHERS_SOURCE =
            "resources/teachers.txt";
    private static final String STUDENTS_SOURCE =
            "resources/students.txt";
    private static final String ATTENDANCE_SOURCE =
            "resources/attendanceRecords.txt";

    @Override
    public boolean checkIfExistsInSystem(String email, String password,
                                         LogInController.LoggingState state) {
        String source;

        switch (state) {
            case STUDENT: {
                source = STUDENTS_SOURCE;
                break;
            }
            case TEACHER: {
                source = TEACHERS_SOURCE;
                break;
            }

            default:
                throw new IllegalStateException("Unexpected value: " + state);
        }

        try (BufferedReader br = new BufferedReader(
                new FileReader(new File(source)))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = br.readLine();
                if (line == null)
                    hasLines = false;
                if (hasLines && !line.isBlank()) {
                    if (line.equals(email + ", " + password))
                        return true;
                }
            }
            return false;

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return false;
    }

    public int getAttendance(String fName, String sName, boolean onlyToday) {
        int presentDays = 0;
        int absDays = 0;

        try (BufferedReader br = new BufferedReader(new FileReader(new File(ATTENDANCE_SOURCE)))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = br.readLine();
                if (line == null)
                    hasLines = false;
                if (hasLines && !line.isBlank()) {

                    try {
                        //  allStudents.add(makeObjectFromString(line));
                        if (!onlyToday) {
                            if (line.toLowerCase().contains(fName.toLowerCase()) &&
                                    line.toLowerCase().contains(sName.toLowerCase())) {
                                if (line.contains("present"))
                                    presentDays++;
                                else
                                    absDays++;
                            }
                        } else {
                            //check date
                            Calendar calendar = Calendar.getInstance();
                            Date c = calendar.getTime();
                            SimpleDateFormat df = new SimpleDateFormat("dd/MM/yyyy");

                            String current_Date = df.format(c);

                            calendar.add(Calendar.DAY_OF_YEAR, 0);
                            c = calendar.getTime();
                            String today_Date = df.format(c);

                            if (line.contains(today_Date) && line.contains("present"))
                                return 2; // if its true
                            if (line.contains(today_Date) && line.contains("absent"))
                                return -2; //if its not true
                            else
                                return 0;


                        }

                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("Number format exception: " + line);
                    }
                }
            }

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        if (presentDays == 0 && absDays == 0)
            return -1;
        else
            return presentDays / (presentDays + absDays);

    }


    public List<Student> getAllStudents() {
        List<Student> allStudents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(STUDENTS_SOURCE)))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = br.readLine();
                if (line == null)
                    hasLines = false;
                if (hasLines && !line.isBlank()) {


                    try {
                        allStudents.add(makeObjectFromString(line));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("Number format exception: " + line);
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

    private Student makeObjectFromString(String line) {
        String[] splittedLine = new String[8];
        splittedLine = line.split(",");
        String email = splittedLine[0];
        String password = splittedLine[1];
        String firstName = splittedLine[2];
        String secondName = splittedLine[3];
        String filePath = splittedLine[4];
        String course = splittedLine[5];
        int sem = Integer.parseInt(splittedLine[6]);

        Student student = new Student(firstName, secondName, filePath, course, sem, getAttendance(firstName, secondName, true),
                getAttendance(firstName, secondName, false));
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

    public List<Student> getStudents(String t1) {
        List<Student> allStudents = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(new File(STUDENTS_SOURCE)))) {
            boolean hasLines = true;
            while (hasLines) {
                String line = br.readLine();
                if (line == null)
                    hasLines = false;
                if (hasLines && !line.isBlank() && line.contains(t1)) {

                    try {
                        allStudents.add(makeObjectFromString(line));
                    } catch (NumberFormatException e) {
                        e.printStackTrace();
                        System.out.println("Number format exception: " + line);
                    }
                }
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return allStudents;
    }
}
