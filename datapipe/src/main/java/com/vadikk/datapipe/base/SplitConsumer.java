package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.DataFrame;

import java.util.List;

public class SplitConsumer implements DataConsumer {
    private List<DataConsumer> consumerList;

    public SplitConsumer(List<DataConsumer> consumerList) {
        this.consumerList = consumerList;
    }

    @Override
    public void send(DataFrame data) {
        for (DataConsumer dataConsumer : consumerList) {
            dataConsumer.send(data);
        }
    }
}
