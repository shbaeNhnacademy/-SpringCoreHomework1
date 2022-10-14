package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;
import org.springframework.stereotype.Component;

import java.io.*;
import java.util.*;


@Component
public class CsvStudents implements Students {

    List<Student> studentList = new ArrayList<>();


    public CsvStudents() {
    }

    @Override
    public void load() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (
                InputStream inputStream = classLoader.getResource("data/student.csv").openStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line=null;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                studentList.add(new Student(Integer.parseInt(split[0]), split[1]));
            }
        } catch (FileNotFoundException e) {
            System.out.println("CsvScores.load - FileNotFoundException " + e);
            throw new RuntimeException(e);
        } catch (IOException e) {
            System.out.println("CsvScores.load - IOException " + e);
            throw new RuntimeException(e);
        }
    }

    @Override
    public Collection<Student> findAll() {
        if (this.studentList.isEmpty()) {
            throw new RuntimeException("반환할 CSV 학생명부가 없습니다");
        }
        return studentList;
    }


    @Override
    public void merge(Collection<Score> scores) {
        if (scores.size() != studentList.size()) {
            throw new InputMismatchException("학생 리스트와 스코어 리스트의 길이가 다릅니다");
        }
        int idx = -1;
        for (Score next : scores) {
            Student student = studentList.get(++idx);
            if (student.getSeq() == next.getStudentSeq()) {
                student.setScore(next);
            }
        }
    }

    @Override
    public void clear() {
        studentList.clear();
    }
}
