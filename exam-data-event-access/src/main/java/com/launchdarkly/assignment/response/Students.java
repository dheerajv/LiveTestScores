package com.launchdarkly.assignment.response;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Set;

public class Students {
  private final Set<String> students;

  public Students(Set<String> students){
    this.students = students;
  }

  public Set<String> getStudents() {
    return Collections.unmodifiableSet(students);
  }
}
