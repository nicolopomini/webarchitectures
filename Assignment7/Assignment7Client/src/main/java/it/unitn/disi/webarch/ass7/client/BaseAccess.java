/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package it.unitn.disi.webarch.ass7.client;

import it.unitn.disi.webarch.ass7.client.interfaces.CourseBeanRemote;
import it.unitn.disi.webarch.ass7.client.interfaces.ExamBeanRemote;
import it.unitn.disi.webarch.ass7.client.interfaces.ProfessorBeanRemote;
import it.unitn.disi.webarch.ass7.client.interfaces.StudentBeanRemote;
import java.util.Properties;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingException;

/**
 *
 * @author pomo
 */
public class BaseAccess {
    private static final String LOOKUP_KEY = "webarch";
    protected Context context;
    protected ProfessorBeanRemote professorBean;
    protected StudentBeanRemote studentBean;
    protected CourseBeanRemote courseBean;
    protected ExamBeanRemote examBean;
    
    public BaseAccess() throws NamingException {
        Properties properties = new Properties();
        properties.put(Context.INITIAL_CONTEXT_FACTORY, "org.jboss.naming.remote.client.InitialContextFactory");
        properties.put(Context.URL_PKG_PREFIXES, "org.jboss.ejb.client.naming");
        properties.put(Context.PROVIDER_URL, "http-remoting://127.0.0.1:8080");
        properties.put("jboss.naming.client.ejb.context", true);
        properties.put(Context.SECURITY_PRINCIPAL, "uni");
        properties.put(Context.SECURITY_CREDENTIALS, "ciao"); 

        this.context = new InitialContext(properties);
        
        final String appName = "Assignment7Server-ear-1.0-SNAPSHOT";
        final String moduleName = "Assignment7Server-ejb-1.0-SNAPSHOT";
        // Professor bean
        String beanName = "ProfessorBean";
        String viewClassName = ProfessorBeanRemote.class.getName();
        String path = appName + "/" + moduleName + "/" + beanName + "!" + viewClassName;
        this.professorBean = (ProfessorBeanRemote) context.lookup(path);
        // Student bean
        beanName = "StudentBean";
        viewClassName = StudentBeanRemote.class.getName();
        path = appName + "/" + moduleName + "/" + beanName + "!" + viewClassName;
        this.studentBean = (StudentBeanRemote) context.lookup(path);
        // Course bean
        beanName = "CourseBean";
        viewClassName = CourseBeanRemote.class.getName();
        path = appName + "/" + moduleName + "/" + beanName + "!" + viewClassName;
        this.courseBean = (CourseBeanRemote) context.lookup(path);
        // Exam bean
        beanName = "ExamBean";
        viewClassName = ExamBeanRemote.class.getName();
        path = appName + "/" + moduleName + "/" + beanName + "!" + viewClassName;
        this.examBean = (ExamBeanRemote) context.lookup(path);
    }
    
    protected void free() throws NamingException {
        this.context.close();
    }
}
