package com.launchdarkly.assignment.datastore;

import org.apache.commons.lang3.tuple.MutablePair;
import org.apache.commons.lang3.tuple.Pair;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//lets make it singleton
public class ScoreCollection {
  private final int MAX_ROWS = 1000;
  private final Map<String, Results> studentResultsMap = new HashMap<>();
  private final Map<Integer, Results> examResultsMap = new HashMap<>();

  private static ScoreCollection scoreCollection = null;
  private ScoreCollection(){
  }

  public static ScoreCollection getInstance(){
    if(null == scoreCollection)
      scoreCollection = new ScoreCollection();

    return scoreCollection;
  }

  public boolean add(Score score){

    if(studentResultsMap.size() == MAX_ROWS)
      return false;

    if(null == score)
      return true;

    populateExamMap(score);
    populateStudentMap(score);

    return true;
  }

  public Set<String> getAllStudents(){
    return studentResultsMap.keySet();
  }

  public Set<Integer> getAllExams(){
    return examResultsMap.keySet();
  }

  public List<Score> getStudentResults(String studentID){
    if(!studentResultsMap.containsKey(studentID))
      return null;

    return studentResultsMap.get(studentID).getScoreList();
  }

  public double getStudentAvgScore(String studentID){
    if(!studentResultsMap.containsKey(studentID))
      return 0.0;

    return studentResultsMap.get(studentID).getAvg();
  }

  public List<Score> getExamResults(int exam){
    if(!examResultsMap.containsKey(exam))
      return null;

    return examResultsMap.get(exam).getScoreList();
  }

  public double getExamAvgScore(int exam){
    if(!examResultsMap.containsKey(exam))
      return 0.0;

    return examResultsMap.get(exam).getAvg();
  }

  private void populateStudentMap(Score score){
    String studentID = score.getStudentID();
    if(studentResultsMap.containsKey(studentID)){
      var studentResults = studentResultsMap.get(studentID);
      studentResults.add(score);

    } else{
      Results results = new Results();
      results.add(score);
      studentResultsMap.put(studentID, results);
    }
  }

  private void populateExamMap(Score score){
    int exam = score.getExam();
    if(examResultsMap.containsKey(exam)){
      var examResults = examResultsMap.get(exam);
      examResults.add(score);

    } else{
      Results results = new Results();
      results.add(score);
      examResultsMap.put(exam, results);
    }
  }

  private static class Results{
    private final List<Score> scoreList = new ArrayList<>();
    private double sum = 0.0;

    public void add(Score score){
      scoreList.add(score);
      sum+= score.getScore();
    }

    public List<Score> getScoreList() {
      return scoreList;
    }

    public double getSum() {
      return sum;
    }

    private double getAvg(){
      return sum/scoreList.size();
    }
  }
}
