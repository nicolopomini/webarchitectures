/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.client;

import java.util.Scanner;
import javax.naming.NamingException;

/**
 *
 * @author pomo
 */
public class Client extends BaseAccess{
    public Client() throws NamingException {
        super();
    }
    public static void main(String[] a) {
        Client manager = null;
        try {
            manager = new Client();
            Scanner in = new Scanner(System.in);
            String input = "";
            while(!input.equals("q")) {
                manager.printCommands();
                input = in.nextLine();
                if(input.equals("a")) { // new student
                    String name, surname, matricola;
                    System.out.println("Insert student's name:");
                    name = in.nextLine();
                    System.out.println("Insert student's surname:");
                    surname = in.nextLine();
                    System.out.println("Insert student's matricola:");
                    matricola = in.nextLine();
                    System.out.println("Inserted: " + manager.studentBean.addStudent(name, surname, matricola));
                } else if(input.equals("b")) {  // add professor
                    String name, surname;
                    System.out.println("Insert professor's name:");
                    name = in.nextLine();
                    System.out.println("Insert professor's surname:");
                    surname = in.nextLine();
                    System.out.println("Inserted: " + manager.professorBean.addProfessor(name, surname));
                } else if(input.equals("c")) {  // add course
                    int profId;
                    String name;
                    System.out.println("Insert course name:");
                    name = in.nextLine();
                    System.out.println("Insert professor ID:");
                    profId = in.nextInt();
                    in.nextLine();
                    System.out.println("Inserted: " + manager.courseBean.addCourse(profId, name));
                } else if(input.equals("d")) {  // add exam
                    int courseId;
                    String date;
                    System.out.println("Insert course ID:");
                    courseId = in.nextInt();
                    in.nextLine();
                    System.out.println("Insert exam date:");
                    date = in.nextLine();
                    System.out.println("Inserted: " + manager.examBean.addExam(courseId, date));
                } else if(input.equals("e")) {  // print students
                    System.out.println(manager.studentBean.getAllStudents());
                } else if(input.equals("f")) {  // print professors
                    System.out.println(manager.professorBean.getAllProfessors());
                } else if(input.equals("g")) {  // print courses
                    System.out.println(manager.courseBean.getAllCourses());
                } else if(input.equals("h")) {  // print exams
                    System.out.println(manager.examBean.getAllExams());
                } else if(input.equals("i")) {  // enroll to course
                    int courseId, studentId;
                    System.out.println("Insert course ID:");
                    courseId = in.nextInt();
                    in.nextLine();
                    System.out.println("Insert student ID:");
                    studentId = in.nextInt();
                    in.nextLine();
                    if(manager.courseBean.enrollInCourse(courseId, studentId))
                        System.out.println("Student " + studentId + " enrolled in course " + courseId);
                    else
                        System.err.println("Error in enrolling student to the course");
                } else if(input.equals("j")) {  // print enrolled to course
                    int courseId;
                    System.out.println("Insert course ID:");
                    courseId = in.nextInt();
                    in.nextLine();
                    System.out.println(manager.courseBean.getEnrolledStudents(courseId));
                } else if(input.equals("k")) {  // enroll to exam
                    int examId, studentId;
                    System.out.println("Insert exam ID:");
                    examId = in.nextInt();
                    in.nextLine();
                    System.out.println("Insert student ID:");
                    studentId = in.nextInt();
                    in.nextLine();
                    if(manager.examBean.enrollToExam(examId, studentId))
                        System.out.println("Student " + studentId + " enrolled in exam " + examId);
                    else
                        System.err.println("Error in enrolling student to the course");
                } else if(input.equals("l")) {  // print enrolled to exam
                    int courseId;
                    System.out.println("Insert course ID:");
                    courseId = in.nextInt();
                    in.nextLine();
                    System.out.println(manager.examBean.getEnrolledStudents(courseId));
                } else if(input.equals("m")) {  // give mark
                    int examId, studentId;
                    String mark;
                    System.out.println("Insert exam ID:");
                    examId = in.nextInt();
                    in.nextLine();
                    System.out.println("Insert student ID:");
                    studentId = in.nextInt();
                    in.nextLine();
                    System.out.println("Insert mark:");
                    mark = in.nextLine();
                    if(manager.examBean.addMark(examId, studentId, mark))
                        System.out.println("Mark assigned");
                    else
                        System.err.println("Error in assigning the mark");
                } else if(input.equals("n")) {  // print marks
                    int examId;
                    System.out.println("Insert exam ID:");
                    examId = in.nextInt();
                    in.nextLine();
                    System.out.println(manager.examBean.getMarksForExam(examId));
                } else if(!input.equals("q")) {  // error
                    System.err.println("Unrecognized command");
                } else
                    System.out.println("Bye!");
                Thread.sleep(1000);
            }
        } catch (NamingException | InterruptedException ex) {
            System.err.println("Something went wrong");
        } finally {
            try {
                if(manager != null) {
                    manager.free();
                }
            } catch (NamingException ex) {}
        }
    }
    private void printCommands() {
        System.out.println("Please select an action to perform\n");
        System.out.println("SET UP");
        System.out.println("\ta) Insert a new Student");
        System.out.println("\tb) Insert a new Professor");
        System.out.println("\tc) Insert a new Course");
        System.out.println("\td) Insert a new Exam");
        System.out.println("\nPRINT EXISTING DATA");
        System.out.println("\te) Get all students");
        System.out.println("\tf) Get all professors");
        System.out.println("\tg) Get all courses");
        System.out.println("\th) Get all exams\n");
        System.out.println("COURSE ENROLLMENT");
        System.out.println("\ti) Enroll a student to a course");
        System.out.println("\tj) Print enrolled students to a course\n");
        System.out.println("EXAM ENROLLMENT");
        System.out.println("\tk) Enroll a student to an exam");
        System.out.println("\tl) Print enrolled students to an exam\n");
        System.out.println("EXAM GRADING");
        System.out.println("\tm) Mark an exam");
        System.out.println("\tn) Print marks assigned to an exam\n");
        System.out.println("\nq) QUIT");
    }
}
