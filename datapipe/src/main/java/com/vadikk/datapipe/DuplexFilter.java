package com.vadikk.datapipe;

public interface DuplexFilter {
    Filter getForwardFilter();
    Filter getBackwardFilter();
}
