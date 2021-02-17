package sample.dll;

import sample.be.Course;

import java.io.*;
import java.util.ArrayList;
import java.util.List;
/**
 * @author kuba
 */
public class CourseDAO {
    private static final String COURSES_SOURCE =
            "resources/courses.txt";

    public List<Course> getCoursesForATeacher(String teacherFName, String teacherSName){
        List<Course> courses = new ArrayList<>();
        try(BufferedReader br = new BufferedReader(new FileReader(new File(COURSES_SOURCE))))
        {
            boolean hasLines = true;
            while(hasLines){
                String line = br.readLine();
                if(line==null)
                    hasLines=false;
                if(hasLines && !line.isBlank() && line.contains(teacherFName)
                && line.contains(teacherSName))
                {

                    try{  courses.add(makeObjectFromString(line));}
                    catch (NumberFormatException e) {
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
        return courses;
    }

    private Course makeObjectFromString(String line) {
        String[] splittedLine = line.split(",");
        String firstName  = splittedLine[0];
        String secondName  = splittedLine[1];
        String course = splittedLine[2];
        String startT  = splittedLine[3];
        String endT  = splittedLine[4];
        String day  = splittedLine[5];
        Course course1 = new Course(firstName, secondName,
                course, startT, endT,day);
        return course1;
    }
}
