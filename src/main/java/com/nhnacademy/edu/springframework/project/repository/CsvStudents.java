package com.nhnacademy.edu.springframework.project.repository;

import com.nhnacademy.edu.springframework.project.service.Student;

import java.io.*;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;


public class CsvStudents implements Students {

    List<Student> studentList = new ArrayList<>();
    private static CsvStudents csvStudents = new CsvStudents();

    private CsvStudents() {
    }

    /** TODO 3 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Students getInstance() {
        return CsvStudents.csvStudents;
    }

    // TODO 7 : student.csv 파일에서 데이터를 읽어 클래스 멤버 변수에 추가하는 로직을 구현하세요.
    // 데이터를 적재하고 읽기 위해서, 적절한 자료구조를 사용하세요.
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

    /**
     * TODO 8 : students 데이터에 score 정보를 추가하세요.
     * @param scores
     */
    @Override
    public void merge(Collection<Score> scores) {
//        Score[] objects = (Score[]) scores.toArray();
        if (scores.size() != studentList.size()) {
            throw new RuntimeException("학생 리스트와 스코어 리스트의 길이가 다릅니다");
        }
        int idx = -1;
        for (Score next : scores) {
            studentList.get(++idx).setScore(next);
        }
    }
}
