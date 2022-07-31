package com.launchdarkly.assignment.datastore;

import com.launchdarkly.assignment.common.Constants;
import org.json.JSONObject;
import org.springframework.http.codec.ServerSentEvent;

import java.util.Queue;

public class DataProcessingTask implements Runnable{

  private final Queue<ServerSentEvent<String>> dataQueue;
  private final ScoreCollection scoreCollection = ScoreCollection.getInstance();
  private final DataFetcher dataFetcher;

  public DataProcessingTask(DataFetcher dataFetcher,
                            Queue<ServerSentEvent<String>> dataQueue){
    this.dataQueue = dataQueue;
    this.dataFetcher = dataFetcher;
  }

  @Override
  public void run() {
    while (true){
      if(!dataQueue.isEmpty())
        createScore(dataQueue.poll());
    }
  }

  private void createScore(ServerSentEvent<String> sse){
    try {
      if(null == sse || null == sse.event() || null == sse.data())
        return;

      JSONObject data = new JSONObject(sse.data()) ;
      int exam = data.getInt(Constants.EXAM);
      String studentId = data.getString(Constants.STUDENT_ID);
      double score = data.getDouble(Constants.SCORE);

      if(!scoreCollection.add(new Score(exam, studentId, score))){
        dataFetcher.unsubscribe();
        System.out.println("All Students = " + scoreCollection.getAllStudents());
        System.out.println("All Exams = " + scoreCollection.getAllExams());
      }

    } catch (Exception e) {
      e.printStackTrace(); //ignore this data - may be there is some problem in the event data
    }
  }
}
