package com.vadikk.datapipe.impl;

import com.vadikk.datapipe.AroundFilter;
import com.vadikk.datapipe.DuplexFilter;
import com.vadikk.datapipe.Filter;
import com.vadikk.datapipe.base.PassthroughFilter;

import java.util.Collections;
import java.util.List;

public class PipelineExecutor implements DuplexFilter {
    private List<AroundFilter> filters;
    private Filter forward;
    private Filter backward;

    public PipelineExecutor(List<AroundFilter> filters) {
        if (filters.size() == 0) {
            filters = Collections.singletonList(new ForwardFilterContainer(new PassthroughFilter()));
        }
        this.filters = filters;

        forward = new OneWayPipelineExecutor(filters, DuplexFilter::getForwardFilter);
        backward = new OneWayPipelineExecutor(filters, DuplexFilter::getBackwardFilter);
    }

    @Override
    public Filter getForwardFilter() {
        return forward;
    }

    @Override
    public Filter getBackwardFilter() {
        return backward;
    }
}
