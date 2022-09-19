package com.haining820.futureapi;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

public class _07_complete {

    public void completeTest() throws ExecutionException, InterruptedException {
        CompletableFuture<String> future = new CompletableFuture<>();
        try {
            future.complete("test success");
        } catch (Exception e) {
            future.completeExceptionally(e);
        }
        System.out.println(future.get());
    }
}
