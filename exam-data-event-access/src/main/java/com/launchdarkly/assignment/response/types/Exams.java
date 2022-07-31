package com.launchdarkly.assignment.response.types;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Set;

public final class Exams {
  private final Set<Integer> exams;

  public Exams(Set<Integer> exams) {
    this.exams = exams;
  }

  @JsonGetter("exams")
  public Set<Integer> getExams() {
    return exams;
  }
}
