package com.launchdarkly.assignment.operations;

import com.launchdarkly.assignment.datastore.TestScoreEventDataCollection;
import com.launchdarkly.assignment.response.types.ExamResults;
import com.launchdarkly.assignment.response.types.Exams;
import com.launchdarkly.assignment.response.types.StudentTestResults;
import com.launchdarkly.assignment.response.types.Students;

import java.util.Set;

public final class TestScoreOperations {

  private final TestScoreEventDataCollection eventDataCollection = TestScoreEventDataCollection.getInstance();

  public Set<String> getStudents(){
    return new Students(eventDataCollection.getAllStudents()).getStudents();
  }

  public Set<Integer> getExams(){
    return new Exams(eventDataCollection.getAllExams()).getExams();
  }

  public StudentTestResults getStudentResults(String studentId){
    return eventDataCollection.getStudentResults(studentId);
  }

  public ExamResults getExamResults(int examNumber){
    return  eventDataCollection.getExamResults(examNumber);
  }
}
