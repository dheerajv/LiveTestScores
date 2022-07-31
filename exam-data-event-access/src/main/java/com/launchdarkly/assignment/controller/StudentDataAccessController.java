package com.launchdarkly.assignment.controller;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonElement;
import com.google.gson.JsonParser;
import com.launchdarkly.assignment.common.Constants;
import com.launchdarkly.assignment.datastore.Score;
import com.launchdarkly.assignment.datastore.ScoreCollection;
import org.json.JSONArray;
import org.json.JSONObject;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class StudentDataAccessController {

  private final ScoreCollection scoreCollection = ScoreCollection.getInstance();

  @RequestMapping
  public String welcome(){
    return "Welcome to LaunchDarkly assignment page!";
  }

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

  @GetMapping("/students")
  public String studentResults(@RequestParam(value = "studentID", defaultValue = "") String studentID){

    if(null == studentID || studentID.isEmpty())
      return students();

    List<Score> studentResults = scoreCollection.getStudentResults(studentID);
    JSONObject resultsJO = new JSONObject();
    if(null != studentResults){
      JSONArray resultJA = new JSONArray();

      for(Score score: studentResults)
        resultJA.put(score.toJsonObject());

      resultsJO.put(Constants.RESULTS, resultJA);
      resultsJO.put(Constants.AVG_SCORE, scoreCollection.getStudentAvgScore(studentID));
    }
    Gson gson = new GsonBuilder().setPrettyPrinting().create();
    JsonElement je = JsonParser.parseString(resultsJO.toString());
    return gson.toJson(je);
  }
}
