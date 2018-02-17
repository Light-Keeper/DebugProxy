package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.Filter;

import java.util.concurrent.Executor;

public class ThreadSwitchFilter implements Filter {
    private DataConsumer consumer;
    private Executor executor;

    public ThreadSwitchFilter(Executor executor) {
        this.executor = executor;
    }

    @Override
    public void setConsumer(DataConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public DataConsumer getConsumer() {
        return (data) -> {
            executor.execute(() -> consumer.send(data));
        };
    }
}
