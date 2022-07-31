package com.launchdarkly.assignment.controller;

import com.launchdarkly.assignment.datastore.ScoreCollection;
import com.launchdarkly.assignment.error.handler.RestResponseError;
import com.launchdarkly.assignment.response.ExamResults;
import com.launchdarkly.assignment.response.Exams;
import com.launchdarkly.assignment.response.StudentTestResults;
import com.launchdarkly.assignment.response.Students;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
  public ResponseEntity<?> studentResults(@PathVariable String studentId) throws RestResponseError {
    StudentTestResults results = scoreCollection.getStudentResults(studentId);
    if(null != results)
      return new ResponseEntity<>(results, HttpStatus.OK);
    else
      throw new RestResponseError(1,"Student ID " + studentId + " not found.");
  }

  @GetMapping(path="/exams/{examNumber}")
  public ResponseEntity<?> examResults(@PathVariable int examNumber) throws RestResponseError {
    ExamResults results = scoreCollection.getExamResults(examNumber);
    if(null != results)
      return new ResponseEntity<>(results, HttpStatus.OK);
    else
      throw new RestResponseError(2,"Exam number " + examNumber + " not found.");
  }
}
