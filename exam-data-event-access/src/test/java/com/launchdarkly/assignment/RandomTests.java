package com.launchdarkly.assignment;

import com.launchdarkly.assignment.datastore.DataFetcher;
import org.junit.jupiter.api.Test;

public class RandomTests {
  @Test
  public void testDataFetching() throws InterruptedException {
    DataFetcher df = new DataFetcher();
    df.subscribeAndFetch();

    Thread.sleep(20);


  }
}
