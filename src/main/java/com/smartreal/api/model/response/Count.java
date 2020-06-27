package com.smartreal.api.model.response;

public class Count {
    private long count;

    protected Count(long count) {
        this.count = count;
    }

    public long getCount() {
        return count;
    }

    public void setCount(long count) {
        this.count = count;
    }

    public static Count of(long count) {
        return new Count(count);
    }
}
