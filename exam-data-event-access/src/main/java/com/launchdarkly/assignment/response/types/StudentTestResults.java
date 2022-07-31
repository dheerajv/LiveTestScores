package com.launchdarkly.assignment.response.types;

import com.launchdarkly.assignment.datastore.TestScoreEventData;

import java.util.ArrayList;
import java.util.List;

public class StudentTestResults {
  private final String studentId;
  private final List<ExamScore> examScores;
  private double sum = 0.0;

  public StudentTestResults(String studentId, List<ExamScore> examScores) {
    this.studentId = studentId;
    this.examScores = examScores;
  }

  public StudentTestResults(String studentId, TestScoreEventData testScoreEventData) {
    this.studentId = studentId;
    examScores = new ArrayList<>();
    examScores.add(new ExamScore(testScoreEventData.getExam(), testScoreEventData.getScore()));
  }

  public void add(TestScoreEventData testScoreEventData) {
    examScores.add(new ExamScore(testScoreEventData.getExam(), testScoreEventData.getScore()));
    sum += testScoreEventData.getScore();
  }

  public String getStudentId() {
    return studentId;
  }

  public List<ExamScore> getExamScores() {
    return examScores;
  }

  public double getAvgScore() {
    return (null != examScores && examScores.size() > 0)
        ? sum / examScores.size()
        : 0.0;
  }

  public static class ExamScore {
    private final int exam;
    private final double score;

    public ExamScore(int exam, double score) {
      this.exam = exam;
      this.score = score;
    }

    public int getExam() {
      return exam;
    }

    public double getScore() {
      return score;
    }
  }
}
