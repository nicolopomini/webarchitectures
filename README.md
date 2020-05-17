# Web Architectures

This repo contains my assignments of the _web architectures_ corse taken at University of Trento, during the A.Y 2018-2019.

## Assignment 1
Modify the simple web server so that all the urls that start with the token "process "
(e.g. http://localhost:8000/process)
launch an external process.
For instance,
http://localhost:8000/process/reverse?par1=string&par2=booleanvalue
should activate an (external) process that takes the par1 string.
If par2 is true, it returns the reversed string (e.g. ROMA -> AMOR).
If par2 is false, it checks if the string is a palindrome, and returns the answer
(true or false). (e.g. ROOR -> true, ROAR –> false) 

## Assignment 2
* Install Apache Web Server on your machine
* Configure the web server so that its cgi-bin functionality is enabled, and connect it with the URL localhost:/active
* Create a shell script called "lista" that lists all the file in the "active" directory
* Create a web page that contains two forms: one that submits some information using GET, the other that submits info using POST. The page must be served by the Apache. The action performed wll be to execute a shell script called "showParams" that will launch a jar file.
* The jar will be encapsulate a java program, which will have to produce an HTML page that prints the received HTTP command (GET or POST) and the passed parameters.

## Assignment 3
In the provided jar there are two classes.

Find out:
- What is their name?
- Where do they inherit from?

Only for the Mistery class:
- which methods are exposed by the class (ignore the inherited ones)?
- what is every public method doing? Run them in order to be able to guess what is their functionality and the meaning of their params.

## Assignment 4
Install the Tomcat Server on your machine

Write and deploy a web app which has:
* A home page with links to the time page and to the date page
* a filter that intercepts ALL pages except home, check if a valid cookie is available, and if not redirects to a login page
* a login page which checks username and password. Names and pwds are kept in an xml file having for each user the stucture described below.
* a time page showing the current time of the day
* a date page showing the current date
* a logout page which deletes the cookie
* all pages present a greeting to the user (showing his/her name), and a link to the logout page

XML Fragment:
```
<user>
  <name>user name</name>
  <password>user password</password>
</user>
```

## Assignment 5
"Document” is custom class (i.e. a class that you build), which contains a text (implemented as an ordered collection of strings). It has methods "addString" (which appenda a String at the end of the Document) and a method "toString" (which overrides the toString method of the Object class), useful to print out the whole Document.

A server shows a remote method “validate” with a parameter of type Document. The method adds the string "Validated on "+date in the Document.  

A client will create a Document, and ask the  server to add the timestamp. At the end the client prints the content of the Document, which of course will have to contain the validation.

NOTE: Before embarking in the project execution, I strongly suggest to follow the Oracle tutorial on Java RMI: http://docs.oracle.com/javase/tutorial/rmi/overview.html

Also the following tutorial may be useful: https://www.tutorialspoint.com/java_rmi/index.htm.

## Assignment 6
Install Hibernate.
Create an application  (you choose the applictive domain) which creates objects of two classes with one relation among them (e.g.: student and exam, customer and order...). A number oof these object have to be saved on a Database.

Another application runs a query to find and print saved object (e.g. all exams given by a student after a given date).

Implement it using only Java SE and Hibernate (not EJB). 

Deliver a report and the source code (including configuration files).

## Assignment 7
Install Wildfly.

Write an enterprise application which models the following in a server application:

- Professors and Students are Persons.

A Person is characterized by Name and Surname.

A Student has a Matriculation Number

A Professor teaches a course (one to one relationship)

A student takes N courses, a course is taken by M students (M:N relationship).

An exam is relative to a course, and has a date.

Students can enrol in cousres.

Students enrolled in a course can enrol in an exam.

Professors evaluate exams of their course.

Deliver a report and the source code.

The following ciient functionalities exist:
1) set up:
- create student(s)
- create teacher(s)
- create course(s)
- create exams(s)
- show existing data

2) enrol to course:
- add student(s) to a course
- print list of enrolled students

3) enrol to exam:
- add student(s) to an exam
- print list of enrolled students

4) grade an exam:
- add grade for enrolled student(s)
- print list of assigned grades

The server has to be implemented using EJB technology
The client bill be a java app connecting to the EJB server
EJB patterns have to be used.

## Assignment 8
Write a Spring-based web application doing the same things as in previous assignment.
This time the interface will be a web interface, and not a command line.

