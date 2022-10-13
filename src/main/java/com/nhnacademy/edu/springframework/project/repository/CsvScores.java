package com.nhnacademy.edu.springframework.project.repository;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class CsvScores implements Scores {

    List<Score> scoreList = new ArrayList<>();
    private static CsvScores csvScores = new CsvScores();
    private CsvScores(){}

    /** TODO 2 :
     * Java Singleton 패턴으로 getInstance() 를 구현하세요.
     **/
    public static Scores getInstance() {
        return CsvScores.csvScores;
    }

    // TODO 5 : score.csv 파일에서 데이터를 읽어 멤버 변수에 추가하는 로직을 구현하세요.
    @Override
    public void load() {
        ClassLoader classLoader = this.getClass().getClassLoader();
        try (
                InputStream inputStream = classLoader.getResource("data/score.csv").openStream();
                BufferedReader reader=new BufferedReader(new InputStreamReader(inputStream));
        ) {
            String line=null;
            while ((line = reader.readLine()) != null) {
                String[] split = line.split(",");
                scoreList.add(new Score(Integer.parseInt(split[0]), Integer.parseInt(split[1])));
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
    public List<Score> findAll() {
        if (this.scoreList.isEmpty()) {
            throw new RuntimeException("반환할 CSV 스코어 리스트가 없습니다");
        }
        return scoreList;
    }
}
