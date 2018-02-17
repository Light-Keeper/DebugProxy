package com.vadikk.datapipe;

public interface Filter {
    void setConsumer(DataConsumer consumer);
    DataConsumer getConsumer();
}
