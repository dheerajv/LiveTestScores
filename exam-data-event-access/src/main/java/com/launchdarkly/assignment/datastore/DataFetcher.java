package com.launchdarkly.assignment.datastore;

import com.launchdarkly.assignment.common.Constants;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.core.ParameterizedTypeReference;
import org.springframework.http.MediaType;
import org.springframework.http.codec.ServerSentEvent;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.Disposable;
import reactor.core.publisher.Flux;

import java.util.LinkedList;
import java.util.Queue;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public final class DataFetcher {

  private final Flux<ServerSentEvent<String>> dataStream;
  private Disposable subscription = null;
  int processorNum = Runtime.getRuntime().availableProcessors();
  ExecutorService executor = Executors.newFixedThreadPool(processorNum +1);

  Queue<ServerSentEvent<String>> queue = new LinkedList<>();

  public DataFetcher(){
    ParameterizedTypeReference<ServerSentEvent<String>> typeRef = new ParameterizedTypeReference<>(){};
    dataStream = WebClient
        .create(Constants.DATA_URI).get()
        .accept(MediaType.TEXT_EVENT_STREAM).retrieve().bodyToFlux(typeRef);
  }

  public void subscribeAndFetch() throws InterruptedException {
    subscription = dataStream.subscribe(sse -> queue.add(sse));
    executor.execute(new DataProcessingTask(this, queue));
    executor.shutdown();
    while (!executor.awaitTermination(1, TimeUnit.HOURS)) {
      System.out.println("Not yet. Still waiting for termination");
    }
  }

  public void unsubscribe(){
    subscription.dispose();
    //TODO?
  }

}
