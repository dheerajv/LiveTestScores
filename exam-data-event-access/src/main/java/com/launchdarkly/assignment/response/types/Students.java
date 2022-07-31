package com.launchdarkly.assignment.response.types;

import com.fasterxml.jackson.annotation.JsonGetter;

import java.util.Set;

public final class Students {
  private final Set<String> students;

  public Students(Set<String> students){
    this.students = students;
  }

  @JsonGetter("students")
  public Set<String> getStudents() {
    return students;
  }
}
