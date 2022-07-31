package com.launchdarkly.assignment.datastore;

import com.launchdarkly.assignment.common.Constants;
import org.json.JSONObject;
import org.springframework.http.codec.ServerSentEvent;

import java.util.Queue;

public final class DataProcessingTask implements Runnable {
  private final Queue<ServerSentEvent<String>> dataQueue;
  private final TestScoreEventDataCollection scoreCollection = TestScoreEventDataCollection.getInstance();
  private final DataFetcher dataFetcher;

  public DataProcessingTask(DataFetcher dataFetcher,
                            Queue<ServerSentEvent<String>> dataQueue) {
    this.dataQueue = dataQueue;
    this.dataFetcher = dataFetcher;
  }

  @Override
  public void run() {
    while (true) {
      try {
        Thread.sleep(10); //Give time to fill the queue
      } catch (InterruptedException e) {
        Thread.currentThread().interrupt();
        e.printStackTrace();
      }

      if (!dataQueue.isEmpty())
        createScore(dataQueue.poll());
    }
  }

  private void createScore(ServerSentEvent<String> sse) {
    try {
      if (null == sse || null == sse.event() || null == sse.data())
        return;

      JSONObject data = new JSONObject(sse.data());
      int exam = data.getInt(Constants.EXAM);
      String studentId = data.getString(Constants.STUDENT_ID);
      double score = data.getDouble(Constants.SCORE);

      if (!scoreCollection.add(new TestScoreEventData(exam, studentId, score))) {
        dataFetcher.unsubscribe();
      }

    } catch (Exception e) {
      //Swallow this exception because it could be just some bad event data
      //For now just print the error on the console and,
      //If we have a logger in place we can log it in the error log file.
      e.printStackTrace();
    }
  }
}
