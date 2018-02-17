package com.vadikk.datapipe.impl;

import com.vadikk.datapipe.AroundFilter;
import com.vadikk.datapipe.DuplexFilter;
import com.vadikk.datapipe.Filter;
import com.vadikk.datapipe.base.PassthroughFilter;

public class BackwardFilterContainer implements AroundFilter {
    private Filter filter;

    public BackwardFilterContainer(Filter filter) {
        this.filter = filter;
    }

    @Override
    public DuplexFilter getForwardFilter() {
        return new PassthroughDuplexFilter();
    }

    @Override
    public DuplexFilter getBackwardFilter() {
        return new DuplexFilter() {
            @Override
            public Filter getForwardFilter() {
                return new PassthroughFilter();
            }

            @Override
            public Filter getBackwardFilter() {
                return filter;
            }
        };
    }
}
