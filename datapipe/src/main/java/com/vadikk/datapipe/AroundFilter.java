package com.vadikk.datapipe;

public interface AroundFilter {
    DuplexFilter getForwardFilter();
    DuplexFilter getBackwardFilter();
}
