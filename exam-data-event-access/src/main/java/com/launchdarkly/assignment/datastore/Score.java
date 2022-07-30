package com.launchdarkly.assignment.datastore;

public final class Score {
  private final int exam;
  private final String studentID;
  private final double score;

  public Score(int exam, String studentID, double score) {
    this.exam = exam;
    this.studentID = studentID;
    this.score = score;
  }

  public int getExam() {
    return exam;
  }

  public String getStudentID() {
    return studentID;
  }

  public double getScore() {
    return score;
  }
}
