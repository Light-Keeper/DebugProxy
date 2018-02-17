package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.Filter;

public class PassthroughFilter implements Filter {
    private DataConsumer consumer;
    @Override
    public void setConsumer(DataConsumer consumer) {
        this.consumer = consumer;
    }

    @Override
    public DataConsumer getConsumer() {
        return data -> consumer.send(data);
    }
}
