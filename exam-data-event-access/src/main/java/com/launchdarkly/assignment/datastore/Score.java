package com.launchdarkly.assignment.datastore;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.launchdarkly.assignment.common.Constants;
import org.json.JSONObject;

public final class Score {
  private final int exam;
  private final String studentId;
  private final double score;
  ObjectMapper mapper = new ObjectMapper();

  public Score(int exam, String studentId, double score) {
    this.exam = exam;
    this.studentId = studentId;
    this.score = score;
  }

  public int getExam() {
    return exam;
  }

  public String getstudentId() {
    return studentId;
  }

  public double getScore() {
    return score;
  }

  public String toJsonString() throws JsonProcessingException {
    if(null == studentId)
      return "{}";

    return mapper.writeValueAsString(this);
  }

  public JSONObject toJsonObject(){
    JSONObject jo = new JSONObject();
    if(null == this.studentId)
      return  jo;

    jo.put(Constants.EXAM, this.exam);
    jo.put(Constants.STUDENT_ID, this.studentId);
    jo.put(Constants.SCORE, this.score);
    return jo;
  }
}
