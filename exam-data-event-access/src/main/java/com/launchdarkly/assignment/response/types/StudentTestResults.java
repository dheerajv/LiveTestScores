package com.launchdarkly.assignment.response.types;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.launchdarkly.assignment.datastore.TestScoreEventData;

import java.util.ArrayList;
import java.util.List;

public final class StudentTestResults {
  private final String studentId;
  private final List<ExamScore> examScores;
  private double sum = 0.0;

  public StudentTestResults(String studentId, TestScoreEventData testScoreEventData) {
    this.studentId = studentId;
    examScores = new ArrayList<>();
    examScores.add(new ExamScore(testScoreEventData.getExam(), testScoreEventData.getScore()));
  }

  public void add(TestScoreEventData testScoreEventData) {
    examScores.add(new ExamScore(testScoreEventData.getExam(), testScoreEventData.getScore()));
    sum += testScoreEventData.getScore();
  }

  @JsonGetter("studentId")
  public String getStudentId() {
    return studentId;
  }

  @JsonGetter("scores")
  public List<ExamScore> getExamScores() {
    return examScores;
  }

  @JsonGetter("averageScore")
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

    @JsonGetter("exam")
    public int getExam() {
      return exam;
    }

    @JsonGetter("score")
    public double getScore() {
      return score;
    }
  }
}
