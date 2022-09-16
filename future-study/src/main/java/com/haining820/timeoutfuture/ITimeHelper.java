package com.haining820.timeoutfuture;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.TimeUnit;

public interface ITimeHelper {

    CompletableFuture<String> fastFail(long timeout, TimeUnit timeUnit);

}
