package com.launchdarkly.assignment.response;

import java.util.Set;

public class Exams {
  private final Set<Integer> exams;

  public Exams(Set<Integer> exams) {
    this.exams = exams;
  }

  public Set<Integer> getExams() {
    return exams;
  }
}
