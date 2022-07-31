package com.launchdarkly.assignment.datastore;

import com.launchdarkly.assignment.response.ExamResults;
import com.launchdarkly.assignment.response.StudentTestResults;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;

//lets make it singleton
public class ScoreCollection {
  private final int MAX_ROWS = 1000;
  private final Map<String, StudentTestResults> studentResultsMap = new HashMap<>();
  private final Map<Integer, ExamResults> examResultsMap = new HashMap<>();

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

  public StudentTestResults getStudentResults(String studentId){
    if(!studentResultsMap.containsKey(studentId))
      return null;

    return studentResultsMap.get(studentId);
  }

  public double getStudentAvgScore(String studentId){
    if(!studentResultsMap.containsKey(studentId))
      return 0.0;

    return studentResultsMap.get(studentId).getAvgScore();
  }

  public ExamResults getExamResults(int exam){
    if(!examResultsMap.containsKey(exam))
      return null;

    return examResultsMap.get(exam);
  }

  public double getExamAvgScore(int exam){
    if(!examResultsMap.containsKey(exam))
      return 0.0;

    return examResultsMap.get(exam).getAvgScore();
  }

  private void populateStudentMap(Score score){
    String studentId = score.getStudentId();
    if(studentResultsMap.containsKey(studentId)){
      StudentTestResults studentResults = studentResultsMap.get(studentId);
      studentResults.add(score);

    } else{
      StudentTestResults studentResults = new StudentTestResults(studentId, score);
      studentResultsMap.put(studentId, studentResults);
    }
  }

  private void populateExamMap(Score score){
    int exam = score.getExam();
    if(examResultsMap.containsKey(exam)){
      var examResults = examResultsMap.get(exam);
      examResults.add(score);

    } else{
      ExamResults examResults = new ExamResults(exam, score);
      examResultsMap.put(exam, examResults);
    }
  }

  /*private static class Results{
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
  }*/
}
