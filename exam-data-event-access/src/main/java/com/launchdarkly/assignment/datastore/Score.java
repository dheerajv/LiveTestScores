package com.launchdarkly.assignment.datastore;

import com.fasterxml.jackson.databind.ObjectMapper;

public final class Score {
  private final int exam;
  private final String studentId;
  private final double score;
  ObjectMapper mapper = new ObjectMapper();

  public Score(int exam, String studentId, double score) {
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
