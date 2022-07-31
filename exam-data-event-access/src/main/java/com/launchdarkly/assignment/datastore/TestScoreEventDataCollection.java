package com.launchdarkly.assignment.datastore;

import com.launchdarkly.assignment.response.types.ExamResults;
import com.launchdarkly.assignment.response.types.StudentTestResults;

import java.util.HashMap;
import java.util.Map;
import java.util.Set;

//Singleton class - Event Data Storage
public final class TestScoreEventDataCollection {
  private static final int MAX_ROWS = 1000;
  private static TestScoreEventDataCollection scoreCollection = null;
  private final Map<String, StudentTestResults> studentResultsMap = new HashMap<>();
  private final Map<Integer, ExamResults> examResultsMap = new HashMap<>();

  public static TestScoreEventDataCollection getInstance(){
    if(null == scoreCollection)
      scoreCollection = new TestScoreEventDataCollection();

    return scoreCollection;
  }

  public boolean add(TestScoreEventData testScoreEventData){
    //Don't want to keep filling
    //Can implement LRU cache to implement eviction policy for outdated event data
    if(studentResultsMap.size() == MAX_ROWS)
      return false;

    if(null == testScoreEventData)
      return true;

    populateExamMap(testScoreEventData);
    populateStudentMap(testScoreEventData);

    return true;
  }

  public Set<String> getAllStudents(){
    return studentResultsMap.keySet();
  }

  public Set<Integer> getAllExams(){
    return examResultsMap.keySet();
  }

  public StudentTestResults getStudentResults(String studentId){
    return studentResultsMap.get(studentId);
  }

  public ExamResults getExamResults(int exam){
    return examResultsMap.get(exam);
  }

  private void populateStudentMap(TestScoreEventData testScoreEventData){
    String studentId = testScoreEventData.getStudentId();
    if(studentResultsMap.containsKey(studentId)){
      StudentTestResults studentResults = studentResultsMap.get(studentId);
      studentResults.add(testScoreEventData);

    } else{
      StudentTestResults studentResults = new StudentTestResults(studentId, testScoreEventData);
      studentResultsMap.put(studentId, studentResults);
    }
  }

  private void populateExamMap(TestScoreEventData testScoreEventData){
    int exam = testScoreEventData.getExam();
    if(examResultsMap.containsKey(exam)){
      var examResults = examResultsMap.get(exam);
      examResults.add(testScoreEventData);

    } else{
      ExamResults examResults = new ExamResults(exam, testScoreEventData);
      examResultsMap.put(exam, examResults);
    }
  }
}
