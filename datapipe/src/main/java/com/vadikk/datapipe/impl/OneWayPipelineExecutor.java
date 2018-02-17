package com.vadikk.datapipe.impl;

import com.vadikk.datapipe.AroundFilter;
import com.vadikk.datapipe.DataConsumer;
import com.vadikk.datapipe.DuplexFilter;
import com.vadikk.datapipe.Filter;
import com.vadikk.datapipe.base.PassthroughFilter;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.function.Function;

class OneWayPipelineExecutor implements Filter {
    private List<Filter> filters = new ArrayList<>();

    OneWayPipelineExecutor(List<AroundFilter> aroundFilters, Function<DuplexFilter, Filter> policy) {
        List<Filter> filtersBack = new ArrayList<>();

        for (AroundFilter filter : aroundFilters) {
            filters.add(policy.apply(filter.getForwardFilter()));
            filtersBack.add(policy.apply(filter.getBackwardFilter()));
        }

        Collections.reverse(filtersBack);
        filters.addAll(filtersBack);

        for (int i = 1; i < filters.size(); i++) {
            Filter current = filters.get(i);
            Filter prev = filters.get(i - 1);
            prev.setConsumer(current.getConsumer());
        }
    }

    @Override
    public void setConsumer(DataConsumer consumer) {
        filters.get(filters.size() - 1).setConsumer(consumer);
    }

    @Override
    public DataConsumer getConsumer() {
        return filters.get(0).getConsumer();
    }
}
