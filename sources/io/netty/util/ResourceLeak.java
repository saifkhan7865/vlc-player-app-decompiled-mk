package io.netty.util;

@Deprecated
public interface ResourceLeak {
    boolean close();

    void record();

    void record(Object obj);
}
