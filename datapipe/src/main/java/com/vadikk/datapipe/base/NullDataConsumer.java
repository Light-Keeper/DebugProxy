package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.DataFrame;

public class NullDataConsumer implements DataConsumer {
    @Override
    public void send(DataFrame data) {
    }
}
