package by.committee.dao;

import by.committee.beans.Course;
import by.committee.beans.Faculty;
import org.apache.log4j.Logger;
import org.apache.log4j.PropertyConfigurator;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import static by.committee.service.Utilities.getExceptionStackTrace;

public class CourseDAO {

    private Logger logger;

    public CourseDAO() {
        logger = Logger.getLogger(this.getClass());
        PropertyConfigurator.configure(this.getClass().getClassLoader().getResource("log4j.properties"));
    }

    private String ALL_COURSES_SELECT_QUERY = "SELECT course.*, " + "       faculty.name as faculty " +
    		"FROM course\n" + "         JOIN faculty ON faculty.idfaculty = course.faculty_idfaculty\n" + "GROUP BY course.idcourse;";

    public ArrayList<Faculty> getFacultiessAndItsCoursesNames() {
        ArrayList<Faculty> faculties = null;
        try(Connection connection = ConnectionPool.getInstance().connect();
            PreparedStatement st = connection.prepareStatement(ALL_COURSES_SELECT_QUERY)) {
            ResultSet set = st.executeQuery();
            HashMap<String,Faculty> map = new HashMap<>();
            while (set.next()) {
                String key = set.getString("faculty");

                if(!map.containsKey(key)){
                    map.put(key,new Faculty(key,set.getInt("faculty_idfaculty")));
                }
                Faculty d = map.get(key);
                d.addCourse(new Course( set.getInt("idcourse"),  set.getString("name"), set.getInt("enrollment_plan_budget"),
                		set.getInt("enrollment_plan_fee") ));
                map.replace(key,d);
            }
            faculties = new ArrayList<>();
            for(Map.Entry<String,Faculty> el : map.entrySet()){
            	faculties.add(el.getValue());
            }
        } catch (SQLException e) {
            logger.error(getExceptionStackTrace(e));
        }
        return faculties;
    }
}