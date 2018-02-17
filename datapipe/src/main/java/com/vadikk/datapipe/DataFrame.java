package com.vadikk.datapipe;

public class DataFrame {
    private byte[] data;

    public DataFrame(byte[] data) {
        this.data = data;
    }

    public byte[] getData() {
        return data;
    }
}
