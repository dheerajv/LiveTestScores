package com.launchdarkly.assignment;

import com.launchdarkly.assignment.datastore.DataFetcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamDataEventAccessApplication {

  public static void main(String[] args)  {
    SpringApplication.run(ExamDataEventAccessApplication.class, args);
    initialize();
  }

  private static void initialize() {
    try {
      DataFetcher df = new DataFetcher();
      df.subscribe();

    } catch (InterruptedException e) {
      Thread.currentThread().interrupt();
      e.printStackTrace();
    }
  }
}
