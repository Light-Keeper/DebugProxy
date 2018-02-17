package com.vadikk.datapipe.base;

import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.DataFrame;

import java.util.ArrayList;
import java.util.List;

public class CollectConsumer implements DataConsumer {
    private List<DataFrame> frames = new ArrayList<>();

    @Override
    public void send(DataFrame data) {
        frames.add(data);
    }

    public List<DataFrame> getFrames() {
        return frames;
    }

    public void setFrames(List<DataFrame> frames) {
        this.frames = frames;
    }
}
