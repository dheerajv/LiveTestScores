package com.launchdarkly.assignment.datastore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchdarkly.assignment.common.Constants;
import org.json.JSONObject;

public final class Score {
  private final int exam;
  private final String studentID;
  private final double score;
  ObjectMapper mapper = new ObjectMapper();

  public Score(int exam, String studentID, double score) {
    this.exam = exam;
    this.studentID = studentID;
    this.score = score;
  }

  public int getExam() {
    return exam;
  }

  public String getStudentID() {
    return studentID;
  }

  public double getScore() {
    return score;
  }

  public String toJsonString() throws JsonProcessingException {
    if(null == studentID)
      return "{}";

    return mapper.writeValueAsString(this);
  }

  public JSONObject toJsonObject(){
    JSONObject jo = new JSONObject();
    if(null == this.studentID)
      return  jo;

    jo.put(Constants.EXAM, this.exam);
    jo.put(Constants.STUDENT_ID, this.studentID);
    jo.put(Constants.SCORE, this.score);
    return jo;
  }
}
