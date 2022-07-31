package com.launchdarkly.assignment.response.types;

import com.fasterxml.jackson.annotation.JsonGetter;
import com.launchdarkly.assignment.datastore.TestScoreEventData;

import java.util.ArrayList;
import java.util.List;

public final class ExamResults {
  private final int exam;
  private final List<StudentScore> studentScores;
  private double sum = 0.0;

  public ExamResults(int exam, TestScoreEventData testScoreEventData) {
    this.exam = exam;
    this.studentScores = new ArrayList<>();
    studentScores.add(new StudentScore(testScoreEventData.getStudentId(), testScoreEventData.getScore()));
  }

  public void add(TestScoreEventData testScoreEventData) {
    studentScores.add(new StudentScore(testScoreEventData.getStudentId(), testScoreEventData.getScore()));
    sum += testScoreEventData.getScore();
  }

  @JsonGetter("exam")
  public int getExam() {
    return exam;
  }

  @JsonGetter("scores")
  public List<StudentScore> getStudentScores() {
    return studentScores;
  }

  @JsonGetter("averageScore")
  public double getAvgScore() {
    return (null != studentScores && studentScores.size() > 0)
        ? sum / studentScores.size()
        : 0.0;
  }

  private static class StudentScore {
    private final String studentId;
    private final double score;

    public StudentScore(String studentId, double score) {
      this.studentId = studentId;
      this.score = score;
    }

    @JsonGetter("studentId")
    public String getStudentId() {
      return studentId;
    }

    @JsonGetter("score")
    public double getScore() {
      return score;
    }
  }
}
