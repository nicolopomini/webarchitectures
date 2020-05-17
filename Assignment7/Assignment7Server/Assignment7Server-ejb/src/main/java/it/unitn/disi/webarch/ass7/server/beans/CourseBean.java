/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.server.beans;

import it.unitn.disi.webarch.ass7.client.interfaces.CourseBeanRemote;
import it.unitn.disi.webarch.ass7.server.dao.CourseDAO;
import it.unitn.disi.webarch.ass7.server.entities.Course;
import it.unitn.disi.webarch.ass7.server.entities.Student;
import java.util.ArrayList;
import java.util.Collection;
import javax.ejb.Stateless;

/**
 *
 * @author pomo
 */
@Stateless
public class CourseBean implements CourseBeanRemote {

    @Override
    public String addCourse(int professorId, String name) {
        CourseDAO dao = new CourseDAO();
        return dao.addCourse(professorId, name).toString();
    }

    @Override
    public String getAllCourses() {
        CourseDAO dao = new CourseDAO();
        ArrayList<Course> all = dao.getAll();
        String rtr = "All courses:\n";
        for(Course c: all) {
            rtr += c.toString() + "\n";
        }
        return rtr;
    }

    @Override
    public boolean enrollInCourse(int courseId, int studentId) {
        CourseDAO dao = new CourseDAO();
        return dao.enrollStudentinCourse(courseId, studentId);
    }

    @Override
    public String getEnrolledStudents(int courseId) {
        CourseDAO dao = new CourseDAO();
        Collection<Student> s = dao.getEnrolledStudents(courseId);
        if(s == null || s.isEmpty())
            return "No students are enrolled in course " + courseId;
        else {
            String rtr = "Students enrolled into course " + courseId + ":\n";
            for(Student c: s) {
                rtr += c.toString() + "\n";
            }
            return rtr;
        }
    }
    
    
    
}
