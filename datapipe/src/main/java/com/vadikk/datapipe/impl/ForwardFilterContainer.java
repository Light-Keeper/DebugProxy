package com.vadikk.datapipe.impl;

import com.vadikk.datapipe.AroundFilter;
import com.vadikk.datapipe.DuplexFilter;
import com.vadikk.datapipe.Filter;
import com.vadikk.datapipe.base.PassthroughFilter;

public class ForwardFilterContainer implements AroundFilter {
    private Filter filter;

    public ForwardFilterContainer(Filter filter) {
        this.filter = filter;
    }

    @Override
    public DuplexFilter getForwardFilter() {
        return new DuplexFilter() {
            @Override
            public Filter getForwardFilter() {
                return filter;
            }

            @Override
            public Filter getBackwardFilter() {
                return new PassthroughFilter();
            }
        };
    }

    @Override
    public DuplexFilter getBackwardFilter() {
        return new PassthroughDuplexFilter();
    }
}
