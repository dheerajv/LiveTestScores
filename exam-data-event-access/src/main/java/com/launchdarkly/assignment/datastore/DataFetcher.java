package com.launchdarkly.assignment.datastore;

import com.launchdarkly.assignment.common.Constants;
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

public final class DataFetcher {
  private final Flux<ServerSentEvent<String>> dataStream;
  private Disposable subscription = null;
  private final Queue<ServerSentEvent<String>> queue = new LinkedList<>();
  private final ExecutorService executor = Executors.newFixedThreadPool(1);

  public DataFetcher() {
    ParameterizedTypeReference<ServerSentEvent<String>> typeRef = new ParameterizedTypeReference<>() {
    };
    dataStream = WebClient
        .create(Constants.DATA_URI).get()
        .accept(MediaType.TEXT_EVENT_STREAM).retrieve().bodyToFlux(typeRef);
  }

  public void subscribe() throws InterruptedException {
    subscription = dataStream.subscribe(queue::add);
    executor.execute(new DataProcessingTask(this, queue));
  }

  //Not sure if this is the correct way to unsubscribe server sent events
  public void unsubscribe() {
    subscription.dispose();
    executor.shutdown();
  }
}
