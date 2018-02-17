package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.DataFrame;

public class ConsoleLogConsumer implements DataConsumer {
    @Override
    public void send(DataFrame data) {
        System.out.println(new String(data.getData()));
    }
}
