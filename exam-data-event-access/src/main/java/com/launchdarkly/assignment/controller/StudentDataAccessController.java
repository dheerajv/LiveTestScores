package com.launchdarkly.assignment.controller;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.launchdarkly.assignment.common.Constants;
import com.launchdarkly.assignment.datastore.Score;
import com.launchdarkly.assignment.datastore.ScoreCollection;
import com.launchdarkly.assignment.response.StudentTestResults;
import com.launchdarkly.assignment.response.Students;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
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
  public String exams(){
    JSONArray ja = new JSONArray();
    for(int exam: scoreCollection.getAllExams())
      ja.put(exam);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement je = JsonParser.parseString(ja.toString());
    return gson.toJson(je);

  }

  @GetMapping(path="/students/{studentId}")
  public StudentTestResults studentResults(@PathVariable String studentId) throws JsonProcessingException {
    return scoreCollection.getStudentResults(studentId);
  }
}
