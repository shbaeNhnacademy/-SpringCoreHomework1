package com.nhnacademy.edu.springframework.project.service;

import org.junit.jupiter.api.Test;

import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

class StudentServiceTest {

    @Test
    void getPassedStudents() {
        DefaultStudentService studentService = new DefaultStudentService();
        Collection<Student> passedStudents = studentService.getPassedStudents();
        System.out.println("passedStudents = " + passedStudents);
    }

    @Test
    void getStudentsOrderByScore() {
    }
}
