package com.launchdarkly.assignment.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.launchdarkly.assignment.datastore.ScoreCollection;
import org.json.JSONArray;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StudentDataAccessController {

  private final ScoreCollection scoreCollection = ScoreCollection.getInstance();

  @RequestMapping
  public String welcome(){
    return "Welcome to LaunchDarkly assignment page!";
  }

  @RequestMapping("/students")
  public String students(){
    JSONArray ja = new JSONArray();
    for(String student: scoreCollection.getAllStudents())
      ja.put(student);

    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement je = JsonParser.parseString(ja.toString());
    return gson.toJson(je);
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
}
