package com.vadikk.datapipe;

import com.vadikk.datapipe.base.*;
import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.*;

public class PipelineBuilderTest {

    @Test
    public void testEmpty(){
        PipelineBuilder b = new PipelineBuilder();
        DuplexFilter pipeline = b.build();
        go(pipeline);
    }

    @Test
    public void testMore(){
        PipelineBuilder b = new PipelineBuilder();
        for(int i = 0; i < 20; i++){
            b.addBackwardFilter(new PassthroughFilter());
        }
        DuplexFilter pipeline = b.build();
        go(pipeline);
    }

    void go(DuplexFilter pipeline){
        DataSupplier dataSupplier = new DataSupplier(true);
        dataSupplier.setConsumer(pipeline.getForwardFilter().getConsumer());

        DataSupplier dataSupplier2 = new DataSupplier(true);
        dataSupplier2.setConsumer(pipeline.getBackwardFilter().getConsumer());

        CollectConsumer collectConsumer = new CollectConsumer();

        pipeline.getForwardFilter().setConsumer(
                new SplitConsumer(Arrays.asList(
                        new ConsoleLogConsumer(),
                        collectConsumer)
                ));

        pipeline.getBackwardFilter().setConsumer(
                new SplitConsumer(Arrays.asList(
                        new ConsoleLogConsumer(),
                        collectConsumer)
                ));

        dataSupplier.add("hello_forward");
        dataSupplier2.add("hello_back");

        assertEquals(2, collectConsumer.getFrames().size());
        assertArrayEquals("hello_forward".getBytes(), collectConsumer.getFrames().get(0).getData());
        assertArrayEquals("hello_back".getBytes(), collectConsumer.getFrames().get(1).getData());
    }
}