package by.committee.service;

import by.committee.beans.Course;
import by.committee.dao.AdminDAO;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class EnrollmentProcess {
    private AdminDAO dao = new AdminDAO();

    public void stopEnrollmentCampaign() {
        dao.insertRecord();
    }

    public void changeStudentStatus() {
        dao.changeStudentStatus();
    }

    public ArrayList<Course> getCourses(){
        HashMap<Integer, Course> m = dao.getCourses();
        ArrayList<Course> l = new ArrayList<>();
        
        for (Map.Entry<Integer,Course> entry : m.entrySet()) {
            l.add(entry.getValue());
        }
        return l;
    }
}
