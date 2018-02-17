package com.vadikk.datapipe.impl;

import com.vadikk.datapipe.DuplexFilter;
import com.vadikk.datapipe.Filter;
import com.vadikk.datapipe.base.PassthroughFilter;

public class PassthroughDuplexFilter implements DuplexFilter {
    private PassthroughFilter f1 = new PassthroughFilter();
    private PassthroughFilter f2 = new PassthroughFilter();

    @Override
    public Filter getForwardFilter() {
        return f1;
    }

    @Override
    public Filter getBackwardFilter() {
        return f2;
    }
}
