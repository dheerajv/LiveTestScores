package com.launchdarkly.assignment.controller;

import com.launchdarkly.assignment.datastore.ScoreCollection;
import com.launchdarkly.assignment.response.ExamResults;
import com.launchdarkly.assignment.response.Exams;
import com.launchdarkly.assignment.response.StudentTestResults;
import com.launchdarkly.assignment.response.Students;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Set;

@RestController
public class StudentDataAccessController {

  private final ScoreCollection scoreCollection = ScoreCollection.getInstance();

  @RequestMapping
  public String welcome(){
    return "Welcome to LaunchDarkly assignment page!";
  }

  @RequestMapping("/students")
  public Set<String> students(){
    return new Students(scoreCollection.getAllStudents()).getStudents();
  }

  @RequestMapping("/exams")
  public Set<Integer> exams(){
    return new Exams(scoreCollection.getAllExams()).getExams();

  }

  @GetMapping(path="/students/{studentId}")
  public StudentTestResults studentResults(@PathVariable String studentId) {
    return scoreCollection.getStudentResults(studentId);
  }

  @GetMapping(path="/exams/{examNumber}")
  public ExamResults examResults(@PathVariable int examNumber) {
    return scoreCollection.getExamResults(examNumber);
  }
}
