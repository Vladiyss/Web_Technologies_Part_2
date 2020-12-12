package by.committee.controller;

import by.committee.beans.Course;
import by.committee.service.EnrollmentProcess;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;

public class AdminController extends HttpServlet {
    private EnrollmentProcess process = new EnrollmentProcess();

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        if(request.getParameter("submit") != null) {
        	process.stopEnrollmentCampaign();
        	process.changeStudentStatus();
        }
        request.getRequestDispatcher("/WEB-INF/lib/admin.jsp").forward(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        ArrayList<Course> list = process.getCourses();
        request.setAttribute("courses", list);
        request.getRequestDispatcher("/WEB-INF/lib/admin.jsp").forward(request, response);
    }
}
