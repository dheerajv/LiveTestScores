package com.launchdarkly.assignment.response;

import com.launchdarkly.assignment.datastore.Score;

import java.util.ArrayList;
import java.util.List;

public class ExamResults {

  private final int exam;
  private final List<StudentScore> studentScores;
  private double sum = 0.0;

  public ExamResults(int exam, List<StudentScore> studentScores) {
    this.exam = exam;
    this.studentScores = studentScores;
  }

  public ExamResults(int exam, Score score) {
    this.exam = exam;
    this.studentScores = new ArrayList<>();
    studentScores.add(new StudentScore(score.getStudentId(), score.getScore()));
  }

  public void add(Score score){
    studentScores.add(new StudentScore(score.getStudentId(), score.getScore()));
    sum+= score.getScore();
  }

  public int getExam() {
    return exam;
  }

  public List<StudentScore> getStudentScores() {
    return studentScores;
  }

  public double getAvgScore() {
    return sum/studentScores.size();
  }

  private static class StudentScore {
    private final String studentId;
    private final double score;

    public StudentScore(String studentId, double score) {
      this.studentId = studentId;
      this.score = score;
    }

    public String getStudentId() {
      return studentId;
    }
    public double getScore() {
      return score;
    }
  }
}
