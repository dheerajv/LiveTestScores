package com.launchdarkly.assignment.datastore;
public final class TestScoreEventData {
  private final int exam;
  private final String studentId;
  private final double score;

  public TestScoreEventData(int exam, String studentId, double score) {
    this.exam = exam;
    this.studentId = studentId;
    this.score = score;
  }

  public int getExam() {
    return exam;
  }

  public String getStudentId() {
    return studentId;
  }

  public double getScore() {
    return score;
  }

}
