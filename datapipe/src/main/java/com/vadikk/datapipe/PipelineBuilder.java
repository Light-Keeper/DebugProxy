package com.vadikk.datapipe;

import com.vadikk.datapipe.impl.ForwardFilterContainer;
import com.vadikk.datapipe.impl.PipelineExecutor;

import java.util.ArrayList;
import java.util.List;

public class PipelineBuilder {
    private List<AroundFilter> filters = new ArrayList<>();

    public PipelineBuilder add(AroundFilter aroundFilter) {
        filters.add(aroundFilter);
        return this;
    }

    public PipelineBuilder addForwardFilter(Filter filter) {
        ForwardFilterContainer f = new ForwardFilterContainer(filter);
        filters.add(f);
        return this;
    }

    public PipelineBuilder addBackwardFilter(Filter filter) {
        ForwardFilterContainer f = new ForwardFilterContainer(filter);
        filters.add(f);
        return this;
    }

    public DuplexFilter build() {
        return new PipelineExecutor(filters);
    }
}
