package com.launchdarkly.assignment.controller;

import com.launchdarkly.assignment.error.handler.RestResponseException;
import com.launchdarkly.assignment.operations.TestScoreOperations;
import com.launchdarkly.assignment.response.types.ExamResults;
import com.launchdarkly.assignment.response.types.StudentTestResults;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public final class StudentDataAccessController {
  TestScoreOperations testScoreOperations = new TestScoreOperations();
  @RequestMapping
  public String welcome(){
    return "Welcome to LaunchDarkly assignment page!";
  }

  @RequestMapping("/students")
  public ResponseEntity<?> students(){
    return new ResponseEntity<>(testScoreOperations.getStudents(),HttpStatus.OK) ;
  }

  @RequestMapping("/exams")
  public ResponseEntity<?> exams(){
    return new ResponseEntity<>(testScoreOperations.getExams(), HttpStatus.OK) ;
  }

  @GetMapping(path="/students/{studentId}")
  public ResponseEntity<?> studentResults(@PathVariable String studentId) throws RestResponseException {
    StudentTestResults results = testScoreOperations.getStudentResults(studentId);
    if(null != results)
      return new ResponseEntity<>(results, HttpStatus.OK);
    else
      throw new RestResponseException(1,"Student ID " + studentId + " not found.");
  }

  @GetMapping(path="/exams/{examNumber}")
  public ResponseEntity<?> examResults(@PathVariable int examNumber) throws RestResponseException {
    ExamResults results = testScoreOperations.getExamResults(examNumber);
    if(null != results)
      return new ResponseEntity<>(results, HttpStatus.OK);
    else
      throw new RestResponseException(2,"Exam number " + examNumber + " not found.");
  }
}
