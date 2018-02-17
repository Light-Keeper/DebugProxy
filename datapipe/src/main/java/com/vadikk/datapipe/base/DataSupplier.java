package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.DataFrame;

import java.util.LinkedList;
import java.util.Queue;

public class DataSupplier {
    private DataConsumer consumer;
    private boolean immediateExecure = false;
    private Queue<DataFrame> frameList = new LinkedList<>();

    public DataSupplier(boolean immediateExecure) {
        this.immediateExecure = immediateExecure;
    }

    public void setConsumer(DataConsumer consumer) {
        this.consumer = consumer;
        if (immediateExecure && consumer != null) run();
    }

    public void add(DataFrame dataFrame) {
        frameList.add(dataFrame);
        if (immediateExecure && consumer != null) run();
    }

    public void add(byte[] data) {
        add(new DataFrame(data));
    }

    public void add(String data) {
        add(new DataFrame(data.getBytes()));
    }

    public void run(){
        while (!frameList.isEmpty()){
            DataFrame next = frameList.poll();
            consumer.send(next);
        }
    }
}
