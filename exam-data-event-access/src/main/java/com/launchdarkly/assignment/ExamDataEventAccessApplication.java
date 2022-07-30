package com.launchdarkly.assignment;

import com.launchdarkly.assignment.datastore.DataFetcher;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class ExamDataEventAccessApplication {

  public static void main(String[] args) throws InterruptedException {
    SpringApplication.run(ExamDataEventAccessApplication.class, args);

    DataFetcher df = new DataFetcher();
    df.subscribeAndFetch();
  }

}
