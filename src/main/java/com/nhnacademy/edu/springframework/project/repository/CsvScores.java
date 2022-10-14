package com.nhnacademy.edu.springframework.project.repository;

import org.springframework.stereotype.Component;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

@Component
public class CsvScores implements Scores {

    List<Score> scoreList = new ArrayList<>();

    public CsvScores() {

    }

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

    @Override
    public void clear() {
        scoreList.clear();
    }
}
